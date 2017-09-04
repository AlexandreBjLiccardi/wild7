/*
 *  ONEMA Dice project.
 *  Copyright (C) 2016 ONEMA
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project. If not, see http://www.gnu.org/licenses.
 */
package wildGenerator;
import static fr.wild.common.IoCommons.*;
import static fr.wild.common.IoFileSystem.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class WildObject {

    String objectName ;
    String processedModel = "real";
    String author = "alexandre.liccardi@onema.fr";


    public WildObject(Row row, Map<String, XSSFSheet> readXLSX){

        if(row.getCell(0).getStringCellValue().equals("WildClass"))return;
        objectName = row.getCell(0).getStringCellValue() ;
        System.out.println("### Building [O] : "+objectName);
        String toPrint = getHeader(row);

        for(int j=1;j<=readXLSX.get("ObjectInvokations").getLastRowNum();j++){
            Row rowj = readXLSX.get("ObjectInvokations").getRow(j);
            if(rowj.getCell(0).getStringCellValue().equals(objectName)) toPrint += (new WildInvokation(rowj,readXLSX)).getAsChain();
        }
        for(int j=1;j<=readXLSX.get("WildMethods").getLastRowNum();j++){
            Row rowj = readXLSX.get("WildMethods").getRow(j);
            if(rowj.getCell(0).getStringCellValue().equals(objectName)) toPrint += (new WildMethod(rowj,readXLSX)).getAsChain();
        }
        toPrint += "</WildObject>";

        try {
            file_delete(processedModel+"/"+objectName+".wild");
            file_delete(processedModel+"/"+objectName+".java");
            file_write(processedModel+"/"+objectName+".wild",toPrint);
            String ixmlFilePath = processedModel+"/"+objectName+".wild" ;
            String ixsltFilePath = "/wildToCode.xslt" ;
            String itransformedFilePath = processedModel+"/"+objectName+".java" ;
            transform(ixsltFilePath, ixmlFilePath, itransformedFilePath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void transform(String ixsltFilePath, String ixmlFilePath,String itransformedFilePath) throws TransformerException{
        TransformerFactory tFactory = TransformerFactory.newInstance("net.sf.saxon.TransformerFactoryImpl", null);
        System.setProperty("javax.xml.transform.TransformerFactory","net.sf.saxon.TransformerFactoryImpl");
        Transformer transformer = tFactory.newTransformer(new StreamSource(WildObject.class.getResourceAsStream(ixsltFilePath)));
        File tOutput = new File(itransformedFilePath);
        if(tOutput.exists())tOutput.delete();
        transformer.transform(new StreamSource(ixmlFilePath), new StreamResult(tOutput));
    }

    private String getHeader(Row row) {
        String toPrint = "<WildObject ident = \""+processedModel+"."+row.getCell(0).getStringCellValue()+"\" author = \""+author+"\"" ;
        HashMap<String,Integer> toAdd = new HashMap<String,Integer>();
        toAdd.put("inherits",1);
        toAdd.put("type",2);
        toAdd.put("MinRAM",7);
        toAdd.put("MinROM",8);
        toAdd.put("MinCPU",9);
        for(String k:toAdd.keySet())if(row.getCell(toAdd.get(k))!=null){
            try{
                toPrint += (" "+ k + " = \""+cast_xmlEscape(row.getCell(toAdd.get(k)).getStringCellValue().trim().replace("Abstraite", "abstract"))+"\"");
            }catch(Exception e){
                toPrint += (" "+ k + " = \""+Math.round(row.getCell(toAdd.get(k)).getNumericCellValue())+"\"");
            }
        }
        toPrint += ">"+System.getProperty("line.separator");
        if(row.getCell(3)!=null)toPrint += cast_xmlEscape(row.getCell(3).getStringCellValue().trim())+System.getProperty("line.separator");
        if(row.getCell(4)!=null){
            String[] splitted = row.getCell(4).getStringCellValue().split("\\n");
            for(int i=0 ; i < splitted.length ; i++){
                if(splitted[i]==null||splitted[i].length()==0)continue ;
                String[] subSplitted = splitted[i].split(":");
                String descr = (subSplitted.length > 1) ? subSplitted[1]:"";
                String lib = subSplitted[0] ;
                toPrint += "<Dependency value = \""+lib+"\" description = \""+ cast_xmlEscape(descr) +"\" />"+System.getProperty("line.separator");
            }
        }
        if(row.getCell(6)!=null){
            String[] splitted = row.getCell(6).getStringCellValue().split("\\n");
            for(int i=0 ; i < splitted.length ; i++){
                if(splitted[i]==null||splitted[i].length()==0)continue ;
                toPrint += (new WildNewParameterLine("Exception",splitted[i],true)).getAsChain();
            }
        }
        if(row.getCell(5)!=null){
            String[] splitted = row.getCell(5).getStringCellValue().split("\\n");
            for(int i=0 ; i < splitted.length ; i++){
                if(splitted[i]==null||splitted[i].length()==0)continue ;
                toPrint += (new WildNewParameterLine("Field",splitted[i])).getAsChain();
            }
        }

        return toPrint ;
    }

}
