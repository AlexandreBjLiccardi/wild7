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
import static fr.wild.common.IoWilds.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.message.MapMessage;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import fr.wild.hibernate.ExecutionChainsaw;
import fr.wild.hibernate.User;

/**
 * Classe de log
 * Utilise Log4j
 * Permet la réception des erreurs, l'impression output de messages, l'envoi de mails, l'écriture en base de données...
 * @author alexandre.liccardi
 * @version [ab]
 */
public class WildLogger{

    private Logger logger = LogManager.getLogger(); // Manager d'écriture de Logs Log4j
    private String[] tokens ;// Liste des tokens

    private HashMap<String, HashMap<String, Object>> confNodeList; // Map des messages par défaut, chargé une seule fois
    private HashMap<String, HashMap<String, String>> confNodeListErrors; // Map des erreurs rencontrées
    private LinkedHashMap<String, Object> config_table ;

    private WildWrapper paramWrapper = null;


    private WildModelList wildModelList ; // Liste des modèles WILD, permet notamment la récupération des messages propres aux éléments
    private WildExecList wildExecList ; // Liste d'exécution générant l'erreur
    private WildBrick wildBrick ; // Brique DICE générant l'erreur
    private WildClass wildClass; // Objet instancié renvoyant l'erreur

    private String userId = null ;
    private String executionId = null ;
    private String wildClassName = null ;
    private String wildId = null ;
    private Wild4Test wild4Test;
	private int errorKey = 1; // Incrément automatique des erreurs rencontrées

    /**
     * Récupère l'intégralité de la liste des erreurs
     * @return
     */
    public HashMap<String,HashMap<String,String>> getErrorList(){
        return this.confNodeListErrors;
    }

    /**
     * Réupère le compte des erreurs
     * @return
     */
    public Integer getErrorCountList(){
        if(this.confNodeListErrors==null)return 0 ;
        return this.confNodeListErrors.size();
    }

    /**
     * Détermine si au moins une erreur est remontée dans le logger
     * @return
     */
    public Boolean isError(){
        if(this.confNodeListErrors==null)return false ;
        return !this.confNodeListErrors.isEmpty();
    }

    /**
     * Constructeur
     * @param i_diceModelList    Liste de modèles servant de contexte au log
     */
    /*public WildLogger(WildModelList i_diceModelList) {
        wildModelList = i_diceModelList ;
    }*/

    /**
     * Constructeur
     * @param i_diceExecList    Liste d'exécution émettant l'objet à logger
     */
    public WildLogger(WildExecList i_diceExecList) {
        wildExecList = i_diceExecList ;
        userId = wildExecList.getUserId();
        executionId = wildExecList.getServiceId();
        wildClassName = "ExecList";
    }

    /**
     * Constructeur
     * @param i_wild4Test    Test d'exécution émettant l'objet à logger
     */
    public WildLogger(Wild4Test i_wild4Test) {
        wild4Test = i_wild4Test ;
        userId = wild4Test.getUserId();
        executionId = wild4Test.getExecutionId();
        wildClassName = wild4Test.getClassName();
    }

    /**
     * Constructeur
     * @param i_diceBrick        Brique wild (Scénario, Composant...) émettant l'objet à logger
     */
    public WildLogger(WildBrick i_wildBrick) {
        wildModelList = i_wildBrick.getDiceModelList() ;
        wildBrick = i_wildBrick;
        tokens = wildBrick.getTokens();
        userId = tokens[0];
        executionId = tokens[1];
        wildClassName = wildBrick.getClass().getSimpleName();
        wildId = tokens[2];
    }

    /**
     * Constructeur
     * @param i_diceClass        Classe d'objet WILD émettant l'objet à logger
     */
    public WildLogger(WildClass i_wildClass) {
        wildClass = i_wildClass ;
        wildBrick = wildClass.WILD_getBrick();
        wildModelList = wildBrick.getDiceModelList() ;
        tokens = wildBrick.getTokens();
        userId = tokens[0];
        executionId = tokens[1];
        wildClassName = wildBrick.getClass().getSimpleName();
        wildId = tokens[2];
    }

    /**
     * Attribue une liste de modèles au loggeur (setter)
     * @param i_diceModelList    Liste de modèles à attribuer
     */
    public void setListModel(WildModelList i_diceModelList){
        wildModelList = i_diceModelList ;
    }

    /**
     * Méthode de résolution d'une erreur,
     * en récupérant les informations correspondantes dans le modèle chargé
     * @param eToLog    Exception JAVA à logger
     * @return    Map contenant la description de l'erreur selon la description WILD
     */
    public HashMap<String, String> solveError(Exception eToLog){
        return solveError(eToLog,null);
    }

    /**
     * Méthode de résolution d'une erreur,
     * en récupérant les informations correspondantes dans le modèle chargé
     * @param eToLog    Exception JAVA à logger
     * @param extraMsg    Message complémentaire à logger
     * @return    Map contenant la description de l'erreur selon la description WILD
     */
    public HashMap<String, String> solveError(Exception eToLog, String extraMsg){
        return solveError(eToLog,extraMsg,null);
    }

    /**
     * Méthode de résolution d'une erreur,
     * en récupérant les informations correspondantes dans le modèle chargé
     * @param eToLog    Exception WILD (code) à logger
     * @return    Map contenant la description de l'erreur selon la description WILD
     */
    public HashMap<String, String> solveError(String eToLogCode){
        return solveError(null,eToLogCode,null);
    }

    /**
     * Méthode de résolution d'une erreur,
     * en récupérant les informations correspondantes dans le modèle chargé
     * @param eToLogCode    Exception WILD (code) à logger
     * @param extraMsg    Message complémentaire à logger
     * @return    Map contenant la description de l'erreur selon la description WILD
     */
    public HashMap<String, String> solveError(String eToLogCode, String extraMsg){
        return solveError(null,eToLogCode,extraMsg);
    }

    /**
     * Méthode de résolution d'une erreur,
     * en récupérant les informations correspondantes dans le modèle chargé
     * @param eToLog
     * @param extraMsg
     * @return    Map contenant la description de l'erreur selon la description WILD
     */
    HashMap<String, String> solveError(Exception eToLog, String eToLogCode, String extraMsg){
        HashMap<String,String> map = new HashMap<String,String>();
        String[] trace = {wildBrick.getModelName(),wildBrick.getShortName()};

        map.put("User",this.userId);
        if(this.wildId!=null&&(Boolean)wildModelList.getProperty("qConfig_jobLogPath"))map.put("Execution",this.wildId);
        else map.put("Execution",this.executionId);
        
        HashMap<String, Object> diceException = null;
        HashMap<String,Object> diceExceptionNA = wildModelList.getException(trace, "NotAvailableException", true);

        if(eToLog!=null){
            map.put("Class",eToLog.getClass().getSimpleName());
            map.put("JavaClass",eToLog.getClass().getSimpleName());
            map.put("JavaMessage",eToLog.getMessage());
            diceException = wildModelList.getException(trace, eToLog, true);
        }else if(eToLogCode!=null){
            map.put("Class",eToLogCode);
            map.put("JavaClass",eToLogCode);
            map.put("JavaMessage",eToLogCode);
            diceException = wildModelList.getException(trace, eToLogCode, true);
         }
        String msg = null ;
        String criticity = null ;
        if(diceException==null){
            diceException = diceExceptionNA ;
            msg = (String) diceExceptionNA.get("description");
            criticity = ((String) diceExceptionNA.get("criticity")).toUpperCase();
        }else{
            msg = ((String) diceException.get("description")!=null)?(String) diceException.get("description"):(String) diceExceptionNA.get("description");
            criticity = ((String) diceException.get("criticity")!=null)?((String) diceException.get("criticity")).toUpperCase():((String) diceExceptionNA.get("criticity")).toUpperCase();
        }
        if(msg == null)msg = "";
        if(extraMsg!=null)msg+=" - " +extraMsg;
        else if(eToLog!=null) msg+=" - " +eToLog.getMessage();
        map.put("Message",msg);
        map.put("criticity",criticity);
        if(criticity.equals("fatal"))exit();
        return map;
    }

    /**
     * Sortie du système en cas d'erreur fatale,
     * avec délétion du fichier de tag d'exécution
     */
    private void exit(){
        System.out.println((String)wildModelList.getProperty("stop_message"));
        File toDel = new File("locked.6d") ;
        if(toDel.exists())toDel.delete();
        System.exit(0);
    }

    /**
     * Fonction d'envoi de mails pour rapports d'erreurs
     * @param exec    Exécution Ã  l'origine de l'envoi du mail
     * @param dSc    Scénario Ã  l'origine de l'envoi du mail
     */
    public void sendMailReport(ExecutionChainsaw exec,WildScenario dSc){
        final String mailFrom = cast_2String(wildModelList.getProperty("mail_from"));
        final String username = cast_2String(wildModelList.getProperty("mail_user"));
        final String password = cast_2String(wildModelList.getProperty("mail_pwd"));
        Properties props = new Properties();
        props.put("mail.smtp.auth", cast_2String(wildModelList.getProperty("mail_auth")));
        props.put("mail.mime.charset", "UTF-8");
        props.put("mail.smtp.starttls.enable", cast_2String(wildModelList.getProperty("mail_ttls")));
        props.put("mail.smtp.starssl.enable", cast_2String(wildModelList.getProperty("mail_starssl")));
        props.put("mail.smtp.ssl.enable", cast_2String(wildModelList.getProperty("mail_ssl")));
        props.put("mail.smtp.host", cast_2String(wildModelList.getProperty("mail_host")));
        props.put("mail.smtp.port", cast_2String(wildModelList.getProperty("mail_port")));
        Session session = Session.getInstance(props,new javax.mail.Authenticator() {protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(username, password);}});
        String Newligne=System.getProperty("line.separator");
        try {
            User user = exec.getUser();
            String msgText ;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(user.getUserMail()));
            if(!dSc.isOnError()){
                // Succès de l'exécution
                message.setSubject(conf_translate(wildModelList.getProperty("mail_object_layout_forSubject_success"),exec,dSc));
                msgText = conf_translate(wildModelList.getProperty("mail_object_layout_forScenario_success"),exec,dSc) ;
            }else{
                // Echec de l'exécution
                message.setSubject(conf_translate(wildModelList.getProperty("mail_object_layout_forSubject_failure"),exec,dSc));
                msgText = conf_translate(wildModelList.getProperty("mail_object_layout_forScenario_failure"),exec,dSc).replaceAll("\\{A\\}",date_now()) ;
            }
            HashMap<String,HashMap<String,Object>> products = dSc.getProducts() ;
            if(products != null && products.size()>0){
                String productsMsg = "";
                Set<String> ksp = products.keySet();
                for(String k:ksp)productsMsg += (Newligne+Newligne+"["+ k +"]" + products.get(k).get("description")+" : "+products.get(k).get("value"));
                msgText=msgText.replaceAll("\\{A\\}",productsMsg);
            }else{
                msgText=msgText.replaceAll("\\{A\\}","Aucun");
            }
            Map<String,String> error = dSc.getError() ;
            if(error != null){
                msgText += Newligne+conf_translate(wildModelList.getProperty("mail_object_layout_forScenario_failure_errorHeader"),exec,dSc);
                for(String k:error.keySet())msgText += Newligne+k+" : "+error.get(k);
            }
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(msgText);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            if(!dSc.isOnError()){
                String filename = dSc.getArchiveFolder()+File.separator+exec.getIdExecution()+".zip";
                if((new File(filename)).exists()){
                    DataSource source = new FileDataSource(filename);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(filename);
                    multipart.addBodyPart(messageBodyPart);
                }
            }
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction d'envoi de mails
     * @param exec    Exécution Ã  l'origine de l'envoi du mail
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws XPathExpressionException
     * @throws DOMException
     */
    public void sendMailReport(ExecutionChainsaw exec) {
        final String mailFrom = cast_2String(wildModelList.getProperty("mail_from"));
        final String username = cast_2String(wildModelList.getProperty("mail_user"));
        final String password = cast_2String(wildModelList.getProperty("mail_pwd"));
        Properties props = new Properties();
        props.put("mail.mime.charset", "UTF-8");
        props.put("mail.smtp.auth", cast_2String(wildModelList.getProperty("mail_auth")));
        props.put("mail.smtp.starssl.enable", cast_2String(wildModelList.getProperty("mail_ttls")));
        props.put("mail.smtp.ssl.enable", cast_2String(wildModelList.getProperty("mail_ssl")));
        props.put("mail.smtp.host", cast_2String(wildModelList.getProperty("mail_host")));
        props.put("mail.smtp.port", cast_2String(wildModelList.getProperty("mail_port")));
        Session session = Session.getInstance(props,new javax.mail.Authenticator() {protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(username, password);}});
        String Newligne=System.getProperty("line.separator");
        try {
            User user = exec.getUser();
            String msgText ;
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(user.getUserMail()));
            // Echec de l'exécution
            message.setSubject(conf_translate(wildModelList.getProperty("mail_object_layout_forSubject_failure"),exec));
            msgText = conf_translate(wildModelList.getProperty("mail_object_layout_forScenario_failure"),exec).replaceAll("\\{A\\}",date_now()) ;
            msgText += Newligne+conf_translate(wildModelList.getProperty("mail_object_layout_forScenario_failure_errorHeader"),exec);
            msgText += Newligne+((String) wildModelList.getProperty("mail_object_layout_forScenario_failure_instanciation")).replaceAll("\\{A\\}", xml_getFirstValue(cast_String2Node(exec.getInfoIn()), "./@ident"));
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(msgText);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère la dernière méthode Dice lancée, en comparaison au ModelList
     * @return
     */
    public String getLastMethod(){
    	String[] strL = trace_getLastDiceMethod(wildModelList);
        return trace_getLastDiceMethod(wildModelList)[strL.length-1];
    }

    /**
     * Récupère le dernier objet Dice lancé, en comparaison au ModelList
     * @return
     */
    public String getLastObject(){
        return trace_getLastDiceMethod(wildModelList)[1];
    }

    /**
     * Récupère le dernier modèle Wild utilisé, en comparaison au ModelList
     * @return
     */
    public String getLastModel(){
        return trace_getLastDiceMethod(wildModelList)[0];
    }

    /**
     * Log d'une erreur via log4j
     * @param eToLog    Exception JAVA à logger
     */
    public void logError(Exception eToLog){
        logError(eToLog, null);
    }

    /**
     * Log d'une erreur via log4j
     * @param eToLog    Exception JAVA à logger
     * @param extraMsg    Message supplémentaire
     */
    public void logError(Exception eToLog, String extraMsg){
        HashMap<String, String> map = new HashMap<String, String>();
        if((Boolean)wildModelList.getProperty("verbose_printStackTraceErrors"))eToLog.printStackTrace();
        try{
            map = this.solveError(eToLog, extraMsg);
        }catch(Exception e){
            String msg = "JAVA : "+ eToLog.getMessage();
            if(extraMsg != null) msg += " - "+ extraMsg ;
            map.put("Message", msg);
            map.put("criticity", "ERROR");
        }finally{
            MapMessage mmk = new MapMessage(map) ;
            if(!execution_isInterrupt(eToLog)&&!(Boolean)wildModelList.getProperty("qConfig_throwInterruptException"))logger.log(Level.toLevel(map.get("criticity")), mmk);
            if(confNodeListErrors==null)confNodeListErrors = new HashMap();
            if(map!=null)this.confNodeListErrors.put(String.valueOf(errorKey++), map);

        }
    }

    /**
     * Log d'une erreur via log4j
     * @param eToLog    Exception JAVA à logger
     * @throws Exception
     */
    public void logError(String eToLogCode) throws Exception{
        logError(eToLogCode, null);
    }

    /**
     * Log d'une erreur via log4j
     * @param eToLog    Exception JAVA à logger
     * @param extraMsg    Message supplémentaire
     * @throws Exception
     */
    public void logError(String eToLogCode, String extraMsg) throws Exception{
        HashMap<String, String> map = new HashMap<String, String>();
        try{
            map = this.solveError(eToLogCode, extraMsg);
        }catch(Exception e){
            String msg = "JAVA wildCode : "+ eToLogCode;
            if(extraMsg != null) msg += " - "+ extraMsg ;
            map.put("Message", msg);
            map.put("criticity", "ERROR");
        }finally{
            MapMessage mmk = new MapMessage(map) ;
            logger.log(Level.toLevel(map.get("criticity")), mmk);
        }
    }

    /**
     * Méthode de résolution d'un message,
     * en récupérant les informations correspondantes dans le modèle chargé
     * @param mToLog    Message WILD (code) à logger
     * @return    Map contenant la description de l'erreur selon la description WILD
     * @throws Exception
     */
    private HashMap<String, String> solveMsg(String mToLogCode) throws Exception{
        return solveMsg(mToLogCode,null);
    }

    /**
     * Méthode de résolution d'un message,
     * en récupérant les informations correspondantes dans le modèle chargé
     * @param mToLog
     * @param extraMsg
     * @return    Map contenant la description de l'erreur selon la description WILD
     * @throws Exception
     */
    private HashMap<String, String> solveMsg(String mToLogCode, String extraMsg) throws Exception{
        if(wildModelList==null&&paramWrapper==null)paramWrapper = new WildWrapper("Configuration","configuration/config.6conf");
        HashMap<String,String> map = new HashMap<String,String>();
        String[] trace = null;
        if(wildBrick != null){
            String[] trace_0 = {wildBrick.getModelName(),wildBrick.getShortName()};
            trace = trace_0 ;
        }
        map.put("User",this.userId);
        if(this.wildId!=null&&(Boolean)wildModelList.getProperty("qConfig_jobLogPath"))map.put("Execution",this.wildId);
        else map.put("Execution",this.executionId);
        
        HashMap<String, Object> diceMessage = null;
        HashMap<String,Object> diceMessageNA = (wildModelList==null)?paramWrapper.getMessage("NotAvailableMessage"):wildModelList.getMessage(trace, "NotAvailableMessage", true);
        map.put("Class",mToLogCode);
        map.put("JavaClass",mToLogCode);
        map.put("JavaMessage",mToLogCode);
        if(trace != null)diceMessage = wildModelList.getMessage(trace, mToLogCode, true);
        else diceMessage = paramWrapper.getMessage(mToLogCode) ;

        String msg = null ;
        String criticity = null ;
            if(diceMessage==null){
                diceMessage = diceMessageNA ;
                msg = (String) diceMessageNA.get("description");
                criticity = ((String) diceMessageNA.get("criticity")).toUpperCase();
            }else{
                msg = ((String) diceMessage.get("description")!=null)?(String) diceMessage.get("description"):(String) diceMessageNA.get("description");
                criticity = ((String) diceMessage.get("criticity")!=null)?((String) diceMessage.get("criticity")).toUpperCase():((String) diceMessageNA.get("criticity")).toUpperCase();
            }
        if(msg == null)msg = "";
        if(extraMsg!=null)msg+=" - " +extraMsg;
        map.put("Message",msg);
        map.put("criticity",criticity);
        if(criticity.equals("fatal"))exit();
        return map;
    }

    /**
     * Ecriture d'un message Log4j
     * @param mToLogCode    Code du message à logger
     * @throws Exception
     */
    public void logEvent(String mToLogCode) throws Exception {
        HashMap<String, String> diceMessage = solveMsg(mToLogCode,null);
        MapMessage mmk = new MapMessage(diceMessage) ;
        logger.log(Level.toLevel(diceMessage.get("criticity")), mmk);
    }

    /**
     * Ecriture d'un message Log4j
     * @param mToLogCode    Code du message à logger
     * @param extraMsg        Texte supplémentaire
     * @throws Exception
     */
    public void logEvent(String mToLogCode, String extraMsg) throws Exception {
        HashMap<String, String> diceMessage = solveMsg(mToLogCode,extraMsg);
        MapMessage mmk = new MapMessage(diceMessage) ;
        logger.log(Level.toLevel(diceMessage.get("criticity")), mmk);
    }

    /**
     * Ecriture d'un message Log4j - Affichage d'une map
     * @param parametersWith    Map Ã  afficher
     * @param extraMsg    Texte complÃ©mentaire Ã  ajouter
     * @throws Exception
     */
    public <S,T> void logMap(HashMap<String, Object> parametersWith, String extraMsg) throws Exception{
        logEvent("UserMessage", cast_2String(parametersWith));
    }

    /**
     * Une liste de modèle est-elle associée au logger
     * @return
     */
    public boolean hasModels() {
        if(this.wildModelList!=null)return true ;
        return false;
    }

	public void WILD_setLastToken(String uNIQ_TEST_ID) {
		this.wildId = uNIQ_TEST_ID ;
		
	}
    
}