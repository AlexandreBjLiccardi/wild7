<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions">
<xsl:output method="text"/>

<xsl:template match="WildObject">
package fr.wild.<xsl:value-of select="tokenize(@ident,'\.')[1]"/>;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
<xsl:for-each select="Dependency">
	<xsl:call-template name="showDependency"/>
</xsl:for-each>

/**
 * Code généré automatiquement par l'outil Wild
 * <xsl:if test = "text()"><xsl:value-of select="replace(/*/text()[position()=1],'^\s*(.+?)\s*$', '$1')"/></xsl:if>
 */
public <xsl:if test = "@type = 'abstract'">abstract </xsl:if>class <xsl:value-of select="tokenize(@ident,'\.')[2]"/> 
<xsl:if test = "@inherits"> extends <xsl:value-of select="@inherits"/></xsl:if>
<xsl:if test = "@interface"> implements <xsl:value-of select="@interface"/></xsl:if>{
<!-- <xsl:if test = "@type = 'abstract'">-->
// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected <xsl:value-of select="tokenize(@ident,'\.')[2]"/>(){}
<!-- </xsl:if>-->
// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
<xsl:for-each select="Field">
	<xsl:call-template name="showField"/>
</xsl:for-each>


<xsl:call-template name="methodInitializer">
	<xsl:with-param name="countChannelInitializer" select="count(distinct-values(./Invokation/Parameter/concat(@type,' ',@ident)))"/>
	<xsl:with-param name="nodeChannelInitializer" select="."/>
</xsl:call-template>


<xsl:for-each select="./Invokation">
	<xsl:call-template name="showConstructor"/>
</xsl:for-each>

<xsl:for-each select="./WildMethod">
	<xsl:call-template name="showMethod"/>
</xsl:for-each>
}

</xsl:template>

<xsl:template name="showParametersJavaDoc"><xsl:param name="nodeChannelInitializer"/>
<xsl:for-each select="distinct-values(./Invokation/Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:variable name = "curr_node" select="$nodeChannelInitializer/Invokation/Parameter[@ident = $curr_id]"/>
<xsl:text> * @param i_</xsl:text><xsl:value-of select="$curr_id"/><xsl:text>	</xsl:text><xsl:value-of select="$curr_node[position()=1]/@description"/><xsl:text>{</xsl:text><xsl:value-of select="$curr_node[position()=1]/text()[position()=1]"/><xsl:text>}
</xsl:text></xsl:for-each>
</xsl:template>
<xsl:template name="showReturnsJavaDoc"><xsl:param name="nodeChannelInitializer"/>
<xsl:for-each select="distinct-values(./Return/Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:variable name = "curr_node" select="$nodeChannelInitializer/Invokation/Parameter[@ident = $curr_id]"/>
<xsl:text> * @return	</xsl:text><xsl:value-of select="$curr_node[position()=1]/@description"/><xsl:text>{</xsl:text><xsl:value-of select="$curr_node[position()=1]/text()[position()=1]"/><xsl:text>}
</xsl:text></xsl:for-each> 
</xsl:template>


<xsl:template name="methodInitializer"><xsl:param name="countChannelInitializer"/><xsl:param name="nodeChannelInitializer"/>
// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
<xsl:for-each select="distinct-values(./Invokation/Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:variable name = "curr_node" select="$nodeChannelInitializer/Invokation/Parameter[@ident = $curr_id]"/>
<xsl:text>	</xsl:text>protected <xsl:value-of select="$curr_node[position()=1]/@type"/><xsl:text> </xsl:text><xsl:value-of select="$curr_id"/><xsl:text>;	//</xsl:text><xsl:value-of select="$curr_node[position()=1]/@description"/><xsl:text>{</xsl:text><xsl:value-of select="$curr_node[position()=1]/text()[position()=1]"/><xsl:text>}
</xsl:text>
</xsl:for-each>

// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***

// CE CONSTRUCTEUR DOIT ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Fonction d'initialisation, commune à tous les constructeurs.
 * "Constructeur unique"
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
<xsl:call-template name="showParametersJavaDoc"><xsl:with-param name="nodeChannelInitializer" select="$nodeChannelInitializer"/></xsl:call-template>
<xsl:call-template name="showReturnsJavaDoc"><xsl:with-param name="nodeChannelInitializer" select="$nodeChannelInitializer"/></xsl:call-template> */
protected void WILD_initialize_<xsl:value-of select="tokenize(@ident,'\.')[2]"/>(
<xsl:text>	WildObject i_WILD_dObject</xsl:text>
<xsl:variable name="countChannelParameterV" select="count(./Invokation/Parameter)"/>
<xsl:if test='$countChannelParameterV != 0'>,</xsl:if><xsl:text>
</xsl:text>
<xsl:for-each select="distinct-values(./Invokation/Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:variable name = "curr_node" select="$nodeChannelInitializer/Invokation/Parameter[@ident = $curr_id]"/>
<xsl:text>	</xsl:text><xsl:value-of select="$curr_node[position()=1]/@type"/><xsl:text> i_</xsl:text><xsl:value-of select="$curr_id"/><xsl:if test='$countChannelInitializer!=position()'>,</xsl:if >
<xsl:if test='$countChannelInitializer!=position()'><xsl:text>
</xsl:text>
</xsl:if>
</xsl:for-each>
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_<xsl:value-of select="/WildObject/@inherits"/>(i_WILD_dObject/*** Paramètres à actualiser ***/);
	// Préparation des variables d'invocation (considérées comme champs globaux)
<xsl:for-each select="distinct-values(./Invokation/Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:variable name = "curr_node" select="$nodeChannelInitializer/Invokation/Parameter[@ident = $curr_id]"/>
<xsl:text>	</xsl:text>this.<xsl:value-of select="$curr_id"/><xsl:text> = i_</xsl:text> <xsl:value-of select="$curr_id"/>;
</xsl:for-each>
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
<xsl:for-each select="./Step"><xsl:call-template name="showStep"/></xsl:for-each>
<xsl:for-each select="./Output"><xsl:call-template name="showOutput"/></xsl:for-each>
	}catch(Exception e){
		this.WILD_logException(e);
	}
}
</xsl:template>

<xsl:template name="showStep">
//	//	//	Etape	"<xsl:value-of select="@ident"/>" : poids relatif de <xsl:value-of select="@weight"/>, <xsl:value-of select="@description"/> <xsl:if test = "text()"> //<xsl:value-of select="replace(/*/text()[position()=1],'^\s*(.+?)\s*$', '$1')"/></xsl:if>
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
</xsl:template>
<xsl:template name="showOutput">
//	//	//	Output	"<xsl:value-of select="@ident"/>" : <xsl:value-of select="@description"/> <xsl:if test = "text()"> //<xsl:value-of select="replace(/*/text()[position()=1],'^\s*(.+?)\s*$', '$1')"/></xsl:if>
	//	this.WILD_setOutput("<xsl:value-of select="@ident"/>",/*** Valeur à remonter en output ***/); // Ne pas modifier
</xsl:template>




<xsl:template name="showDependency">import <xsl:if test = "@type='static'">static </xsl:if> <xsl:value-of select="@value"/><xsl:text>;</xsl:text><xsl:if test = "text()"> //<xsl:value-of select="text()"/></xsl:if><xsl:text>
</xsl:text>

</xsl:template>

<xsl:template name="showField">
<xsl:text>	protected </xsl:text><xsl:value-of select="@type"/><xsl:text> </xsl:text><xsl:value-of select="@ident"/>
<xsl:if test = "@default"><xsl:text> = </xsl:text> 
<xsl:choose><xsl:when test="lower-case(@type)='string'">"<xsl:value-of select="@default"/>"</xsl:when><xsl:otherwise><xsl:value-of select="@default"/></xsl:otherwise></xsl:choose>
</xsl:if>
<xsl:text>;</xsl:text>
<xsl:if test = "text()"><xsl:text> // </xsl:text> <xsl:value-of select="text()"/></xsl:if>
<xsl:if test = "@description"><xsl:text> // </xsl:text> <xsl:value-of select="@description"/></xsl:if>
<xsl:text>
</xsl:text>
</xsl:template>

<xsl:template name="showConstructor">
<xsl:variable name="thisConstruct" select = "."/>
<xsl:variable name="chanelMethod" select="../@ident"/>

// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
<xsl:for-each select="Parameter">
<xsl:text> * @param i_</xsl:text><xsl:value-of select="@ident"/><xsl:text>	</xsl:text><xsl:value-of select="@description"/><xsl:text>{</xsl:text><xsl:value-of select="text()[position()=1]"/><xsl:text>}
</xsl:text></xsl:for-each>
<xsl:for-each select="Return">
<xsl:text> * @return </xsl:text><xsl:value-of select="@ident"/><xsl:text>	</xsl:text><xsl:value-of select="@description"/><xsl:text>{</xsl:text><xsl:value-of select="text()[position()=1]"/><xsl:text>}
</xsl:text></xsl:for-each> */

public <xsl:value-of select="tokenize($chanelMethod,'\.')[2]"/>(
<xsl:text>	WildObject i_WILD_dObject</xsl:text>
<xsl:variable name="countChannelParameterV" select="count(./Parameter)"/>
<xsl:if test='$countChannelParameterV != 0'>,</xsl:if><xsl:text>
</xsl:text>
<xsl:for-each select="./Parameter">
<xsl:call-template name="showParameter">
		<xsl:with-param name="countChannelParameter" select="$countChannelParameterV"/>
</xsl:call-template>
</xsl:for-each>) throws Exception{
<xsl:for-each select="./Parameter[@value]">
<xsl:text>	</xsl:text><xsl:value-of select="@type"/><xsl:text>	</xsl:text><xsl:value-of select="@ident"/> = <xsl:choose><xsl:when test="lower-case(@type)='string'">"<xsl:value-of select="@value"/>"</xsl:when><xsl:otherwise><xsl:value-of select="@value"/></xsl:otherwise></xsl:choose>;
</xsl:for-each>
<xsl:for-each select="./Parameter[@default]">
<xsl:text>	</xsl:text>if(<xsl:value-of select="@ident"/>==null)<xsl:value-of select="@ident"/>=<xsl:choose><xsl:when test="lower-case(@type)='string'">"<xsl:value-of select="@default"/>"</xsl:when><xsl:otherwise><xsl:value-of select="@default"/></xsl:otherwise></xsl:choose>;
</xsl:for-each>
<xsl:variable name='countChannelInitializer' select='count(distinct-values(../Invokation/Parameter/@ident))'/>
<xsl:text>	</xsl:text>this.WILD_initialize_<xsl:value-of select="tokenize(/WildObject/@ident,'\.')[2]"/>(i_WILD_dObject,<xsl:for-each select="distinct-values(../Invokation/Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:choose><xsl:when test='$thisConstruct/Parameter[@ident=$curr_id]'>i_<xsl:value-of select="$curr_id"/></xsl:when>
<xsl:otherwise>null</xsl:otherwise></xsl:choose>
<xsl:if test='$countChannelInitializer!=position()'>,</xsl:if >
</xsl:for-each>);
}
<xsl:if test="./Parameter[@default]">
// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
<xsl:for-each select="Parameter[not(@default)]">
<xsl:text> * @param i_</xsl:text><xsl:value-of select="@ident"/><xsl:text>	</xsl:text><xsl:value-of select="@description"/><xsl:text>{</xsl:text><xsl:value-of select="text()[position()=1]"/><xsl:text>}
</xsl:text></xsl:for-each>
<xsl:for-each select="Return">
<xsl:text> * @return </xsl:text><xsl:value-of select="@ident"/><xsl:text>	</xsl:text><xsl:value-of select="@description"/><xsl:text>{</xsl:text><xsl:value-of select="text()[position()=1]"/><xsl:text>}
</xsl:text></xsl:for-each> */

public <xsl:value-of select="tokenize($chanelMethod,'\.')[2]"/>(
<xsl:text>	WildObject i_WILD_dObject</xsl:text>
<xsl:variable name="countChannelParameterV" select="count(./Parameter[not(@default)])"/>
<xsl:if test='$countChannelParameterV != 0'>,</xsl:if><xsl:text>
</xsl:text>
<xsl:for-each select="./Parameter[not(@default)]">
<xsl:call-template name="showParameter">
		<xsl:with-param name="countChannelParameter" select="$countChannelParameterV"/>
</xsl:call-template>
</xsl:for-each>) throws Exception{
<xsl:for-each select="./Parameter[@value]">
<xsl:text>	</xsl:text><xsl:value-of select="@type"/><xsl:text>	</xsl:text><xsl:value-of select="@ident"/> = <xsl:choose><xsl:when test="lower-case(@type)='string'">"<xsl:value-of select="@value"/>"</xsl:when><xsl:otherwise><xsl:value-of select="@value"/></xsl:otherwise></xsl:choose>;
</xsl:for-each>

<xsl:variable name='countChannelInitializer' select='count(distinct-values(../Invokation/Parameter/@ident))'/>
<xsl:text>	</xsl:text>this.WILD_initialize_<xsl:value-of select="tokenize(/WildObject/@ident,'\.')[2]"/>(i_WILD_dObject,<xsl:for-each select="distinct-values(../Invokation/Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:choose><xsl:when test='$thisConstruct/Parameter[@ident=$curr_id]'>
<xsl:if test='$thisConstruct/Parameter[@ident=$curr_id and not(@default)]'>
<xsl:text>i_</xsl:text><xsl:value-of select="$curr_id"/>
</xsl:if>
<xsl:if test='$thisConstruct/Parameter[@ident=$curr_id and @default]'>
<xsl:if test='$thisConstruct/Parameter[@ident=$curr_id and @type="String"]'>"</xsl:if>
<xsl:value-of select="$thisConstruct/Parameter[@ident=$curr_id]/@default"/>
<xsl:if test='$thisConstruct/Parameter[@ident=$curr_id and @type="String"]'>"</xsl:if>
</xsl:if>
</xsl:when>
<xsl:otherwise>null</xsl:otherwise></xsl:choose>
<xsl:if test='$countChannelInitializer!=position()'>,</xsl:if >
</xsl:for-each>);
}
</xsl:if>
</xsl:template>
<xsl:template name="showParameter"><xsl:param name="countChannelParameter"/>
<xsl:if test='not(@value)'><xsl:text>	</xsl:text><xsl:value-of select="@type"/><xsl:text> i_</xsl:text><xsl:value-of select="@ident"/><xsl:if test='$countChannelParameter!=position()'>,</xsl:if><xsl:text>
</xsl:text></xsl:if>
</xsl:template>

<xsl:template name="showDescription">
	<xsl:if test = "./@description"><xsl:value-of select="./@description"/></xsl:if><xsl:if test = "./text()">{ <xsl:value-of select="replace(text()[position()=1],'^\s*(.+?)\s*$', '$1')"/> }</xsl:if>
	</xsl:template>

<xsl:template name="showMethod">
<xsl:variable name="thisMethod" select = "."/>
<xsl:variable name="chanelMethod" select="./@ident"/>
/*	### NOUVELLE METHODE ###
	Méthode : <xsl:value-of select="$chanelMethod"/><xsl:text> - </xsl:text>
<xsl:call-template name="showDescription"/>

*/
// CETTE METHODE DOIT ETRE MODIFIE.
<xsl:variable name='countChannelInitializer' select='count(distinct-values(./Invokation/Parameter/@ident))'/>
<xsl:variable name='nodeChannelInitializer' select='/WildObject/WildMethod[@ident=$chanelMethod]'/>
/**
 * <xsl:call-template name="showDescription"/><xsl:text>
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
</xsl:text>
<xsl:call-template name="showParametersJavaDoc"><xsl:with-param name="nodeChannelInitializer" select="$nodeChannelInitializer"/></xsl:call-template>
<xsl:call-template name="showReturnsJavaDoc"><xsl:with-param name="nodeChannelInitializer" select="$nodeChannelInitializer"/></xsl:call-template> */
public <xsl:choose><xsl:when test = "./Return/Parameter"> <xsl:value-of select="./Return/Parameter/@type"/> </xsl:when><xsl:otherwise><xsl:text>void</xsl:text></xsl:otherwise></xsl:choose>
<xsl:text> </xsl:text><xsl:value-of select="$chanelMethod"/><xsl:text> (</xsl:text>
<xsl:for-each select="distinct-values(./Invokation/Parameter/concat(@type,' i_',@ident))">
	<xsl:variable name='paramName' select='tokenize(.," ")[2]'/>
	<xsl:variable name='paramType' select='tokenize(.," ")[1]'/>
	<xsl:if test = '1=position()'><xsl:text>
	</xsl:text></xsl:if>
	<xsl:value-of select="."/><xsl:if test = '$countChannelInitializer!=position()'>,</xsl:if><xsl:text>
	</xsl:text>
</xsl:for-each><xsl:text>)throws Exception{</xsl:text>
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
<xsl:if test = "./Return/Parameter"><xsl:text>	//	Variable générique de retour
</xsl:text><xsl:text>	</xsl:text><xsl:value-of select="./Return/Parameter/@type"/> WILD_toReturn = null ;</xsl:if> // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
<xsl:for-each select="./Step"><xsl:call-template name="showStep"/></xsl:for-each>
<xsl:for-each select="./Output"><xsl:call-template name="showOutput"/></xsl:for-each>
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier<xsl:if test = "./Return/Parameter">
		return WILD_toReturn</xsl:if> ; // Ne pas modifier
	}
}
<xsl:variable name='countChannelInvokers' select='count(./Invokation)'/>
<xsl:if test="($countChannelInvokers!=1 and $countChannelInvokers!=0) or (./Invokation[Parameter/@default])">
// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES</xsl:if>
<xsl:if test="$countChannelInvokers!=1 and $countChannelInvokers!=0">
<xsl:for-each select="./Invokation">
<xsl:variable name='countChannelInvokers_sub' select='count(./Parameter)'/>
<xsl:variable name='thisInvoke' select='.'/>
 <xsl:choose>
        <xsl:when test="$countChannelInvokers_sub=$countChannelInitializer"> </xsl:when>
		<xsl:otherwise>
/**
 * <xsl:for-each select=".."><xsl:call-template name="showDescription"/></xsl:for-each>
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
<xsl:for-each select="distinct-values(./Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:variable name = "curr_node" select="$nodeChannelInitializer/Invokation/Parameter[@ident = $curr_id]"/>
<xsl:text> * @param i_</xsl:text><xsl:value-of select="$curr_id"/><xsl:text>	</xsl:text><xsl:value-of select="$curr_node[position()=1]/@description"/><xsl:text> {</xsl:text><xsl:value-of select="$curr_node[position()=1]/text()[position()=1]"/><xsl:text>}
</xsl:text></xsl:for-each>
<xsl:for-each select="distinct-values(../Return/Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:variable name = "curr_node" select="$nodeChannelInitializer/Return/Parameter[@ident = $curr_id]"/>
<xsl:text> * @return	</xsl:text><xsl:value-of select="$curr_node[position()=1]/@description"/><xsl:text> {</xsl:text><xsl:value-of select="$curr_node[position()=1]/text()[position()=1]"/><xsl:text>}
</xsl:text></xsl:for-each> */
 public <xsl:choose><xsl:when test = "../Return/Parameter"> <xsl:value-of select="../Return/Parameter/@type"/> </xsl:when><xsl:otherwise><xsl:text>void</xsl:text></xsl:otherwise></xsl:choose>
<xsl:text> </xsl:text><xsl:value-of select="$chanelMethod"/><xsl:text> (</xsl:text>
<xsl:variable name='countChannelInitializer_param' select='count(./Parameter)'/>
<xsl:for-each select="./Parameter/concat(@type,' i_',@ident)">
	<xsl:variable name='paramName' select='tokenize(.," ")[2]'/>
	<xsl:variable name='paramType' select='tokenize(.," ")[1]'/>
	<xsl:if test = '1=position()'><xsl:text>
	</xsl:text></xsl:if>
		<xsl:value-of select="."/><xsl:if test = '$countChannelInitializer_param!=position()'>,</xsl:if><xsl:text>
	</xsl:text>
</xsl:for-each><xsl:text>)throws Exception{</xsl:text>
<xsl:for-each select="$thisInvoke/Parameter"><xsl:if test="@default">
	if(i_<xsl:value-of select="@ident"/>==null)i_<xsl:value-of select="@ident"/> = <xsl:value-of select="@default"/>;</xsl:if></xsl:for-each>
	<xsl:if test="../Return">return </xsl:if><xsl:value-of select="$chanelMethod"/>(<xsl:for-each select="distinct-values(../Invokation/Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:choose><xsl:when test='$thisInvoke/Parameter[@ident=$curr_id]'>i_<xsl:value-of select="$curr_id"/></xsl:when>
<xsl:otherwise>null</xsl:otherwise></xsl:choose>
<xsl:if test='$countChannelInitializer!=position()'>,</xsl:if >
</xsl:for-each>);
}</xsl:otherwise>
    </xsl:choose>
</xsl:for-each>
</xsl:if>
<xsl:for-each select="./Invokation[Parameter/@default]">
<xsl:variable name="thisConstruct" select = "."/>
<xsl:variable name='countChannelInvokers_sub' select='count(./Parameter)'/>
<xsl:variable name='thisInvoke' select='.'/>
 <!--<xsl:choose>
        <xsl:when test="$countChannelInvokers_sub=$countChannelInitializer"> </xsl:when>
		<xsl:otherwise>-->
/**
 * <xsl:for-each select=".."><xsl:call-template name="showDescription"/></xsl:for-each>
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
<xsl:for-each select="distinct-values(./Parameter[not(@default)]/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:variable name = "curr_node" select="$nodeChannelInitializer/Invokation/Parameter[@ident = $curr_id]"/>
<xsl:text> * @param i_</xsl:text><xsl:value-of select="$curr_id"/><xsl:text>	</xsl:text><xsl:value-of select="$curr_node[position()=1]/@description"/><xsl:text> {</xsl:text><xsl:value-of select="$curr_node[position()=1]/text()[position()=1]"/><xsl:text>}
</xsl:text></xsl:for-each>
<xsl:for-each select="distinct-values(../Return/Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:variable name = "curr_node" select="$nodeChannelInitializer/Return/Parameter[@ident = $curr_id]"/>
<xsl:text> * @return	</xsl:text><xsl:value-of select="$curr_node[position()=1]/@description"/><xsl:text> {</xsl:text><xsl:value-of select="$curr_node[position()=1]/text()[position()=1]"/><xsl:text>}
</xsl:text></xsl:for-each> */
 public <xsl:choose><xsl:when test = "../Return/Parameter"> <xsl:value-of select="../Return/Parameter/@type"/> </xsl:when><xsl:otherwise><xsl:text>void</xsl:text></xsl:otherwise></xsl:choose>
<xsl:text> </xsl:text><xsl:value-of select="$chanelMethod"/><xsl:text> (</xsl:text>
<xsl:variable name='countChannelInitializer_param' select='count(./Parameter[not(@default)])'/>
<xsl:for-each select="./Parameter[not(@default)]/concat(@type,' i_',@ident)">
	<xsl:variable name='paramName' select='tokenize(.," ")[2]'/>
	<xsl:variable name='paramType' select='tokenize(.," ")[1]'/>
	<xsl:if test = '1=position()'><xsl:text>
	</xsl:text></xsl:if>
		<xsl:value-of select="."/><xsl:if test = '$countChannelInitializer_param!=position()'>,</xsl:if><xsl:text>
	</xsl:text>
</xsl:for-each><xsl:text>)throws Exception{
</xsl:text><xsl:if test="../Return">return </xsl:if><xsl:value-of select="$chanelMethod"/>(<xsl:for-each select="distinct-values(../Invokation/Parameter/@ident)">
<xsl:variable name = "curr_id" select="."/>
<xsl:choose>
<xsl:when test='$thisInvoke/Parameter[@ident=$curr_id]'>
<xsl:if test='$thisConstruct/Parameter[@ident=$curr_id and not(@default)]'>
<xsl:text>i_</xsl:text><xsl:value-of select="$curr_id"/>
</xsl:if>
<xsl:if test='$thisConstruct/Parameter[@ident=$curr_id and @default]'>
<xsl:if test='$thisConstruct/Parameter[@ident=$curr_id and @type="String"]'>"</xsl:if>
<xsl:value-of select="$thisConstruct/Parameter[@ident=$curr_id]/@default"/>
<xsl:if test='$thisConstruct/Parameter[@ident=$curr_id and @type="String"]'>"</xsl:if>
</xsl:if>
</xsl:when>
<xsl:otherwise>null</xsl:otherwise></xsl:choose>
<xsl:if test='$countChannelInitializer!=position()'>,</xsl:if >
</xsl:for-each>);
} <!--</xsl:otherwise>
   </xsl:choose>-->
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>