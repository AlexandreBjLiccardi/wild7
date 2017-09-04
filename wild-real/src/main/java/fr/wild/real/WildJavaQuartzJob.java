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
import fr.wild.orchestra.Wild4Test;
import fr.wild.orchestra.WildObject;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

// Dépendances, bibliothèques JAVA par exemple.


/**
 * Code généré automatiquement par l'outil Wild
 * Classe d'exécution d'un job Quartz, exécution d'un cas JAVA
 */
public class WildJavaQuartzJob extends WildQuartzJob{

	// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildJavaQuartzJob(){}

	// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

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
	 * @param i_parametersNode
	 */
	protected void WILD_initialize_WildJavaQuartzJob(
		WildObject i_WILD_dObject,
		String i_parametersXmlFile
			) throws Exception {
		WILD_initialize_WildQuartzJob(i_WILD_dObject, i_parametersXmlFile);

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

	public WildJavaQuartzJob(
		WildObject i_WILD_dObject,
		String i_parametersXmlFile
			) throws Exception{
		this.WILD_initialize_WildJavaQuartzJob(i_WILD_dObject,i_parametersXmlFile);
	}

	@Override
	protected void DEV_execute() throws Exception {
		final Wild4Test builder = wildList.DEV_getBuilder();
		final String wildId = IoCommons.cast_getRandom();
		builder.addObject(wildId, "real", DEV_getParameterValue(PROP_JOB_JAVACLASS, null,null,String.class), new Object[]{serviceParametersMap});
		final Callable wild = builder.getObject(wildId);
		final Object result = wild.call();
		if (result!=null) executionInfos.put("result",result);
	}	

}

