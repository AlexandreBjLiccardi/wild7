<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions">
<xsl:output method="text"/>

<xsl:template match="Execution">
&lt;p id="ds_links"&gt;Liens de téléchargement&lt;/p&gt;
&lt;a href="" onClick = "downloadArchiveFromReport('<xsl:value-of select="@idExecution"/>')"&gt;Télécharger l'archive.&lt;/a&gt;
&lt;p id='sub_val'&gt;Résultats de validation&lt;/p&gt;
<xsl:for-each select="Products/Product[@ident='./Product#6D#2']">
	<xsl:call-template name="showMainProduct"/>
</xsl:for-each>
&lt;p id='sub_det'&gt;Détail des validations : nombres totaux d&apos;erreurs&lt;/p&gt;
&lt;DIV class = "detailed_result"&gt;
<xsl:for-each select="Products/Product[@ident='./Product#6D#6' and @value]">
	<xsl:call-template name="showProduct"/>
</xsl:for-each>
<xsl:for-each select="Products/Product[@ident='./Product#6D#4' and @value]">
	<xsl:call-template name="showProduct"/>
</xsl:for-each>
<xsl:for-each select="Products/Product[@ident='./Product#6D#7' and @value]">
	<xsl:call-template name="showProduct"/>
</xsl:for-each>
<xsl:for-each select="Products/Product[@ident='./Product#6D#8' and @value]">
	<xsl:call-template name="showProduct"/>
</xsl:for-each>
<xsl:for-each select="Products/Product[@ident='./Product#6D#9' and @value]">
	<xsl:call-template name="showProduct"/>
</xsl:for-each>
<xsl:for-each select="Products/Product[@ident='./Product#6D#10' and @value]">
	<xsl:call-template name="showProduct"/>
</xsl:for-each>
<xsl:for-each select="Products/Product[@ident='./Product#6D#11' and @value]">
	<xsl:call-template name="showProduct"/>
</xsl:for-each>
<xsl:for-each select="Products/Product[@ident='./Product#6D#12' and @value]">
	<xsl:call-template name="showProduct"/>
</xsl:for-each>
<xsl:for-each select="Products/Product[@ident='./Product#6D#13' and @value]">
	<xsl:call-template name="showProduct"/>
</xsl:for-each>
<xsl:for-each select="Products/Product[@ident='./Product#6D#14' and @value]">
	<xsl:call-template name="showProduct"/>
</xsl:for-each>
&lt;/DIV&gt;

&lt;p id='sub_detsynth'&gt;Tableau de résultats synthétique&lt;/p&gt;
&lt;DIV class = "table_result"&gt;
&lt;TABLE id = 'data_listResults_summary'&gt;
&lt;THEAD&gt;
&lt;TH&gt;
Type d&apos;erreur
&lt;/TH&gt;
&lt;TH&gt;
Message d&apos;erreur
&lt;/TH&gt;
&lt;TH&gt;
Nombre
&lt;/TH&gt;
&lt;/THEAD&gt;
&lt;TBODY&gt;

<xsl:for-each select="Products/Product[@ident='./Product#6D#5']/Product/Product">
&lt;TR&gt;
&lt;TD&gt;
<xsl:value-of select="@type"/>&lt;/BR&gt;<xsl:value-of select="replace(@level,'error','Erreur')"/>
&lt;/TD&gt;
&lt;TD&gt;
<xsl:value-of select="@message"/>
&lt;/TD&gt;
&lt;TD&gt;
<xsl:value-of select="@count"/>
&lt;/TD&gt;
</xsl:for-each>


&lt;/TBODY&gt;
&lt;/TABLE&gt;
&lt;/DIV&gt;

&lt;p id='sub_tab'&gt;Tableau de résultats complet&lt;/p&gt;
&lt;DIV class = "table_result"&gt;
<xsl:if test="Products/Product[@ident='./Product#6D#7']/@value &lt; 2500">
&lt;TABLE id = 'data_listResults'&gt;
&lt;THEAD&gt;
&lt;TH&gt;
Type d&apos;erreur
&lt;/TH&gt;
&lt;TH&gt;
Localisation
&lt;/TH&gt;
&lt;TH&gt;
Message d&apos;erreur
&lt;/TH&gt;
&lt;TH&gt;
Gravité
&lt;/TH&gt;
&lt;/THEAD&gt;
&lt;TBODY&gt;
<xsl:for-each select="Products/Product[@ident='./Product#6D#3']/Product/Product">
&lt;TR&gt;
&lt;TD&gt;
<xsl:value-of select="@type"/>
&lt;/TD&gt;
&lt;TD&gt;
<xsl:value-of select="@localisation"/>
&lt;/TD&gt;
&lt;TD&gt;
<xsl:value-of select="@message"/>
&lt;/TD&gt;
&lt;TD&gt;
<xsl:value-of select="replace(@level,'error','Erreur')"/>
&lt;/TD&gt;
&lt;/TR&gt;
</xsl:for-each>
&lt;/TBODY&gt;
&lt;/TABLE&gt;
</xsl:if>
<xsl:if test="Products/Product[@ident='./Product#6D#7']/@value &gt; 2500">
Plus de 2 500 erreurs ont été retournées par les différents tests. L'affichage est impossible : merci de vous référer à la matrice produite dans le fichier ZIP (matrice CSV).
</xsl:if>
&lt;/DIV&gt;
</xsl:template>

<xsl:template name="showProduct">
&lt;DIV class = "detailed_result_sub"&gt;
&lt;DIV class = "detailed_result_descr"&gt;
<xsl:value-of select="@description"/> :
&lt;/DIV&gt;

<xsl:if test='@value = "0"'>&lt;DIV class = "detailed_result_nb_safe"&gt;
<xsl:value-of select="@value"/>
&lt;/DIV&gt;</xsl:if>
<xsl:if test='@value != "0"'>&lt;DIV class = "detailed_result_nb_unsafe"&gt;
<xsl:value-of select="@value"/>
&lt;/DIV&gt;</xsl:if>
&lt;div style="clear: both;"/&gt;&lt;/DIV&gt;

</xsl:template>

<xsl:template name="showMainProduct">

&lt;DIV class = "main_result"&gt;
&lt;DIV class = "main_result_img"&gt;
<xsl:variable name = "testSucess" select="@value"/>
<xsl:if test='$testSucess = "true"'> &lt;IMG SRC = "../themes/custom/default/img/valid_ce.png" class="img_result"/&gt;
</xsl:if>
<xsl:if test='$testSucess = "false"'> &lt;IMG SRC = "../themes/custom/default/img/unvalid_ce.png" class="img_result"/&gt; </xsl:if>
&lt;/DIV&gt;
&lt;DIV class = "main_result_text"&gt;
<xsl:variable name = "testSucess" select="@value"/>
<xsl:if test='$testSucess = "true"'> Le fichier est conforme aux tests de la Commission Européenne. Aucune erreur reportée.&lt;/BR&gt;Le fichier testÃ© est considéré comme le "référentiel" du schéma testé. D'autres erreurs, non bloquantes, peuvent exister. La liste est produite ci-dessous.</xsl:if>
<xsl:if test='$testSucess = "false"'> Le fichier n'est pas conforme aux tests de la Commission Européenne.&lt;/BR&gt;La liste des erreurs est produite ci-dessous. </xsl:if>
&lt;/DIV&gt;
&lt;/DIV&gt;
</xsl:template>


</xsl:stylesheet>