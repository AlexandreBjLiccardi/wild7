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

import static fr.wild.common.IoCommons.cast_xmlEscape;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class WildMethod {

    private String objectName ;
    private String methodName ;
    private String asChain ;

    public WildMethod(Row row, Map<String, XSSFSheet> readXLSX){
        methodName = row.getCell(1).getStringCellValue() ;
        objectName = row.getCell(0).getStringCellValue() ;
        asChain=getHeader(row);
        if(readXLSX.get("MethodInvokations")!=null)for(int j=1;j<=readXLSX.get("MethodInvokations").getLastRowNum();j++){
            Row rowj = readXLSX.get("MethodInvokations").getRow(j);
            if(rowj.getCell(0).getStringCellValue().equals(objectName)&&rowj.getCell(1).getStringCellValue().equals(methodName)) {
                String invoker = ( new WildInvokation(rowj,readXLSX)).getAsChain();
                if(invoker!=null)asChain += invoker ;
            }
        }
        asChain += getNext(row);
        asChain += "</WildMethod>"+System.getProperty("line.separator");
    }


    private String getHeader(Row row) {
        String toPrint = "<WildMethod ident = \""+row.getCell(1).getStringCellValue()+"\" author =\""+row.getCell(7).getStringCellValue()+"\" update =\""+row.getCell(8).getDateCellValue()+"\"" ;
        HashMap<String,Integer> toAdd = new HashMap<String,Integer>();

        toAdd.put("description",2);

        for(String k:toAdd.keySet())if(row.getCell(toAdd.get(k))!=null){
            try{
                toPrint += (" "+ k + " = \""+cast_xmlEscape(row.getCell(toAdd.get(k)).getStringCellValue().trim().replace("Abstraite", "abstract"))+"\"");
            }catch(Exception e){
                toPrint += (" "+ k + " = \""+Math.round(row.getCell(toAdd.get(k)).getNumericCellValue())+"\"");
            }
        }
        toPrint += ">"+System.getProperty("line.separator");
        return toPrint ;
    }

    private String getNext(Row row) {
        String toPrint = "";
        if(row.getCell(6)!=null&&row.getCell(6).getStringCellValue().length()>0){
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
                String weight = "10";
                if(splitted[i].indexOf("(+++)")!=-1)weight="100";
                else if(splitted[i].indexOf("(++)")!=-1)weight="50";
                else if(splitted[i].indexOf("(+)")!=-1)weight="20";
                else if(splitted[i].indexOf("(---)")!=-1)weight="1";
                else if(splitted[i].indexOf("(--)")!=-1)weight="2";
                else if(splitted[i].indexOf("(-)")!=-1)weight="5";
                toPrint += "<Step ident = \""+(i+1)+"\" weight = \""+weight+"\" description = \""+cast_xmlEscape(splitted[i])+"\"/>"+System.getProperty("line.separator");
            }
        }
        if(row.getCell(4)!=null&&row.getCell(4).getStringCellValue().trim().length()!=0){
            String[] splitted = row.getCell(4).getStringCellValue().split("\\n");
            for(int i=0 ; i < splitted.length ; i++){
                if(splitted[i]==null||splitted[i].length()==0)continue ;
                String subSplitted = splitted[i].split("\\(")[1].split("\\)")[0];
                toPrint += "<Output ident = \""+methodName+"_output"+(i+1)+"\" type = \""+cast_xmlEscape(subSplitted)+"\" description = \""+cast_xmlEscape(splitted[i])+"\"/>"+System.getProperty("line.separator");
            }
        }
        if(row.getCell(3)!=null&&!row.getCell(3).getStringCellValue().trim().equals("void")){
            String[] splitted = row.getCell(3).getStringCellValue().split("\\n");
            for(int i=0 ; i < splitted.length ; i++){
                if(splitted[i]==null||splitted[i].length()==0)continue ;
                String subSplitted = splitted[i].split("\\(")[1].split("\\)")[0];
                toPrint += "<Return><Parameter ident = \""+methodName+"_ret\" type = \""+cast_xmlEscape(subSplitted)+"\" description = \""+cast_xmlEscape(splitted[i])+"\"/></Return>"+System.getProperty("line.separator");
            }
        }
        return toPrint;
    }

    public String getAsChain() {
        return asChain;
    }

}
