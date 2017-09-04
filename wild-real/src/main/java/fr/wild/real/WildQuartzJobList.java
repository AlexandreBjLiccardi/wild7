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
import static fr.wild.common.IoFileSystem.file_sha1;

import fr.wild.common.IoCommons;
import fr.wild.common.IoFileSystem;
import fr.wild.orchestra.Wild4Test;
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;
import fr.wild.real.WildQuartzJob.WildJobDetail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.sis.util.ObjectConverters;
import org.geotoolkit.util.DomUtilities;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


/**
 * Code généré automatiquement par l'outil Wild
 * Liste de jobs Quartz à exécuter
 */
public class WildQuartzJobList extends WildClass{

	/**
	 * Nombre maximum de job éxécutés en parallèle<br>
	 * Type : Integer<br>
	 */
	public static final String PROP_JOBS_NJOBS = "jobsConfiguration.nJobs";
	
	/**
	 * Nombre maximum de Job schedulés<br>
	 * Type : Integer<br>
	 */
	public static final String PROP_JOBS_NSCHEDULES = "jobsConfiguration.nSchedules";

	/**
	 * Durée maximale d'exécution, en secondes<br>
	 * Type : Integer<br>
	 */
	public static final String PROP_JOBS_MAXDURATION = "jobsConfiguration.maxDuration";


	// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildQuartzJobList (){}

	// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

	// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String parametersXmlFile;	//Paramètres sous forme de fichier XML dont on donne le lien{}


	// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***
	private final Map<String,Object> parametersMap = new HashMap<>();

	
	private Map<String,Object[]> loadedFiles = new HashMap<>(); 
	
	//scheduler quartz
	private Wild4Test builder;
    private Scheduler quartzScheduler;

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
	protected void WILD_initialize_WildQuartzJobList (
		WildObject i_WILD_dObject,
		String i_parametersXmlFile
			) throws Exception {

		// Amorce de la classe
		// Initialisation de la classe d'objet selon le schéma Wild
		WILD_dObject = i_WILD_dObject ;
		WILD_initialize_WildClass(i_WILD_dObject/*** Paramètres à actualiser ***/);
		// Préparation des variables d'invocation (considérées comme champs globaux)
		this.parametersXmlFile = i_parametersXmlFile;

		// Mode try de récupération des erreurs pour log
		try {
			builder = new Wild4Test();

			//lecture des parametres
			try {
				parametersMap.putAll(IoCommons.param_readParameters(null, i_parametersXmlFile, null));
			} catch(FileNotFoundException ex) {
				this.WILD_Logger.logError("Fichier de configuration manquant.\n"+ex.getMessage());
			}

			// Creation du scheduler Quartz
			final Properties properties = new Properties();
			properties.put("org.quartz.threadPool.threadCount", DEV_getParameterValue(PROP_JOBS_NJOBS, null, "2", String.class));
			final StdSchedulerFactory schedFact = new StdSchedulerFactory();
			schedFact.initialize(properties);
			quartzScheduler = schedFact.getScheduler();
			
			quartzScheduler.getListenerManager().addJobListener(new JobListener() {
                @Override
                public String getName() {
                    return "wild";
                }
                @Override
                public void jobToBeExecuted(JobExecutionContext jec) {
                	final WildJobDetail detail = (WildJobDetail) jec.getJobDetail();
                	WILD_echo("Launched : "+detail.getId());
                }
                @Override
                public void jobExecutionVetoed(JobExecutionContext jec) {
                    //avant execution mais annulé par le trigger
                }
                @Override
                public void jobWasExecuted(JobExecutionContext jec, JobExecutionException jee) {
                    //apres execution ou interruption
                	final WildJobDetail detail = (WildJobDetail) jec.getJobDetail();
                	if(jee==null)WILD_echo("Finished : "+detail.getId());
                	else if(IoCommons.execution_isInterrupt(jee)) WILD_echo("Interrupted by user : "+detail.getId());
                	else WILD_echo("Error encountered : "+detail.getId());
                	try {
                		Object freq = detail.getWild().infos().get("jobConfiguration.frequency");
						if(freq==null||(Long)freq<=0)deleteJob(detail.getId());
					} catch (Exception e) {}
                }
            });
			
			quartzScheduler.start();

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
	public WildQuartzJobList (
		WildObject i_WILD_dObject,
		String i_parametersXmlFile
			) throws Exception {
		this.WILD_initialize_WildQuartzJobList (i_WILD_dObject,i_parametersXmlFile);
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Ajout d'un job  la liste d'exécution{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_xmlPath	chemin vers le fichier XML décrivant les paramètres{}
	 * @return	{}
	 */
	public String addJob (
		String i_xmlPath
			)throws Exception{

		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		String WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Lecture et préparation des paramètres
			this.WILD_setStep(); // Ne pas modifier
			final File xml_jobPath = new File(i_xmlPath);

			//	Etape	"2" : poids relatif de 10, Vérification des prérequis
			this.WILD_setStep(); // Ne pas modifier
			Object[] loader = DVP_checkLoader(i_xmlPath) ;
			WILD_toReturn = (String) loader[0];
			if((Integer) loader[1] == 3)return null;
			
			final Element config = DomUtilities.firstElement((Element) loader[2], "configuration", true);
			final Map jobParams = IoCommons.param_readParameters(config, null, null);

			//	Etape	"3" : poids relatif de 10, Composition de la chaîne intermédiaire XML
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"4" : poids relatif de 10, Construction du nouveau job
			this.WILD_setStep(); // Ne pas modifier
			final WildQuartzJob quartzJob;
			
			if (jobParams.containsKey(WildQuartzJob.PROP_JOB_JAVACLASS)) {
				builder.addObject(WILD_toReturn, "real", "WildJavaQuartzJob", new Object[]{i_xmlPath});
				quartzJob = builder.getObject(WILD_toReturn);
			} else if (jobParams.containsKey(WildQuartzJob.PROP_JOB_SHELL)) {
				builder.addObject(WILD_toReturn, "real", "WildShellQuartzJob", new Object[]{i_xmlPath});
				quartzJob = builder.getObject(WILD_toReturn);
			} else {
				IoFileSystem.file_copy(i_xmlPath, IoFileSystem.DVP_findAvailableName(
						WILD_dObject.getDiceModelList().getProperty("qWorkspace_path") 
							+ "/" + WILD_dObject.getDiceModelList().getProperty("qReject_path")
							+ "/" + xml_jobPath.getName() 
						,"xml"));
				IoFileSystem.file_delete(i_xmlPath);
				loadedFiles.remove(xml_jobPath.getName());
				throw new IOException("Parametre javaClass ou shell manquant, job : "+xml_jobPath.getName());
			}
			quartzJob.DVP_setId(WILD_toReturn);
			//	Etape	"5" : poids relatif de 10, Paramétrage du nouveau job
			this.WILD_setStep(); // Ne pas modifier
			quartzJob.wildList = this;
			quartzScheduler.addJob(quartzJob.detail, true);
			quartzJob.launch();
		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Suppresion d'un job de la liste d'exécution{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_jobId	Identifiant unique du job{}
	 * @return	{}
	 */
	public Boolean deleteJob (
		String i_jobId
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Boolean WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {
			i_jobId = this.DVP_getIdResolve(i_jobId);
			final WildQuartzJob.WildJobDetail detail = (WildQuartzJob.WildJobDetail) quartzScheduler.getJobDetail(new JobKey(i_jobId));
			final WildQuartzJob wild = detail.getWild();
			wild.stop();
			wild.delete();
			WILD_toReturn = quartzScheduler.deleteJob(new JobKey(i_jobId));
			this.loadedFiles.remove(new File(this.DVP_getPathResolve(i_jobId)).getName());
			//	Etape	"1" : poids relatif de 10, Identification du job et transmission des variables
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	public Boolean DVP_killJob(String i_jobId) throws Exception{
		try{
		i_jobId = this.DVP_getIdResolve(i_jobId);
		if(i_jobId==null)return false ;
		final WildQuartzJob.WildJobDetail detail = (WildQuartzJob.WildJobDetail) quartzScheduler.getJobDetail(new JobKey(i_jobId));
		final WildQuartzJob wild = detail.getWild();
		wild.stop();
		return true ;
		}catch(Exception e){e.printStackTrace();}
		return false;
	}
	
	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Récupération des infos sur un job{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_jobId	Identifiant unique du job{}
	 * @return	{}
	 */
	public Map<String,Object> infoJob (
		String i_jobId
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Map<String,Object> WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Identification du job et transmission des variables
			this.WILD_setStep(); // Ne pas modifier
			
			i_jobId = this.DVP_getIdResolve(i_jobId);

			if(i_jobId==null){
				WILD_endMethod();
				return null;
			}

			final WildQuartzJob.WildJobDetail detail = (WildQuartzJob.WildJobDetail) quartzScheduler.getJobDetail(new JobKey(i_jobId));
			if (detail!=null) {
				final WildQuartzJob wild = detail.getWild();
				WILD_toReturn = wild.infos();
			}

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Récupération des infos sur tous les jobs{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public Map<String,Map<String,Object>> getListInfos ()throws Exception {
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Map<String,Map<String,Object>> WILD_toReturn = new HashMap<>();

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Identification du job et transmission des variables
			this.WILD_setStep(); // Ne pas modifier
			final Set<JobKey> jobKeys = quartzScheduler.getJobKeys(null);
			for (JobKey key : jobKeys) {
				WILD_toReturn.put(key.getName(), infoJob(key.getName()));
			}

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Définition d'une durée maximale d'exécution, pour un job{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_jobId	Identifiant unique du job{}
	 * @param i_newMaxDuration	Nouvelle durée maximale en secondes{}
	 * @return	{}
	 */
	public Integer setMaxDuration (
		String i_jobId,
		Integer i_newMaxDuration
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Integer WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Identification du job
			this.WILD_setStep(); // Ne pas modifier
			final WildQuartzJob.WildJobDetail detail = (WildQuartzJob.WildJobDetail) quartzScheduler.getJobDetail(new JobKey(i_jobId));
			final WildQuartzJob wild = detail.getWild();
			WILD_toReturn = wild.setFrequency(i_newMaxDuration);

			//	Etape	"2" : poids relatif de 10, Affectation de la variable
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"3" : poids relatif de 10, Retour de la variable
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Définition de la fréquence d'exécution du job, si null exécution immédiate et one shot.{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_jobId	Identifiant unique du job{}
	 * @param i_newLength	Nouvelle période en secondes{}
	 * @return	{}
	 */
	public Integer setFrequency (
		String i_jobId,
		Integer i_newLength
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Integer WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Identification du job
			this.WILD_setStep(); // Ne pas modifier
			final WildQuartzJob.WildJobDetail detail = (WildQuartzJob.WildJobDetail) quartzScheduler.getJobDetail(new JobKey(i_jobId));
			final WildQuartzJob wild = detail.getWild();
			WILD_toReturn = wild.setFrequency(i_newLength);

			//	Etape	"2" : poids relatif de 10, Affectation de la variable
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"3" : poids relatif de 10, Retour de la variable
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Pour un job : à true, la dernière exécution du job s'est réalisée avec succès, à false, la dernière exécution est un échec, vide, aucune exécution a eu lieu pour le job.{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_jobId	Identifiant unique du job{}
	 * @return	{}
	 */
	public Boolean isDone (
		String i_jobId
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Boolean WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Identification du job et transmission des variables
			this.WILD_setStep(); // Ne pas modifier
			final WildQuartzJob.WildJobDetail detail = (WildQuartzJob.WildJobDetail) quartzScheduler.getJobDetail(new JobKey(i_jobId));
			final WildQuartzJob wild = detail.getWild();
			WILD_toReturn = wild.isDone();

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Impression d'un rapport XML contenant la description des job schédulés{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public String printList ()throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		String WILD_toReturn = new File(parametersXmlFile).toPath()
				.getParent().resolve("list.csv").toFile().getAbsolutePath(); // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			//	Etape	"1" : poids relatif de 10, Amorce du rapport
			this.WILD_setStep(); // Ne pas modifier

			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			final Document doc = docBuilder.newDocument();
			final Element jobs = doc.createElement("list");
			doc.appendChild(jobs);

			//	Etape	"2" : poids relatif de 10, Récupération et écriture du rapport job par job
			this.WILD_setStep(); // Ne pas modifier

			final Set<JobKey> jobKeys = quartzScheduler.getJobKeys(null);
			for (JobKey key : jobKeys) {
				final WildQuartzJob.WildJobDetail detail = (WildQuartzJob.WildJobDetail) quartzScheduler.getJobDetail(key);
				final WildQuartzJob job = detail.getWild();
				final String id = job.getId();
				final Element jobNode = doc.createElement("job");
				jobNode.setAttribute("id", id);

				//configuration du job
				final Node jobConfigNode = IoCommons.param_mapToNode("configuration", job.jobParametersMap);
				final Node jobServiceNode = IoCommons.param_mapToNode("service", job.serviceParametersMap);
				final Element jobConfig = (Element) doc.adoptNode(((Document)jobConfigNode).getDocumentElement());
				final Element jobService = (Element) doc.adoptNode(((Document)jobServiceNode).getDocumentElement());
				jobNode.appendChild(jobConfig);
				jobNode.appendChild(jobService);

				//etat de l'execution
				final Element execNode = doc.createElement("execution");
				final Boolean done = isDone(id);
				execNode.setAttribute("isDone", done==null ? "" : String.valueOf(done));
				final Map<String, Object> infoJob = infoJob(id);
				for (Entry<String,Object> entry : infoJob.entrySet()) {
					final Element infoNode = doc.createElement(entry.getKey());
					infoNode.setTextContent(String.valueOf(entry.getValue()));
					execNode.appendChild(infoNode);
				}
				jobNode.appendChild(execNode);


				jobs.appendChild(jobNode);
			}

			//	Etape	"3" : poids relatif de 10, Ecriture sur disque du rapport
			this.WILD_setStep(); // Ne pas modifier

			DomUtilities.write(doc, new File(WILD_toReturn));

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	/**
	 * Scheduler quartz.
	 * 
	 * @return Scheduler
	 */
	Scheduler DEV_getQuartzScheduler() {
		return quartzScheduler;
	}

	/**
	 * Builder d'objet Wild.
	 *
	 * @return Wild4Test
	 */
	Wild4Test DEV_getBuilder() {
		return builder;
	}

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
		final Map parameter = (Map) parametersMap.get(name);
		if (parameter != null) {
			value = (T) parameter.get("value");
		}
		if(valueType!=null && value!=null) value = ObjectConverters.convert(value, valueType);
		return (value == null) ? defaulValue : value;
	}
	
	
	private Object[] DVP_checkLoader(String i_xmlPath) throws SAXException, IOException, ParserConfigurationException, NoSuchAlgorithmException{
		Object[] WILD_toReturn = new Object[3];
		String WILD_toReturn_0 = null;
		Integer WILD_toReturn_1 = 0 ; 
		Object[] equivForFile = new Object[3];
		File nFXml = new File(i_xmlPath);
		equivForFile[1] = file_sha1(nFXml);
		
		if(loadedFiles.get(nFXml.getName())==null){
			final Element doc = IoCommons.xml_getDocument(i_xmlPath).getDocumentElement();
			WILD_toReturn_0 = doc.getAttribute("id").trim();
			if(WILD_toReturn_0==null||WILD_toReturn_0.isEmpty())WILD_toReturn_0 = IoCommons.cast_getRandom();
			WILD_toReturn_1 = 1 ;
			equivForFile[0] = WILD_toReturn_0;
			equivForFile[2] = WILD_toReturn_1 ;
			WILD_toReturn[2] = doc ;
			loadedFiles.put(nFXml.getName(), equivForFile);
		}else{
			Object[] describer = loadedFiles.get(nFXml.getName());
			WILD_toReturn_0 = (String) describer[0];
			if(!equivForFile[1].equals(describer[1])){ // Le fichier a été modifié entre deux boucles
				final Element doc = IoCommons.xml_getDocument(i_xmlPath).getDocumentElement();
				WILD_toReturn_0 = doc.getAttribute("id").trim();
				if(WILD_toReturn_0==null||WILD_toReturn_0.isEmpty())WILD_toReturn_0 = (String) describer[0];
				equivForFile[0] = WILD_toReturn_0;
				equivForFile[2] = WILD_toReturn_1 ;
				WILD_toReturn_1 = 2 ;
				WILD_toReturn[2] = doc ;
				loadedFiles.put(nFXml.getName(), equivForFile);
			}else{
				WILD_toReturn_1 = 3 ;
				describer[2] = WILD_toReturn_1 ;
				loadedFiles.put(nFXml.getName(), describer);
				WILD_toReturn_0 = (String) describer[0] ; 
			}
		}
		WILD_toReturn[0] = WILD_toReturn_0 ;
		WILD_toReturn[1] = WILD_toReturn_1 ;
	
		return WILD_toReturn ;
			
	}
	
	public String DVP_getPathResolve(String IdOrPath){
		File tester = new File(IdOrPath) ;
		Object[] jobIds = loadedFiles.get(tester.getName());
		// C'est un ID
		if(jobIds==null)for(Entry<String, Object[]> e:loadedFiles.entrySet())if((e.getValue()[0]).equals(IdOrPath))return 
				WILD_dObject.getDiceModelList().getProperty("qWorkspace_path") 
				+ "/" + WILD_dObject.getDiceModelList().getProperty("qTests_path")
				+ "/" +e.getKey();
		// C'est un chemin
		if(tester.exists())return IdOrPath;
		return null;
	}
	public String DVP_getIdResolve(String IdOrPath){
		File tester = new File(IdOrPath) ;
		Object[] jobIds = loadedFiles.get(tester.getName());
		// C'est un chemin
		if(jobIds!=null&&tester.exists())return (String) jobIds[0];
		// Sinon, ce peut être un ID ou rien
		if(jobIds==null)for(Entry<String, Object[]> e:loadedFiles.entrySet())if((e.getValue()[0]).equals(IdOrPath))return IdOrPath ;
		return null;
	}

}

