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

import fr.wild.utils.PostMultiPartObject;
import fr.wild.utils.URLException;
import fr.wild.common.IoCommons;
import fr.wild.orchestra.WildObject;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Node;


/**
 * Code généré automatiquement par l'outil Wild
 * Client d'un service protocole REST
 */
public class WildRestService extends WildWebService implements Callable{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildRestService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected Map<String,Object> cookies; // cookies nécessaires au appels
	protected String cookiesStr; // chaîne concaténée pour appel des cookies
	protected String urlService; // URL du service
	protected String urlCookie; // URL pour appel de cookie


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***
	private URL url;
	private HttpURLConnection urlConnection;
	private final Map<String,String> headerMap = new HashMap<>();

// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Constructeur, appelle nécessairement WILD_initialize()
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_parametersNode	Paramètres sous forme de nœud XML{}
	 */
	public WildRestService(
			WildObject i_WILD_dObject,
			Node i_parametersNode
			)throws Exception{
		this.WILD_initialize_WildRestService(i_WILD_dObject,i_parametersNode,null,null);
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
	public WildRestService(
			WildObject i_WILD_dObject,
			String i_parametersXmlFile
			)throws Exception{
		this.WILD_initialize_WildRestService(i_WILD_dObject,null,i_parametersXmlFile,null);
	}


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Constructeur, appelle nécessairement WILD_initialize()
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_parametersMap	Paramètres sous forme de Map{}
	 */
	public WildRestService(
			WildObject i_WILD_dObject,
			Map<String,Object> i_parametersMap
			)throws Exception{
		this.WILD_initialize_WildRestService(i_WILD_dObject,null,null,i_parametersMap);
	}


// CE CONSTRUCTEUR DOIT ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Fonction d'initialisation, commune à tous les constructeurs.
	 * "Constructeur unique"
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_parametersNode	Paramètres sous forme de nœud XML{}
	 * @param i_parametersXmlFile	Paramètres sous forme de fichier XML dont on donne le lien{}
	 * @param i_parametersMap	Paramètres sous forme de Map{}
	 */
	protected void WILD_initialize_WildRestService(
			WildObject i_WILD_dObject,
			Node i_parametersNode,
			String i_parametersXmlFile,
			Map<String, Object> i_parametersMap
			) throws Exception {

		// Amorce de la classe
		// Initialisation de la classe d'objet selon le schéma Wild
		WILD_dObject = i_WILD_dObject ;
		WILD_initialize_WildWebService(i_WILD_dObject/*** Paramètres à actualiser ***/, null, null, null, null);
		// Préparation des variables d'invocation (considérées comme champs globaux)

		// Mode try de récupération des erreurs pour log
		try{
			setHeader(null, i_parametersNode, null, i_parametersXmlFile, i_parametersMap);
		}catch(Exception e){
			this.WILD_logException(e);
		}
	}

	/**
	 * Extrait à partir d'une Map de parametres les propriétés :
	 * - cookies
	 * - cookiesStr
	 * - urlService
	 * - urlCookie
	 */
	private void DEV_extractCommonProperties(Map<String,Map<String,Object>> parameters) throws Exception {
		Object cdtRequestUrl = IoCommons.param_getValue(parameters, PROP_REQUEST_URL, null, null);
		Object cdtRequestPath = IoCommons.param_getValue(parameters, PROP_REQUEST_PATH, null, null);
		if (cdtRequestUrl instanceof String) {
			urlService = (String) cdtRequestUrl;
		}
		if (cdtRequestPath instanceof String) {
			String path = (String) cdtRequestPath;
			if (urlService.endsWith("/")) {
				urlService += path.startsWith("/") ? path.substring(1) : path;
			} else {
				urlService += path.startsWith("/") ? path : "/"+path;
			}
		}

		Object cdtCookieUrl = IoCommons.param_getValue(parameters, PROP_COOKIES_URL, null, null);
		if (cdtCookieUrl instanceof String) {
			urlCookie = (String) cdtCookieUrl;
		}

		Object cdtCookieValues = IoCommons.param_getValue(parameters, PROP_COOKIES_COOKIESVALUE, null, null);
		if (cdtCookieValues instanceof String) {
			cookiesStr = (String) cdtCookieValues;
			this.cookies = new HashMap<>();
			for(String cookieSub : cookiesStr.split(";")) {
				final String key = cookieSub.split("=")[0];
				final String value = cookieSub.substring(key.length()+1);
				cookies.put(key, value);
			}
		}

		//preparation du headerMap pour les requetes
		if (cookiesStr!=null && !cookies.isEmpty()) {
			headerMap.put("Cookie", cookiesStr);
		} else {
			headerMap.remove("Cookie");
		}

	}

	/**
	 * Connexion à une URL distante (instanciation de l'objet){
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 */
	public void urlConnect() throws Exception {
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {
			//	Etape	"1" : poids relatif de 10, Vérification des prérequis
			this.WILD_setStep(); // Ne pas modifier


			//preparation des entetes
			fillAuthentication();

			//preperation des parametres
			final StringBuilder parameters = new StringBuilder();
			for (Entry<String,Map<String,Object>> entry : parametersMap.entrySet()) {
				if (entry.getKey().startsWith(TYPE_PARAMETER)) {
					if (parameters.length()!=0) {
						parameters.append('&');
					}

					final String key = entry.getKey().split("\\.")[1];
					parameters.append(URLEncoder.encode(key, "UTF-8"));
					final Map<String,Object> properties = (Map<String,Object>) entry.getValue();
					final String value = String.valueOf(properties.get("value"));
					if (value != null) {
						parameters.append('=');
						parameters.append(URLEncoder.encode(value, "UTF-8"));
					}
				}
			}

			final String mode = getParameterValue(PROP_REQUEST_QUERY, null, "POST");

			// Auto implements cookies
			String urlCookie = IoCommons.param_getValue(parametersMap,PROP_COOKIES_URL, null ,null) ;
			if(urlCookie!=null ) this.getCookie(urlCookie);

			if ("GET".equals(mode)) {
				//construction de l'url
				final StringBuilder urlBuilder = new StringBuilder(createURL(urlService));

				//ajout des parametres
				if (parameters.length() != 0) {
					if (!urlBuilder.toString().contains("?")) {
						urlBuilder.append('?');
					}
					final char c = urlBuilder.charAt(urlBuilder.length()-1);
					if (!(c == '?' || c == '&')) {
						urlBuilder.append('&');
					}
					urlBuilder.append(parameters.toString());
				}

				url = new URL(urlBuilder.toString());

				//	Etape	"2" : poids relatif de 10, Instanciation de la connexion
				this.WILD_setStep(); // Ne pas modifier
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setDoOutput( getParameterValue(PROP_REQUEST_DOOUTPUT, null, true) );
				urlConnection.setInstanceFollowRedirects( getParameterValue(PROP_REQUEST_FOLLOWREDIRECT, null, true) );
				urlConnection.setUseCaches( getParameterValue(PROP_REQUEST_USECACHES, null, false) );
				urlConnection.setRequestProperty("Connection", getParameterValue(PROP_REQUEST_CONNECTION, null, "Keep-Alive") );
				urlConnection.setRequestProperty("Content-Type", getParameterValue(PROP_REQUEST_CONTENTTYPE, null, "application/x-www-form-urlencoded"));
				urlConnection.setRequestProperty("charset", getParameterValue(PROP_REQUEST_CHARSET, null, "utf-8"));
				if(this.cookiesStr!=null)urlConnection.setRequestProperty("Cookie", cookiesStr );// Auto implements cookies
				for (Entry<String,String> headerParam : headerMap.entrySet()) {
					urlConnection.addRequestProperty(headerParam.getKey(), headerParam.getValue());
				}

			} else if ("POST".equals(mode)) {
				final String urlString = createURL(urlService);
				url = new URL(urlString);

				//	Etape	"2" : poids relatif de 10, Instanciation de la connexion
				this.WILD_setStep(); // Ne pas modifier
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setDoOutput( true );
				urlConnection.setRequestMethod( "POST" );
				urlConnection.setDoOutput( getParameterValue(PROP_REQUEST_DOOUTPUT, null, true) );
				urlConnection.setInstanceFollowRedirects( getParameterValue(PROP_REQUEST_FOLLOWREDIRECT, null, true) );
				urlConnection.setUseCaches( getParameterValue(PROP_REQUEST_USECACHES, null, false) );
				urlConnection.setRequestProperty("Connection", getParameterValue(PROP_REQUEST_CONNECTION, null, "Keep-Alive") );
				urlConnection.setRequestProperty("Content-Type", getParameterValue(PROP_REQUEST_CONTENTTYPE, null, "application/x-www-form-urlencoded"));
				if(this.cookiesStr!=null)urlConnection.setRequestProperty("Cookie", cookiesStr );// Auto implements cookies
				final String charset = getParameterValue(PROP_REQUEST_CHARSET, null, "utf-8");
				urlConnection.setRequestProperty("charset", charset);


				for (Entry<String,String> headerParam : headerMap.entrySet()) {
					urlConnection.addRequestProperty(headerParam.getKey(), headerParam.getValue());
				}

				//envoi des parametres
				List multipart = new ArrayList();
				Object preqList =	getParameterValue(PROP_REQUEST_MULTIPART, null, null);
				
				if(preqList instanceof List)multipart = (List) preqList ;
				else multipart.add(preqList);
				if (preqList==null||multipart==null || multipart.isEmpty()) {
					try (OutputStream out = urlConnection.getOutputStream()) {
						out.write(parameters.toString().getBytes());
						out.flush();
					}
				} else {
					final String boundary = Long.toHexString(System.currentTimeMillis());
					urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);

					//on converti les parametres en multi-part
					for (Entry<String,Map<String,Object>> entry : parametersMap.entrySet()) {
						
						if (entry.getKey().startsWith(TYPE_PARAMETER)) {
							final Map<String,Object> properties = (Map<String,Object>) entry.getValue();
							final String key = entry.getKey().split("\\.")[1];
							final String name = URLEncoder.encode(key, "UTF-8");
							final String value = URLEncoder.encode(String.valueOf(properties.get("value")),"UTF-8");
							multipart.add(0,new PostMultiPartObject(name, null, value));
						}
					}

					try (OutputStream out = urlConnection.getOutputStream();) {
						DEV_writeMultiPart(multipart, out, charset, boundary);
					}
				}

			} else {
				throw new Exception("Mode de requête (GET/POST) incorrecte : "+mode);
			}

		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
	}

	/**
	 * Récupération d'un cookie correspondant à une adresse distante{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_urlCookie	URL spécifique de connexion pour obtention du cookie{}
	 * @param i_varCookies	nom éventuel des variables à retenir, si null les retient tous{}
	 * @return	{}
	 */
	public String getCookie (
			String i_urlCookie,
			List<String> i_varCookies
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		String WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try{

			//	Etape	"1" : poids relatif de 10, Vérification des prérequis
			this.WILD_setStep(); // Ne pas modifier
			if (i_urlCookie == null) {
				i_urlCookie = urlCookie;
			}
			if (i_urlCookie == null) {
				throw new Exception("URL des cookies non définie.");
			}

			final List<String> cookieIds = getParameterValue(PROP_COOKIES_COOKIESLIST, i_varCookies, null);

			//	Etape	"2" : poids relatif de 10, Connexion à l'adresse spécifique pour récupération de cookie
			this.WILD_setStep(); // Ne pas modifier
			final URL url = new URL(i_urlCookie);
			final HttpURLConnection cnx = (HttpURLConnection) url.openConnection();

			//	Etape	"3" : poids relatif de 10, Envoi de la requête
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"4" : poids relatif de 10, Récupération des variables passées en cookie
			this.WILD_setStep(); // Ne pas modifier
			final Map<String, List<String>> headerFields = cnx.getHeaderFields();

			//	Etape	"5" : poids relatif de 10, Compilation des résultats
			this.WILD_setStep(); // Ne pas modifier
			final List<String> cookiesHeader = headerFields.get("Set-Cookie");
			final StringBuilder sb = new StringBuilder();
			if (cookiesHeader != null) {
				for (int i=0,n=cookiesHeader.size(); i<n; i++) {
					final String cookie = cookiesHeader.get(i);
					if (cookie!=null) {
						final String cookieSub = cookie.split(";")[0];
						final String key = cookieSub.split("=")[0];
						if (cookieIds==null || cookieIds.contains(key)) {
							if (i!=0) sb.append(';');
							sb.append(cookiesHeader.get(i).split(";")[0]);
						}
					}
				}
			}
			WILD_toReturn = sb.toString();
			//on affecte les cookies a la classe courante
			parametersMap.put(PROP_COOKIES_COOKIESVALUE, Collections.singletonMap("value", (Object)WILD_toReturn));
			DEV_extractCommonProperties(parametersMap);

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

	/**
	 * Récupération d'un cookie correspondant à une adresse distante{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlCookie	URL spécifique de connexion pour obtention du cookie {}
	 * @return	Valeur du cookie(String) {}
	 */
	public String getCookie(
			String i_urlCookie
			) throws Exception {
		return getCookie(i_urlCookie, null);
	}

/*	### NOUVELLE METHODE ###
	Méthode : setCookie - Affectation d'un cookie à une instance de connexion URL{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Affectation d'un cookie à une instance de connexion URL{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_valuesCookiesMap	Map de description des variables de cookies{}
	 * @param i_valuesCookiesStr	Chaîne de caractères contenant les valeurs de cookies{}
	 */
	public void setCookie(
			Map<String, Object> i_valuesCookiesMap,
			String i_valuesCookiesStr
			) throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try{
			//	Etape	"1" : poids relatif de 10, Construction de la chaîne de caractères de concaténation des variables
			this.WILD_setStep(); // Ne pas modifier

			if (i_valuesCookiesMap!=null && i_valuesCookiesStr==null) {
				final StringBuilder sb = new StringBuilder();
				for (Map.Entry<String,Object> entry : cookies.entrySet()) {
					if(sb.length()!=0) sb.append(';');
					sb.append(entry.getKey()).append("=").append(entry.getValue());
				}
				i_valuesCookiesStr = sb.toString();
			}
			if	(i_valuesCookiesStr!=null) {
				parametersMap.put(PROP_COOKIES_COOKIESVALUE, Collections.singletonMap("value", (Object)i_valuesCookiesStr));
			}
			DEV_extractCommonProperties(parametersMap);

			//	Etape	"2" : poids relatif de 10, Passage des variables à l'URL
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
	}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
	/**
	 * Affectation d'un cookie à une instance de connexion URL{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_valuesCookiesMap	Map de description des variables de cookies {}
	 */
	public void setCookie (
			Map<String,Object> i_valuesCookiesMap
			)throws Exception{
		setCookie(i_valuesCookiesMap,null);
	}

	/**
	 * Affectation d'un cookie à une instance de connexion URL{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_valuesCookiesStr	Chaîne de caractères contenant les valeurs de cookies {}
	 */
	public void setCookie (
			String i_valuesCookiesStr
			)throws Exception{
		setCookie(null,i_valuesCookiesStr);
	}

	/**
	 * Construction de l'amorce de requête (chaînage des variables) - appel des cas POST ou GET{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_urlConnexion	URL de connexion{}
	 * @param i_xmlParameters	Paramètres sous forme XML{}
	 * @param i_restMode	Protocole GET ou POST{}
	 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres{}
	 * @param i_xmlParametersMap	Paramètres sous forme de Map{}
	 */
	public void setHeader (
			String i_urlConnexion,
			Node i_xmlParameters,
			String i_restMode,
			String i_xmlParametersFile,
			Map<String,Object> i_xmlParametersMap
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try{

			//	Etape	"1" : poids relatif de 10, Identification de la structure de passage de variables (Map ou XML)
			this.WILD_setStep(); // Ne pas modifier

			final Map<String,Map<String,Object>> surcharge = IoCommons.param_readParameters(i_xmlParameters, i_xmlParametersFile, (Map)i_xmlParametersMap);
			if (surcharge != null) {
				this.parametersMap.putAll(surcharge);
			}
			if (i_restMode != null) {
				this.parametersMap.put(PROP_REQUEST_QUERY, Collections.singletonMap("value", (Object)i_restMode));
			}
			if (i_urlConnexion != null) {
				this.parametersMap.put(PROP_REQUEST_URL, Collections.singletonMap("value", (Object)i_urlConnexion));
			}

			//	Etape	"2" : poids relatif de 10, Vérification des prérequis
			this.WILD_setStep(); // Ne pas modifier
			DEV_extractCommonProperties(parametersMap);

			//	Etape	"3" : poids relatif de 10, Appel de la méthode POST ou GET
			this.WILD_setStep(); // Ne pas modifier


		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
	}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

	/**
	 * Construction de l'amorce de requête (chaînage des variables) - appel des cas POST ou GET{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL de connexion {}
	 * @param i_xmlParameters	Paramètres sous forme XML {}
	 * @param i_restMode	Protocole GET ou POST {}
	 */
	public void setHeader(
			String i_urlConnexion,
			Node i_xmlParameters,
			String i_restMode
			)throws Exception{
		setHeader(i_urlConnexion,i_xmlParameters,i_restMode,null,null);
	}

	/**
	 * Construction de l'amorce de requête (chaînage des variables) - appel des cas POST ou GET{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL de connexion {}
	 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres {}
	 * @param i_restMode	Protocole GET ou POST {}
	 */
	public void setHeader(
			String i_urlConnexion,
			String i_xmlParametersFile,
			String i_restMode
			)throws Exception{
		setHeader(i_urlConnexion,null,i_restMode,i_xmlParametersFile,null);
	}

	/**
	 * Construction de l'amorce de requête (chaînage des variables) - appel des cas POST ou GET{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL de connexion {}
	 * @param i_xmlParametersMap	Paramètres sous forme de Map {}
	 * @param i_restMode	Protocole GET ou POST {}
	 */
	 public void setHeader (
			String i_urlConnexion,
			Map<String,Object> i_xmlParametersMap,
			String i_restMode
			)throws Exception{
		setHeader(i_urlConnexion,null,i_restMode,null,i_xmlParametersMap);
	}

/*	### NOUVELLE METHODE ###
	Méthode : setGetHeader - Construction de l'amorce de requête (chaînage des variables) - GET{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Construction de l'amorce de requête (chaînage des variables) - GET{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_urlConnexion	URL de connexion{}
	 * @param i_xmlParameters	Paramètres sous forme XML{}
	 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres{}
	 * @param i_xmlParametersMap	Paramètres sous forme de Map{}
	 */
	public void setGetHeader (
			String i_urlConnexion,
			Node i_xmlParameters,
			String i_xmlParametersFile,
			Map<String,Object> i_xmlParametersMap
			)throws Exception{
		setHeader(i_urlConnexion, i_xmlParameters, "GET", i_xmlParametersFile, i_xmlParametersMap);
	}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
	/**
	 * Construction de l'amorce de requête (chaînage des variables) - GET{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL de connexion {}
	 * @param i_xmlParameters	Paramètres sous forme XML {}
	 */
	public void setGetHeader (
			String i_urlConnexion,
			Node i_xmlParameters
			)throws Exception{
		setGetHeader(i_urlConnexion,i_xmlParameters,null,null);
	}

	/**
	 * Construction de l'amorce de requête (chaînage des variables) - GET{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL de connexion {}
	 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres {}
	 */
	public void setGetHeader (
			String i_urlConnexion,
			String i_xmlParametersFile
			)throws Exception{
		 setGetHeader(i_urlConnexion,null,i_xmlParametersFile,null);
	}

	/**
	 * Construction de l'amorce de requête (chaînage des variables) - GET{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL de connexion {}
	 * @param i_xmlParametersMap	Paramètres sous forme de Map {}
	 */
	public void setGetHeader (
			String i_urlConnexion,
			Map<String,Object> i_xmlParametersMap
			)throws Exception{
		setGetHeader(i_urlConnexion,null,null,i_xmlParametersMap);
	}

/*	### NOUVELLE METHODE ###
	Méthode : setPostHeader - Construction de l'amorce de requête (chaînage des variables) - POST{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Construction de l'amorce de requête (chaînage des variables) - POST{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_urlConnexion	URL de connexion{}
	 * @param i_xmlParameters	Paramètres sous forme XML{}
	 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres{}
	 * @param i_xmlParametersMap	Paramètres sous forme de Map{}
	 */
	public void setPostHeader (
			String i_urlConnexion,
			Node i_xmlParameters,
			String i_xmlParametersFile,
			Map<String,Object> i_xmlParametersMap
			)throws Exception{
		setHeader(i_urlConnexion, i_xmlParameters, "POST", i_xmlParametersFile, i_xmlParametersMap);
	}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

	/**
	 * Construction de l'amorce de requête (chaînage des variables) - POST{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL de connexion {}
	 * @param i_xmlParameters	Paramètres sous forme XML {}
	 */
	public void setPostHeader (
			String i_urlConnexion,
			Node i_xmlParameters
			)throws Exception{
		setPostHeader(i_urlConnexion,i_xmlParameters,null,null);
	}

	/**
	 * Construction de l'amorce de requête (chaînage des variables) - POST{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL de connexion {}
	 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres {}
	 */
	public void setPostHeader (
			String i_urlConnexion,
			String i_xmlParametersFile
			)throws Exception{
		setPostHeader(i_urlConnexion,null,i_xmlParametersFile,null);
	}

	/**
	 * Construction de l'amorce de requête (chaînage des variables) - POST{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL de connexion {}
	 * @param i_xmlParametersMap	Paramètres sous forme de Map {}
	 */
	public void setPostHeader (
			String i_urlConnexion,
			Map<String,Object> i_xmlParametersMap
			)throws Exception{
		setPostHeader(i_urlConnexion,null,null,i_xmlParametersMap);
	}

/*	### NOUVELLE METHODE ###
	Méthode : getDatas - Récupération des données pour enregistrement dans un fichier (capture de flux){
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Récupération des données pour enregistrement dans un fichier (capture de flux){
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public String getDatas ()throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		String WILD_toReturn; // Ne pas modifier

		//chemin de sortie
		/*String outPath = (String) getParameterValue(PROP_BEHAVIOR_OUTPUTFILE+"#quartz", null, null);
		if (outPath == null)*/String outPath = (String) getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null)+"/out.txt";
	//	else outPath += "/out.txt";
		if (outPath == null) throw new Exception("Fichier de sortie de la réponse non défini");
		WILD_toReturn = outPath;
		try{(new File(outPath)).getParentFile().mkdirs();}catch(Exception e){e.printStackTrace();}
		
		//preparation des statistiques
		String errorServiceCode = "0";
		long timeService = 0;
		boolean serviceSuccess = false;
		String requestURL = url.toString();
		Node parametersRequestURL = IoCommons.param_mapToNode("service",parametersMap);
		final int timeOut = getParameterValue(PROP_REQUEST_TIMEOUT, null, 0);

		final String outputMode = getParameterValue(PROP_BEHAVIOR_OUTPUTFILEMODE, null, "OVERRIDE");
		final boolean append = "APPEND".equalsIgnoreCase(outputMode);

		// Mode try de récupération des erreurs pour log
		final long before = System.currentTimeMillis();
		try{

			//	Etape	"1" : poids relatif de 10, Création des flux entrants et sortants
			this.WILD_setStep(); // Ne pas modifier
			//	Etape	"2" : poids relatif de 10, Capture du flux
			this.WILD_setStep(); // Ne pas modifier
			final InputStream in = DEV_openStream(urlConnection,timeOut);
			try (OutputStream out = new BufferedOutputStream(new FileOutputStream(outPath,append))) {
				IOUtils.copy(in, out);
				out.flush();
			}
			in.close();
			errorServiceCode = ((Integer)urlConnection.getResponseCode()).toString();
			serviceSuccess = true;
			//	Etape	"3" : poids relatif de 10, Vérification des flux et clôture
			this.WILD_setStep(); // Ne pas modifier
			checkDatas(outPath, null);
		}catch(Exception e){
			if(e.getCause()!=null&&(Exception) e.getCause() instanceof URLException)e=(Exception) e.getCause();
			//this.WILD_logException(e); // Ne pas modifier
			serviceSuccess = false;
			if (IoCommons.execution_isInterrupt(e)) errorServiceCode = "url-client-thread-interruption" ;
			else errorServiceCode = ((Integer)urlConnection.getResponseCode()).toString() ;
			if(e instanceof URLException)stats.put(STAT_ERROR_SERVICE_NODE,((URLException)e).getContentNode());
			else stats.put(STAT_ERROR_SERVICE_NODE,IoCommons.cast_String2Node("<Errors status = \"URL error\">"+IoCommons.cast_xmlEscape(e.toString())+"</Errors>"));
			stats.put("JAVA_ERROR", e);
			throw e;
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
			if (urlConnection!=null) {
				urlConnection.disconnect();
			}
			//statistiques
			final long after = System.currentTimeMillis();
			timeService = after-before;
			stats.put(STAT_ERROR_SERVICE_CODE,		errorServiceCode);
			stats.put(STAT_TIME_SERVICE,			timeService);
			stats.put(STAT_SERVICE_SUCCESS,			serviceSuccess);
			stats.put(STAT_REQUEST_URL,				requestURL);
			stats.put(STAT_PARAMETERS_REQUEST_URL,  parametersRequestURL);
			stats.put(STAT_PROC_END_KEY, IoCommons.date_now());
			stats.put(STAT_PROC_END_KEY+"_ts", IoCommons.date_nowLong());
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	/**
	 * Ajout de l'authentificiation BASIC dans headerMap en utilisant les parametres :
	 * <ul>
	 * <li>clientId</li>
	 * <li>clientPwd</li>
	 * </ul>
	 */
	protected void fillAuthentication() {
		if (clientId != null && clientPwd != null) {
			final String userPassword = clientId + ":" + clientPwd;
			final String encoding = Base64.encodeBase64URLSafeString(userPassword.getBytes());
			headerMap.put("Authorization", "Basic " + encoding);
		} else {
			//au cas où cela aurait changé
			headerMap.remove("Authorization");
		}
    }

	/**
	 * Java ne suit pas les URLs directement s'il y a un changement de protocol.
     * Voir : http://bugs.java.com/bugdatabase/view_bug.do?bug_id=4620571
     *
     * @param cnx
	 * @param timeOut 0 pour la valeur par defaut
     * @return
	 * @throws Exception 
     */
    protected InputStream DEV_openStream(URLConnection cnx, int timeOut) throws Exception{
        while (cnx instanceof HttpURLConnection) {
            HttpURLConnection httpCnx = (HttpURLConnection) cnx;

            final InputStream is = openRichException(httpCnx,timeOut);
            final int status = httpCnx.getResponseCode();
            final boolean redirect = status == HttpURLConnection.HTTP_MOVED_TEMP
                                  || status == HttpURLConnection.HTTP_MOVED_PERM
                                  || status == HttpURLConnection.HTTP_SEE_OTHER;

            if (redirect) {
                // get redirection url
                final String newUrl = httpCnx.getHeaderField("Location");
                // get new cookies
                final String cookies = httpCnx.getHeaderField("Set-Cookie");
                is.close();

                // open redirection
                httpCnx = (HttpURLConnection) new URL(newUrl).openConnection();

                //Set all fields from the headerMap to the properties of this URLConnection.
                for(final Entry<String,String> entry : headerMap.entrySet()){
                    httpCnx.setRequestProperty(entry.getKey(),entry.getValue());
                }
                httpCnx.setRequestProperty("Cookie", cookies);

				cnx = httpCnx;
            } else {
                return is;
            }
        }

        return openRichException(cnx,timeOut);
    }

	/**
	 * Ecriture des form/multi-part
	 */
	static void DEV_writeMultiPart(List multipart, OutputStream out, String charset, String boundary) throws IOException {
		final String crlf = "\r\n";

		try (
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, charset), true);
		) {
			//add attached datas
			for (int i=0,n=multipart.size();i<n;i++) {
				Object cdt = multipart.get(i);
				
				final PostMultiPartObject part;
				if (cdt instanceof PostMultiPartObject) {
					part = (PostMultiPartObject) cdt;
				} else {
					part = new PostMultiPartObject((Map)null,null,cdt);
				}

				writer.append("--" + boundary).append(crlf);
				part.writePart(writer, out);
			}

			//end multipart.
			writer.append("--" + boundary + "--").append(crlf).flush();
			out.flush();
		}
	}


	@Override
	public Object call() throws Exception {
		try{
			urlConnect();
			getDatas();
		}catch(Exception e){
			throw e;
		}finally{
			this.DEV_dumpStats();
		}
		return null;
	}

}

