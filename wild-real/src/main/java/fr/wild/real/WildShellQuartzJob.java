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
import fr.wild.orchestra.WildObject;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.geotoolkit.util.DomUtilities;
import org.w3c.dom.Document;

// Dépendances, bibliothèques JAVA par exemple.


/**
 * Code généré automatiquement par l'outil Wild
 * Classe d'exécution d'un job Quartz, exécution d'un script SHELL
 */
public class WildShellQuartzJob  extends WildQuartzJob{

	// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildShellQuartzJob (){}

	// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

	// VARIABLES GLOBALES ("Fields") transmises par les constructeurs


	// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***

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
	protected void WILD_initialize_WildShellQuartzJob (
		WildObject i_WILD_dObject,
		String i_parametersXmlFile
			) throws Exception {

		// Amorce de la classe
		// Initialisation de la classe d'objet selon le schéma Wild
		WILD_dObject = i_WILD_dObject ;
		WILD_initialize_WildQuartzJob(i_WILD_dObject, i_parametersXmlFile);
		// Préparation des variables d'invocation (considérées comme champs globaux)

		// Mode try de récupération des erreurs pour log
		try {

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

	public WildShellQuartzJob (
		WildObject i_WILD_dObject,
		String i_parametersXmlFile
			) throws Exception{
		this.WILD_initialize_WildShellQuartzJob (i_WILD_dObject,i_parametersXmlFile);
	}

	@Override
	protected void DEV_execute() throws Exception {

		final File params = File.createTempFile("service", ".xml");

		try {
			final Document doc = (Document) IoCommons.param_mapToNode("service", serviceParametersMap);
			DomUtilities.write(doc, params);

			final String shell = DEV_getParameterValue(PROP_JOB_SHELL, null, null, String.class);
			final Process process = Runtime.getRuntime().exec(new String[]{shell,params.getPath()});

			// On récupere le flux sortant
			new Thread() {
				@Override
				public void run() {
					final List<String> list = new ArrayList<>();
					executionInfos.put("shellOutStream", list);
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
						String line;
						try {
							while((line = reader.readLine()) != null) {
								list.add(line);
							}
						} finally {
							reader.close();
						}
					} catch(IOException ioe) {
						WildShellQuartzJob.this.WILD_Logger.logError(ioe);
					}
				}
			}.start();

			// On récupere le flux d'erreurs
			new Thread() {
				@Override
				public void run() {
					final List<String> list = new ArrayList<>();
					executionInfos.put("shellErrorStream", list);
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
						String line;
						try {
							while((line = reader.readLine()) != null) {
								list.add(line);
							}
						} finally {
							reader.close();
						}
					} catch(IOException ioe) {
						WildShellQuartzJob.this.WILD_Logger.logError(ioe);
					}
				}
			}.start();

			final int outputCode = process.waitFor();

			if (outputCode != 0) {
				executionInfos.put("shellOutputCode", outputCode);
				throw new Exception("");
			}

		} finally {
			params.delete();
		}

	}

}

