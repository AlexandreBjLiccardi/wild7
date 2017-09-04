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
package fr.wild.orchestra;

import static fr.wild.common.IoCommons.*;
import static fr.wild.common.IoFileSystem.*;
import static fr.wild.common.IoWilds.*;
import static fr.wild.common.Connector.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.wild.hibernate.ReferenceConcept;
import fr.wild.hibernate.UserFile;


public class WildSourceType {

    private Node parseNode ;
    private String describedType ;
    private Object computeValue ;
    private HashMap<String, String> eqJavaTypes ;
    private Boolean onError = false ;
    private List<String> errorList = new ArrayList<String>();
    private ArrayList<Map<String, String>> fileSourcesErrors ;

    private String rawValue ;
    private String useAs ;
    private WildModelList wildModelList ;
    public String getJavaType(){return eqJavaTypes.get(describedType);}
    public Object getValue(){return computeValue;}
    public String getUseAs(){return useAs;}
    public Boolean isOnError(){return onError ;}
    public WildScenario wildScenario ;


    private void setEqJavaTypes(){
        eqJavaTypes = new HashMap<String, String>();
        eqJavaTypes.put("user.file","String");
        eqJavaTypes.put("posted.file","String");
        eqJavaTypes.put("other.user.file","String");
        eqJavaTypes.put("metadata.file","String");
        eqJavaTypes.put("reference.file","String");
        eqJavaTypes.put("published.file","String");
        eqJavaTypes.put("public.file","String");
        eqJavaTypes.put("search.user.file","List<String>");
        eqJavaTypes.put("search.posted.file","List<String>");
        eqJavaTypes.put("url.file","String");
        eqJavaTypes.put("string","String");
        eqJavaTypes.put("integer","Integer");
        eqJavaTypes.put("float","Float");
        eqJavaTypes.put("boolean","Boolean");
        eqJavaTypes.put("list.string","List<String>");
        eqJavaTypes.put("list.integer","List<Integer>");
        eqJavaTypes.put("list.float","List<Float>");
        eqJavaTypes.put("list.boolean","List<Boolean>");
        eqJavaTypes.put("map.boundaries","String[]");
        eqJavaTypes.put("reference.id","String");
        eqJavaTypes.put("reference.id.objectid","String[]");
        eqJavaTypes.put("list.reference.id.objectid","List<String[]>");
        eqJavaTypes.put("map","HashMap<String,Object>");
    }


    public WildSourceType(Node i_parseNode, WildScenario i_wildScenario) throws Exception{
        wildScenario = i_wildScenario ;
        wildModelList = wildScenario.getDiceModelList() ;
        setEqJavaTypes();
        parseNode = i_parseNode ;
        rawValue = xml_getFirstValue(parseNode, "./@value");
        if(rawValue==null) rawValue = xml_getText(parseNode);
        useAs = xml_getFirstValue(parseNode, "./@useAs");
        if(useAs == null ){
            this.onError = true ;
            this.errorList.add("UseAs missing");
        }
        describedType = xml_getFirstValue(parseNode, "./@type").toLowerCase();

        if(describedType == null)describedType = "String" ;
        switch(describedType){
            case "user.file": getFromType_user_file();break;
            case "posted.file": getFromType_posted_file();break;
            case "other.user.file": getFromType_other_user_file();break;
            case "metadata.file": getFromType_metadata_file();break;
            case "reference.file": getFromType_referentiel_file();break;
            case "published.file": getFromType_published_file();break;
            case "public.file": getFromType_public_file();break;
            case "search.user.file": getFromType_search_user_file();break;
            case "search.posted.file": getFromType_search_posted_file();break;
            case "url.file": getFromType_url_file();break;
            case "string": getFromType_java();break;
            case "integer": getFromType_java();break;
            case "float": getFromType_java();break;
            case "boolean": getFromType_java();break;
            case "list.string": getFromType_list_string();break;
            case "list.integer": getFromType_list_integer();break;
            case "list.float": getFromType_list_float();break;
            case "list.boolean": getFromType_list_boolean();break;
            case "map.boundaries": getFromType_map_geom();break;
            case "map.point": getFromType_map_geom();break;
            case "list.map.boundaries": getFromType_list_map_geom();break;
            case "list.map.point": getFromType_list_map_geom();break;
            case "reference.id": getFromType_referentiel_id();break;
            case "reference.id.objectid": getFromType_referentiel_id_objectid();break;
            case "list.reference.id.objectid": getFromType_list_referentiel_id_objectid();break;
            case "result.file": getFromType_result_file();break;
            case "map": getFromType_map();break;
            default : onError = true ; this.errorList.add("Unknown type : "+describedType+".");
        }
    }

    @SuppressWarnings("unchecked")
    private void getFromType_search_posted_file() {
        try {
            String searchPath = wildScenario.getPhpFolder_in();
            rawValue = this.rawValue.replaceAll("REGEX\\[([^<]*)\\]", "$1").toLowerCase();
            computeValue = new ArrayList<String>();
            List<String> ls = file_getDirContents(searchPath);
            for(String aFile:ls)
                if(aFile.toLowerCase().matches(rawValue)){
                    String filePath = searchPath+File.separator+aFile ;
                    String newPos = this.wildScenario.execTempFolder+File.separator+this.rawValue;
                    file_copy(filePath,    (String) newPos);
                    ((List<String>) computeValue).add(newPos);
                }
        } catch (Exception e) {
            this.onError = true;
            this.errorList.add("Cannot cast \"" + rawValue + "\" to "+ getJavaType() + " for source called : " + this.useAs);
        }
    }


    private void getFromType_search_user_file() {
        try {
            String searchPath = conf_translate((String) wildModelList.getProperty("user_path_files"),wildScenario);
            rawValue = this.rawValue.replaceAll("REGEX\\[([^<]*)\\]", "$1").toLowerCase();
            computeValue = new ArrayList<String>();
            List<String> ls = file_getDirContents(searchPath);
            for(String aFile:ls)
                if(aFile.toLowerCase().matches(rawValue)){
                    String filePath = searchPath+File.separator+aFile ;
                    String newPos = this.wildScenario.execTempFolder+File.separator+this.rawValue;
                    file_copy(filePath,    (String) newPos);
                    ((List<String>) computeValue).add(newPos);
                }
        } catch (Exception e) {
            this.onError = true;
            this.errorList.add("Cannot cast \"" + rawValue + "\" to "+ getJavaType() + " for source called : " + this.useAs);
        }
    }

    private void getFromType_other_user_file() {
        try{
            // Parse de l'amorce vers : Identifiant de l'utilisateur, Identifiant du fichier
            String[] expRawValue = this.rawValue.split("#6D#");
            String userId = expRawValue[0] ;
            String fileId = expRawValue[1] ;
            Session db_sub = wildScenario.getNewConnect();
            UserFile uFile = getDbEntity(db_sub, UserFile.class, fileId);
            // Comportement en fonction du niveau de publication du fichier
            String filePath ;
            switch(uFile.getPublicationLevel().getIdPublicationLevel()){
                case (short)5:
                    filePath=conf_translate(((String) wildModelList.getProperty("user_path_files")).replaceAll("#6D#UserId#6D#",userId),wildScenario)+File.separator+uFile.getNameUserFile();
                    break;
                case (short)6:
                    filePath=conf_translate(((String) wildModelList.getProperty("public_path_files")).replaceAll("#6D#UserId#6D#",userId),wildScenario)+File.separator+uFile.getNameUserFile();
                    break;
                case (short)7:
                    filePath=conf_translate(((String) wildModelList.getProperty("references_path_files")).replaceAll("#6D#UserId#6D#",userId),wildScenario)+File.separator+uFile.getNameUserFile();
                    break;
                default :
                    throw new Exception("Access to file denied.");
            }
            computeValue = this.wildScenario.execTempFolder+File.separator+uFile.getNameUserFile();
            try{
                file_copy(filePath,    (String) computeValue);
            }catch(Exception e){
                if(fileSourcesErrors==null)fileSourcesErrors = new ArrayList<Map<String, String>>();
                Map<String, String> error = wildScenario.getLogger().solveError(e," - missing source file : "+rawValue);
                this.onError = true ;
                fileSourcesErrors.add(error);
            }
        }catch(Exception e){
            this.onError = true ;
            this.errorList.add("Error from \""+cast_2String(parseNode)+"\" to "+getJavaType()+" for source called : "+this.useAs +" : "+e.getMessage());
        }
    }

    private void getFromType_referentiel_id() {
        try {
            /*Session db_sub = wildScenario.getNewConnect();
            ReferenceConcept uRef = getDbEntity(db_sub, ReferenceConcept.class,
                    rawValue);*/
            computeValue = "<ReferenceConcept>"+rawValue+"</ReferenceConcept>" ;
        } catch (Exception e) {
            if (fileSourcesErrors == null)
                fileSourcesErrors = new ArrayList<Map<String, String>>();
            Map<String, String> error = wildScenario.getLogger().solveError(
                    e, " - missing source file : " + rawValue);
            this.onError = true;
            fileSourcesErrors.add(error);
        }

    }

    private void getFromType_referentiel_id_objectid() {
        try {
            computeValue = new String[2];
            //Session db_sub = wildScenario.getNewConnect();
            String[] expRawValue = rawValue.split("#6D#") ;
            //ReferenceConcept uRef = getDbEntity(db_sub, ReferenceConcept.class,    expRawValue[0]);
            computeValue = expRawValue;
        } catch (Exception e) {
            if (fileSourcesErrors == null)
                fileSourcesErrors = new ArrayList<Map<String, String>>();
            Map<String, String> error = wildScenario.getLogger().solveError(
                    e, " - missing source file : " + rawValue);
            this.onError = true;
            fileSourcesErrors.add(error);
        }

    }

    private void getFromType_list_referentiel_id_objectid() {
        try {
            computeValue = new ArrayList<String[]>();
            Session db_sub = wildScenario.getNewConnect();
            NodeList nl = xml_getNodes(this.parseNode,"//listElt");
            computeValue = new ArrayList<String[]>();
            String refId = xml_getFirstValue(this.parseNode, "./@value");
            if(refId==null) refId = xml_getText(this.parseNode);
            for(int i=1; i<nl.getLength() ;i++){
                String[] str = {refId, xml_getText(nl.item(i))} ;
                ((List<String[]>) computeValue).add(str);
            }
        } catch (Exception e) {
            if (fileSourcesErrors == null)
                fileSourcesErrors = new ArrayList<Map<String, String>>();
            Map<String, String> error = wildScenario.getLogger().solveError(
                    e, " - missing source file : " + rawValue);
            this.onError = true;
            fileSourcesErrors.add(error);
        }

    }

    private void getFromType_result_file() {
        String filePath = conf_translate((String) wildModelList.getProperty("user_path_files"),wildScenario)+File.separator+this.rawValue ;
        computeValue = this.wildScenario.execTempFolder+File.separator+this.rawValue;
        try{
            file_copy(filePath,    (String) computeValue);
        }catch(Exception e){}

    }


    private void getFromType_map_geom() {
        try{
            computeValue = rawValue.split("#6D#");
        }catch(Exception e){
            this.onError = true ;
            this.errorList.add("Cannot cast \""+rawValue+"\" to "+getJavaType()+" for source called : "+this.useAs);
        }
    }

    @SuppressWarnings("unchecked")
    private void getFromType_list_map_geom() {
        try{
            NodeList nl = xml_getNodes(this.parseNode,"//listElt");
            computeValue = new ArrayList<String[]>();
            for(int i=0; i<nl.getLength() ;i++){
                String eltValue = xml_getFirstValue(nl.item(i), "./@value");
                if(eltValue==null) eltValue = xml_getText(nl.item(i));
                ((List<String[]>) computeValue).add(eltValue.split("#6D#"));
            }
        }catch(Exception e){
            this.onError = true ;
            this.errorList.add("Cannot cast \""+cast_2String(parseNode)+"\" to "+getJavaType()+" for source called : "+this.useAs);
        }
    }

    @SuppressWarnings("unchecked")
    private void getFromType_list_boolean() {
        try{
            NodeList nl = xml_getNodes(this.parseNode,"//listElt");
            computeValue = new ArrayList<Boolean>();
            for(int i=0; i<nl.getLength() ;i++){
                String eltValue = xml_getFirstValue(nl.item(i), "./@value");
                if(eltValue==null) eltValue = xml_getText(nl.item(i));
                ((List<Boolean>) computeValue).add((Boolean) cast_2ObjectThrow("Boolean", eltValue));
            }
        }catch(Exception e){
            this.onError = true ;
            this.errorList.add("Cannot cast \""+cast_2String(parseNode)+"\" to "+getJavaType()+" for source called : "+this.useAs);
        }
    }

    private void getFromType_list_float() {
        try{
            NodeList nl = xml_getNodes(this.parseNode,"//listElt");
            computeValue = new ArrayList<Float>();
            for(int i=0; i<nl.getLength() ;i++){
                String eltValue = xml_getFirstValue(nl.item(i), "./@value");
                if(eltValue==null) eltValue = xml_getText(nl.item(i));
                ((List<Float>) computeValue).add((Float) cast_2ObjectThrow("Float", eltValue));
            }
        }catch(Exception e){
            this.onError = true ;
            this.errorList.add("Cannot cast \""+cast_2String(parseNode)+"\" to "+getJavaType()+" for source called : "+this.useAs);
        }
    }


    private void getFromType_list_integer() {
        try{
            NodeList nl = xml_getNodes(this.parseNode,"//listElt");
            computeValue = new ArrayList<Integer>();
            for(int i=0; i<nl.getLength() ;i++){
                String eltValue = xml_getFirstValue(nl.item(i), "./@value");
                if(eltValue==null) eltValue = xml_getText(nl.item(i));
                ((List<Integer>) computeValue).add((Integer) cast_2ObjectThrow("Integer", eltValue));
            }
        }catch(Exception e){
            this.onError = true ;
            this.errorList.add("Cannot cast \""+cast_2String(parseNode)+"\" to "+getJavaType()+" for source called : "+this.useAs);
        }
    }


    private void getFromType_list_string() {
        try{
            NodeList nl = xml_getNodes(this.parseNode,"//listElt");
            computeValue = new ArrayList<String>();
            for(int i=0; i<nl.getLength() ;i++){
                String eltValue = xml_getFirstValue(nl.item(i), "./@value");
                if(eltValue==null) eltValue = xml_getText(nl.item(i));
                ((List<String>) computeValue).add(eltValue);
            }
        }catch(Exception e){
            this.onError = true ;
            this.errorList.add("Cannot cast \""+cast_2String(parseNode)+"\" to "+getJavaType()+" for source called : "+this.useAs);
        }
    }

    private void getFromType_java() {
        try{
            computeValue = cast_2ObjectThrow(getJavaType(),rawValue);
        }catch(Exception e){
            this.onError = true ;
            this.errorList.add("Cannot cast \""+rawValue+"\" to "+getJavaType()+" for source called : "+this.useAs);
        }
    }


    private void getFromType_url_file() {
        try{
            rawValue = "URL#6D#"+rawValue;
            computeValue = cast_2Object(getJavaType(), rawValue);
        }catch(Exception e){
            this.onError = true ;
            this.errorList.add("Cannot cast \""+rawValue+"\" to "+getJavaType()+" for source called : "+this.useAs);
        }
    }

    private void getFromType_published_file() {
        String filePath = conf_translate((String) wildModelList.getProperty("published_path_files"),wildScenario)+File.separator+this.rawValue ;
        computeValue = this.wildScenario.execTempFolder+File.separator+this.rawValue;
        try{
            file_copy(filePath,    (String) computeValue);
        }catch(Exception e){
            if(fileSourcesErrors==null)fileSourcesErrors = new ArrayList<Map<String, String>>();
            Map<String, String> error = wildScenario.getLogger().solveError(e," - missing source file : "+rawValue);
            this.onError = true ;
            fileSourcesErrors.add(error);
        }
    }

    private void getFromType_public_file() {
        String filePath = conf_translate((String) wildModelList.getProperty("public_path_files"),wildScenario)+File.separator+this.rawValue ;
        computeValue = this.wildScenario.execTempFolder+File.separator+this.rawValue;
        try{
            file_copy(filePath,    (String) computeValue);
        }catch(Exception e){
            if(fileSourcesErrors==null)fileSourcesErrors = new ArrayList<Map<String, String>>();
            Map<String, String> error = wildScenario.getLogger().solveError(e," - missing source file : "+rawValue);
            this.onError = true ;
            fileSourcesErrors.add(error);
        }
    }

    private void getFromType_referentiel_file() {
        String filePath = conf_translate((String) wildModelList.getProperty("references_path_files"),wildScenario)+File.separator+this.rawValue ;
        computeValue = this.wildScenario.execTempFolder+File.separator+this.rawValue;
        try{
            file_copy(filePath,    (String) computeValue);
        }catch(Exception e){
            if(fileSourcesErrors==null)fileSourcesErrors = new ArrayList<Map<String, String>>();
            Map<String, String> error = wildScenario.getLogger().solveError(e," - missing source file : "+rawValue);
            this.onError = true ;
            fileSourcesErrors.add(error);
        }
    }

    private void getFromType_metadata_file() {
        String filePath = conf_translate((String) wildModelList.getProperty("metadatas_path_files"),wildScenario)+File.separator+this.rawValue ;
        computeValue = this.wildScenario.execTempFolder+File.separator+this.rawValue;
        try{
            file_copy(filePath,    (String) computeValue);
        }catch(Exception e){
            if(fileSourcesErrors==null)fileSourcesErrors = new ArrayList<Map<String, String>>();
            Map<String, String> error = wildScenario.getLogger().solveError(e," - missing source file : "+rawValue);
            this.onError = true ;
            fileSourcesErrors.add(error);
        }
    }

    private void getFromType_posted_file() {
        String filePath = this.wildScenario.getPhpFolder_in()+File.separator+this.rawValue ;
        computeValue = this.wildScenario.execTempFolder+File.separator+this.rawValue;
        try{
            file_copy(filePath,    (String) computeValue);
        }catch(Exception e){
            if(fileSourcesErrors==null)fileSourcesErrors = new ArrayList<Map<String, String>>();
            Map<String, String> error = wildScenario.getLogger().solveError(e," - missing source file : "+rawValue);
            this.onError = true ;
            fileSourcesErrors.add(error);
        }
    }


    private void getFromType_user_file() {
        String filePath = conf_translate((String) wildModelList.getProperty("user_path_files"),wildScenario)+File.separator+this.rawValue ;
        computeValue = this.wildScenario.execTempFolder+File.separator+this.rawValue;
        try{
            file_copy(filePath,    (String) computeValue);
        }catch(Exception e){
            if(fileSourcesErrors==null)fileSourcesErrors = new ArrayList<Map<String, String>>();
            Map<String, String> error = wildScenario.getLogger().solveError(e," - missing source file : "+rawValue);
            this.onError = true ;
            fileSourcesErrors.add(error);
        }
    }


    private void getFromType_map() {
        try {
            try{
                NodeList nl = xml_getNodes(this.parseNode,"//listElt");
                computeValue = new HashMap<String,Object>();
                for(int i=0; i<nl.getLength() ;i++){
                    WildSourceType newType = new WildSourceType(nl.item(i),this.wildScenario) ;
                    ((HashMap<String,Object>) computeValue).put(newType.getUseAs(),newType.getValue());
                }
            }catch(Exception e){
                this.onError = true ;
                this.errorList.add("Cannot cast \""+cast_2String(parseNode)+"\" to "+getJavaType()+" for source called : "+this.useAs);
            }
        } catch (Exception e) {
            if (fileSourcesErrors == null)
                fileSourcesErrors = new ArrayList<Map<String, String>>();
            Map<String, String> error = wildScenario.getLogger().solveError(
                    e, " - missing source file : " + rawValue);
            this.onError = true;
            fileSourcesErrors.add(error);
        }

    }

    public Map<String, String> getFileSourceError() {
        if(this.fileSourcesErrors!=null)return this.fileSourcesErrors.get(0);
        return null;
    }

    public List<Map<String, String>> getFileSourceErrors() {
        return this.fileSourcesErrors;
    }

    public List<String> getListError() {
        return this.errorList ;
    }

}