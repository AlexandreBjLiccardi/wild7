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

public class WildNewParameterLine {

    private String asChain ;

    public String getAsChain() {
        return asChain;
    }

    private void initialize(String name, String line, Boolean asException) {

        String[] subPar = line.split("\\(");
        String[] subsubPar = (subPar.length>1)? subPar[1].split("\\)")[0].split("\\{"):null ;
        String[] descrArray = (/*subPar.length > 1&&*/line.split(":").length>1)? line.split(":"):new String[]{"",line};

        String ident = cast_xmlEscape(subPar[0].trim());
        String type = "String";
        if(subsubPar!=null)type = cast_xmlEscape(subsubPar[0].split("\\{")[0].trim());
        else if(subPar.length>1)type = cast_xmlEscape(subPar[1].split("\\)")[0]);

        String default_p = (subsubPar!=null&&subsubPar.length>1)? cast_xmlEscape(subsubPar[1].trim().replaceAll("^\\{","").replaceAll("\\}$","")):null;
        String descr =  cast_xmlEscape(descrArray[1].trim());

        asChain = "<"+name ;
        if(ident!=null)asChain += " ident = \""+(ident)+"\"";
        if(!asException)if(type!=null)asChain += " type = \""+(type)+"\"";
        if(asException)if(type!=null)asChain += " criticity = \""+(type.toUpperCase().replace("STRING","ERROR"))+"\"";

        if(default_p!=null)asChain += " default = \""+(default_p)+"\"";
        if(asException){
            asChain += ">";
            if(descr!=null)asChain += (descr);
            asChain += "</Exception>"+System.getProperty("line.separator");
        }else{
            if(descr!=null)asChain += " description = \""+(descr)+"\"";
            asChain += " />"+System.getProperty("line.separator");
        }

    }

    public WildNewParameterLine(String name, String line) {
        initialize(name, line, false);
    }

    public WildNewParameterLine(String name, String line, Boolean asException) {
        initialize(name, line, asException);
    }

}
