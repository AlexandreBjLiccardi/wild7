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

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class launcher {

    public static void main(String[] args) throws Exception {
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Wild object excel", "xlsx"));

        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            final File fis = chooser.getSelectedFile();
            final XSSFWorkbook wb = new XSSFWorkbook(fis);
            final Map<String, XSSFSheet> readXLSX = new HashMap<>();

            readXLSX.put("WildMethods", wb.getSheet("WildMethods")) ;
            readXLSX.put("WildObjects", wb.getSheet("WildObjects")) ;
            readXLSX.put("ObjectInvokations", wb.getSheet("ObjectInvokations")) ;
            readXLSX.put("MethodInvokations", wb.getSheet("MethodInvokations")) ;
            for(int i=1;i<=readXLSX.get("WildObjects").getLastRowNum();i++){
                new WildObject(readXLSX.get("WildObjects").getRow(i),readXLSX);
            }
            wb.close();
        }
    }

}
