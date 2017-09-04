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

import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class WildInvokation {
    private String objectName ;
    private String methodName ;
    private String asChain ;

    public WildInvokation(Row row, Map<String, XSSFSheet> readXLSX){
        if(row.getCell(1)!=null)methodName = row.getCell(1).getStringCellValue() ;
        objectName = row.getCell(0).getStringCellValue() ;
        String contentsParam = (row.getCell(2)==null)? null:row.getCell(2).getStringCellValue() ;
        if(contentsParam==null||contentsParam.length()==0)return;
        asChain = "<Invokation>"+System.getProperty("line.separator");
        String[] splitted = row.getCell(2).getStringCellValue().split("\\n");
        for(int i=0 ; i < splitted.length ; i++){
            if(splitted[i]==null||splitted[i].length()==0)continue ;
            asChain += (new WildNewParameterLine("Parameter",splitted[i])).getAsChain();
        }
        asChain += "</Invokation>"+System.getProperty("line.separator");
    }

    public String getAsChain() {
        return asChain ;
    }
}