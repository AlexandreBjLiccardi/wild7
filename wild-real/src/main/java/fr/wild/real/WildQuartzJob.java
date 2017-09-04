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
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.common.IoCommons;
import static fr.wild.common.IoFileSystem.file_sha1;
import static fr.wild.common.IoCommons.date_nowLong;
import fr.wild.common.IoFileSystem;
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

import static fr.wild.real.WildWebService.PROP_REQUEST_ENDPOINT;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.Reference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.sis.util.ObjectConverters;
import org.geotoolkit.util.DomUtilities;
import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.UnableToInterruptJobException;
import org.quartz.impl.JobDetailImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// Dépendances, bibliothèques JAVA par exemple.


/**
 * Code généré automatiquement par l'outil Wild
 * Classe d'exécution d'un job Quartz
 */
public abstract class WildQuartzJob extends WildClass{
	
	/**
	 * Timer utilisé pour arréter les job apres le temps imparti.
	 */
	private static final Timer TIMER = new Timer();

	/**
	 * Durée maximale d'exécution, en secondes<br>
	 * Type : Integer<br>
	 */
	public static final String PROP_JOB_MAXDURATION = "jobConfiguration.maxDuration";
	/**
	 * Période entre deux exécutions, en secondes<br>
	 * Type : Integer<br>
	 */
	public static final String PROP_JOB_FREQUENCY = "jobConfiguration.frequency";
	/**
	 * Le précédent job est  tué s'il existe une instance lors du lancement<br>
	 * Type : Boolean<br>
	 */
	public static final String PROP_JOB_KILLIFTWICE = "jobConfiguration.killIfTwice";
	/**
	 * Nom du type de service JAVA utilisé<br>
	 * Type : String<br>
	 */
	public static final String PROP_JOB_JAVACLASS= "jobConfiguration.javaClass";
	/**
	 * Chemin du script shell à exécuter<br>
	 * Type : String<br>
	 */
	public static final String PROP_JOB_SHELL = "jobConfiguration.shell";

	// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildQuartzJob(){}

	// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

	// VARIABLES GLOBALES ("Fields") transmises par les constructeurs


	// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***
	protected String id;
	protected String id_init = null;
	protected Integer frequency;
	protected Integer maxDuration;
	protected String parametersXmlFile;
	protected final Map<String,Object> jobParametersMap = new HashMap<>();
	protected final Map<String,Object> serviceParametersMap = new HashMap<>();

	protected WildJobDetail detail;
	protected WildQuartzJobList wildList;

	//derniere execution
	private final AtomicReference<Boolean> isDone = new AtomicReference<>(null);
	protected final Map<String,Object> executionInfos = new ConcurrentHashMap<>();
	private Boolean interrupted = false;
	protected String sha1 ;
	
	
	protected Integer n_red = 0 ;
	
	
	public void incr_red(){
		n_red++;
		if(id_init==null)id_init = id ;
		id=id_init+"_"+n_red;
		this.jobParametersMap.put("behavior.outputFile", 
				Collections.singletonMap("value",
				((String)((Map)this.serviceParametersMap.get("behavior.outputFile#quartz")).get("value")).replaceAll(id_init,id)
				)
				);	
		this.serviceParametersMap.put("behavior.outputFile", 
				Collections.singletonMap("value",
				((String)((Map)this.serviceParametersMap.get("behavior.outputFile#quartz")).get("value")).replaceAll(id_init,id)
				)
				);	
	}
	
	
	public void setInterrupted(){
		interrupted = true ;
		executionInfos.put("status", 3);
	}
	// CE CONSTRUCTEUR DOIT ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Fonction d'initialisation, commune à tous les constructeurs.
	 * "Constructeur unique"
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_parametersXmlFile	Paramètres sous forme de fichier XML dont on donne le lien{}
	 */
	protected void WILD_initialize_WildQuartzJob(
		WildObject i_WILD_dObject,
		String i_parametersXmlFile
			) throws Exception {

		// Amorce de la classe
		// Initialisation de la classe d'objet selon le schéma Wild
		WILD_dObject = i_WILD_dObject ;
		WILD_initialize_WildClass(i_WILD_dObject/*** Paramètres à actualiser ***/);
		// Préparation des variables d'invocation (considérées comme champs globaux)

		// Mode try de récupération des erreurs pour log
		try{
			//lecture des parametres
			Element doc = IoCommons.xml_getDocument(i_parametersXmlFile).getDocumentElement();
			sha1 = file_sha1(i_parametersXmlFile);
			parametersXmlFile = i_parametersXmlFile ;
			jobParametersMap.putAll(IoCommons.param_readParameters(DomUtilities.firstElement(doc, "configuration"),null,null));
			serviceParametersMap.putAll(IoCommons.param_readParameters(DomUtilities.firstElement(doc, "service"),null,null));
			
			executionInfos.put("status", 0);
		}catch(Exception e){
			this.WILD_logException(e);
		}
	}

	// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Constructeur, appelle nécessairement WILD_initialize()
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_parametersXmlFile	Paramètres sous forme de fichier XML dont on donne le lien{}
	 */
	public WildQuartzJob(
		WildObject i_WILD_dObject,
		String i_parametersXmlFile
			) throws Exception{
		this.WILD_initialize_WildQuartzJob(i_WILD_dObject,i_parametersXmlFile);
	}

	/**
	 * 
	 * @return Détail quartz du job
	 */
	public JobDetail getJobDetail() {
		return detail;
	}

	/**
	 * Définition d'une durée maximale d'exécution, pour un job{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_newMaxDuration	Nouvelle durée maximale en secondes{}
	 * @return	{}
	 */
	public Integer setMaxDuration (
		Integer i_newMaxDuration
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Affectation de la variable
			this.WILD_setStep(); // Ne pas modifier
			jobParametersMap.put(PROP_JOB_MAXDURATION, i_newMaxDuration);
			maxDuration = i_newMaxDuration;

			//	Etape	"2" : poids relatif de 10, Retour de la variable
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}

		return maxDuration ; // Ne pas modifier
	}

	/**
	 * Définition de la fréquence d'exécution du job, si null exécution immédiate et one shot.{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_newLength	Nouvelle période en secondes{}
	 * @return	{}
	 */
	public Integer setFrequency (
		Integer i_newLength
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Transmission des variables
			this.WILD_setStep(); // Ne pas modifier
			jobParametersMap.put(PROP_JOB_FREQUENCY, i_newLength);
			frequency = i_newLength;

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}

		return frequency ; // Ne pas modifier
	}

	/**
	 * Récupération de l'identifiant unique du job{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public String getId ()throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		String WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Affectation de la variable
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"2" : poids relatif de 10, Retour de la variable
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}

		return id; // Ne pas modifier
	}
	/**
	 * Setter de l'identifiant unique
	 * @param newId
	 */
	public void DVP_setId(String newId){
		id = newId ;
		detail = new WildJobDetail();
		frequency = DEV_getParameterValue(PROP_JOB_FREQUENCY, null, null, Integer.class);
		maxDuration = DEV_getParameterValue(PROP_JOB_MAXDURATION, null, null, Integer.class);
		jobParametersMap.put("behavior.outputFile", Collections.singletonMap("value", (Object)(this.WILD_dObject.getDiceModelList().getProperty("qWorkspace_path")+"/"+this.WILD_dObject.getDiceModelList().getProperty("qExec_path")+"/"+id)));
		DVP_preparePathes();
		serviceParametersMap.put("behavior.uniqId", Collections.singletonMap("value", id));
		final String fullPath = this.WILD_dObject.getDiceModelList().getProperty("qWorkspace_path")+"/"+this.WILD_dObject.getDiceModelList().getProperty("qExec_path")+"/"+id ;
		serviceParametersMap.put("behavior.outputFile", Collections.singletonMap("value", fullPath));
		serviceParametersMap.put("behavior.outputFile#quartz", Collections.singletonMap("value", fullPath));
		executionInfos.put(this.PROP_JOB_FREQUENCY, frequency);
		executionInfos.put(this.PROP_JOB_MAXDURATION, maxDuration);
		executionInfos.put("sha1", sha1);
	}
	/**
	 * Lancement du job{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public Boolean launch ()throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Boolean WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Lecture et préparation des paramètres
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"2" : poids relatif de 10, Vérification des prérequis
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"3" : poids relatif de 10, Instanciation de l'objet WILD
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"4" : poids relatif de 10, Lancement de l'objet WILD
			this.WILD_setStep(); // Ne pas modifier

			final TriggerBuilder<Trigger> tb = TriggerBuilder.newTrigger().forJob(detail);
			final Trigger trigger;
			if (frequency == null || frequency<=0) {
				trigger = tb.startNow().build();
			} else {
				ScheduleBuilder sb = SimpleScheduleBuilder.repeatSecondlyForever(frequency);
				trigger = tb.withSchedule(sb).build();
			}
			final Scheduler scheduler = wildList.DEV_getQuartzScheduler();
			scheduler.scheduleJob(detail, Collections.singleton(trigger), true);
		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}

		return WILD_toReturn ; // Ne pas modifier
	}

	/**
	 * Arrêt du job{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public Boolean stop() throws Exception {
		Throwable t = new Throwable();
		t.printStackTrace();
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Boolean WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Récupération du processus
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"2" : poids relatif de 10, Interruption du processus
			this.WILD_setStep(); // Ne pas modifier

			//on enleve toutes les programmations
			final Scheduler scheduler = wildList.DEV_getQuartzScheduler();
			/*final List<? extends Trigger> triggers = scheduler.getTriggersOfJob(detail.getKey());
			for (Trigger trigger : triggers) {
				scheduler.unscheduleJob(trigger.getKey());
			}*/
			if((int)executionInfos.get("status")==1){
				//on stop toutes les executions
				scheduler.pauseJob(detail.getKey());
				//on interrompt les executions en cours
				scheduler.interrupt(detail.getKey());
				WILD_echo("Attempt to kill job purposely : "+detail.getKey());
			}else WILD_echo("Delete cause 1-shot : "+detail.getKey());
			//	Etape	"3" : poids relatif de 10, Vérification des variables
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}
	public Boolean delete() throws Exception {
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Boolean WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Récupération du processus
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"2" : poids relatif de 10, Interruption du processus
			this.WILD_setStep(); // Ne pas modifier

			//on enleve toutes les programmations
			final Scheduler scheduler = wildList.DEV_getQuartzScheduler();
			final List<? extends Trigger> triggers = scheduler.getTriggersOfJob(detail.getKey());
			for (Trigger trigger : triggers) {
				scheduler.unscheduleJob(trigger.getKey());
			}
			//	Etape	"3" : poids relatif de 10, Vérification des variables
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}
	/**
	 * Récupération des infos sur le job en cours{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public Map<String,Object> infos ()throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Map<String,Object> WILD_toReturn = new HashMap<>(executionInfos) ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Transmission des variables
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	/**
	 * A true, la dernière exécution du job s'est réalisée avec succès, à false, la dernière exécution est un échec, vide, aucune exécution a eu lieu pour le job.{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public Boolean isDone ()throws Exception {
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Boolean WILD_toReturn = isDone.get() ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Transmission des variables
			this.WILD_setStep(); // Ne pas modifier
		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	protected abstract void DEV_execute() throws Exception;

	/**
	 * Récupérer la valeur du parametre.
	 *
	 * @param <T>
	 * @param name
	 * @param defaulValue valeur utilisé si le paramètre n'est pas défini
	 * @param override valeur de surcharge, si définie elle est retournée
	 * @return valeur ou valeur par défaut
	 */
	protected <T extends Object> T DEV_getParameterValue(String name, T override, T defaulValue, Class<T> valueType) {
		if (override!=null) return override;
		T value = null;
		final Map parameter = (Map) jobParametersMap.get(name);
		if (parameter != null) {
			value = (T) parameter.get("value");
		}
		if(valueType!=null && value!=null) value = ObjectConverters.convert(value, valueType);
		return (value == null) ? defaulValue : value;
	}

	/**
	 * Description quartz du job.
	 */
	public final class WildJobDetail extends JobDetailImpl {

		public WildJobDetail() {
			setJobClass(WildJob.class);
			setDurability(true);
			setKey(new JobKey(id));
		}

		public WildQuartzJob getWild() {
			return WildQuartzJob.this;
		}
		
		public String getId(){
			return id;
		}
	}

	/**
	 * Execution quartz unique du job.
	 */
	public static final class WildJob implements Job, InterruptableJob {

		private final AtomicBoolean running = new AtomicBoolean(true);
		private final AtomicReference<Thread> thread = new AtomicReference<>();
		private WildQuartzJob wild;
		
		
		@Override
		public void execute(JobExecutionContext jec) throws JobExecutionException {
			thread.set(Thread.currentThread());
			final WildJobDetail detail = (WildJobDetail) jec.getJobDetail();
			wild = detail.getWild();
			//wild.executionInfos.clear();
			wild.executionInfos.put("status", 1);
			wild.incr_red();
			//on assure l'arret du job apres la durée maximum
			if (wild.maxDuration !=null && wild.maxDuration>0) {
				TIMER.schedule(new TimerTask() {
					@Override
					public void run() {
						if (running.get()) {
							wild.setInterrupted();
							try {
								final Scheduler scheduler = wild.wildList.DEV_getQuartzScheduler();
								scheduler.interrupt(wild.detail.getKey());
							} catch (Exception ex) {
								wild.WILD_Logger.logError(ex);
							}						
						}
					}
				}, wild.maxDuration*1000);
			}
			
			//information de l'état
			try {
				//execution de la classe
				wild.DEV_execute();
				wild.executionInfos.put("status", 2);
			} catch (Throwable ex) {
				if(IoCommons.execution_isInterrupt(ex))	wild.setInterrupted();
				running.set(false);
				wild.isDone.set(Boolean.FALSE);
				wild.executionInfos.put("status", 3);
				//on copie l'exception dans les infos
				final StringWriter strWriter = new StringWriter();
				final PrintWriter writer = new PrintWriter(strWriter);
				ex.printStackTrace(writer);
				writer.flush();
				strWriter.flush();
				wild.executionInfos.put("jobException", strWriter.toString());
				JobExecutionException e2 = new JobExecutionException(ex);
		       // throw e2;
				e2.printStackTrace();
			}finally{
				try {
					wild.DEV_pgSave();
					wild.DEV_cleanOutput();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//wild.executionInfos.put("status", 2);
				wild.isDone.set(Boolean.TRUE);
				running.set(false);
			}

		}

		@Override
		public void interrupt() throws UnableToInterruptJobException {
			wild.isDone.set(Boolean.FALSE);
			final Thread t = thread.get();
			if (t!=null)t.interrupt();
		}

		public void pgSave() throws XPathExpressionException, ClassNotFoundException, DOMException, SAXException, IOException, ParserConfigurationException, SQLException{
			wild.DEV_pgSave();
		}
		public void cleanOutput() throws IOException{
			wild.DEV_cleanOutput();
		}
	}
	private String DVP_nodeAttrValue(NamedNodeMap nAttr, String getAttribute) throws ClassNotFoundException, DOMException{
		return IoCommons.cast_xmlUnescape((String) DVP_nodeAttrValue(nAttr, getAttribute, "String" ));
	}
	private <L> L DVP_nodeAttrValue(NamedNodeMap nAttr, String getAttribute, String returnType ) throws ClassNotFoundException, DOMException{
		Node result = nAttr.getNamedItem(getAttribute) ;
		if(result==null||result.getNodeValue()==null){
			if("long".equalsIgnoreCase(returnType.toLowerCase())||"integer".equalsIgnoreCase(returnType.toLowerCase())||"int".equalsIgnoreCase(returnType.toLowerCase()))return (L) IoCommons.cast_2Object(returnType, "0")[1];
			else return null ;
		} 
		return (L) IoCommons.cast_2Object(returnType, result.getNodeValue())[1];
	}
/**
 * Sauvegarde des données dans PostgreSQL, quelque soit le job, pour un ficheir XML standard fourni
 * @throws ParserConfigurationException 
 * @throws IOException 
 * @throws SAXException 
 * @throws XPathExpressionException 
 * @throws DOMException 
 * @throws ClassNotFoundException 
 * @throws SQLException 
 */
	public void DEV_pgSave() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException, ClassNotFoundException, DOMException, SQLException {

		String jdbcAmo = "jdbc:postgresql://"
				+ WILD_dObject.getDiceModelList().getProperty("db_host") 
				+":"+WILD_dObject.getDiceModelList().getProperty("db_port")
				+"/"+WILD_dObject.getDiceModelList().getProperty("db_dbName");
		String jdbcUser = (String)WILD_dObject.getDiceModelList().getProperty("db_user");
		String jdbcPwd = (String)WILD_dObject.getDiceModelList().getProperty("db_passWord");
		
		
		Node docLoop = IoCommons.xml_getFirstNode(this.DEV_getParameterValue("behavior.outputFile",null,null,null)+"/summary.xml","TEST");
		
		if(docLoop==null)return;
		HashMap<Integer,Object[]> full_log = new HashMap();
		int i = 1;
		HashMap<String,Object> test_executions = new HashMap();
		
		// Détermination d'un objet de configuration	
		// Réalise auparavant un test sur recordConfig : la configuration existe-t-elle déjà en bdd ?
		Boolean recordConfig = fr.wild.common.Connector.ping_jdbc_result(jdbcAmo, jdbcUser, jdbcPwd, 
			"SELECT * FROM \""+WILD_dObject.getDiceModelList().getProperty("db_defaultSchema")+"\".test_service_config WHERE sha1='"+this.sha1+"' LIMIT 1"
		);
		if(!recordConfig){
			HashMap<String,Object> test_service_config = new HashMap();
			test_service_config.put("id_test_service_config",this.sha1);
			test_service_config.put("sha1",this.sha1);
			test_service_config.put("config_file_path",parametersXmlFile);
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			test_service_config.put("date_file_path",formatter.format(new File(parametersXmlFile).lastModified()));
			test_service_config.put("date_first_path",fr.wild.common.IoCommons.date_nowdate());
			NodeList intendedServices = IoCommons.xml_getNodes(docLoop, "intendedServices/intendedService");
			if(intendedServices!=null){
				HashMap<Integer,String> intendedServices_map = new HashMap();
				List<String> intendedServices_ls = new ArrayList();
				for(int j=0; j<intendedServices.getLength(); j++){
					Node intendedService = intendedServices.item(j);
					NamedNodeMap paramAttrib = intendedService.getAttributes();
					intendedServices_map.put(Integer.parseInt(DVP_nodeAttrValue(paramAttrib,"order")), DVP_nodeAttrValue(paramAttrib,"value"));
				}
				for(int j=0; j<intendedServices_map.size(); j++)intendedServices_ls.add(intendedServices_map.get(j));
				test_service_config.put("intended_services",intendedServices_ls);
			}
			full_log.put(i++,new Object[]{"test_service_config", test_service_config});
		
		NodeList xmlParams = IoCommons.xml_getNodes(this.parametersXmlFile, "test/service/parameter");
		
		if(xmlParams!=null)for(Integer j=0;j<xmlParams.getLength();j++){
				HashMap<String,Object> test_service_parameters = new HashMap();
				Node param = xmlParams.item(j);
				NamedNodeMap paramAttrib = param.getAttributes();
				//test_service_parameters.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
				test_service_parameters.put("id_test_service_config", this.sha1);
				if(paramAttrib.getNamedItem("id")!=null)test_service_parameters.put("parameter_id",DVP_nodeAttrValue(paramAttrib,"id"));
				if(paramAttrib.getNamedItem("position")!=null)test_service_parameters.put("parameter_position",DVP_nodeAttrValue(paramAttrib,"position"));
				if(paramAttrib.getNamedItem("type")!=null)test_service_parameters.put("parameter_type",DVP_nodeAttrValue(paramAttrib,"type"));
				String values ;
				if(param.hasChildNodes()){
					NodeList nLvalues = param.getChildNodes();
					values = "{";
					Integer valuesNb = 0 ;
					for(Integer k=0;k<nLvalues.getLength();k++){
						String curVal = "\""+nLvalues.item(k).getTextContent().trim().replaceAll("\"","\\\"")+"\"";
						if(nLvalues.item(k).getNodeName().toLowerCase().equalsIgnoreCase("value"))
							values +=( valuesNb++ ==0)? curVal : ","+curVal;
					}
					values += "}";
					if(values.equalsIgnoreCase("{}"))values = null;
					if(valuesNb==1)values=values.replaceAll("^\\{","").replaceAll("\\}$","").replaceAll("^\"","").replaceAll("\"$","");
				}else{
					values = param.getTextContent().trim();
				}	
				test_service_parameters.put("parameter_value",values);			
				full_log.put(i++,new Object[]{"test_service_parameters", test_service_parameters});
		}
			
			
		}
		

// Suivant si l'on arrive d'un test simple ou complexe, il y a un ou plusieurs noeuds à traiter
			NodeList subTests = docLoop.getChildNodes() ;
			HashMap<Integer, Node> hMsubTests = new HashMap();
			for(Integer ni=0;ni<subTests.getLength();ni++){
				Node nodeTest = subTests.item(ni);
				if(nodeTest==null)continue;
				if(nodeTest.getNodeName().toLowerCase().startsWith("node")&&nodeTest.getNodeName().contains("_"))hMsubTests.put(ni, nodeTest);
			}
			if(hMsubTests.size()==0)hMsubTests.put(0, docLoop);
			
			NamedNodeMap paramAttribTest = docLoop.getAttributes();	
			test_executions.put("id_test_executions", id);
			test_executions.put("date_begin", DVP_nodeAttrValue(paramAttribTest,"start_date"));
			test_executions.put("date_end", IoCommons.date_now());
			test_executions.put("fulltime", IoCommons.date_nowLong()- Long.valueOf(paramAttribTest.getNamedItem("start_date_ts").getNodeValue()));
			test_executions.put("interrupted", interrupted);
			
			Long full_size = (long) 0;
			Map<String,Integer> maxForService = new HashMap();
			
			
			
			for(Integer ni=0;ni<hMsubTests.size();ni++){
				Node nodeTest = hMsubTests.get(ni);
				if(nodeTest==null)continue;
				NamedNodeMap attribTest = nodeTest.getAttributes();
				if(DVP_nodeAttrValue(attribTest,"requestURL")==null)continue;
				String nodeName = nodeTest.getNodeName() ;
				String testKey = (nodeName.startsWith("node")&&nodeName.contains("_"))?
						nodeName.split("_")[0].replaceAll("^node","")+"."+nodeName.split("_")[1]
						:nodeName;
				String[] radKey =  testKey.split("\\.");
				if(radKey.length>1){
					Integer currentItemNb = (Integer) IoCommons.cast_2Object("Integer", radKey[0])[1];
					String keyItem = radKey[1];
					if(maxForService.get(keyItem)==null||maxForService.get(keyItem)<currentItemNb)maxForService.put(keyItem, currentItemNb);
				}

				Long test_size = DVP_nodeAttrValue(attribTest,"size","Long") ;
				full_size += (test_size!=null)?(long)test_size :(long)0;
				Long errorNumber = (long)DVP_nodeAttrValue(attribTest,"errorNumberSchema","Long")+ (long)DVP_nodeAttrValue(attribTest,"errorNumberStructure","Long");
				Long keyNb = (long)DVP_nodeAttrValue(attribTest,"keyNumber","Long")+(long)DVP_nodeAttrValue(attribTest,"numberSites","Long");

				HashMap<String,Object> test_service_executions = new HashMap();
				test_service_executions.put("id_test_service_executions",id+"."+testKey);
				test_service_executions.put("id_test_service_config",sha1);
				test_service_executions.put("id_test_executions",id);
				test_service_executions.put("method_name",testKey.split("\\.")[testKey.split("\\.").length-1]);
				test_service_executions.put("date_begin", DVP_nodeAttrValue(attribTest,"start_date"));
				if(test_service_executions.get("date_begin")==null)test_service_executions.put("date_begin",date_nowLong());
				test_service_executions.put("date_end", DVP_nodeAttrValue(attribTest,"end_date"));
				test_service_executions.put("disk_size", test_size);
				test_service_executions.put("element_size",keyNb);
				test_service_executions.put("fulltime",DVP_nodeAttrValue(attribTest,"time","Long"));
				test_service_executions.put("success",DVP_nodeAttrValue(attribTest,"success","Long"));
				test_service_executions.put("requested_url",DVP_nodeAttrValue(attribTest,"requestURL"));
				test_service_executions.put("response_format",DVP_nodeAttrValue(attribTest,"responseFormat"));
				test_service_executions.put("schema_error_number",errorNumber);
				test_service_executions.put("schema_validity",(DVP_nodeAttrValue(attribTest,"errorNumberSchema","Long")==null||(long)DVP_nodeAttrValue(attribTest,"errorNumberSchema","Long")==0));
				test_service_executions.put("requested_schema",DVP_nodeAttrValue(attribTest,"testStructure"));
				test_service_executions.put("business_validity", DVP_nodeAttrValue(attribTest,"validSandre"));
				test_service_executions.put("business_error_number", DVP_nodeAttrValue(attribTest,"numberErrorsSandre"));
				test_service_executions.put("requested_scenario",DVP_nodeAttrValue(attribTest,"quesu"));	
				String cdError_service = DVP_nodeAttrValue(attribTest,"errorServiceCode");
				test_service_executions.put("interrupted","url-client-thread-interruption".equals(cdError_service));
				
				full_log.put(i++,new Object[]{"test_service_executions", test_service_executions});
				// Sélection des résultats détaillés
				String[] toReport = new String[]{
						"numberSites",
						"errorNumberStructure",
						"errorNumberSchema",
						"keyNumber",
						"testStructure",
						"responseFormat",
						"time",
						"quesu",
						"size",
						"success",
						"errorServiceCode"};
				for(String nK:toReport){
					Object value = DVP_nodeAttrValue(attribTest,nK);
					if(value==null)continue;
					HashMap<String,Object> detailed_results = new HashMap();
					detailed_results.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
					detailed_results.put("result_id", nK);
					detailed_results.put("result_position", "./"+nK);
					detailed_results.put("result_type", "result");
					detailed_results.put("result_value", value);
					full_log.put(i++,new Object[]{"detailed_results", detailed_results});
				}
				
				// Erreurs de structure
				// // XML
				Node xml_eStruct_node = IoCommons.xml_getFirstNode(nodeTest, "errorsSchema");
				NodeList xml_eStruct = (xml_eStruct_node==null)?null:xml_eStruct_node.getChildNodes();
				if(xml_eStruct!=null)for(Integer j=0;j<xml_eStruct.getLength();j++){
					HashMap<String,Object> schema_errors_service = new HashMap();
					NamedNodeMap hN = xml_eStruct.item(j).getAttributes();
					schema_errors_service.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
					schema_errors_service.put("error_line", DVP_nodeAttrValue(hN, "line"));
					schema_errors_service.put("error_column", DVP_nodeAttrValue(hN, "column"));
					schema_errors_service.put("error_level", DVP_nodeAttrValue(hN, "level").trim());
					schema_errors_service.put("error_message", DVP_nodeAttrValue(hN, "message").trim());
					String eCode = DVP_nodeAttrValue(hN, "error_code").trim();
					if(eCode!=null)schema_errors_service.put("error_code", eCode);
					else schema_errors_service.put("error_code", ((String)DVP_nodeAttrValue(hN, "error_message").trim()).split(":")[0].trim());
					full_log.put(i++, new Object[]{"schema_errors",schema_errors_service});
				}
				// // JSON
				NodeList json_eStruct = IoCommons.xml_getNodes(nodeTest, "errorsKey");
				if(json_eStruct!=null)for(Integer j=0;j<json_eStruct.getLength();j++){
					HashMap<String,Object> schema_errors_service = new HashMap();
					NamedNodeMap hN = json_eStruct.item(j).getAttributes();
					schema_errors_service.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
					schema_errors_service.put("error_line", DVP_nodeAttrValue(hN, "order"));
					schema_errors_service.put("error_column", 0);
					schema_errors_service.put("error_level", "error");
					Node json_eStruct_value = IoCommons.xml_getFirstNode(json_eStruct.item(j),"value");
					if(json_eStruct_value!=null)schema_errors_service.put("error_message", DVP_nodeAttrValue(json_eStruct_value.getAttributes(), "message").trim());
					schema_errors_service.put("error_code", "json-structure");
					full_log.put(i++, new Object[]{"schema_errors",schema_errors_service});
				}
			// Erreurs métiers
				
				Node xml_eSandre_node = IoCommons.xml_getFirstNode(nodeTest, "errorsSandre");
				NodeList xml_eSandre = (xml_eSandre_node==null)?null:xml_eSandre_node.getChildNodes();
				if(xml_eSandre!=null)for(Integer j=0;j<xml_eSandre.getLength();j++){
					HashMap<String,Object> schema_errors_service = new HashMap();
					NamedNodeMap hN = xml_eSandre.item(j).getAttributes();
					schema_errors_service.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
					schema_errors_service.put("error_line", DVP_nodeAttrValue(hN, "LigneErreur"));
					schema_errors_service.put("error_column", DVP_nodeAttrValue(hN, "ColonneErreur"));
					schema_errors_service.put("error_level", DVP_nodeAttrValue(hN, "SeveriteErreur").trim());
					schema_errors_service.put("error_message", DVP_nodeAttrValue(hN, "DescriptifErreur").trim());
					schema_errors_service.put("error_code", DVP_nodeAttrValue(hN, "CdErreur").trim());
					full_log.put(i++, new Object[]{"fa_errors",schema_errors_service});
				}
				NodeList json_eSpec = IoCommons.xml_getNodes(nodeTest, "errorsTest");
				if(json_eSpec!=null)for(Integer j=0;j<json_eSpec.getLength();j++){
					HashMap<String,Object> schema_errors_service = new HashMap();
					NamedNodeMap hN = json_eSpec.item(j).getAttributes();
					schema_errors_service.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
					schema_errors_service.put("error_line", DVP_nodeAttrValue(hN, "order"));
					schema_errors_service.put("error_column", 0);
					schema_errors_service.put("error_level", "error");
					Node json_eStruct_value = IoCommons.xml_getFirstNode(json_eSpec.item(j),"value");
					if(json_eStruct_value!=null)schema_errors_service.put("error_message", DVP_nodeAttrValue(json_eStruct_value.getAttributes(), "message").trim());
					schema_errors_service.put("error_code", "json-business-specific");
					full_log.put(i++, new Object[]{"fa_errors",schema_errors_service});
				}
				
				// Erreurs service
				
				Node xmlServerError = IoCommons.xml_getFirstNode(nodeTest, "errorServiceNode");

				if(xmlServerError==null)xmlServerError = IoCommons.xml_getFirstNode(nodeTest, "Errors");
				Boolean isSoap = false ;
				if(xmlServerError==null){
					xmlServerError = IoCommons.xml_getFirstNode(nodeTest, "SoapError");
					if(xmlServerError!=null)isSoap = true ;
				}
				HashMap<String,Object> schema_errors_service = new HashMap();
				String severity = "S.FATAL";
				if(xmlServerError!=null){
					
					schema_errors_service.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
					// Cas SOAP
					if(xmlServerError.getFirstChild().getNodeName().equalsIgnoreCase("soapenv:Body")||isSoap){
						
						Node specific_error = IoCommons.xml_getFirstNode(xmlServerError, "detail");
						if(specific_error == null)specific_error = IoCommons.xml_getFirstNode(xmlServerError.getFirstChild(), "detail");
						String cdError = null;
						String msgError = "";
					// Sans doute à cause des xmlns, la lecture des noeud est hasardeuse => on force le parcours
					// Cas pour lequel on dispose du détail de l'erreur
						if(specific_error!=null){
							final String sev2 = DVP_nodeAttrValue(specific_error.getAttributes(),"SeveriteErreur");
							if(sev2!=null)severity=sev2;
							if(specific_error.hasChildNodes()){
								NodeList specific_error_ls = specific_error.getChildNodes();		
								for(Integer l = 0 ; l < specific_error_ls.getLength() ; l++){
									NodeList specific_error_ls2 = specific_error_ls.item(l).getChildNodes();
									if(specific_error_ls.item(l).getNodeName().equalsIgnoreCase("CdErreur"))cdError = (specific_error_ls.item(l).getTextContent().trim());
									if(specific_error_ls.item(l).getNodeName().equalsIgnoreCase("LocationErreur"))msgError = specific_error_ls.item(l).getTextContent().trim() + msgError;
									if(specific_error_ls.item(l).getNodeName().equalsIgnoreCase("DescriptifErreur"))msgError += ":" + specific_error_ls.item(l).getTextContent().trim();
									for(Integer l2 = 0 ; l2 < specific_error_ls2.getLength() ; l2++){
										if(cdError==null&&specific_error_ls2.item(l2).getNodeName().equalsIgnoreCase("CdErreur"))cdError = (specific_error_ls2.item(l2).getTextContent().trim());
										if("".equals(msgError)&&specific_error_ls2.item(l2).getNodeName().equalsIgnoreCase("LocationErreur"))msgError = specific_error_ls2.item(l2).getTextContent().trim() + msgError;
										if("".equals(msgError)&&specific_error_ls2.item(l2).getNodeName().equalsIgnoreCase("DescriptifErreur"))msgError += ":" + specific_error_ls2.item(l2).getTextContent().trim();
									}
									if("".equals(msgError)&&specific_error_ls.item(l).getNodeName().equalsIgnoreCase("Exception"))msgError += ":" + specific_error_ls2.item(l).getTextContent().trim();
								}
						}}	
						if(cdError==null&&xmlServerError.getFirstChild()!=null&&xmlServerError.getFirstChild().hasChildNodes()){
						// Cas pour lequel on ne dispose pas du détail de l'erreur
							NodeList specific_error_ls2 = xmlServerError.getFirstChild().getChildNodes();
							for(Integer l2 = 0 ; l2 < specific_error_ls2.getLength() ; l2++){
								if(specific_error_ls2.item(l2).getNodeName().equalsIgnoreCase("faultcode"))cdError = (specific_error_ls2.item(l2).getTextContent().trim());
								if(specific_error_ls2.item(l2).getNodeName().equalsIgnoreCase("faultstring"))msgError = specific_error_ls2.item(l2).getTextContent().trim();											
						}	
						if(cdError==null)cdError=cdError_service;
						
					}
						schema_errors_service.put("error_line", 0);
						schema_errors_service.put("error_column", 0);
						schema_errors_service.put("error_level", "S.FATAL");
						schema_errors_service.put("error_message", msgError);
						schema_errors_service.put("error_code", cdError);
					}else{
					// Autre cas
						String msgError = xmlServerError.getTextContent();
						schema_errors_service.put("error_line", 0);
						schema_errors_service.put("error_column", 0);
						schema_errors_service.put("error_level", severity);
						schema_errors_service.put("error_message", msgError.trim());
						schema_errors_service.put("error_code", cdError_service);

					}
					
				}
				String attributeJavaError = DVP_nodeAttrValue(attribTest,"JAVA_ERROR") ;
				if(schema_errors_service.isEmpty()&&attributeJavaError!=null&&!"null".equals(attributeJavaError)){
				// Autre cas
					String msgError = DVP_nodeAttrValue(attribTest,"JAVA_ERROR");
					schema_errors_service.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
					schema_errors_service.put("error_line", 0);
					schema_errors_service.put("error_column", 0);
					schema_errors_service.put("error_level", "S.FATAL.JAVA.");
					if(msgError!=null)schema_errors_service.put("error_message", msgError.trim());
					schema_errors_service.put("error_code", cdError_service);
				}
				if(!schema_errors_service.isEmpty())full_log.put(i++,new Object[]{"schema_errors", schema_errors_service});	
			}
			test_executions.put("fullsize", full_size);
			full_log.put(0,new Object[]{"test_executions", test_executions});
			
		//	if(0==0)return;
			
				Class.forName("org.postgresql.Driver");
				Connection opened_dbConnect ;
				opened_dbConnect = DriverManager.getConnection(jdbcAmo, jdbcUser, jdbcPwd);
				HashMap<String,HashMap<String,String>> mapTypper = IoCommons.pg_getSchemaDefs(opened_dbConnect, (String) WILD_dObject.getDiceModelList().getProperty("db_defaultSchema"));				
				for(Integer ki=0;ki<full_log.size();ki++){
					Object[] aRow = full_log.get(ki);
					HashMap<String,Object> currentContains = (HashMap<String,Object>)aRow[1] ;
					String indexTest = (currentContains.get("id_test_service_executions")==null)?null:(String) currentContains.get("id_test_service_executions") ;
					String[] radTest = (indexTest==null)?null:indexTest.split("\\.");
					// Si le test n'est pas le dernier de la série, on ne l'enregistre pas : il existe un test qui "est allé plus loin"
					if(	indexTest!=null
							&& radTest.length==3
							&&!indexTest.equals(id+"."+maxForService.get(radTest[2])+"."+radTest[2]))
						continue;
								
					String order = IoCommons.pg_insertStr(mapTypper,(String) WILD_dObject.getDiceModelList().getProperty("db_defaultSchema"),(String)aRow[0],currentContains);
					Statement statement = null;
					try {
						statement = opened_dbConnect.createStatement();					
						statement.executeUpdate(order);
					} catch (SQLException eSql) {
						System.out.println(order);
						eSql.printStackTrace();
					} finally {
						if (statement != null) statement.close();
					}

				}
				opened_dbConnect.close();			
	}

	
	public void DEV_cleanOutput() throws IOException{
		try{
		if(this.DEV_getParameterValue("behavior.saveAsXml",true,null,null)){
			final String fileIN = this.DEV_getParameterValue("behavior.outputFile",null,null,null)+"/summary.xml";
			final String fileOUT = this.WILD_dObject.getDiceModelList().getProperty("qWorkspace_path")+"/"+this.WILD_dObject.getDiceModelList().getProperty("qArchives_path")+"/"+id+".zip";
			if(new File(fileIN).exists())IoFileSystem.file_zip(fileIN,fileOUT,true);
			IoFileSystem.file_delete((String)this.DEV_getParameterValue("behavior.outputFile",null,null,null));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void DVP_preparePathes(){
		final Map parameter = (Map) serviceParametersMap.get("request.multipart");
		if(parameter == null 
			|| parameter.isEmpty() 
			|| parameter.get("value") == null
			||(new File(parameter.get("value").toString())).exists()
		)return ;
		final String filePath = this.WILD_dObject.getDiceModelList().getProperty("qWorkspace_path")+"/"+this.WILD_dObject.getDiceModelList().getProperty("qFiles_path")+"/"+parameter.get("value").toString();
		if(!(new File(filePath)).exists())return ;
		parameter.put("value", filePath);
		serviceParametersMap.put("request.multipart", parameter);
	}
}

