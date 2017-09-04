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

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static fr.wild.common.IoCommons.*;
import static fr.wild.common.IoWilds.*;
/**
 * Classe générique dont sont hérités tous les objet du modèle
 * @author Silver
 *
 */
public abstract class WildClass {
	protected WildLogger WILD_Logger ;
	protected LinkedHashMap<Integer, HashMap<String, HashMap<String, Object>>>  WILD_ClassOutputs; // Indexes : Numéro de sous élément, Identifiant de l'output, Argument de l'output
	protected LinkedHashMap<Integer, Integer[]>  WILD_StepProgress = new LinkedHashMap<Integer, Integer[]>() ;
	protected String WILD_baseFolderPath ;
	protected String WILD_baseTempFolderPath ;
	protected HashMap<Integer, Object[]> WILD_execHisto = new HashMap<Integer, Object[]>() ;
	protected WildObject WILD_dObject ;

	
/**
 * Exécution d'une requête SQL
 * dans le schéma dice_workshop de la base de données WILD	
 * @param strToExec	Ordre sql à exécuter
 * @throws Exception 
 */
protected void WILD_executeSql(String strToExec) throws Exception{
	WILD_echo("sqlOrder to write #SQL# "+strToExec);
}
/**
 * Recupération d'une valeur par une requête SQL
 * dans le schéma dice_workshop de la base de données WILD
 * @param strToExec	Ordre sql à exécuter	
 * @throws Exception 
 */
protected <T> T WILD_getSql(String strToExec) throws Exception{
	WILD_echo("sqlOrder to catch #SQL# "+strToExec);
	return (T) "-9999" ;
}
	
/**
 * Récupération des identifiants uniques d'exécution
 * @return [id utilisateur, id exécution, id instance de scénario, id instance de composant, id instance d'objet JAVA] 
 */
protected String WILD_userId(){
return	WILD_dObject.getTokens()[0];
}
/**
 * Récupère la valeur d'un élément préconfiguré dans un config.6conf en amont (configuration de modèle)
 * @param ident	Nom WILD du paramètre de configuration
 * @return
 */
@SuppressWarnings("unchecked")
public <T> T WILD_getConf(String ident){
	return (T) WILD_dObject.getDiceModelList().getProperty(ident) ;
}
/**
 * Actualise dans une chaîne de caractère, les éléments #6D#xxxx#6D# par les valeurs instanciées correspondantes
 * Porte sur le nom du scénario, le nom de l'utilisateur etc. (voir fichier configuration/config.6conf
 * @param ident
 * @return
 */
public String WILD_getConf_translate(String ident){
	return conf_translate(WILD_getConf(ident),WILD_dObject.getTokens(),WILD_dObject);
}
/**
 * Recherche la meilleur conversion d'un nom de fichier, sensé exister sur le disque.
 * Tente le String fourni comme une adresse locale ou absolue, puis dans le répertoire temporaire, puis celui d'exécution.
 * Si rien ne marche, renvoie le String inital.
 * @param initPath chemin "local" à convertir
 * @return	chemin dans le répertoire des exécutions en cours
 */
protected String WILD_convertPath_choseFile(String initPath){
	if((new File(initPath)).exists())return initPath ;
	String newPath = WILD_convertPath(initPath, false);
	if(new File(newPath).exists())return newPath ;
	newPath = WILD_convertPath(initPath, true);
	if(new File(newPath).exists())return newPath ;
	return initPath ;
}
/**
 * Convertit le chemin "local" vers un chemin dans le répertoire des exécutions en cours
 * @param initPath chemin "local" à convertir
 * @return	chemin dans le répertoire des exécutions en cours
 */
protected String WILD_convertPath(String initPath){
	return  WILD_convertPath(initPath, false);
}

/**
 * [DISCOURAGED]
 * Affiche le message de fin d'exécution du modèle
 * @throws Exception
 */
public void WILD_drop() throws Exception{
	if((Boolean) WILD_dObject.getDiceModelList().getProperty("log_verbose_showBricks")) WILD_Logger.logEvent("DiceObjectEnd");
}

/**
 * [DISCOURAGED]
 * Configuration préalable à l'exécution d'une méthode
 * @throws Exception
 */
public void WILD_beginMethod() throws Exception{
	WILD_execHisto.put(WILD_dObject.getLastElement(), new Object[] {"WildMethod",  WILD_Logger.getLastMethod()}) ;
	if((Boolean) WILD_dObject.getDiceModelList().getProperty("log_verbose_showBricks"))WILD_Logger.logEvent("DiceMethodBegin");
}
/**
 * [DISCOURAGED]
 * Méthode obligatoire à la fin de l'exécution d'une méthode
 * @throws Exception
 */
public void WILD_endMethod() throws Exception{
	if((Boolean) WILD_dObject.getDiceModelList().getProperty("log_verbose_showBricks"))WILD_Logger.logEvent("DiceMethodEnd");
}
/**
 * [DISCOURAGED]
 * Méthode permettant de passer à l'étape suivante
 */
protected void WILD_setStep(){
	Integer methodToken = WILD_getLastToken();
	Integer methodStepProgress = (WILD_StepProgress.get(methodToken)==null)?0:WILD_StepProgress.get(methodToken)[0]+1;
	WILD_setStep(methodToken, methodStepProgress, null); 
}
/**
 * [DISCOURAGED]
 * Passage direct à une méthode spécifique
 * @param methodToken	Identifiant de méthode
 * @param methodStepProgress	Niveau d'avancement dans la méthode
 */
protected void WILD_setStep(Integer methodToken, Integer methodStepProgress){
	WILD_setStep(methodToken, methodStepProgress, null); 
}
/**
 * [DISCOURAGED] 
 * Passage direct à une méthode spécifique, en forçant un objet d'un longueur spécifique pour ratio
 * A utiliser par exemple pour les téléchargaments
 * @param methodToken
 * @param methodStepProgress
 * @param evalLength
 */
protected void WILD_setStep(Integer methodToken, Integer methodStepProgress,Object evalLength){
	StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
	String fromMethod = stackTraceElements[3].getMethodName();
	if(fromMethod.equals("add"))return ;
	if((Boolean)WILD_getConf("log_verbose_showSteps"))
		try {
			
			WILD_echo("NewStep : "+
					stackTraceElements[3].getMethodName()
					+":"+methodStepProgress);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	WILD_StepProgress.put(methodToken, new Integer[]{methodStepProgress,0, cast_evalLength(evalLength)}); 
}



/**
 * Récupère l'adresse locale simplifiée du fichier
 * @param initPath	chemin dans le répertoire des exécutions en cours 
 * @return Adresse locale simplifiée
 */
protected String WILD_urlPath(String initPath){
	return initPath.replaceAll("^"+WILD_baseFolderPath,"").replaceAll("^"+WILD_baseTempFolderPath,"").replaceAll("^\\\\", "");
}
/**
 * Convertit le chemin "local" vers un chemin dans le répertoire des exécutions en cours
 * @param initPath chemin "local" à convertir
 * @param finalOverTemp à true, renvoie le répertoire d'exécution final, à false, renvoie le répertoire temporaire
 * @return	chemin dans le répertoire des exécutions en cours
 */
protected String WILD_convertPath(String initPath, Boolean finalOverTemp){
	if(initPath.startsWith(WILD_baseTempFolderPath)||initPath.startsWith(WILD_baseFolderPath)
			||(initPath.startsWith("//"))
			||(initPath.contains(":")&&initPath.contains("/")&&initPath.indexOf(":")<initPath.indexOf("/"))
			||(initPath.contains(":")&&initPath.contains("\\")&&initPath.indexOf(":")<initPath.indexOf("\\"))
	){
		return initPath ;
	}
	if(finalOverTemp)return WILD_baseFolderPath+ File.separator + initPath.replace(File.separator+"$", "") ;
	return WILD_baseTempFolderPath + File.separator +  initPath.replace(File.separator+"$", "");
}
/**
 * [DISCOURAGED]
 * Méthode d'initialisation (quasi constructeur)
 * Appelée seulement par les classes directement filles de WildClass
 * @param dObject
 * @throws Exception
 */
protected void WILD_initialize_WildClass(WildObject dObject) throws Exception{
	WILD_dObject = dObject ;
	WILD_Logger = new WildLogger(this);
	WILD_baseFolderPath = WILD_dObject.getExecFolder();
	WILD_baseTempFolderPath = WILD_dObject.getExecTempFolder();
	WILD_execHisto.put(-1, new Object[] {"WildObject", this.getClass().getSimpleName()}) ;
	if((Boolean) WILD_dObject.getDiceModelList().getProperty("log_verbose_showBricks"))WILD_Logger.logEvent("DiceObjectBegin");
}
/**
 * Publication d'un évènement à logger
 * @param id_Msg	Code ident du message à logger, dans les fichiers de configuration
 * @throws Exception
 */
public void WILD_logEvent(String id_Msg) throws Exception{
	WILD_Logger.logEvent(id_Msg);
}
/**
 * Log d'un message utilisateur pour trace ("message quelconque")
 * @param echo_Msg	Chaîne de caractère à afficher
 * @throws Exception
 */
public void WILD_echo(String echo_Msg) {
	try {
		WILD_Logger.logEvent("UserMessage",echo_Msg);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
/**
 * Log d'une exception
 * @param exception Exception JAVA à logger
 * @throws Exception
 */
protected void WILD_logException(Exception exception) throws Exception{
	WILD_Logger.logError(exception);
	if(execution_isInterrupt(exception)&&(Boolean)WILD_getConf("qConfig_throwInterruptException"))throw exception;
}

/**
 * Récupération de la liste d'erreur rencontrées pour la brique
 */
public HashMap<String,HashMap<String,String>> getErrorList(){
	return WILD_Logger.getErrorList();
}

/**
 * Détermine si au moins une erreur est remontée dans le logger
 */
public boolean isError(){
	return WILD_Logger.isError();
}
/**
 * Ajout d'un output (sortie accessoire, conservée et remontée)
 * @param OutputMethodToken	Identifiant de la méthode générant l'output
 * @param OpIdent	Nom de la valeur d'output
 * @param OpValue	Valeur de l'output
 */

protected void WILD_setOutput(Integer OutputMethodToken, String OpIdent, Object OpValue ){
	try{
	String OutputMethodName = WILD_getNameFromToken(OutputMethodToken);
	if(WILD_ClassOutputs==null)WILD_ClassOutputs = new LinkedHashMap<Integer, HashMap<String, HashMap<String, Object>>>();
	HashMap<String, Object> descrOutput = null ;
	if(OutputMethodToken==-1){
		descrOutput = this.WILD_getBrick().getOutput_descr(OpIdent);
	}else{
		String[] smallArray = {WILD_dObject.getModelName(),WILD_dObject.getName(),OutputMethodName};
		try{
			descrOutput = WILD_dObject.getModel().getOutput(smallArray, OpIdent);
		}catch(Exception e){
			//e.printStackTrace();
			return;
		}
	}
	descrOutput.put("value", OpValue);
	HashMap<String, HashMap<String, Object>> outputByIdent = WILD_ClassOutputs.get(OutputMethodToken) ;
	if(outputByIdent==null)outputByIdent = new HashMap<String, HashMap<String, Object>>();
	//outputByIdent.put(map_getAvaibleName(OpIdent,outputByIdent), descrOutput);
	outputByIdent.put(OpIdent, descrOutput);
	WILD_ClassOutputs.put(WILD_getLastToken(), outputByIdent);	
	}catch(Exception e){
		e.printStackTrace();
	}
}
/**
 * Ajout d'un output (sortie accessoire, conservée et remontée)
 * @param OpIdent	Nom de la valeur d'output
 * @param OpValue	Valeur de l'output
 */
protected void WILD_setOutput(String OpIdent, Object OpValue){
	WILD_setOutput(WILD_getLastToken(), OpIdent, OpValue );
}

/**
 * Récupération de la liste des outputs (sorties accessaoires) préparée
 * @return
 */
public LinkedHashMap<String, HashMap<String, Object>> WILD_getOutputs() {
	if(WILD_ClassOutputs==null)return null;
	LinkedHashMap<String, HashMap<String, Object>> toRet = new LinkedHashMap<String, HashMap<String, Object>>();
	for(Integer i:WILD_ClassOutputs.keySet())for(String j:WILD_ClassOutputs.get(i).keySet()){
		String key = (WILD_getNameFromToken(i)!=null)?WILD_getNameFromToken(i)+"["+i+"]."+j:j;
		toRet.put(key,WILD_ClassOutputs.get(i).get(j));
	}
	return toRet ;
}
/**
 * Récupération de la valeur d'un output associé à l'objet	
 * @param outputName	Nom de l'output à récupérer
 * @return	Valeur de l'output à récupérer
 */
@SuppressWarnings("unchecked")
public <T> T WILD_getOutput(String outputName){
	return (T) this.WILD_getOutputs().get(outputName);
}
/**
 * [DISCOURAGED]
 * Méthode et/ou surcharge pour la progression de méthodes
 */
protected void WILD_nextStep(){
	WILD_nextStep(WILD_getLastToken());
}
/**
 * [DISCOURAGED]
 * Méthode et/ou surcharge pour la progression de méthodes
 */
protected void WILD_setNextForStep(){
	WILD_setNextForStep(WILD_getLastToken());
}
/**
 * [DISCOURAGED] 
 * Méthode et/ou surcharge pour la progression de méthodes
 * @param methodToken Méthode en cours
 */
protected void WILD_setNextForStep(Integer methodToken){
	WILD_setNextForStep(methodToken,1);
}
/**
 * [DISCOURAGED] 
 * Méthode et/ou surcharge pour la progression de méthodes
 * Affectation d'une valeur d'avancement
 * @param valBoost Numéro d'étape à forcer
 */
protected void WILD_setBoostForStep(Integer valBoost){
	WILD_setNextForStep(WILD_getLastToken(),valBoost);
}
/**
 * [DISCOURAGED] 
 * Méthode et/ou surcharge pour la progression de méthodes
 * @param methodToken Méthode en cours
 */
protected void WILD_nextStep(Integer methodToken){
	Integer[] lastStep = WILD_StepProgress.get(methodToken);
	if(lastStep==null)lastStep=new Integer[]{0,null,null};
	lastStep[0] = lastStep[0] + 1 ;
	WILD_StepProgress.put(methodToken, lastStep); 
}
/**
 * [DISCOURAGED] 
 * Méthode et/ou surcharge pour la progression de méthodes
 * @param methodToken Méthode en cours
 * @param newBoost Numéro d'étape à forcer
 */
protected void WILD_setNextForStep(Integer methodToken, Integer newBoost){
	Integer[] lastStep = WILD_StepProgress.get(methodToken); 
	lastStep[1] = lastStep[1] + newBoost ;
	if(lastStep[1] > lastStep[2])lastStep[1] = lastStep[2] ;
	WILD_StepProgress.put(methodToken, lastStep); 
}
/**
 * [DISCOURAGED] 
 * Récupération de l'indicateur de progression en cours, pour une étape
 * @param integer
 * @return
 */
public Integer WILD_getStepProgress(Integer integer){
	if(WILD_StepProgress.get(integer)==null)return 0;
	return WILD_StepProgress.get(integer)[0];
}
/**
 * [DISCOURAGED] 
 * Récupération du numéro de la dernière méthode exécutée
 * @return
 */
public String WILD_getLastMethod(){
	return (String) WILD_execHisto.get(map_maxKey(WILD_execHisto.keySet()))[1] ;
}
/**
 * [DISCOURAGED] 
 * Récupération du numéro de la dernière méthode à exécuter
 * @return
 */
public Integer WILD_getLastToken(){
	return (Integer) map_maxKey(WILD_execHisto.keySet());
}
/**
 * [DISCOURAGED]
 * A partir du numéro de la méthode en cours, récupère son nom
 * @param outputMethodToken
 * @return
 */
protected String WILD_getNameFromToken(Integer outputMethodToken){
	return (String) WILD_execHisto.get(outputMethodToken)[1];
}
/**
 *  (DISCOURAGED]
 *  Récupération de l'objet accessible par le service WILD
 * @return
 */
public WildBrick WILD_getBrick() {
	return this.WILD_dObject ;
}



}