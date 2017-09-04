
package fr.wild.real;

import fr.wild.common.IoCommons;
import java.util.Map;
import org.apache.sis.test.XMLComparator;
import org.geotoolkit.util.DomUtilities;
import org.junit.Test;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class WildSoapServiceTest {

	private static final String PARAM1 =
			"<service>\n" +
			"	<parameter id=\"maxJobDuration\" type=\"behavior\">\n" +
			"		<name>Durée maximale d'exécution, en seconde</name>\n" +
			"		<value>500</value>\n" +
			"	</parameter>\n" +
			"	<parameter id=\"url\" type=\"request\">\n" +
			"		<name>Adresse WSDL du service</name>\n" +
			"		<value>http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2/Monitoring.wsdl</value>\n" +
			"	</parameter>\n" +
			"	<operation id=\"getData\">\n" +
			"			<name>Nom de l'opération demandée au service distant</name>\n" +
			"		<parameter id=\"domain\" type=\"parameter\" position=\"domain\">\n" +
			"			<name>Nom de domaine SANDRE</name>\n" +
			"			<value>3.1</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"cdSite\" type=\"parameter\" position=\"sites/CdSite\">\n" +
			"			<value>05179750</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"schemeAgencyID\" type=\"parameter\" position=\"sites/CdSite/@schemeAgencyID\">\n" +
			"			<name>Code de schéma SANDRE</name>\n" +
			"			<value>1</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"DateDebutDonnees\" type=\"parameter\" position=\"temporalConstraints/DateDebutDonnees\">\n" +
			"			<name>Date de début des données pour récupération des analyses</name>\n" +
			"			<value>2012-01-01T00:00:00</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"DateFinDonnees\" type=\"parameter\" position=\"temporalConstraints/DateFinDonnees\">\n" +
			"			<name>Date de fin des données pour récupération des analyses</name>\n" +
			"			<value>2016-12-31T23:59:59</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"analyticConstraints\" type=\"parameter\" position=\"analyticConstraints\">\n" +
			"			<name>Contraintes analytiques supplémentaires</name>\n" +
			"			<value></value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"outputSchema\" type=\"parameter\" position=\"outputSchema\">\n" +
			"			<name>Schéma de contrôle en sortie</name>\n" +
			"			<value>http://xml.sandre.eaufrance.fr/scenario/quesu/3/sandre_sc_quesu.xsd</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"outputFormat\" type=\"parameter\" position=\"outputFormat\">\n" +
			"			<name>Format de fichier en sortie</name>\n" +
			"			<value>text/xml</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"xsd_control\" type=\"behavior\">\n" +
			"			<name>Fichier XSD de contrôle en sortie</name>\n" +
			"			<value>http://xml.sandre.eaufrance.fr/scenario/quesu/2/sandre_sc_quesu.xsd</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"key\" type=\"result\" position=\"//ANALYSE\">\n" +
			"			<name>xPath de la \"clé\" de décompte des éléments produits</name>\n" +
			"		</parameter>\n" +
			"	</operation>\n" +
			"</service>";

	private static final String PARAM2 =
			"<service>\n" +
			"	<parameter id=\"maxJobDuration\" type=\"behavior\">\n" +
			"		<name>Durée maximale d'exécution, en seconde</name>\n" +
			"		<value>3600</value>\n" +
			"	</parameter>\n" +
			"<!-- Les paramètres hors \"operation\" s'appliquent pour tous les appels. Essentiellement : types {behaviors} -->\n" +
			"	<parameter id=\"url\" type=\"request\">\n" +
			"		<name>Adresse WSDL du service</name>\n" +
			"		<value>http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2/Monitoring.wsdl</value>\n" +
			"		<!-- AEAG : le mieux spécifié, donc l'exemple est le plus \"light\". -->\n" +
			"	</parameter>\n" +
			"	<parameter id=\"typeTest\" type=\"behavior\">\n" +
			"		<name>Type de test</name>\n" +
			"		<value>complete</value>\n" +
			"		<!-- On va donc faire appel au parseur SANDRE ! -->\n" +
			"	</parameter>\n" +
			"	<parameter id=\"xsdControl\" type=\"behavior\">\n" +
			"			<name>Fichier XSD de contrôle en sortie</name>\n" +
			"			<value>http://xml.sandre.eaufrance.fr/scenario/quesu/2/sandre_sc_quesu.xsd</value>\n" +
			"	</parameter>\n" +
			"	<parameter id=\"n_codesSite\" type=\"behavior\">\n" +
			"			<name>Taille de l'échantillon de sites</name>\n" +
			"			<value>10</value>\n" +
			"	</parameter>\n" +
			"	<parameter id=\"allAtOnce\" type=\"behavior\">\n" +
			"			<name>Tous les sites dans une même requête ?</name>\n" +
			"			<value>true</value>\n" +
			"			<!-- Donc, pour les trois dernières opérations, on aura une liste de 10 sites passée en paramètres -->\n" +
			"	</parameter>\n" +
			"<!-- Pour chaque opération, les paramètres spécifiques -->	\n" +
			"<!-- Opération 1, getSites -->	\n" +
			"	<operation order = \"1\" id = \"getSites\">\n" +
			"			<name>Nom de l'opération demandée au service distant</name>\n" +
			"		<parameter id=\"domain\" type=\"parameter\" position=\"domain\">\n" +
			"			<name>Nom de domaine SANDRE</name>\n" +
			"			<value>3.1</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"outputSchema\" type=\"parameter\" position=\"outputSchema\">\n" +
			"			<name>Schéma de contrôle en sortie</name>\n" +
			"			<value>http://xml.sandre.eaufrance.fr/scenario/quesu/3/sandre_sc_quesu.xsd</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"outputFormat\" type=\"parameter\" position=\"outputFormat\">\n" +
			"			<name>Format de fichier en sortie</name>\n" +
			"			<value>text/xml</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"key\" type=\"result\">\n" +
			"			<name>xPath de la \"clé\" de décompte des éléments produits</name>\n" +
			"			<value>//CdSite/text()[1]</value>\n" +
			"		</parameter>\n" +
			"	</operation>\n" +
			"<!-- Pour chaque opération, les paramètres spécifiques -->	\n" +
			"<!-- Opération 2, getSiteDescription -->		\n" +
			"	<operation order = \"2\" id = \"getSiteDescription\">\n" +
			"			<name>Nom de l'opération demandée au service distant</name>\n" +
			"		<parameter id=\"domain\" type=\"parameter\" position=\"domain\">\n" +
			"			<name>Nom de domaine SANDRE</name>\n" +
			"			<value>3.1</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"key\" type=\"result\" position = \"//StationMesure/CdStationMesureEauxSurface/text()[1]\">\n" +
			"			<name>xPath de la \"clé\" de décompte des éléments produits</name>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"list_CdSite\" type=\"parameter\" position=\"sites/CdSite\">\n" +
			"			<name>xPath de la \"clé\" de décompte des éléments produits</name>\n" +
            "			<value>05169050</value>\n" +
            "			<value>05173420</value>\n" +
            "			<value>05173350</value>\n" +
            "			<value>05180700</value>\n" +
            "			<value>05167450</value>\n" +
            "			<value>05173300</value>\n" +
            "			<value>05180550</value>\n" +
            "			<value>05180210</value>\n" +
            "			<value>05172350</value>\n" +
            "			<value>05176130</value>\n" +
            "			<value>05176900</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"outputSchema\" type=\"parameter\" position=\"outputSchema\">\n" +
			"			<name>Schéma de contrôle en sortie</name>\n" +
			"			<value>http://xml.sandre.eaufrance.fr/scenario/quesu/3/sandre_sc_quesu.xsd</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"outputFormat\" type=\"parameter\" position=\"outputFormat\">\n" +
			"			<name>Format de fichier en sortie</name>\n" +
			"			<value>text/xml</value>\n" +
			"		</parameter>\n" +
			"	</operation>\n" +
			"<!-- Opération 3, getDataAvailability -->		\n" +
			"	<operation order = \"3\" id = \"getDataAvailability\">\n" +
			"			<name>Nom de l'opération demandée au service distant</name>\n" +
			"		<parameter id=\"domain\" type=\"parameter\" position=\"domain\">\n" +
			"			<name>Nom de domaine SANDRE</name>\n" +
			"			<value>3.1</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"list_CdSite\" type=\"parameter\" position=\"sites/CdSite\">\n" +
			"			<name>xPath de la \"clé\" de décompte des éléments produits</name>\n" +
            "			<value>05169050</value>\n" +
            "			<value>05173420</value>\n" +
            "			<value>05173350</value>\n" +
            "			<value>05180700</value>\n" +
            "			<value>05167450</value>\n" +
            "			<value>05173300</value>\n" +
            "			<value>05180550</value>\n" +
            "			<value>05180210</value>\n" +
            "			<value>05172350</value>\n" +
            "			<value>05176130</value>\n" +
            "			<value>05176900</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"maxYear\" type=\"result\" position=\"max(//Annee)\">\n" +
			"			<name>xPath de l'année de l'analyse la plus récente</name>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"minYear\" type=\"result\" position=\"min(//Annee)\">\n" +
			"			<name>xPath de l'année de l'analyse la plus ancienne</name>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"outputSchema\" type=\"parameter\" position=\"outputSchema\">\n" +
			"			<name>Schéma de contrôle en sortie</name>\n" +
			"			<value>http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"outputFormat\" type=\"parameter\" position=\"outputFormat\">\n" +
			"			<name>Format de fichier en sortie</name>\n" +
			"			<value>text/xml</value>\n" +
			"		</parameter>\n" +
			"	</operation>	\n" +
			"	\n" +
			"<!-- Opération 4, getData -->	\n" +
			"	<operation order = \"4\" id=\"getData\">\n" +
			"			<name>Nom de l'opération demandée au service distant</name>\n" +
			"		<parameter id=\"domain\" type=\"parameter\" position=\"domain\">\n" +
			"			<name>Nom de domaine SANDRE</name>\n" +
			"			<value>3.1</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"xsd_control\" type=\"behavior\">\n" +
			"			<name>Fichier XSD de contrôle en sortie</name>\n" +
			"			<value>http://xml.sandre.eaufrance.fr/scenario/quesu/2/sandre_sc_quesu.xsd</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"key\" type=\"result\" position=\"//ANALYSE\">\n" +
			"			<name>xPath de la \"clé\" de décompte des éléments produits</name>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"list_CdSite\" type=\"parameter\" position=\"sites/CdSite\">\n" +
			"			<name>xPath de la \"clé\" de décompte des éléments produits</name>\n" +
            "			<value>05169050</value>\n" +
            "			<value>05173420</value>\n" +
            "			<value>05173350</value>\n" +
            "			<value>05180700</value>\n" +
            "			<value>05167450</value>\n" +
            "			<value>05173300</value>\n" +
            "			<value>05180550</value>\n" +
            "			<value>05180210</value>\n" +
            "			<value>05172350</value>\n" +
            "			<value>05176130</value>\n" +
            "			<value>05176900</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"DateDebutDonnees\" type=\"parameter\" position=\"temporalConstraints/DateDebutDonnees\">\n" +
			"			<name>Date de début des données pour récupération des analyses</name>\n" +
			"			<value>2006-01-01T00:00:00</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"DateFinDonnees\" type=\"parameter\" position=\"temporalConstraints/DateFinDonnees\">\n" +
			"			<name>Date de fin des données pour récupération des analyses</name>\n" +
			"			<value>2014-12-31T23:59:59</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"analyticConstraints\" type=\"parameter\" position=\"analyticConstraints\">\n" +
			"			<name>Contraintes analytiques supplémentaires</name>\n" +
			"			<value></value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"outputSchema\" type=\"parameter\" position=\"outputSchema\">\n" +
			"			<name>Schéma de contrôle en sortie</name>\n" +
			"			<value>http://xml.sandre.eaufrance.fr/scenario/quesu/3/sandre_sc_quesu.xsd</value>\n" +
			"		</parameter>\n" +
			"		<parameter id=\"outputFormat\" type=\"parameter\" position=\"outputFormat\">\n" +
			"			<name>Format de fichier en sortie</name>\n" +
			"			<value>text/xml</value>\n" +
			"		</parameter>\n" +
			"	</operation>\n" +
			"\n" +
			"</service>";


	private static final String MESSAGE1 =
			"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2\">\n" +
			"   <soapenv:Header/>\n" +
			"   <soapenv:Body>\n" +
			"      <ns:getData>\n" +
			"         <ns:domain>3.1</ns:domain>\n" +
			"         <ns:sites>\n" +
			"            <ns:CdSite schemeAgencyID=\"1\">05179750</ns:CdSite>\n" +
			"         </ns:sites>\n" +
			"         <ns:temporalConstraints>\n" +
			"            <ns:DateDebutDonnees>2012-01-01T00:00:00</ns:DateDebutDonnees>\n" +
			"            <ns:DateFinDonnees>2016-12-31T23:59:59</ns:DateFinDonnees>\n" +
			"         </ns:temporalConstraints>\n" +
			"         <ns:analyticConstraints>\n" +
			"         </ns:analyticConstraints>\n" +
			"          <ns:outputSchema>http://xml.sandre.eaufrance.fr/scenario/quesu/3/sandre_sc_quesu.xsd</ns:outputSchema>\n" +
			"         <ns:outputFormat>text/xml</ns:outputFormat>\n" +
			"      </ns:getData>\n" +
			"   </soapenv:Body>\n" +
			"</soapenv:Envelope>";

	private static final String MESSAGE_GETSITES =
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2\">\n" +
			"   <soapenv:Header/>\n" +
			"   <soapenv:Body>\n" +
			"      <ns:getSites>\n" +
			"         <ns:domain>3.1</ns:domain>\n" +
			"         <ns:outputSchema>http://xml.sandre.eaufrance.fr/scenario/quesu/3/sandre_sc_quesu.xsd</ns:outputSchema>\n" +
			"         <ns:outputFormat>text/xml</ns:outputFormat>\n" +
			"      </ns:getSites>\n" +
			"   </soapenv:Body>\n" +
			"</soapenv:Envelope>";

	private static final String MESSAGE_GETSITEDESCRIPTION =
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2\">\n" +
			"   <soapenv:Header/>\n" +
			"   <soapenv:Body>\n" +
			"      <ns:getSiteDescription>\n" +
			"         <ns:domain>3.1</ns:domain>\n" +
			"         <ns:sites>\n" +
			"            <ns:CdSite>05169050</ns:CdSite>\n" +
			"            <ns:CdSite>05173420</ns:CdSite>\n" +
			"            <ns:CdSite>05173350</ns:CdSite>\n" +
			"            <ns:CdSite>05180700</ns:CdSite>\n" +
			"            <ns:CdSite>05167450</ns:CdSite>\n" +
			"            <ns:CdSite>05173300</ns:CdSite>\n" +
			"            <ns:CdSite>05180550</ns:CdSite>\n" +
			"            <ns:CdSite>05180210</ns:CdSite>\n" +
			"            <ns:CdSite>05172350</ns:CdSite>\n" +
			"            <ns:CdSite>05176130</ns:CdSite>\n" +
			"            <ns:CdSite>05176900</ns:CdSite>\n" +
			"         </ns:sites>\n" +
			"         <ns:outputSchema>http://xml.sandre.eaufrance.fr/scenario/quesu/3/sandre_sc_quesu.xsd</ns:outputSchema>\n" +
			"         <ns:outputFormat>text/xml</ns:outputFormat>\n" +
			"      </ns:getSiteDescription>\n" +
			"   </soapenv:Body>\n" +
			"</soapenv:Envelope>";

	private static final String MESSAGE_GETDATAAVAILIBILITY =
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2\">\n" +
			"   <soapenv:Header/>\n" +
			"   <soapenv:Body>\n" +
			"      <ns:getDataAvailability>\n" +
			"         <ns:domain>3.1</ns:domain>\n" +
			"         <ns:sites>\n" +
			"           <ns:CdSite>05169050</ns:CdSite>\n" +
			"            <ns:CdSite>05173420</ns:CdSite>\n" +
			"            <ns:CdSite>05173350</ns:CdSite>\n" +
			"            <ns:CdSite>05180700</ns:CdSite>\n" +
			"            <ns:CdSite>05167450</ns:CdSite>\n" +
			"            <ns:CdSite>05173300</ns:CdSite>\n" +
			"            <ns:CdSite>05180550</ns:CdSite>\n" +
			"            <ns:CdSite>05180210</ns:CdSite>\n" +
			"            <ns:CdSite>05172350</ns:CdSite>\n" +
			"            <ns:CdSite>05176130</ns:CdSite>\n" +
			"            <ns:CdSite>05176900</ns:CdSite>\n" +
			"         </ns:sites>\n" +
			"         <ns:outputSchema>http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2</ns:outputSchema>\n" +
			"         <ns:outputFormat>text/xml</ns:outputFormat>\n" +
			"      </ns:getDataAvailability>\n" +
			"   </soapenv:Body>\n" +
			"</soapenv:Envelope>";

		private static final String MESSAGE_GETDATA =
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2\">\n" +
			"   <soapenv:Header/>\n" +
			"   <soapenv:Body>\n" +
			"      <ns:getData>\n" +
			"         <ns:domain>3.1</ns:domain>\n" +
			"         <ns:sites>\n" +
			"            <ns:CdSite>05169050</ns:CdSite>\n" +
			"            <ns:CdSite>05173420</ns:CdSite>\n" +
			"            <ns:CdSite>05173350</ns:CdSite>\n" +
			"            <ns:CdSite>05180700</ns:CdSite>\n" +
			"            <ns:CdSite>05167450</ns:CdSite>\n" +
			"            <ns:CdSite>05173300</ns:CdSite>\n" +
			"            <ns:CdSite>05180550</ns:CdSite>\n" +
			"            <ns:CdSite>05180210</ns:CdSite>\n" +
			"            <ns:CdSite>05172350</ns:CdSite>\n" +
			"            <ns:CdSite>05176130</ns:CdSite>\n" +
			"            <ns:CdSite>05176900</ns:CdSite>\n" +
			"         </ns:sites>\n" +
			"         <ns:temporalConstraints>\n" +
			"            <ns:DateDebutDonnees>2006-01-01T00:00:00</ns:DateDebutDonnees>\n" +
			"            <ns:DateFinDonnees>2014-12-31T23:59:59</ns:DateFinDonnees>\n" +
			"         </ns:temporalConstraints>\n" +
			"         <ns:analyticConstraints>\n" +
			"         </ns:analyticConstraints>\n" +
			"          <ns:outputSchema>http://xml.sandre.eaufrance.fr/scenario/quesu/3/sandre_sc_quesu.xsd</ns:outputSchema>\n" +
			"         <ns:outputFormat>text/xml</ns:outputFormat>\n" +
			"      </ns:getData>\n" +
			"   </soapenv:Body>\n" +
			"</soapenv:Envelope>";


	/**
	 * Test de creation d'un message soap à partir des paramètres.
	 */
	@Test
	public void testSoapMessageCreation() throws Exception {
		final Map<String,Map<String,Object>> parameters = IoCommons.param_readParameters(DomUtilities.read(PARAM1), null, null);
		final String message = WildSoapService.DEV_buildSoapMessage("getData", "http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2", parameters);
		final XMLComparator comparator = new XMLComparator(MESSAGE1, message);
		comparator.compare();
	}

	@Test
	public void testSoapMessageGetSites() throws Exception {
		final Map<String,Map<String,Object>> parameters = IoCommons.param_readParameters(DomUtilities.read(PARAM2), null, null);
		final String message = WildSoapService.DEV_buildSoapMessage("getSites", "http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2", parameters);
		final XMLComparator comparator = new XMLComparator(MESSAGE_GETSITES, message);
		comparator.compare();
	}

	@Test
	public void testSoapMessageGetSiteDescription() throws Exception {
		final Map<String,Map<String,Object>> parameters = IoCommons.param_readParameters(DomUtilities.read(PARAM2), null, null);
		final String message = WildSoapService.DEV_buildSoapMessage("getSiteDescription", "http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2", parameters);
		final XMLComparator comparator = new XMLComparator(MESSAGE_GETSITEDESCRIPTION, message);
		comparator.compare();
	}

	@Test
	public void testSoapMessageGetDataAvailability() throws Exception {
		final Map<String,Map<String,Object>> parameters = IoCommons.param_readParameters(DomUtilities.read(PARAM2), null, null);
		final String message = WildSoapService.DEV_buildSoapMessage("getDataAvailability", "http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2", parameters);
		final XMLComparator comparator = new XMLComparator(MESSAGE_GETDATAAVAILIBILITY, message);
		comparator.compare();
	}

	@Test
	public void testSoapMessageGetData() throws Exception {
		final Map<String,Map<String,Object>> parameters = IoCommons.param_readParameters(DomUtilities.read(PARAM2), null, null);
		final String message = WildSoapService.DEV_buildSoapMessage("getData", "http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2", parameters);
		final XMLComparator comparator = new XMLComparator(MESSAGE_GETDATA, message);
		comparator.compare();
	}

}
