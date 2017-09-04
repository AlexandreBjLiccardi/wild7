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

import static fr.wild.common.Connector.*;
import static fr.wild.common.IoWilds.*;
import static fr.wild.common.IoCommons.*;
import static fr.wild.common.IoFileSystem.*;

import com.sun.management.ThreadMXBean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import fr.wild.hibernate.AdminLevel;
import fr.wild.hibernate.Criticity;
import fr.wild.hibernate.ExecutionArchive;
import fr.wild.hibernate.ExecutionChainsaw;
import fr.wild.hibernate.ExecutionConsumption;
import fr.wild.hibernate.ExecutionInfo;
import fr.wild.hibernate.ExecutionList;
import fr.wild.hibernate.JavaConsumption;
import fr.wild.hibernate.JavaConsumptionId;
import fr.wild.hibernate.JavaService;
import fr.wild.hibernate.JavaServiceInfo;
import fr.wild.hibernate.Model;
import fr.wild.hibernate.ModelsExecution;
import fr.wild.hibernate.ModelsExecutionId;
import fr.wild.hibernate.Status;
import fr.wild.hibernate.User;


/**
 * Classe principale de lancement du service DICE.
 * Fonctionne en bouclant sur une table HIBERNATE (ExecutionChainsaw).
 * @author alexandre.liccardi
 * @version WILD0.1a "Ambivalence"
 */
public class WildExecList {

    // Objets DICE
    private WildModelList service_DiceModelList ; // Ensemble des éléments WILD utilisables par l'application
    private final LinkedHashMap<String, Object> config_table = config_getTab() ; // Liste des paramètres de configuration et leur valeur, pour éviter les consultations du fichier de configuration.
    //private WildLogger diceLogger_dmList ; // Log4j pour scénarios
    private WildLogger diceLogger_loop ; // Log4j pour execList
    // Variables de connexion
    private SessionFactory connect_dicePg ; // Connexion à la base de données postgresql (schéma [dice_workshop]).
    private Session db_sub ; // Session de connexion à l'intérieur d'une boucle
    private Session db ; // Session de connexion commune à toutes les boucles (pour suivi du service notamment)
    // Variables d'identification
    private JavaService javaService ; // Instance du service en cours d'exécution
    private String id_serviceInfo ; // Identifiant unique d'instance du service, c'est-à-dire d'instance de cette classe
    private String id_userServiceInfo ; // Identifiant unique de l'utilisateur qui instancie la classe
    private User userServiceInfo ; // Utilisateur qui instancie la classe
    // Variables de configuration
    private final Integer executionsPerRow = (Integer) config_table.get("system_maxTasksPerRow") ; //Nombre de jobs max. exécutés simultanément par le service
    private final Integer executionsPerUser = (Integer) config_table.get("system_maxTasksPerUser") ; //Nombre de jobs max. exécutés simultanément pour un utilisateur
    private final int[] statutsExecutionCases = {0,1,2,3,4,5,6,7,100,105,200,201,203,300} ; // Status d'exécution nécessitant un comportement spécifique (voir dealWithJobs()) - traités dans cet ordre
    // Suivi des exécutions
    private ExecutorService taskPoolService = Executors.newCachedThreadPool(); // Liste des callables (DiceScenarios) à exécuter, dans le framework Executor
    private CompletionService<ConcurrentHashMap<String,Object>> taskCompletionService = new ExecutorCompletionService<ConcurrentHashMap<String,Object>>(taskPoolService); // Jobs de compression / décompression ou scénarios à exécuter
    private ConcurrentHashMap<String, Future<ConcurrentHashMap<String, Object>>> subFutures_tolaunch = new ConcurrentHashMap<String, Future<ConcurrentHashMap<String, Object>>>(); // Liste des retours de callables (correspond à la liste des threads). Permet un renommage commun entre [taskPoolService] et [subScenarioList_tolaunch].
    private ConcurrentHashMap<String, WildExecutionPacker> packers_tolaunch ; // Liste des compresseurs / décompresseurs de dossiers d'exécution
    private LinkedHashMap<String, WildScenario> subScenarioList_tolaunch ; // Liste des informations nécessaires à la construction des scénarios (un par "tour")
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> results_launched ; // Retours d'exécution par scénario, pour export notamment
    // Suivi des ressources disponibles
    private final String[] resources = {"minRAM","minROM","minCPU"}; // Liste standard des ressources à suivre
    private LinkedHashMap<String, LinkedHashMap<String, Integer>> subScenarioList_resources; // Ressources par scénario
    private LinkedHashMap<String, Integer> availableTheoResources ; // Ressources théoriques (= configurées), réduites par la charge des exécutions en cours
    private LinkedHashMap<String, Integer> availableTheoInitResources ; // Ressources théoriques (= configurées), non modifiées
    private LinkedHashMap<String, Integer> availablePhysicalResources ; // Ressources récupérées par le système
    private ThreadMXBean threadBean = (ThreadMXBean) ManagementFactory.getThreadMXBean(); // Gestionnaire de thread pour calcul des charges courantes par exécution
    // Comportements spécifiques du service
    private Boolean serviceSuspended = false ; // Le service n'accepte plus de nouvelle exécution
    private Boolean readServiceProperty = true ; // Le service découvre le nouveau comportement, pour enregistrement du message
    private long lastCleanUpDate = 0; // Date de la dernière opération de nettoyage de jobs
    private long lastStepUpDate = 0; // Date du dernier "ping" d'un scénario
    private long lastDbInitUpDate = 0; // Date du dernier rafraîchissement de la connexion à la base de données PG
    private long lastGCUpDate = 0; // Date de la dernière exécution du garbage collector
    private long lastDbresourcesUpDate = 0; // Date de la dernière mise à jour des logs ressources

    /**
     * Constructeur
     * Assure la connexion avec la base de données
     * Monte le modèle
     * Construit le service
     * Nettoie les précédentes instances
     * Lance la boucle
     * @param i_id_userServiceInfo    Identifiant de l'utilisateur qui lance le service
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public WildExecList(String i_id_userServiceInfo) throws Exception{
        System.out.println("DICE service starting.");
        System.setProperty("log4j.configurationFile", "configuration/log4j2.xml");
        diceLogger_loop = new WildLogger(this);
        id_userServiceInfo = i_id_userServiceInfo ;
        id_serviceInfo = cast_getRandom();
        Status idStatus = null ;
        try{
        // Connexion Hibernate à la base de données
            connect_dicePg = getDbSessionFactory(config_table);
            db = connect_dicePg.openSession();
            idStatus = getDbEntity(db,Status.class, (short)8);
            initDbSub();
        //Construction du modèle
            flushModel();
            //this.diceLogger_dmList = new WildLogger(service_DiceModelList);
        // Construction d'un nouveau service
            userServiceInfo = getDbEntity(db, User.class, id_userServiceInfo) ;
            javaService = new JavaService(id_serviceInfo,userServiceInfo,new Date());
            javaService.setStatus(idStatus);
            saveDb(db,javaService);
            writeLog("ServiceLaunch");
        // Instanciation des ressources
            reeval_runningResources();
        // Initialisation des variables
            subFutures_tolaunch = new ConcurrentHashMap<String, Future<ConcurrentHashMap<String, Object>>>();
            subScenarioList_tolaunch = new LinkedHashMap<String, WildScenario>();
            packers_tolaunch = new ConcurrentHashMap<String, WildExecutionPacker>();
            results_launched = new ConcurrentHashMap<String, ConcurrentHashMap<String, Object>>();
            subScenarioList_resources = new LinkedHashMap<String, LinkedHashMap<String, Integer>>();
        // Lancement de la boucle de contrôle via la base de données
            int turn_nb = 0;
            while(keepOnRollin()){
                dealCleaner();
                dealWithService();
                dealWithResources(turn_nb);
                dealWithJobs();
                Thread.sleep((Integer) config_table.get("system_delay"));
                initDbSub();
                turn_nb++;
            }
        // Sortie de boucle (fin d'exécution)
             idStatus = getDbEntity(db,Status.class, (short)102);
         } catch (Exception e) {
             e.printStackTrace();
            if(db != null){
                idStatus = getDbEntity(db,Status.class, (short)309);
                this.diceLogger_loop.logError("ServiceLoopFailure",e.getMessage());
            }
        }finally{
        // Renseignement des informations du service en sortie
            if(javaService!=null){
                javaService.setStatus(idStatus);
                javaService.setDateEnd(new Date());
                saveDb(db,javaService);
                db_sub.close();db.close();
            }
            taskPoolService.shutdown();
            setServiceRunning();
            System.out.println("DICE service shut down.");
            System.exit(0);
         }
     }

    /**
     * Nettoyage du dossier php out, si l'archive a été générée depuis plus de {phpOut_delay_dispo}
     */
    private void clean_archive(){
        for(ExecutionArchive executionArchive:getDbList(db_sub, ExecutionArchive.class , "where id_status != 101 and EXTRACT(EPOCH FROM now()-date_archive)/60 > "+config_table.get("phpOut_delay_dispo")+" order by date_archive", (Integer)config_table.get("phpOut_delay_dispo_nopMax"))){
            String outPhpPath = conf_translate((String)config_table.get("execution_php_path"),executionArchive);
            try{
                file_delete(outPhpPath);
            }catch(Exception e){}
            finally{
                executionArchive.setStatus(getStatus(101));
                try{updateDb(db,executionArchive);}catch(Exception e){e.printStackTrace();}
            }
        }
    }

    /**
     * Nettoyage des répertoires qui ne sont plus en cours d'exécution
     */
    private void clean_files(){
        File f1 = new File(((String) config_table.get("execution_path")).split("#6D#")[0]);
        if(f1.exists())for(String f:f1.list())clean_file(new File(f1.getAbsolutePath()+File.separator+f), null);
        File f2 = new File(((String) config_table.get("execution_php_path")).split("#6D#")[0]);
        if(f2.exists())for(String f:f2.list())if(!f.equals("identifier"))clean_file(new File(f2.getAbsolutePath()+File.separator+f),(Integer) config_table.get("phpOut_delay_dispo"));
    }

    /**
     * Supprime un répertoire s'il n'est plus en cours d'exécution
     * @param fileToSearch    File à supprimer
     */
    private void clean_file(File fileToSearch, Integer delay){
        if(fileToSearch.isDirectory()){
            try {
                delay = (delay==null)?0:delay;
                Integer age = (int) ((date_nowLong()-fileToSearch.lastModified())/(60000)) ;
                if(age>delay){
                    ExecutionChainsaw executionChainsaw = getDbEntity(db_sub, ExecutionChainsaw.class, fileToSearch.getName());
                    if(executionChainsaw==null)executionChainsaw = getDbEntity(db_sub, ExecutionChainsaw.class, fileToSearch.getName().replaceAll("TEMP_", ""));
                    if(executionChainsaw==null)file_delete(fileToSearch);
                }
            } catch (IOException e) {}
        }
        if(fileToSearch.isDirectory())for(String f:fileToSearch.list())clean_file(new File(fileToSearch.getAbsolutePath()+File.separator+f), delay);
    }

    /**
     * Fournit un objet hibernate "statut"
     * @param idStatus
     * @return
     */
    private Status getStatus(int idStatus){
        return getDbEntity(db_sub,Status.class, (short)idStatus);
    }

    /**
     * Gère le service en cours d'exécution
     * adoptet des comportements différents en fonction du statut renseigné dans la base PG
     * @throws Exception
     */
    private synchronized  void dealWithService() throws Exception {
        JavaService javaService_control = getDbEntity(db_sub, JavaService.class, id_serviceInfo);
        short idStatus = javaService_control.getStatus().getIdStatus() ;
        switch(idStatus){
        // Cas de l'arrêt différé
        case 104:
                writeServiceInfo(idStatus);
                serviceSuspended =true ;
                if(subScenarioList_tolaunch.size()==0){
                    javaService_control.setStatus(getStatus(102));
                    saveDb(db,javaService_control);
                    readServiceProperty = true;
                }
                break;
        // Cas de l'arrêt immédiat
        case 105:
                writeServiceInfo(idStatus);
                System.exit(0);
                break;
        // Cas du fush de modèle
        case 106:
                writeServiceInfo(idStatus);
                if(readServiceProperty){
                    readServiceProperty = false ;
                }
                serviceSuspended =true ;
                if(subScenarioList_tolaunch.size()==0){
                    javaService_control.setStatus(getStatus(8));
                    saveDb(db,javaService_control);
                    this.flushModel();
                    readServiceProperty = true ;
                }
                serviceSuspended =false ;
                break;
            default:break;
        }

    }

    /**
     * Prépare, pour une exécution, la chaîne XML de log
     * L'enregistre dans un fichier dans le chemin d'exécution
     * @param execution    Exécution à traiter
     * @throws Exception
     */
    private void prepareSummary(ExecutionChainsaw execution) throws Exception{
        String execId = execution.getIdExecution();
        WildScenario dS = this.subScenarioList_tolaunch.get(execId);
        String logPath = conf_translate((String)config_table.get("execution_path"),execution)+"/logs/log.6log" ;
        String phpoutPath = conf_translate((String)config_table.get("archive_path"),execution)+"/"+execId+".html" ;
        file_write(new   File(logPath),this.getSummary(execution));
        try{
            xml_transformWithXsl(dS.get_xslForSummary(),logPath,phpoutPath);
            file_permissions(phpoutPath);
            file_permissions(new File(phpoutPath).getParent());
            file_permissions(logPath);
            file_permissions(new File(logPath).getParent());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Analyse une exécution et renvoie la chaîne de log correspondante
     * @param execution
     * @return
     * @throws Exception
     */
    private String getSummary(ExecutionChainsaw execution) throws Exception {
        WildScenario dS= this.subScenarioList_tolaunch.get(execution.getIdExecution());
        if(dS==null) return null;
        return dS.getSummary();
    }

    /**
     * Nettoyage des service et des exécutions en attente dans la base de données.
     * Supprime les services existants non clôts, supprime les exécutions non terminées, débutées par un autre service.
     */
    private void set_all_done(){
        // Nettoyage des services
        String hqlUpdate_ec = "Update JavaService set id_status = 309 where id_status < 100 and idJavaservice != '"+id_serviceInfo+"'" ;
        Transaction tx = db.beginTransaction();
        db.createQuery( hqlUpdate_ec ).executeUpdate();
        tx.commit();
        // Nettoyage des exécutions
        String hqlUpdate_ee = "Update ExecutionChainsaw set id_status = 309 where id_status < 100 and id_javaservice != '"+id_serviceInfo+"' and id_javaservice is not null" ;
        Transaction txe = db.beginTransaction();
        db.createQuery( hqlUpdate_ee ).executeUpdate();
        txe.commit();
    }

    /**
     * Fonction générique d'estimation et de préparation des ressources disponibles
     * Evaluation des ressources disponibles pour exécution lors du passage de la boucle
     * Enregistrement, au besoin, des ressources disponibles pour suivi.
     * @throws Exception
     */
    private synchronized  void dealWithResources(int turn_nb) throws Exception{
        // Réévaluation à chaque tour
        reeval_runningResources();
        setOccupiedResources();
        long now = date_nowLong();
        // L'enregistrement s'effectue toutes les {system_record_delay} secondes
        if((now-lastDbresourcesUpDate)/1000 > (Integer)config_table.get("system_record_delay")){
            saveOccupiedResources() ;
            lastDbresourcesUpDate = now ;
        }
        // Garbage Collector éventuel
        if((now-lastGCUpDate)/1000 > (Integer)config_table.get("system_gc_delay")){
            System.gc();
            lastGCUpDate = now ;
            this.diceLogger_loop.logEvent("GCActivity");
        }
    }

    /**
     * Fonction générique de traitement des exécutions en cours, selon leur statut d'avancement.
     * La liste des status d'avancement vient de statutsExecutionCases, elle est traitée dans l'ordre
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private synchronized  void dealWithJobs() throws Exception{
        //Fonctions d'exécution des jobs
        for(int statut:statutsExecutionCases)inside_status(statut);
    }

    /**
     * Fonction générique de nettoyage du système
     * @throws Exception
     */
    private synchronized void dealCleaner() throws Exception{
        long now = date_nowLong();
        if((now-lastCleanUpDate)/60000 > (Integer)config_table.get("system_cleaner_jobs_delay")){
            clean_archive();
            clean_files();
            set_all_done();
            lastCleanUpDate = now ;
            this.diceLogger_loop.logEvent("CleanerActivity");
        }
    }

    /**
     * Fonction générique de traitement des exécutions en cours, selon leur statut d'avancement.
     * Détail d'une boucle.
     * @param idStatus
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private synchronized  void inside_status(int idStatus) throws Exception{
        switch(idStatus){
            case 0: inside_status_0(); break;
            case 1: inside_status_1(); break;
            case 2: inside_status_2(); break;
            case 3: inside_status_3(); break;
            case 4: inside_status_4(); break;
            case 5: inside_status_5(); break;
            case 6: inside_status_6(); break;
            case 7: inside_status_7(); break;
            case 100: inside_status_100(); break;
            case 105: inside_status_105(); break;
            case 300: inside_status_300(); break;
            default: break;
        }
    }

    /**
     * Traitement des exécutions répondant au statut :
     * 0    Dépôt non traité    Le dépôt vient d'être déposé. Il n'est pas encore prise en charge par le système.
     */
    private synchronized  void inside_status_0(){
        if(serviceSuspended)return;
        for(ExecutionChainsaw execution:getDbList(db_sub, ExecutionChainsaw.class , "where id_status = 0 or id_status = 201 or id_status = 200 order by date_post", executionsPerRow)){
            if(execution.getStatus().getIdStatus()!=200&&execution.getStatus().getIdStatus()!=201)setIdStatusExecution(execution, 1);
             saveDb(db_sub,execution);
        }
    }

    /**
     * Traitement des exécutions répondant au statut :
     * 1    Dépôt pris en compte    Le dépôt est ajouté à une file d'attente préliminaire, interprétation de la commande XML de scénario.
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private synchronized  void inside_status_1() throws Exception{
        for(ExecutionChainsaw execution:getDbList(db_sub, ExecutionChainsaw.class , "where id_status = 1 or id_status = 201 or id_status = 200 order by date_post", executionsPerRow)){
            try{
                writeScenarioBuild(execution);
                subScenarioList_tolaunch.put(execution.getIdExecution(), new WildScenario(service_DiceModelList, execution, this.connect_dicePg));
                if(execution.getStatus().getIdStatus()!=200&&execution.getStatus().getIdStatus()!=201)setIdStatusExecution(execution, 2);
                if(execution.getStatus().getIdStatus()!=200)check_NeverOk(execution);
            }catch(Exception e){
                e.printStackTrace();
                check_killExecution(execution);
                this.diceLogger_loop.logError("WildScenarioError", "IdExecution : "+execution.getIdExecution()+" - JAVA : "+e.getMessage());
            }
        }
    }

    /**
     * Traitement des exécutions répondant au statut :
     * 2    Tests préliminaires    Plusieurs tests permettant d'initier le traitement sont réalisés : tests de charge, estimation du temps d'exécution, consommation courante de l'utilisateur
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private synchronized  void inside_status_2() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        for(ExecutionChainsaw execution:getDbList(db_sub, ExecutionChainsaw.class , "where id_status = 2 or id_status = 201 or id_status = 200 order by id_status, date_post DESC", executionsPerRow)){
            if(execution.getStatus().getIdStatus()==200||isOk_forRun(execution)){
                reeval_runningResources(execution);
                packers_tolaunch.put(execution.getIdExecution(), new WildExecutionPacker(execution, config_table));
                subFutures_tolaunch.put("Unpack#6D#"+execution.getIdExecution(), taskCompletionService.submit(packers_tolaunch.get(execution.getIdExecution())));
                if(execution.getStatus().getIdStatus()!=200)setIdStatusExecution(execution, 3);
            }
        }
    }

    /**
     * Traitement des exécutions répondant au statut :
     * 3    Dépôt en cours de préparation    Décompression des fichiers.
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private synchronized  void inside_status_3() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException, ClassNotFoundException, InterruptedException, ExecutionException{

        for(ExecutionChainsaw execution:getDbList(db_sub, ExecutionChainsaw.class , "where id_status = 3 or id_status = 200 order by  id_status, date_post DESC", executionsPerRow)){
            try{
                String idPackExecution = "Unpack#6D#"+execution.getIdExecution() ;
                if(subFutures_tolaunch.get(idPackExecution)==null){
                     execution.setStatus(getStatus(308));
                }else if(subFutures_tolaunch.get(idPackExecution).isCancelled()){
                     execution.setStatus(getStatus(308));
                }else if(subFutures_tolaunch.get(idPackExecution).isDone()){
                    if(execution.getStatus().getIdStatus()!=200)execution.setStatus(getStatus(4));
                    // Liste des fichiers par transmission au scénario
                    WildScenario dS = this.subScenarioList_tolaunch.get(execution.getIdExecution());
                    // Mise de l'exécution en erreur si les sources n'ont pas été correctement montées
                    if(subFutures_tolaunch.get(idPackExecution)!=null){
                        List<Map<String, String>> sourcesErrors = dS.buildSources(execution,subFutures_tolaunch.get(idPackExecution).get());
                        if(sourcesErrors!=null&&sourcesErrors.size()>0){
                            execution.setStatus(getStatus(308));
                            writeErrors(execution,sourcesErrors);
                            continue;
                        }
                    }
                    writeOutPacker(execution);
                    writeInfoPacker(execution);
                }else{
                    writeOutPacker(execution);
                    continue;
                }
                freeSubFuture(idPackExecution);
            }catch(Exception e){
                e.printStackTrace();
            }
            saveDb(db_sub,execution);
        }
    }
    
    /**
     * Traitement des exécutions répondant au statut :
     * 4    En attente de traitement    Attente de libération des ressources pour instanciation du scénario.
     * @throws Exception
     */
    private synchronized  void inside_status_4() throws Exception{
        for(ExecutionChainsaw execution:getDbList(db_sub, ExecutionChainsaw.class , "where id_status = 4 or id_status = 200 order by  id_status, date_post DESC", executionsPerRow)){
            if(execution.getStatus().getIdStatus()==200||isOk_forRun_physical(execution)){
                writeScenarioLaunched(execution);
                execution.setDateBegin(date_nowdate());
                subFutures_tolaunch.put(execution.getIdExecution(), taskCompletionService.submit(subScenarioList_tolaunch.get(execution.getIdExecution())));
                setIdStatusExecution(execution, 5);
            }
        }
    }

    /**
     * Traitement des exécutions répondant au statut :
     * 5    En cours de traitement    Le scénario est en cours d'exécution (exécution DICE).
     * @throws Exception
     */
    private synchronized  void inside_status_5() throws Exception{
        long now = date_nowLong();
        if((now-lastStepUpDate)/1000 > (Integer)config_table.get("system_cleaner_stepUpdate_delay")){
            for(ExecutionChainsaw execution:getDbList(db_sub, ExecutionChainsaw.class , "where id_status = 5 order by date_post", executionsPerRow)){
                String idExecution = execution.getIdExecution() ;
                if(subFutures_tolaunch.get(idExecution)==null){
                    execution.setStatus(getStatus(308));
                }else if(subFutures_tolaunch.get(idExecution).isCancelled()){
                    execution.setStatus(getStatus(308));
                }else if(subFutures_tolaunch.get(idExecution).isDone()){
                    execution.setDateEnd(date_nowdate());
                    if(!checkOnError(execution)){
                        execution.setStatus(getStatus(6));
                        try {
                            results_launched.put(idExecution, subFutures_tolaunch.get(idExecution).get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                    if(execution.getStepVisible())writeOut(execution);
                    continue;
                }

                freeSubFuture(idExecution);
                saveDb(db_sub,execution);
            }
            lastStepUpDate = now ;
        }
    }

    private void freeSubFuture(String idK){
        if(subFutures_tolaunch.get(idK)!=null){
            Future<ConcurrentHashMap<String, Object>> valSub = subFutures_tolaunch.get(idK);
            valSub = null;
            subFutures_tolaunch.remove(idK);
        }
    }

    /**
     * Traitement des exécutions répondant au statut :
     * 6    Traité sur DICE    Le traitement DICE est terminé. Récupération des résultats et libération des ressources système.
     * @throws Exception
     */
    private synchronized  void inside_status_6() throws Exception{
        for(ExecutionChainsaw execution:getDbList(db_sub, ExecutionChainsaw.class , "where id_status = 6 order by date_post", executionsPerRow)){
            String idExecution = execution.getIdExecution() ;
            WildScenario dS = this.subScenarioList_tolaunch.get(idExecution);
            writeScenarioFinished(execution);
            dS.saveProducts() ;
            saveExecModels(dS, idExecution);
            prepareSummary(execution);
            /*Boolean joinInput = (Boolean)cast_2Boolean(dS.getInfoAmo("joinInput"));
            if((joinInput!=null&&joinInput&&(new File(dS.getExecTempFolder()+File.separator+"inputs").exists())))file_copy(dS.getExecTempFolder()+File.separator+"inputs", dS.getExecFolder()+File.separator+"inputs");
            */
            subFutures_tolaunch.put("Pack#6D#"+execution.getIdExecution(), taskCompletionService.submit(packers_tolaunch.get(execution.getIdExecution())));
            setIdStatusExecution(execution, 7);
        }
    }

    /**
     * Traitement des exécutions répondant au statut :
     * 7    Archivage des résultats    Compression des jeux de fichiers utilisés et synthèse HTML des résultats.
     * @throws Exception
     */
    private synchronized  void inside_status_7() throws Exception{
        for(ExecutionChainsaw execution:getDbList(db_sub, ExecutionChainsaw.class , "where id_status = 7 order by date_post", executionsPerRow)){
            String idPackExecution = "Pack#6D#"+execution.getIdExecution() ;
            if(subFutures_tolaunch.get(idPackExecution)==null){
                execution.setStatus(getStatus(308));
            }else if(subFutures_tolaunch.get(idPackExecution).isCancelled()){
                execution.setStatus(getStatus(308));
            }else if(subFutures_tolaunch.get(idPackExecution).isDone()){
                execution.setStatus(getStatus(100));
                writeOutPacker(execution);
                writeInfoPacker(execution);
            }else{
                writeOutPacker(execution);
                continue;
            }
            freeSubFuture(idPackExecution);
            packers_tolaunch.remove(execution.getIdExecution());
            saveDb(db_sub,execution);
        }
    }
    
    /**
     * Traitement des exécutions répondant aux statuts :
     * 100    Fin d'exécution
     * @throws Exception
     */
    private synchronized  void inside_status_100() throws Exception{
        for(ExecutionChainsaw execution:getDbList(db_sub, ExecutionChainsaw.class , "where id_status = 100 order by date_post", executionsPerRow))endExecution(execution);
    }

    /**
     * Traitement des exécutions répondant aux statuts :
     * 105    Arrêt forcé
     * @throws Exception
     */
    private synchronized  void inside_status_105() throws Exception{
        for(ExecutionChainsaw execution:getDbList(db_sub, ExecutionChainsaw.class , "where id_status = 105 order by date_post", executionsPerRow)){
            String idExecution = execution.getIdExecution() ;
            if(subFutures_tolaunch.get(idExecution)!=null)subFutures_tolaunch.get(idExecution).cancel(true);
            WildScenario dS= this.subScenarioList_tolaunch.get(idExecution);
            if(dS!=null){
                dS.dropFiles();
                dS.closeConnect();
            }

            deleteDbEntity(db_sub,execution);
            deleteDbEntity(db_sub,getDbEntity(db_sub,ExecutionArchive.class,idExecution));
            for(ExecutionConsumption executionConsumption:getDbList(db_sub, ExecutionConsumption.class , "where id_execution = '"+idExecution+"'", executionsPerRow))deleteDbEntity(db_sub,executionConsumption);
            for(ExecutionInfo executionInfo:getDbList(db_sub, ExecutionInfo.class , "where id_execution = '"+idExecution+"'", executionsPerRow))deleteDbEntity(db_sub,executionInfo);
            deleteDbEntity(db_sub,getDbEntity(db_sub,ExecutionList.class,idExecution));
            subScenarioList_tolaunch.remove(idExecution);
            System.out.println("Removed : "+idExecution);
        }
    }

    /**
     * Traitement des exécutions répondant aux statuts :
     * 300    Exécutions en erreur
     * @throws Exception
     */
    private synchronized  void inside_status_300() throws Exception{
        for(ExecutionChainsaw execution:getDbList(db_sub, ExecutionChainsaw.class , "where id_status >= 300 and id_status < 400 order by date_post", executionsPerRow)){
            writeScenarioFinished(execution);
            endExecution(execution) ;
        }
    }

    private void writeErrors(ExecutionChainsaw execution, List<Map<String, String>> sourcesErrors){
        for(Map<String, String> aMap:sourcesErrors){
            ExecutionInfo executionInfo = new ExecutionInfo();
            executionInfo.setExecutionList(execution.getExecutionList());
            executionInfo.setContents(aMap.get("Message"));
            executionInfo.setAdminLevel(getDbEntity(db_sub, AdminLevel.class, (short)0));
            executionInfo.setCriticity(getDbEntity(db_sub, Criticity.class, (short)0));
            try{saveDb(db_sub,executionInfo);}catch(Exception e){e.printStackTrace();}
        }
    }

    /**
     * Déplace les résultats d'une exécution depuis la chainsaw vers les archives
     * @param execution
     * @param dateMail
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws XPathExpressionException
     * @throws DOMException
     */
    private void moveToArchive(ExecutionChainsaw execution, Date dateMail){
        try{
            String idExecution = execution.getIdExecution() ;
            String thisName = (this.subScenarioList_tolaunch.keySet().contains(idExecution))?subScenarioList_tolaunch.get(idExecution).getName():xml_getFirstValue(cast_String2Node(execution.getInfoIn()), "./@ident");

            ExecutionArchive arch = new ExecutionArchive();
            arch.setDateBegin(execution.getDateBegin());
            arch.setDateEnd(execution.getDateEnd());
            arch.setDatePost(execution.getDatePost());
            arch.setExecutionList(execution.getExecutionList());
            arch.setIdExecution(idExecution);
            arch.setUser(execution.getUser());
            arch.setScenarioName(thisName);

            if(this.subScenarioList_tolaunch.keySet().contains(idExecution)){
                WildScenario dS = subScenarioList_tolaunch.get(idExecution);
                arch.setJavaService(execution.getJavaService());
                arch.setStatus(execution.getStatus());
                arch.setDateArchive(date_nowdate());
                if(dateMail!=null)arch.setDateMail(date_nowdate());
                if(dS.getSummary()!=null)arch.setSummary(dS.getSummary());
                if(dS.getpgArrayProducts()!=null)arch.setProducts(dS.getpgArrayProducts());
            }
            if(!saveDb(db_sub,arch))deleteDbEntity(db_sub,arch);
        }catch(Exception e){
            e.printStackTrace();
            this.diceLogger_loop.logError(e);
        }
    }

    /**
     * Termine une exécution :
     * déplacement vers archive, envoi de mail, suppression des enregistrements qui ne seront plus utilisés...
     * @param execution
     * @throws Exception
     */
    private void endExecution(ExecutionChainsaw execution) throws Exception{
        String idExecution = execution.getIdExecution();
        WildScenario dS= this.subScenarioList_tolaunch.get(idExecution);
        Date dateMail = date_null() ;
        if(dS!=null){
            Boolean sendMailInfo = (Boolean)cast_2Boolean(dS.getInfoAmo("sendMail"));
            if((sendMailInfo!=null&&sendMailInfo)||(execution.getWithMail()!=null&&execution.getWithMail())){
                diceLogger_loop.sendMailReport(execution,dS);
                dateMail = date_nowdate();
            }
        }else{
            Boolean sendMailInfo = (Boolean)cast_2Boolean(xml_getFirstValue(cast_String2Node(execution.getInfoIn()), "./@sendMail"));
            if((sendMailInfo!=null&&sendMailInfo)||(execution.getWithMail()!=null&&execution.getWithMail())){
                diceLogger_loop.sendMailReport(execution);
                dateMail = date_nowdate();
            }
        }
        moveToArchive(execution, dateMail);
        if(dS!=null){
            dS.dropFiles();
            dS.closeConnect();
        }
        deleteDbEntity(db_sub,execution);
        clearExecution(idExecution);
    }

    private void clearExecution(String idExecution){
        WildScenario dS = subScenarioList_tolaunch.get(idExecution);
        if(dS!=null)dS.freeThread();
        clearIdFromMap(subScenarioList_tolaunch,idExecution);
        clearIdFromMap(packers_tolaunch,idExecution);
        clearIdFromMap(subFutures_tolaunch,idExecution);
        clearIdFromMap(results_launched,idExecution);
        String pidExecution = "Pack#6D#"+idExecution;
        clearIdFromMap(subScenarioList_tolaunch,pidExecution);
        clearIdFromMap(packers_tolaunch,pidExecution);
        clearIdFromMap(subFutures_tolaunch,pidExecution);
        clearIdFromMap(results_launched,pidExecution);
        pidExecution = "Unpack#6D#"+idExecution;
        clearIdFromMap(subScenarioList_tolaunch,pidExecution);
        clearIdFromMap(packers_tolaunch,pidExecution);
        clearIdFromMap(subFutures_tolaunch,pidExecution);
        clearIdFromMap(results_launched,pidExecution);
        if((Boolean)config_table.get("system_auto_gc"))System.gc();
    }

    private long showsize(Serializable ser) {
        //Serializable ser = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(ser);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.size();
    }

    private <T,V> void clearIdFromMap(HashMap<T, V> toMod, T key){
        if(toMod!=null&&toMod.get(key)!=null){
            try{toMod.put(key,null);}catch(Exception e){}
            V d_o = toMod.get(key);
            d_o = null ;
            toMod.remove(key);
        }
    }

    private <T,V> void clearIdFromMap(ConcurrentHashMap<T, V> toMod, T key){
        if(toMod!=null&&toMod.get(key)!=null){
            try{toMod.put(key,null);}catch(Exception e){}
            V d_o = toMod.get(key);
            d_o = null ;
            toMod.remove(key);
        }
    }

    /**
     * Evalue pour une exécution, la capacité de traitement par le service
     * @param execution    Exécution à évaluer
     * @return
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private Boolean isOk_forRun(ExecutionChainsaw execution) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        if(!isOk_forRun_theo(execution))return false;
        if(!isOk_forRun_physical(execution))return false;
        if(!isOk_forRun_consumption(execution)&&execution.getStatus().getIdStatus()!=201)return false;
        if(!isOk_forRun_maxDiceTasks())return false;
        return true ;
    }

    /**
     * Evalue pour une exécution, la capacité de traitement par le service, das l'absolu
     * @param execution    Exécution à évaluer
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws XPathExpressionException
     */
    private void check_NeverOk(ExecutionChainsaw execution) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        WildScenario wildScenario = subScenarioList_tolaunch.get(execution.getIdExecution());
        for(String resource : resources)if(availableTheoInitResources.get(resource) < wildScenario.getResources().get(resource))setIdStatusExecution(execution, 303);
    }

    private void check_killExecution(ExecutionChainsaw execution) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        setIdStatusExecution(execution, 300);
    }

    /**
     * Evalue pour une exécution, la capacité de traitement par le service, au regard du nombre maximal de tâche autorisée par la configuration
     * @param execution    Exécution à évaluer
     * @return
     */
    private Boolean isOk_forRun_maxDiceTasks(){
        List<ExecutionChainsaw> cu_l_c = getDbList(db_sub, ExecutionChainsaw.class , "where id_status > 2 ");
        if(cu_l_c.size()>this.executionsPerRow)return false;
        return true;
    }

    /**
     * Evalue pour une exécution, la capacité de traitement par le service, au regard de la déclaration a priori des charges pour chaque scénario
     * @param execution    Exécution à évaluer
     * @return
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private Boolean isOk_forRun_theo(ExecutionChainsaw execution) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        WildScenario wildScenario = subScenarioList_tolaunch.get(execution.getIdExecution());
        for(String resource : resources)if(availableTheoResources.get(resource) < wildScenario.getResources().get(resource))return false ;
        return true ;
    }

    /**
     * Evalue pour une exécution, la capacité de traitement par le service, au regard des ressources physiques disponibles pour chaque scénario
     * @param execution    Exécution à évaluer
     * @return
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private Boolean isOk_forRun_physical(ExecutionChainsaw execution) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        /*WildScenario wildScenario = subScenarioList_tolaunch.get(execution.getIdExecution());
        for(String resource : resources)if(availablePhysicalResources.get(resource) < wildScenario.getResources().get(resource))return false ;*/
        return true ;
    }

    /**
     * Evalue pour une exécution, la capacité de traitement par le service, au regard de la consommation en cours par l'utilisateur
     * @param execution    Exécution à évaluer
     * @param execution
     * @return
     */
    private Boolean isOk_forRun_consumption(ExecutionChainsaw execution){
        List<ExecutionChainsaw> cu_l_c = getDbList(db_sub, ExecutionChainsaw.class , "where id_user = '"+execution.getUser().getIdUser()+"'") ;
        if(cu_l_c==null) return true ;
        if(cu_l_c.size()>=this.executionsPerUser)return false;
        return true ;
    }

    /**
     * Actualise le status d'une exécution
     * @param execution    Exécution à actualiser
     * @param idStatus    Nouvelle valeur de statut
     */
    private void setIdStatusExecution(ExecutionChainsaw execution, int idStatus){
        execution.setStatus(getStatus(idStatus));
        saveDb(db_sub,execution);
    }

    /**
     * Initialisation de la connexion HIBERNATE
     */
    private void initDbSub(){
        long now = date_nowLong();
        if((now-lastDbInitUpDate) > (Integer)config_table.get("system_cleaner_pgConnect_delay")){
            if(db_sub!=null&&db_sub.isOpen())db_sub.close();
            db_sub = connect_dicePg.openSession();
            lastDbInitUpDate = now ;
        }
    }

    /**
     * Renseigne si un service est déjà en cours d'exécution.
     * Créé un fichier de contrôle "locked.6d" à la racine de l'exécution.
     * Ce fichier est systématiquement détruit en cas d'interuption sauf destruction par le root de la JVM.
     * @return
     * @throws IOException
     */
    private Boolean isServiceRunning() throws IOException{
        File controlFile = new File("locked.6d");
        if(!controlFile.exists()||controlFile.length()>0){
            setServiceRunning();
            return false ;
        }
        System.out.println(config_table.get("alreadyrunning_message"));
        System.exit(0);
        return true ;
    }

    /**
     * Créé un fichier de contrôle "locked.6d" à la racine de l'exécution, pour renseigner si un service est déjà lancé.
     * Ce fichier est systématiquement détruit en cas d'interuption sauf destruction par le root de la JVM.
     * @throws IOException
     */
    private void setServiceRunning() throws IOException{
        File controlFile = new File("locked.6d");
        if(!controlFile.exists()){
            controlFile.createNewFile();
            controlFile.deleteOnExit();
        }else{
            controlFile.delete() ;
        }
    }

    /**
     * Enregistrement des ressources d'occupation système dans la base de données.
     * Intervient après appel de setOccupiedResources().
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private void saveOccupiedResources() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        // Sauvegarde pour chacune des exécutions (évaluation des max. de charge)
        Set<String> kS = subScenarioList_resources.keySet();
        List<String> ls = new ArrayList<String>();
        for(String k:kS)ls.add(k);
        for(String k:ls){
            WildScenario dS = subScenarioList_tolaunch.get(k);
            if(dS == null){
                subScenarioList_resources.remove(k);
                continue ;
            }
            LinkedHashMap<String, Integer> toRet = subScenarioList_resources.get(k);
            ExecutionConsumption eC = getDbEntity(db_sub, ExecutionConsumption.class, k) ;
            if(eC==null){
                eC = new ExecutionConsumption(dS.getExecution().getExecutionList(),
                dS.getMinROM(),dS.getMinRAM(),dS.getMinCPU(),
                toRet.get("minROM"),toRet.get("minRAM"),toRet.get("minCPU"));
            }else{
                eC.setEstimatedCpu(toRet.get("minCPU"));
                eC.setEstimatedRam(toRet.get("minRAM"));
                eC.setEstimatedRom(toRet.get("minROM"));
                eC.setMaxAllocatedCpu(dS.getMinCPU());
                eC.setMaxAllocatedRam(dS.getMinRAM());
                eC.setMaxAllocatedRom(dS.getMinROM());
            }
            saveDb(db_sub,eC);
        }

        // Sauvegarde pour le service complet (évaluation des max. de charge)
        JavaConsumptionId jcId = new JavaConsumptionId(new Date(), id_serviceInfo);
        JavaConsumption jc = new JavaConsumption(jcId, javaService/*getDbEntity(db_sub, JavaService.class, id_serviceInfo)*/,
                 availableTheoResources.get("minROM"),availableTheoResources.get("minRAM"), availableTheoResources.get("minCPU"),
                 availablePhysicalResources.get("minROM"),availablePhysicalResources.get("minRAM"), availablePhysicalResources.get("minCPU"),
                 this.subScenarioList_tolaunch.size());
        saveDb(db_sub,jc);
    }

    /**
     * Réévaluation des ressources allouées à l'ensemble des exécutions en cours, pour évaluation de la disponibilité système.
     * Alimente les variables globales availableTheoResources et availablePhysicalResources, à partir de la liste des scénarios en cours d'exécutio (subScenarioList_tolaunch).
     * availableTheoResources : évaluation a priori, par scénario
     * availablePhysicalResources : selon la charge physique restante
     * On ne calcule les disponibilités max. qu'une fois, dans availableTheoInitResources.
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ClassNotFoundException
     * @throws DOMException
     */
    private void reeval_runningResources() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException, ClassNotFoundException, DOMException{
        availableTheoResources = new LinkedHashMap<String, Integer>() ;
        availablePhysicalResources = new LinkedHashMap<String, Integer>() ;
        if(availableTheoInitResources==null){
            availableTheoInitResources = new LinkedHashMap<String, Integer>();
            for(String resource : resources) availableTheoInitResources.put(resource, (Integer) config_table.get("max_estimated_"+resource));
        }
        for(String resource : resources) availableTheoResources.put(resource, availableTheoInitResources.get(resource));
        if(subScenarioList_tolaunch==null)return;
        Set<String> ks = subScenarioList_tolaunch.keySet() ;
        for(String k:ks)reeval_runningResources(subScenarioList_tolaunch.get(k));
        Long[] seAPhysical = system_physicalAvailable((Integer)config_table.get("system_max_clock"));
        availablePhysicalResources.put("minRAM", seAPhysical[0].intValue());
        availablePhysicalResources.put("minROM", seAPhysical[1].intValue());
        availablePhysicalResources.put("minCPU", seAPhysical[2].intValue());
    }

    /**
     * Réévaluation des ressources allouées à un scénario en cours, pour évaluation de la disponibilité système.
     * @param wildScenario    Scénarios à évaluer
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private void reeval_runningResources(WildScenario wildScenario) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        for(String resource : resources){
            Integer newResourceValue = availableTheoResources.get(resource) - wildScenario.getResources().get(resource) ;
            availableTheoResources.put(resource, newResourceValue);
        }
    }

    /**
     * Réévaluation des ressources allouées à une exécution en cours, pour évaluation de la disponibilité système.
     * @param diceScenario    Exécution à évaluer
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private void reeval_runningResources(ExecutionChainsaw execution) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        reeval_runningResources(subScenarioList_tolaunch.get(execution.getIdExecution()));
    }

    /**
     * Evalue les ressources maximales occupées par le thread de l'ensemble des exécutions en cours, stocke les résultats dans une variable subScenarioList_resources.
     * @throws AttributeNotFoundException
     * @throws InstanceNotFoundException
     * @throws MBeanException
     * @throws ReflectionException
     * @throws MalformedObjectNameException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private void setOccupiedResources() throws AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException, MalformedObjectNameException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        for(String k:subScenarioList_tolaunch.keySet())setOccupiedResources(subScenarioList_tolaunch.get(k));
    }

    /**
     * Evalue les ressources maximales occupées par un scénario en cours, stocke les résultats dans une variable subScenarioList_resources.
     * @param wildScenario    Scénario à évaluer
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private void setOccupiedResources(WildScenario wildScenario) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        if(subScenarioList_resources==null)subScenarioList_resources = new LinkedHashMap<String, LinkedHashMap<String, Integer>>();
        String threadName = wildScenario.getThreadName();
        LinkedHashMap<String, Integer> toRet = new LinkedHashMap<String, Integer>();
        for(long i:threadBean.getAllThreadIds()){
            if(threadBean.getThreadInfo(i).getThreadName().equals(threadName)){
                // Evaluation de la RAM consommée
                LinkedHashMap<String,Integer> sL = subScenarioList_resources.get(wildScenario.getExecutionId()) ;
                Integer maxRAM = (sL!=null)?sL.get("minRAM"):0;
                Integer newRam = (int) threadBean.getThreadAllocatedBytes(i);
                if(maxRAM != null && maxRAM != 0 && maxRAM > newRam) newRam = maxRAM ;
                toRet.put("minRAM", newRam);
                // Evaluation du délais de réponse CPU consommé
                Integer maxCPU = (sL!=null)?sL.get("minCPU"):0;
                Integer newCpu = (int) threadBean.getThreadCpuTime(i);
                if(maxCPU != null && maxCPU != 0 && maxCPU > newCpu) newCpu = maxCPU;
                toRet.put("minCPU", maxCPU);
                // Evaluation de la taille du répertoire de travail
                Integer maxROM = (sL!=null)?sL.get("minROM"):0;
                Integer newRom = (wildScenario.getExecTempFolder()==null)?0:(int) file_size(new File(wildScenario.getExecTempFolder()));
                if(maxROM != null && maxROM != 0 && maxROM > newRom) newRom = maxROM ;
                toRet.put("minROM", newRom);
                subScenarioList_resources.put(wildScenario.getExecutionId(), toRet);
                return ;
            }
        }
    }
    
    /**
     * Ecriture du suivi de l'activité du service en archive
     * @param message Message à afficher (1 : texte, 2 : niveau d'admin pour accès, 3 : criticité
     */
    private void writeServiceInfo(Object[] thisServiceInfos){
        if(thisServiceInfos==null)return;
        JavaServiceInfo javaInfo = new JavaServiceInfo();
        javaInfo.setJavaService(javaService);
        javaInfo.setAdminLevel(getDbEntity(db_sub,AdminLevel.class,(short)thisServiceInfos[1]));
        javaInfo.setCriticity(getDbEntity(db_sub,Criticity.class,(short)thisServiceInfos[2]));
        javaInfo.setContents((String)thisServiceInfos[0]);
        saveDb(db_sub,javaInfo);
    }

    /**
     * Ecriture du suivi de l'activité du service en archive , spécifique à un status particulier rencontré
     * @param idStatus
     */
    private void writeServiceInfo(short idStatus){
        if(readServiceProperty){
            String msgXml =
                    "<Message "
                    + "criticity = '4' "
                    + "adminLevel = '4'"
                    + "time = '"+date_now()+"'>"+
                    (String) config_table.get("status"+idStatus+"_javaservicemsg")
                    +"</Message>" ;
            Object[] msg = {msgXml,(short)4};
            writeServiceInfo(msg);
            readServiceProperty = false ;
        }
    }

    /**
     * Ecriture des informations en archive
     * @param execution    Exécution qui fournit le message à afficher
     */
    /*private void writeInfo(ExecutionChainsaw execution){
        writeInfo(execution, subScenarioList_tolaunch.get(execution.getIdExecution() ).getInfos());
    }*/

    /**
     * Ecriture des informations en archive
     * @param execution    Exécution qui fournit le message à afficher
     * @param thisScenarioInfos    Message à afficher (1 : texte, 2 : niveau d'admin pour accès, 3 : criticité
     */
    private void writeInfo(ExecutionChainsaw execution, Object[] thisScenarioInfos){
        if(thisScenarioInfos==null)return;
        ExecutionInfo executionInfo = new ExecutionInfo();
        executionInfo.setExecutionList(execution.getExecutionList());
        executionInfo.setAdminLevel(getDbEntity(db_sub,AdminLevel.class,(short)thisScenarioInfos[1]));
        executionInfo.setCriticity(getDbEntity(db_sub,Criticity.class,(short)thisScenarioInfos[2]));
        executionInfo.setContents((String)thisScenarioInfos[0]);
        try{saveDb(db_sub,executionInfo);}catch(Exception e){}
    }

    /**
     * Ecriture des informations pour l'affichage utilisateur
     * @param execution    Exécution qui fournit le message à afficher
     * @throws Exception
     */
    private void writeOut(ExecutionChainsaw execution) throws Exception{
        String eltReport = "";
        List<Object[]> rt = subScenarioList_tolaunch.get(execution.getIdExecution()).getStep() ;
        Integer i = 0;
        Boolean inComponent = false ;
        Boolean inObject = false ;
        for(Object[] r:rt){
            i++;
            switch((String)r[0]){
            case "WildScenario" :
                eltReport += "<WildScenario ident = '"+r[1]+"' progressLower = '"+((Object[])r[2])[0]+"' progressUpper = '"+((Object[])r[2])[1]+"' "+ "currentSubElement ='"+r[4]+"' totalSubElements = '"+r[5]+"'>"+cast_xmlEscape(r[3]);
                break;
            case "WildComponent" :
                if(inComponent)eltReport +="</WildComponent>";
                eltReport += "<WildComponent ident = '"+r[1]+"' progressLower = '"+((Object[])r[2])[0]+"' progressUpper = '"+((Object[])r[2])[1]+"' currentSubElement ='"+r[4]+"' totalSubElements = '"+r[5]+"'>"+cast_xmlEscape(r[3]);
                inComponent = true ;
                break;
            case "WildObject" :
                if(inObject)eltReport +="</WildObject>";
                eltReport += "<WildObject ident = '"+r[1]+"' currentSubElement ='"+r[4]+"' totalSubElements = '"+r[5]+"'>";
                if(r[3]!=null)eltReport += cast_xmlEscape(r[3]);
                //eltReport += "</WildObject>";
                inObject = true ;
                break;
            case "WildMethod" :
                eltReport += "<WildMethod ident = '"+r[1]+"' ";
                if(r[2]!=null)eltReport += "progressLower = '"+((Object[])r[2])[0]+"' progressUpper = '"+((Object[])r[2])[1]+"'";
                eltReport += ">";
                if(r[3]!=null)eltReport += cast_xmlEscape(r[3]);
                eltReport += "</WildMethod>";
                break;
            }
        }
        if(inObject)eltReport +="</WildObject>";
        if(inComponent)eltReport +="</WildComponent>";
        if(eltReport.length()>0)eltReport += "</WildScenario>";
        else eltReport = null;
        if((Boolean) this.config_table.get("log_verbose_showSteps"))this.diceLogger_loop.logEvent("UserMessage",eltReport);
        writeOut(execution, new Object[]{eltReport,(short)4,(short)4});
    }

    /**
     * Ecriture des informations pour l'affichage utilisateur
     * @param execution    Exécution qui fournit le message à afficher
     * @param thisScenarioInfos    Message à afficher (1 : texte, 2 : criticité, 3 : niveau d'admin pour accès
     */
    private void writeOut(ExecutionChainsaw execution, Object[] thisScenarioInfos){
        if(thisScenarioInfos==null)return;
        execution.setInfoOut(
                "<Message criticity = '"+(short)thisScenarioInfos[1]+"' adminLevel = '"+(short)thisScenarioInfos[2]+"' time = '"+date_now()+"'>"+
                (String)thisScenarioInfos[0]+
                "</Message>"
                );
        saveDb(db_sub,execution);
    }

    /**
     * ecriture de l'information du lancement d'un scénario
     * @param execution    Exécution renseignée
     * @throws Exception
     */
    private void writeScenarioLaunched(ExecutionChainsaw execution) throws Exception{
        Object[] rens = {((String) config_table.get("scenario_dice_javamsg")).replaceAll("\\{S\\}",subScenarioList_tolaunch.get(execution.getIdExecution()).getName())
                ,(short)0,(short)0} ;
        this.diceLogger_loop.logEvent("NewScenarioLaunched", execution.getIdExecution() + " : " + this.subScenarioList_tolaunch.get(execution.getIdExecution()).getName());
        writeInfo(execution, rens);
    }

    /**
     * ecriture de l'information de la construction d'un scénario
     * @param execution    Exécution renseignée
     * @throws Exception
     */
    private void writeScenarioBuild(ExecutionChainsaw execution) throws Exception{
        Object[] rens = {((String) config_table.get("execution_dice_javamsg")).replaceAll("\\{E\\}",execution.getIdExecution())
                ,(short)0,(short)0} ;
        this.diceLogger_loop.logEvent("NewScenarioBuild", execution.getIdExecution());
        writeInfo(execution, rens);
    }

    /**
     * ecriture d'une erreur sur un scénario
     * @param execution    Exécution renseignée
     */
    private void writeScenarioError(ExecutionChainsaw execution, Map<String,String> error){
        if(error != null){
            Object[] rens = {error.get("Message")+" {"+cast_xmlEscape(error.get("Class")+" : "+error.get("JavaMessage"))+"}",(short)0,(short)0} ;
            writeInfo(execution, rens);
        }else{
            Object[] rens = {"Unknown error occured while initializing. Check out for type. {Initializer}",(short)0,(short)0} ;
            writeInfo(execution, rens);
        }
    }

    /**
     * Ecriture de l'information de la fin d'exécution d'un scénario
     * @param execution    Exécution renseignée
     * @throws Exception
     */
    private void writeScenarioFinished(ExecutionChainsaw execution) throws Exception{
        String idExecution = execution.getIdExecution();
        if(subScenarioList_tolaunch.keySet().contains(idExecution)){
            Object[] rens = {((String) config_table.get("scenario_dice_javasuccessmsg")).replaceAll("\\{S\\}",subScenarioList_tolaunch.get(idExecution).getName()),(short)0,(short)0} ;
            this.diceLogger_loop.logEvent("ScenarioEnded", execution.getIdExecution() + " : " + this.subScenarioList_tolaunch.get(execution.getIdExecution()).getName());
            writeInfo(execution, rens);
        }
    }

    /**
     * Log de réalisation d'une compression ou d'une décompression pour archive
     * @param execution    Exécution renseignée
     */
    private void writeInfoPacker(ExecutionChainsaw execution){
        writeInfo(execution, this.packers_tolaunch.get(execution.getIdExecution() ).getInfos());
    }

    /**
     * Suivi d'avancement d'une compression ou d'une décompression
     * @param execution    Exécution renseignée
     */
    private void writeOutPacker(ExecutionChainsaw execution){
        writeOut(execution, this.packers_tolaunch.get(execution.getIdExecution() ).getOut());
    }

    /**
     * Evalue si le service doit continuer à la fin d'une boucle.
     * Le statut du service est consulté :
     * 102    Terminé    Fin d'exécution (service).
     * 104    Arrêt demandé    Demande de l'arrêt (DICE ne prend plus de boucle en compte).
     * 105    Arrêt forcé    Demande de l'arrêt (DICE coupe les travaux en cours).
     * @return
     */
    private Boolean keepOnRollin(){
        Boolean keepOnRollin = true ;
        short javaServiceStatus  = ((JavaService) getDbEntity(db_sub,JavaService.class,id_serviceInfo)).getStatus().getIdStatus() ;
        switch (javaServiceStatus) {
            case 102 :     keepOnRollin = false ; break;
            case 104 :     keepOnRollin = false ; break;
            default :     keepOnRollin = true; break;
        }
        return keepOnRollin ;
    }

    /**
     * Création des modèles DICE
     * Crée et stocke un WildModelList, ce modèle est appelé par l'ensemble des scénarios pour ne pas être rechargé depuis le XML.
     * @throws Exception
     */
    public void flushModel() throws Exception{
        writeLog("FlushAttempt");
        try {
           service_DiceModelList = new WildModelList(this.diceLogger_loop, this.connect_dicePg);
        } catch (Exception e) {
            this.diceLogger_loop.logEvent("FlushFailure");
            e.printStackTrace();
        }
        writeLog("FlushSuccess");
    }

    /**
     * Ecriture de log
     * @param Source    Information de provenance du wrapper
     * @throws Exception
     */
    private void writeLog(String Source) throws Exception{
        diceLogger_loop.logEvent(Source);
    }

    /**
     * Récupération de l'identifiant utilisateur
     * @return    identifiant utilisateur
     */
    public String getUserId() {
        return this.id_userServiceInfo;
    }

    /**
     * Récupération de l'identifiant service
     * @return    identifiant service
     */
    public String getServiceId() {
        return this.id_serviceInfo;
    }

    /**
     * Vérification de l'état en erreur ou non de l'exécution
     * Si en erreur, passe le statut à 302
     * Log l'erreur
     * @param execution    Exécution à vérifier
     * @return    true si en erreur, false si pas d'erreur
     */
    public Boolean checkOnError(ExecutionChainsaw execution){
        String idExecution = execution.getIdExecution();
        WildScenario dS = subScenarioList_tolaunch.get(idExecution);
        if(dS.isOnError()){
            execution.setStatus(getStatus(302));
            writeScenarioError(execution, dS.getError());
            return true ;
        }
        return false ;
    }

    /**
     * Récupération des messages d'erreur associés à l'exécution (pourquoi l'exécutions'est arrêté ?)
     * @param execution    Exécution à vérifier
     * @return    LMap contenant la description de l'erreur
     */
    public Map<String,String> getError(ExecutionChainsaw execution){
        String idExecution = execution.getIdExecution();
        WildScenario dS = subScenarioList_tolaunch.get(idExecution);
        if(!dS.isOnError()) return null ;
        return dS.getError();
    }

    /**
     * Enregistrement des modèlesutilisés pour l'exécution
     * @param dS    Scénario exécuté
     * @param idExecution    Identifiant de l'exécution
     */
    private void saveExecModels(WildScenario dS, String idExecution){
        for(String modelName:dS.getModelsList()){
            if(!modelName.equals("scenarios")&&!modelName.equals("components")){
                ModelsExecution me = null ;
                Model model = getDbEntityWhere(db_sub,Model.class,"where lower(short_name) = '"+modelName+"' order by date_creation desc");
                if(model!=null) {
                    me = new ModelsExecution(new ModelsExecutionId(Long.valueOf(model.getIdModel()), idExecution));
                    saveDb(db_sub,me);
                }
            }
        }
    }
}