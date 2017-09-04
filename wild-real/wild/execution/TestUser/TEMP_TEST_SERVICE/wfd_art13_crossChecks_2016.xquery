
(:  =====                WFD CROSS SCHEMA VALIDATION                 =====  :)
(:  ======================================================================  :)
(:    XQuery agency:  The European Commission:)
(:    XQuery version: 1.0:)
(:    XQuery date:    :)
(:    Revision:       $Revision:  $:)
(:    Revision date:  $Date: 2008-12-04 15:41:43 +0100 (to, 04 dec 2008) $:)
(:  :)
xquery version "1.0";
(:===================================================================:)
(: Namespace declaration                                             :)
(:===================================================================:)
import module namespace xmlutil = "urn:eu:com:env:wfd:converter:standard:util:1" at "http://cdr.eionet.europa.eu/help/WFD/WFD_521_2016/QA%20resources/wfd_art13_util_2016.xquery";
declare namespace xmlconv="urn:eu:com:env:wfd:converter:standard:swb:1";
declare namespace wfd="http://dd.eionet.europa.eu/schemas/WFD2016";
declare namespace wfdgml="http://dd.eionet.europa.eu/schemas/WFD2016/wfdgml";
(:declare namespace wfd2010="http://water.eionet.europa.eu/schemas/dir200060ec";:)
declare variable $format := '';
declare variable $outputMode := 2;
declare variable $test := -999;
declare variable $xsd_list_name := ('http://dd.eionet.europa.eu/schemas/WFD2016/GWB_2016.xsd','http://dd.eionet.europa.eu/schemas/WFD2016/GWMET_2016.xsd',
                                    'http://dd.eionet.europa.eu/schemas/WFD2016/Monitoring_2016.xsd',
                                    'http://dd.eionet.europa.eu/schemas/WFD2016/RBMPPoM_2016.xsd','http://dd.eionet.europa.eu/schemas/WFD2016/SWB_2016.xsd',
                                    'http://dd.eionet.europa.eu/schemas/WFD2016/SWMET_2016.xsd');
(:===================================================================:)
(: Parameters                                             :)
(:===================================================================:)
(:declare variable $source_RBDSUCA as xs:string;:)
(: New Schemas :)
declare variable $xsd_GWB := 'http://dd.eionet.europa.eu/schemas/WFD2016/GWB_2016.xsd';
declare variable $xsd_GWMET := 'http://dd.eionet.europa.eu/schemas/WFD2016/GWMET_2016.xsd';
declare variable $xsd_Monitoring := 'http://dd.eionet.europa.eu/schemas/WFD2016/Monitoring_2016.xsd';
declare variable $xsd_RBDSUCA := 'http://dd.eionet.europa.eu/schemas/WFD2016/RBDSUCA_2016.xsd';
declare variable $xsd_RBMPPoM := 'http://dd.eionet.europa.eu/schemas/WFD2016/RBMPPoM_2016.xsd';
declare variable $xsd_SWB := 'http://dd.eionet.europa.eu/schemas/WFD2016/SWB_2016.xsd';
declare variable $xsd_SWMET := 'http://dd.eionet.europa.eu/schemas/WFD2016/SWMET_2016.xsd';
declare variable $xsd_ProtectedArea := 'http://dd.eionet.europa.eu/schemas/WFD2016/GML_ProtectedArea_2016.xsd';
(:=======================================================================:)
(:                          Standard Variables                            :)
(:To execute Xqueries with an Xquery engine uncomment the following lines:)
(:=======================================================================:)
(:declare variable $source_url as xs:string external;:)
(:declare variable $ENV := xmlconv:getENVExml();:)
(:declare variable $SWBlist := xmlconv:getSWBxml();:)
(:declare variable $GWBlist := xmlconv:getGWBxml();:)
(:declare variable $RBDSUCAlist := xmlconv:getRBDSUCAxml();:)
(:declare variable $SWMETlist := xmlconv:getSWMETxml();:)
(:declare variable $GWMETlist := xmlconv:getGWMETxml();:)
(:declare variable $RBMPPoMlist := xmlconv:getRBMPPoMxml();:)
(:declare variable $Monitoringlist := xmlconv:getMonitoringxml();:)
(:=======================================================================:)
(:                             FME Variables                             :)
(:     To execute Xqueries in FME uncomment the following lines          :)
(:=======================================================================:)
(:declare variable $source_url := {fme:get-attribute("url_rbdsuca")};:)
(:declare variable $SWBlist  := if(exists({fme:get-attribute("SWB")})) then ({fme:get-attribute("SWB")}) else ();:)
(:declare variable $GWBlist := if(exists({fme:get-attribute("GWB")})) then ({fme:get-attribute("GWB")}) else ();:)
(:declare variable $RBDSUCAlist := if(exists({fme:get-attribute("url_rbdsuca")})) then ({fme:get-attribute("url_rbdsuca")}) else () ; :)
(:declare variable $SWMETlist := if(exists({fme:get-attribute("SWMET")})) then ({fme:get-attribute("SWMET")}) else ();:)
(:declare variable $GWMETlist := if(exists({fme:get-attribute("GWMET")})) then ({fme:get-attribute("GWMET")}) else ();:)
(:declare variable $RBMPPoMlist := if(exists({fme:get-attribute("RBMPPOM")})) then ({fme:get-attribute("RBMPPOM")}) else ();:)
(:declare variable $Monitoringlist := if(exists({fme:get-attribute("MONITORING")})) then ({fme:get-attribute("MONITORING")}) else ();:)
declare variable $ENV := "";
declare variable $source_url := 'http://cdrtest.eionet.europa.eu/fr/eu/wfd2016/';
declare variable $SWBlist as xs:string external;
declare variable $GWBlist  as xs:string external;
declare variable $RBDSUCAlist  as xs:string external;
declare variable $SWMETlist as xs:string external;
declare variable $GWMETlist as xs:string external;
declare variable $RBMPPoMlist as xs:string external;
declare variable $Monitoringlist as xs:string external;
declare variable $countryCode := substring-before(substring-after($source_url , 'http://cdrtest.eionet.europa.eu/'), '/');
declare variable $source_url_no_xml_path := substring($source_url, 1, string-length($source_url) - 3);
declare function xmlconv:getENVExml() 
{
    if ($test = 1) then
    (
        let $file := doc("http://localhost/envelope/xml") 
(:) let $source_RBDSUCA := doc("http://localhost/RBDSUCA/GML/xml"):)
        return ($file)
    )
    else
    (
      let $file := doc($source_url)
(:)  let $source_RBDSUCA := doc($source_url../../../rbdsuca/env*):)
    return $file
    )
};
(:To execute Xqueries with an Xquery engine uncomment the following lines:)
declare function xmlconv:getSWMETxml()
{
    for $file in $ENV//envelope/file[@schema=$xsd_SWMET]/@name
    return concat($source_url_no_xml_path,data($file))
};
declare function xmlconv:getSWBxml()
{
    for $file in $ENV//envelope/file[@schema=$xsd_SWB]/@name
    return concat($source_url_no_xml_path,data($file))
};
declare function xmlconv:getGWBxml()
{
    for $file in $ENV//envelope/file[@schema=$xsd_GWB]/@name
    return concat($source_url_no_xml_path,data($file))
};
declare function xmlconv:getRBDSUCAxml()
{
    for $file in $ENV//envelope/file[@schema=$xsd_RBDSUCA]
    return $file/@link
};
declare function xmlconv:getGWMETxml()
{
    for $file in $ENV//envelope/file[@schema=$xsd_GWMET]/@name
    return concat($source_url_no_xml_path,data($file))
};
declare function xmlconv:getRBMPPoMxml()
{
    for $file in $ENV//envelope/file[@schema=$xsd_RBMPPoM]/@name
    return concat($source_url_no_xml_path,data($file))
};
declare function xmlconv:getMonitoringxml()
{
    for $file in $ENV//envelope/file[@schema=$xsd_Monitoring]/@name
    return concat($source_url_no_xml_path,data($file))
};
declare function xmlconv:getMaxDate($nodes, $t) {
    let $subset := $nodes/results/file[@title=$t]
    let $maxDate := max($subset//xs:dateTime(@uploaded))
    return $maxDate
};
declare function xmlconv:getMaxDate($nodes) {
    let $maxDate := max($nodes//xs:dateTime(@released))
    return $maxDate
};
(:declare function xmlconv:getFileList($service) { :)
(:    (:put after locality to only pick up files from closed envelopes - ' and @isreleased="1" ':)  :)
(:    let $nodes := doc($service)//file[@locality = $locality]:)
(:    let $max:=xmlconv:getMaxDate($nodes):)
(:    let $files := $nodes[xs:dateTime(@released)=$max ] :)
(:    return $files   :)
(:};:)
(:RBDSUCA doesnt use locality setting:)
declare function xmlconv:getAllFileList($service) {
(:put after locality to only pick up files from closed envelopes - ' and @isreleased="1" ':)
    let $nodes := doc($service)//file[@isreleased="0" and @country_code = upper-case($countryCode) ]
    let $max:=xmlconv:getMaxDate($nodes)
    let $files := $nodes[xs:dateTime(@released)=$max ]
    return $files   
};
(:==================================================================:)
(:Validation:)
(:==================================================================:)
(:Envelope validation:)
declare function xmlconv:control_fileType(){
    for $i in $ENV//envelope/file
        return if ($i/@type="text/xml") then(
            if (not($i/@schema = $xsd_list_name)) then(
                if ($i/@schema = $xsd_RBDSUCA)then(
                    xmlutil:generateOutput_withError_level($outputMode, xs:string($i/@name), 361, 0, 'FILE', "ENVELOPE",$xmlutil:LEVEL_ERROR2)
                )else(
                    xmlutil:generateOutput_withError_level($outputMode, xs:string($i/@name), 369, 0, 'FILE', "ENVELOPE",$xmlutil:LEVEL_ERROR2)
                )
            )
            else()    
        )
        else()
};
declare function xmlconv:getAQFiles()   {
       let $sw := xmlconv:getAQFilesSW()
       union xmlconv:getAQFilesGW()
       union xmlconv:getAQFilesSM()
       union xmlconv:getAQFilesGM()
       union xmlconv:getAQFilesRB()
       union xmlconv:getAQFilesMO()
       union xmlconv:getAQFilesRS()
            return 
                $sw
};
declare function xmlconv:getAQFilesSW()   {
    let $i := ''
    return if(not(exists($SWBlist)))then(
        xmlutil:generateOutput_withError_level($outputMode, 'SWB.xml', 309, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
    )else(if (exists($SWBlist) and count($SWBlist)>1) then
        (
            xmlutil:generateOutput_withError_level($outputMode, 'SWB.xml', 368, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
        )
        else()
    )    
};
declare function xmlconv:getAQFilesGW()   {
    let $i := ''
    return if(not(exists($GWBlist)))then(
        xmlutil:generateOutput_withError_level($outputMode, 'GWB.xml', 309, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
    )else(if (exists($GWBlist) and count($GWBlist)>1) then
        (
            xmlutil:generateOutput_withError_level($outputMode, 'GWB.xml', 368, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
        )
        else()
    )
};
declare function xmlconv:getAQFilesSM()   {
    let $i := ''
    return if(not(exists($SWMETlist)))then(
        xmlutil:generateOutput_withError_level($outputMode, 'SWMET.xml', 309, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
    )else(if (exists($SWMETlist) and count($SWMETlist)>1) then
        (
            xmlutil:generateOutput_withError_level($outputMode, 'SWMET.xml', 368, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
        )
        else()
    )
};
declare function xmlconv:getAQFilesGM()   {
    let $i := ''
    return if(not(exists($GWMETlist)))then(
        xmlutil:generateOutput_withError_level($outputMode, 'GWMET.xml', 309, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
    )else(if (exists($GWMETlist) and count($GWMETlist)>1) then
        (
            xmlutil:generateOutput_withError_level($outputMode, 'GWMET.xml', 368, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
        )
        else()
    )
};
declare function xmlconv:getAQFilesRB()   {
    let $i := ''
    return if(not(exists($RBMPPoMlist)))then(
        xmlutil:generateOutput_withError_level($outputMode, 'RBMPPoM.xml', 309, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
    )else(if (exists($RBMPPoMlist) and count($RBMPPoMlist)>1) then
        (
            xmlutil:generateOutput_withError_level($outputMode, 'RBMPPoM.xml', 368, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
        )
        else()
    )
};
declare function xmlconv:getAQFilesMO()   {
    let $i := ''
    return if(not(exists($Monitoringlist)))then(
        xmlutil:generateOutput_withError_level($outputMode, 'Monitoring.xml', 309, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
    )else(if (exists($Monitoringlist) and count($Monitoringlist)>1) then
        (
            xmlutil:generateOutput_withError_level($outputMode, 'Monitoring.xml', 368, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
        )
        else()
    )
};
declare function xmlconv:getAQFilesRS()   {
    let $i := ''
    return if(not(exists($RBDSUCAlist)))then(
        xmlutil:generateOutput_withError_level($outputMode, 'RBDSUCA.xml', 309, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
    )else(if (exists($RBDSUCAlist) and count($RBDSUCAlist)>1) then
        (
            xmlutil:generateOutput_withError_level($outputMode, 'RBDSUCA.xml', 368, 0, "XML", "FILE",$xmlutil:LEVEL_ERROR2)
        )
        else()
    )
};
(:==================================================================:)
(:RBDSUCA:)
(:==================================================================:)
(:==================================================================:)
(:SWB:)
(:==================================================================:)
(: #10001 The euRBDCode must be consistent with the codes reported in the RBDSUCA schema, element euRBDCode :)
declare function xmlconv:checkeuRBDCodeCode_SWB_RBDSUCA($source_list) { 
    for $source in $source_list
        for $i in doc($source)/wfd:SWB/wfd:euRBDCode
            for $RBDSUCA in $RBDSUCAlist
                let $ii := doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD[wfd:euRBDCode = $i]
                where count($ii) = 0
                return xmlutil:generateOutput_withError_level($outputMode, $i, 10001, 0, "euRBDCode", "SWB",$xmlutil:LEVEL_ERROR2)
};
(: #10003 The reported EUSubUnitCode must be consistent with the codes reported in the RBDSUCA schema, element EUSubUnitCode :)
declare function xmlconv:checkSubUnitcode_SWB_RBDSUCA($source_list) {
    for $source in $source_list
        for $i in doc($source)/wfd:SWB/wfd:SurfaceWaterBody/wfd:euSubUnitCode
            for $RBDSUCA in $RBDSUCAlist
            let $ii := doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD/wfd:SubUnit[wfd:euSubUnitCode = $i]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i, 10003, 0, "euSubUnitCode", "SWB/SurfaceWaterBody",$xmlutil:LEVEL_ERROR2)
};
(: #10004 The reported SurfaceWaterBodyTypeCode must be consistent with the codes reported in the SWMET schema, element surfaceWaterBodyTypeCode :)
declare function xmlconv:checkSurfaceWaterBodyTypeCode_SWB_SWMET($source_list) {
    for $source in $source_list
        for $i in doc($source)/wfd:SWB/wfd:SurfaceWaterBody/wfd:surfaceWaterBodyTypeCode[wfd:surfaceWaterBodyTypeCode != 'Not applicable']
            for $SWMET in $SWMETlist
                let $ii := doc($SWMET)//wfd:SWMET/wfd:SWType[wfd:swTypeCode = $i]
                where count($ii) = 0
                return xmlutil:generateOutput_withError_level($outputMode, $i, 10004, 0, "surfaceWaterBodyTypeCode", "SWB/SurfaceWaterBody",$xmlutil:LEVEL_ERROR2)
};
(: #10006 The reported CAS Number(s) must be consistent with the values in the SWMET schema, element RBSP :)
declare function xmlconv:checkSWFailingRBSP_SWB_SWMET($source_list) {
    for $source in $source_list
        for $i in doc($source)/wfd:SWB/wfd:SurfaceWaterBody/wfd:FailingRBSP/wfd:swFailingRBSP
            for $SWMET in $SWMETlist
            let $ii := doc($SWMET)/wfd:SWMET/wfd:SWRBSP[wfd:rbspCode = $i]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i, 10006, 0, "swFailingRBSP", "SWB/SurfaceWaterBody",$xmlutil:LEVEL_ERROR2)
};
(: #10005 The reported SurfaceWaterBodyIntercalibrationType must be consistent with the codes reported in SWMET/ IntercalibrationType :)
declare function xmlconv:checkSurfaceWaterBodyIntercalibrationType_SWB_SWMET($source_list) {
    for $source in $source_list
        for $i in doc($source)/wfd:SWB/wfd:SurfaceWaterBody[wfd:surfaceWaterBodyIntercalibrationType = 'Not applicable']
            for $SWMET in $SWMETlist
            let $ii := doc($SWMET)//wfd:SWMET/wfd:SWType[wfd:swIntercalibrationType = $i/wfd:surfaceWaterBodyIntercalibrationType]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i, 10005, 0, "surfaceWaterBodyIntercalibrationType", "SWB/SurfaceWaterBody",$xmlutil:LEVEL_ERROR2)
};
(:==================================================================:)
(:GWB:)
(:==================================================================:)
(: #10001 The euRBDCode must be consistent with the codes reported in the RBDSUCA schema, element euRBDCode :)
declare function xmlconv:checkeuRBDCodeCode_GWB_RBDSUCA($source_list) {  
    for $source in $source_list
        for $i in doc($source)/wfd:GWB/wfd:euRBDCode
            for $RBDSUCA in $RBDSUCAlist
            let $ii := doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD[wfd:euRBDCode = $i]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i, 10001, 0, "euRBDCode", "GWB",$xmlutil:LEVEL_ERROR2)
};
(: #10008 The reported LinkSurfaceWaterBodiesCode must be consistent with the codes reported in schema SWB, element EUSurfaceWaterBodyCode :)
declare function xmlconv:checkLinkSurfaceWaterBodiesCode_GWB_SWB($source_list) {  
    for $source in $source_list
        for $i in doc($source)/wfd:GWB/wfd:GroundWaterBody/wfd:linkSurfaceWaterBodyCode
            for $SWB in $SWBlist
            let $ii := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody[wfd:euSurfaceWaterBodyCode = $i]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i, 10008, 0, "linkSurfaceWaterBodyCode", "GWB/GroundWaterBody",$xmlutil:LEVEL_WARNING)
};
(:==================================================================:)
(:GWMET:)
(:==================================================================:)
(: #10001 The euRBDCode must be consistent with the codes reported in the RBDSUCA schema, element euRBDCode :)
declare function xmlconv:checkeuRBDCodeCode_GWMET_RBDSUCA($source_list) {  
    for $source in $source_list
        for $i in doc($source)/wfd:GWMET
            for $RBDSUCA in $RBDSUCAlist
            let $ii := doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD[wfd:euRBDCode = $i/wfd:euRBDCode]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i/wfd:euRBDCode, 10001, 0, "euRBDCode", "GWMET",$xmlutil:LEVEL_ERROR2)
};
(: #10027 GWMET/GWMethodologies/diminutionDamage :)
declare function xmlconv:check_diminutionDamage_with_GWB($source_list){
    for $source in $source_list
        for $i in doc($source)//wfd:GWMET/wfd:GWMethodologies[wfd:diminutionDamage = 'Not applicable']
            for $GWB in $GWBlist
                let $linkSurfaceWaterBody := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:linkSurfaceWaterBody = 'Yes']
                let $linkTerrestrialEcosystem := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:linkTerrestrialEcosystem = 'Yes']
                return if(count($i) != 0 and (count($linkSurfaceWaterBody) != 0 or count($linkTerrestrialEcosystem) != 0))then(
                    xmlutil:generateOutput_withError_level($outputMode, "", 10027, 0, "diminutionDamage", "GWMET/GWMethodologies",$xmlutil:LEVEL_ERROR2)
                )else()
};
(: #10028 GWMET/GWMethodologies/methodCriterionExtentExceedance :)
declare function xmlconv:check_methodCriterionExtentExceedance_with_GWB($source_list){
     for $source in $source_list
        for $i in doc($source)//wfd:GWMET/wfd:GWMethodologies[wfd:methodCriterionExtentExceedance = 'Not relevant as no monitoring site exceeds any groundwater quality standard or threshold value for any pollutant']
            for $GWB in $GWBlist
            let $methodCriterion := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:gwChemicalStatusValue = '3']
            return if(count($i) != 0 and count($methodCriterion ) != 0) then (
                xmlutil:generateOutput_withError_level($outputMode, "", 10028, 0, "methodCriterionExtentExceedance", "GWMET/GWMethodologies",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10029 GWMET/GWMethodologies/transboundaryGWBPresent :)
declare function xmlconv:check_transboundaryGWBPresent_with_GWB($source_list){
     for $source in $source_list
         let $transboun := doc($source)//wfd:GWMET/wfd:GWMethodologies[wfd:transboundaryGWBPresent = 'Yes']
         for $GWB in $GWBlist
            let $groundwaterBodyTran := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:groundwaterBodyTransboundary = 'Yes']
            return if(count($transboun) = 0 and count($groundwaterBodyTran)  != 0)then(
                xmlutil:generateOutput_withError_level($outputMode, "", 10029, 0, "transboundaryGWBPresent", "GWMET/GWMethodologies",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10030 Cross-schema check (not documented in the WFD guidance document): at least one entry in pollutantIndicatorCode must be reported for each substance reported as 'Yes' in one or more instances of GWB/GroundWaterBody/GWPollutant/gwPollutantCausingRisk and/or GWB/GroundWaterBody/GWPollutant/gwPollutantCausingFailure:)
declare function xmlconv:check_pollutantIndicatorCode_with_GWB($source_list){
  for $GWB in $GWBlist
    for $GWPollutant in doc($GWB)//wfd:GWB/wfd:GroundWaterBody/wfd:GWPollutant[wfd:gwPollutantCausingRisk = 'Yes' or wfd:gwPollutantCausingFailure = 'Yes']
         for $source in $source_list
             let $polluCode := doc($source)//wfd:GWMET/wfd:ThresholdValue[wfd:pollutantIndicatorCode = $GWPollutant/wfd:gwPollutantCode]
            return if( count($polluCode) = 0)then(
                xmlutil:generateOutput_withError_level($outputMode, $GWPollutant/wfd:gwPollutantCode, 10030, 0, "pollutantIndicatorCode", "GWMET/ThresholdValue",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10030 Cross-schema check (not documented in the WFD guidance document): at least one entry in pollutantIndicatorCode must be reported for each substance reported as 'Yes' in one or more instances of GWB/GroundWaterBody/GWPollutant/gwPollutantCausingRisk and/or GWB/GroundWaterBody/GWPollutant/gwPollutantCausingFailure:)
declare function xmlconv:check_pollutantIndicatorCoderOther_with_GWB($source_list){
  for $GWB in $GWBlist
    for $GWPollutant in doc($GWB)//wfd:GWB/wfd:GroundWaterBody/wfd:GWPollutant[(wfd:gwPollutantCausingRisk = 'Yes' or wfd:gwPollutantCausingFailure = 'Yes') and wfd:gwPollutantCode = 'EEA_00-00-0 - Other chemical parameter' and exists(wfd:gwPollutantOther)]
         for $source in $source_list
             let $polluCode := doc($source)//wfd:GWMET/wfd:ThresholdValue[wfd:pollutantIndicatorCode = 'EEA_00-00-0 - Other chemical parameter' and wfd:pollutantIndicatorCodeOther = $GWPollutant/wfd:gwPollutantOther]
            return if(count($polluCode) = 0)then(
                xmlutil:generateOutput_withError_level($outputMode, $GWPollutant/wfd:gwPollutantOther, 10030, 0, "pollutantIndicatorCodeOther", "GWMET/ThresholdValue",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10031 GWMET/GWExemptions/gwExemption44Impact :)
declare function xmlconv:check_gwExemption44Impact_with_GWB($source_list){
    for $source in $source_list
        let $exemp := doc($source)//wfd:GWMET/wfd:GWExemptions[wfd:gwExemption44Impact = 'NOTA - Not applicable']
        for $GWB in $GWBlist
            let $gwQuantitative := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:gwQuantitativeExemptionType = 'Article4(4) - Technical feasibility' or wfd:gwQuantitativeExemptionType = 'Article4(4) - Disproportionate cost' or wfd:gwQuantitativeExemptionType = 'Article4(4) - Natural conditions']
            let $gwChemical := doc($GWB)//wfd:GWB/wfd:GroundWaterBody/wfd:GWPollutant/wfd:GWChemicalExemptionType[wfd:gwChemicalExemptionType = 'Article4(4) - Technical feasibility' or wfd:gwChemicalExemptionType = 'Article4(4) - Disproportionate cost' or wfd:gwChemicalExemptionType = 'Article4(4) - Natural conditions']
            return if(count($exemp) != 0 and (count($gwQuantitative) != 0 or count($gwChemical) != 0))then(
                xmlutil:generateOutput_withError_level($outputMode, "", 10031, 0, "gwExemption44Impact", "GWMET/GWExemptions",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10032 GWMET/GWExemptions/gwExemption45Impact :)
declare function xmlconv:check_gwExemption45Impact_with_GWB($source_list){
    for $source in $source_list
        let $exemp := doc($source)//wfd:GWMET/wfd:GWExemptions[wfd:gwExemption45Impact = 'NOTA - Not applicable']
        for $GWB in $GWBlist
            let $gwQuantitative := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:gwQuantitativeExemptionType = 'Article4(5) - Technical feasibility' or wfd:gwQuantitativeExemptionType = 'Article4(5) - Disproportionate cost']
            let $gwChemical := doc($GWB)//wfd:GWB/wfd:GroundWaterBody/wfd:GWPollutant/wfd:GWChemicalExemptionType[wfd:gwChemicalExemptionType = 'Article4(5) - Technical feasibility' or wfd:gwChemicalExemptionType = 'Article4(5) - Disproportionate cost']
            return if(count($exemp) != 0 and (count($gwQuantitative) != 0 or count($gwChemical) != 0))then(
                xmlutil:generateOutput_withError_level($outputMode, "", 10032, 0, "gwExemption45Impact", "GWMET/GWExemptions",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10033 GWMET/GWExemptions/gwDisproportionateCost :)
declare function xmlconv:check_gwDisproportionateCost_with_GWB($source_list){
    for $source in $source_list
        let $gwDisp := doc($source)//wfd:GWMET/wfd:GWExemptions[wfd:gwDisproportionateCost != 'Yes']
        for $GWB in $GWBlist
            let $gwQuantitative := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:gwQuantitativeExemptionType = 'Article4(4) - Disproportionate cost' or wfd:gwQuantitativeExemptionType = 'Article4(5) - Disproportionate cost']
            let $gwChemical := doc($GWB)//wfd:GWB/wfd:GroundWaterBody/wfd:GWPollutant/wfd:GWChemicalExemptionType[wfd:gwChemicalExemptionType = 'Article4(4) - Disproportionate cost' or wfd:gwChemicalExemptionType = 'Article4(5) - Disproportionate cost']
               return if(count($gwDisp) != 0 and (count($gwQuantitative) != 0 or count($gwChemical) != 0))then(
                xmlutil:generateOutput_withError_level($outputMode, "", 10033, 0, "gwDisproportionateCost", "GWMET/GWExemptions",$xmlutil:LEVEL_ERROR2)
               )else()
};
(: #10034 GWMET/GWExemptions/gwTechnicalInfeasibility :)
declare function xmlconv:check_gwTechnicalInfeasibility_with_GWB($source_list){
    for $source in $source_list
        let $gwTechnical := doc($source)//wfd:GWMET/wfd:GWExemptions[wfd:gwTechnicalInfeasibility = 'Technical infeasibility has not been used as a reason for exemption']
        for $GWB in $GWBlist
           let $gwQuantitative := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:gwQuantitativeExemptionType = 'Article4(4) - Technical feasibility' or wfd:gwQuantitativeExemptionType = 'Article4(5) - Technical feasibility']
           let $gwChemical := doc($GWB)//wfd:GWB/wfd:GroundWaterBody/wfd:GWPollutant/wfd:GWChemicalExemptionType[wfd:gwChemicalExemptionType = 'Article4(4) - Technical feasibility' or wfd:gwChemicalExemptionType = 'Article4(5) - Technical feasibility']
           return if(count($gwTechnical) != 0 and (count($gwQuantitative) != 0 or count($gwChemical) != 0))then(
                xmlutil:generateOutput_withError_level($outputMode, "", 10034, 0, "gwTechnicalInfeasibility", "GWMET/GWExemptions",$xmlutil:LEVEL_ERROR2)
           )else()
};
(: #10035 GWMET/GWExemptions/gwNaturalConditions :)
declare function xmlconv:check_gwNaturalConditions_with_GWB($source_list){
    for $source in $source_list
       let $gwNatural := doc($source)//wfd:GWMET/wfd:GWExemptions[wfd:gwNaturalConditions = 'Natural condition has not been used as a reason for exemption for groundwater bodies']
       for $GWB in $GWBlist
           let $gwQuantitative := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:gwQuantitativeExemptionType = 'Article4(4) - Natural conditions']
           let $gwChemical := doc($GWB)//wfd:GWB/wfd:GroundWaterBody/wfd:GWPollutant/wfd:GWChemicalExemptionType[wfd:gwChemicalExemptionType = 'Article4(4) - Natural conditions']
           return if(count($gwNatural) != 0 and (count($gwQuantitative) != 0 or count($gwChemical) != 0))then(
            xmlutil:generateOutput_withError_level($outputMode, "", 10035, 0, "gwNaturalConditions", "GWMET/GWExemptions",$xmlutil:LEVEL_ERROR2)
           )else()
};
(: #10036 GWMET/GWExemptions/gwExemption46 :)
declare function xmlconv:check_gwExemption46_with_GWB($source_list){
    for $source in $source_list
        let $gwExemption := doc($source)//wfd:GWMET/wfd:GWExemptions[wfd:gwExemption46 = 'Article 4(6) has not been applied']
        for $GWB in $GWBlist
            let $gwQuantitative := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:gwQuantitativeExemptionType = 'Article4(6) - Natural causes' or wfd:gwQuantitativeExemptionType = 'Article4(6) - Force Majeure' or wfd:gwQuantitativeExemptionType ='Article4(6) - Accidents']
            let $gwChemical := doc($GWB)//wfd:GWB/wfd:GroundWaterBody/wfd:GWPollutant/wfd:GWChemicalExemptionType[wfd:gwChemicalExemptionType = 'Article4(6) - Natural causes' or wfd:gwChemicalExemptionType = 'Article4(6) - Force Majeure' or wfd:gwChemicalExemptionType ='Article4(6) - Accidents']
            return if(count($gwExemption) != 0 and (count($gwQuantitative) != 0 or count($gwChemical) != 0))then(
                 xmlutil:generateOutput_withError_level($outputMode, "", 10036, 0, "gwExemption46", "GWMET/GWExemptions",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10037 GWMET/GWExemptions/gwExemption47 :)
declare function xmlconv:gwExemption47_with_GWB($source_list){
    for $source in $source_list
        let $gwExemption := doc($source)//wfd:GWMET/wfd:GWExemptions[wfd:gwExemption47 = 'Article 4(7) has not been applied']
        for $GWB in $GWBlist
            let $gwQuantitative := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:gwQuantitativeExemptionType = 'Article4(7) - New modification' or wfd:gwQuantitativeExemptionType = 'Article4(7) - Sustainable human development']
            let $gwChemical := doc($GWB)//wfd:GWB/wfd:GroundWaterBody/wfd:GWPollutant/wfd:GWChemicalExemptionType[wfd:gwChemicalExemptionType = 'Article4(7) - New modification' or wfd:gwChemicalExemptionType = 'Article4(7) - Sustainable human development']
            return if(count($gwExemption) != 0 and (count($gwQuantitative) != 0 or count($gwChemical) != 0))then(
                 xmlutil:generateOutput_withError_level($outputMode, "", 10037, 0, "gwExemption47", "GWMET/GWExemptions",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10038 GWMET/GWExemptions/gwExemptionsTransboundary :)
declare function xmlconv:check_gwExemptionsTransboundary_GWB($source_list){
    for $source in $source_list
        let $gwExemption :=  doc($source)//wfd:GWMET/wfd:GWExemptions[wfd:gwExemptionsTransboundary = 'Not applicable']
        for $GWB in $GWBlist
           let $gwTras := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:groundwaterBodyTransboundary = 'Yes']
           return if(count($gwExemption) > 0 and count($gwTras) > 0) then(
                xmlutil:generateOutput_withError_level($outputMode, "", 10038, 0, "gwExemptionsTransboundary", "GWMET/GWExemptions",$xmlutil:LEVEL_ERROR2)
           )else()
};
(:==================================================================:)
(:SWMET:)
(:==================================================================:)
(: #10001 The euRBDCode must be consistent with the codes reported in the RBDSUCA schema, element euRBDCode :)
declare function xmlconv:checkeuRBDCodeCode_SWMET_RBDSUCA($source_list) {  
    for $source in $source_list
        for $i in doc($source)/wfd:SWMET
            for $RBDSUCA in $RBDSUCAlist
            let $ii := doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD[wfd:euRBDCode = $i/wfd:euRBDCode]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i/wfd:euRBDCode, 10001, 0, "euRBDCode", "SWMET",$xmlutil:LEVEL_ERROR2)
};
(: #10011 The reported IntercalibrationType must be consistent with the codes reported in SWB/SWCharacterisation/SurfaceWaterBodyIntercalibrationType :)
declare function xmlconv:checkIntercalibrationType_SWMET_SWB($source_list) {  
    for $source in $source_list
        for $i in doc($source)/wfd:SWMET/wfd:SWType/wfd:swIntercalibrationType
            for $SWB in $SWBlist
            let $ii := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody[wfd:surfaceWaterBodyIntercalibrationType = $i]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i, 10011, 0, "intercalibrationType", "SWMET/SWType",$xmlutil:LEVEL_ERROR2)
};
(: #10012 IRBDTypologyCoOrdinationReference Report if International is ‘Yes’ in RBDSUCA schema :)
declare function xmlconv:checkIRBDTypologyCoOrdinationReference_SWMET_RBDSUCA($source_list) {
for $source in $source_list
    for $i in doc($source)/wfd:SWMET
        for $RBDSUCA in $RBDSUCAlist
        let $ii := doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD[wfd:euRBDCode = $i/wfd:euRBDCode]
        return if ($ii/wfd:internationalRBD = 'Yes') then
        (
            if (not(exists($i/wfd:SWMethodologies/wfd:iRBDTypologyCoOrdinationReference)) or ($i/wfd:SWMethodologies/wfd:iRBDTypologyCoOrdinationReference = '')) then
            (
                xmlutil:generateOutput_withError_level($outputMode, "", 10012, 0, "iRBDTypologyCoOrdinationReference", "SWMET/SWMethodologies",$xmlutil:LEVEL_ERROR2)
            )
            else
            (
            )
        )
        else
        (
        )
};
(: #10020 SWMET/SWExemptions/swExemption44Impact :)
declare function xmlconv:check_swExemption44Impact_with_SWB($source_list){
    for $source in $source_list
        for $i in doc($source)/wfd:SWMET/wfd:SWExemptions[wfd:swExemption44Impact = 'NOTA - Not applicable']
            for $SWB in $SWBlist
                let $swEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWEcologicalExemptionType[wfd:swEcologicalExemptionType = 'Article4(4) - Technical feasibility' or wfd:swEcologicalExemptionType = 'Article4(4) - Disproportionate cost' or wfd:swEcologicalExemptionType = 'Article4(4) - Natural conditions']
                let $qeEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:QualityElement[wfd:qeEcologicalExemptionType = 'Article4(4) - Technical feasibility' or wfd:qeEcologicalExemptionType = 'Article4(4) - Disproportionate cost' or wfd:qeEcologicalExemptionType = 'Article4(4) - Natural conditions']
                let $protectedAreaExemption := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWAssociatedProtectedArea[wfd:protectedAreaExemption = 'Article4(4) - Technical feasibility' or wfd:protectedAreaExemption = 'Article4(4) - Disproportionate cost' or wfd:protectedAreaExemption = 'Article4(4) - Natural conditions']
                let $swChemicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWPrioritySubstance/wfd:SWChemicalExemptionType[wfd:swChemicalExemptionType = 'Article4(4) - Technical feasibility' or wfd:swChemicalExemptionType = 'Article4(4) - Disproportionate cost' or wfd:swChemicalExemptionType = 'Article4(4) - Natural conditions']
                return if (count($i) != 0 and (count($swEcologicalExemptionType) != 0 or count($qeEcologicalExemptionType) != 0 or count($protectedAreaExemption) != 0 or count($swChemicalExemptionType) != 0)) then(
                    xmlutil:generateOutput_withError_level($outputMode, "", 10020, 0, "swExemption44Impact", "SWMET/SWExemptions",$xmlutil:LEVEL_ERROR2)
                )else()
};
(: #10021 SWMET/SWExemptions/swExemption45Impact :)
declare function xmlconv:check_swExemption45Impact_with_SWB($source_list){
    for $source in $source_list
        for $i in doc($source)/wfd:SWMET/wfd:SWExemptions[wfd:swExemption45Impact = 'NOTA - Not applicable']
            for $SWB in $SWBlist
                let $swEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWEcologicalExemptionType[wfd:swEcologicalExemptionType = 'Article4(5) - Technical feasibility' or wfd:swEcologicalExemptionType = 'Article4(5) - Disproportionate cost']
                let $qeEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:QualityElement[wfd:qeEcologicalExemptionType = 'Article4(5) - Technical feasibility' or wfd:qeEcologicalExemptionType = 'Article4(5) - Disproportionate cost']
                let $protectedAreaExemption := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWAssociatedProtectedArea[wfd:protectedAreaExemption = 'Article4(5) - Technical feasibility' or wfd:protectedAreaExemption = 'Article4(5) - Disproportionate cost']
                let $swChemicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWPrioritySubstance/wfd:SWChemicalExemptionType[wfd:swChemicalExemptionType = 'Article4(5) - Technical feasibility' or wfd:swChemicalExemptionType = 'Article4(5) - Disproportionate cost']
                return if (count($i) != 0 and (count($swEcologicalExemptionType) != 0 or count($qeEcologicalExemptionType) != 0 or count($protectedAreaExemption) != 0 or count($swChemicalExemptionType) != 0)) then(
                    xmlutil:generateOutput_withError_level($outputMode, "", 10021, 0, "swExemption45Impact", "SWMET/SWExemptions",$xmlutil:LEVEL_ERROR2)
                )else()
};
(: #10022 SWMET/SWExemptions/swDisproportionateCost :)
declare function xmlconv:check_swDisproportionateCost_with_SWB($source_list){
    for $source in $source_list
        for $SWB in $SWBlist
            let $swDisproport := doc($source)//wfd:SWMET/wfd:SWExemptions[wfd:swDisproportionateCost != 'Yes']
            let $swEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWEcologicalExemptionType[wfd:swEcologicalExemptionType = 'Article4(4) - Disproportionate cost' or wfd:swEcologicalExemptionType = 'Article4(5) - Disproportionate cost']
            let $qeEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:QualityElement[wfd:qeEcologicalExemptionType = 'Article4(4) - Disproportionate cost' or wfd:qeEcologicalExemptionType = 'Article4(5) - Disproportionate cost']
            let $protectedAreaExemption := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWAssociatedProtectedArea[wfd:protectedAreaExemption = 'Article4(4) - Disproportionate cost' or wfd:protectedAreaExemption = 'Article4(5) - Disproportionate cost']
            let $swChemicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWPrioritySubstance/wfd:SWChemicalExemptionType[wfd:swChemicalExemptionType = 'Article4(4) - Disproportionate cost' or wfd:swChemicalExemptionType = 'Article4(5) - Disproportionate cost']
            return if(count($swDisproport) != 0 and(count($swEcologicalExemptionType) != 0 or count($qeEcologicalExemptionType) != 0 or count($protectedAreaExemption) != 0 or count($swChemicalExemptionType) != 0))then(
                xmlutil:generateOutput_withError_level($outputMode, "", 10022, 0, "swDisproportionateCost", "SWMET/SWExemptions",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10023 SWMET/SWExemptions/swTechnicalInfeasibility :)
declare function xmlconv:check_swTechnicalInfeasibility_with_SWB($source_list){
     for $source in $source_list
         for $SWB in $SWBlist
            let $swDisproport := doc($source)//wfd:SWMET/wfd:SWExemptions[wfd:swTechnicalInfeasibility = 'Technical infeasibility has not been used as a reason for exemption']
            let $swEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWEcologicalExemptionType[wfd:swEcologicalExemptionType = 'Article4(4) - Technical feasibility' or wfd:swEcologicalExemptionType = 'Article4(5) - Technical feasibility']
            let $qeEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:QualityElement[wfd:qeEcologicalExemptionType = 'Article4(4) - Technical feasibility' or wfd:qeEcologicalExemptionType = 'Article4(5) - Technical feasibility']
            let $protectedAreaExemption := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWAssociatedProtectedArea[wfd:protectedAreaExemption = 'Article4(4) - Technical feasibility' or wfd:protectedAreaExemption = 'Article4(5) - Technical feasibility']
            let $swChemicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWPrioritySubstance/wfd:SWChemicalExemptionType[wfd:swChemicalExemptionType = 'Article4(4) - Technical feasibility' or wfd:swChemicalExemptionType = 'Article4(5) - Technical feasibility']
            return if(count($swDisproport) != 0 and(count($swEcologicalExemptionType) != 0 or count($qeEcologicalExemptionType) != 0 or count($protectedAreaExemption) != 0 or count($swChemicalExemptionType) != 0))then(
                xmlutil:generateOutput_withError_level($outputMode, "", 10023, 0, "swTechnicalInfeasibility", "SWMET/SWExemptions",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10024 SWMET/SWExemptions/swNaturalConditions :)
declare function xmlconv:check_swNaturalConditions_with_SWB($source_list){
     for $source in $source_list
         for $SWB in $SWBlist
            let $swDisproport := doc($source)//wfd:SWMET/wfd:SWExemptions[wfd:swNaturalConditions = 'Natural condition has not been used as a reason for exemption for surface water bodies']
            let $swEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWEcologicalExemptionType[wfd:swEcologicalExemptionType = 'Article4(4) - Natural conditions']
            let $qeEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:QualityElement[wfd:qeEcologicalExemptionType = 'Article4(4) - Natural conditions']
            let $protectedAreaExemption := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWAssociatedProtectedArea[wfd:protectedAreaExemption = 'Article4(4) - Natural conditions']
            let $swChemicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWPrioritySubstance/wfd:SWChemicalExemptionType[wfd:swChemicalExemptionType = 'Article4(4) - Natural conditions']
            return if(count($swDisproport) != 0 and(count($swEcologicalExemptionType) != 0 or count($qeEcologicalExemptionType) != 0 or count($protectedAreaExemption) != 0 or count($swChemicalExemptionType) != 0))then(
                xmlutil:generateOutput_withError_level($outputMode, "", 10024, 0, "swNaturalConditions", "SWMET/SWExemptions",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10025 SWMET/SWExemptions/swExemption46 :)
declare function xmlconv:check_swExemption46_with_SWB($source_list){
     for $source in $source_list
         for $SWB in $SWBlist
            let $swDisproport := doc($source)//wfd:SWMET/wfd:SWExemptions[wfd:swExemption46 = 'Article 4(6) has not been applied']
            let $swEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWEcologicalExemptionType[wfd:swEcologicalExemptionType = 'Article4(6) - Natural causes' or wfd:swEcologicalExemptionType = 'Article4(6) - Force Majeure' or wfd:swEcologicalExemptionType = 'Article4(6) - Accidents']
            let $qeEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:QualityElement[wfd:qeEcologicalExemptionType = 'Article4(6) - Natural causes' or wfd:qeEcologicalExemptionType = 'Article4(6) - Force Majeure' or wfd:qeEcologicalExemptionType = 'Article4(6) - Accidents']
            let $protectedAreaExemption := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWAssociatedProtectedArea[wfd:protectedAreaExemption = 'Article4(6) - Natural causes' or wfd:protectedAreaExemption = 'Article4(6) - Force Majeure' or wfd:protectedAreaExemption = 'Article4(6) - Accidents']
            let $swChemicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWPrioritySubstance/wfd:SWChemicalExemptionType[wfd:swChemicalExemptionType = 'Article4(6) - Natural causes' or wfd:swChemicalExemptionType = 'Article4(6) - Force Majeure' or wfd:swChemicalExemptionType = 'Article4(6) - Accidents']
            return if(count($swDisproport) != 0 and(count($swEcologicalExemptionType) !=0  or count($qeEcologicalExemptionType) !=0 or count($protectedAreaExemption) !=0 or count($swChemicalExemptionType) !=0)) then(
                xmlutil:generateOutput_withError_level($outputMode, "", 10025, 0, "swExemption46", "SWMET/SWExemptions",$xmlutil:LEVEL_ERROR2)
            )else()
};
(: #10026 SWMET/SWExemptions/swExemption47 :)
declare function xmlconv:check_swExemption47_with_SWB($source_list){
     for $source in $source_list
         for $SWB in $SWBlist
            let $swDisproport := doc($source)//wfd:SWMET/wfd:SWExemptions[wfd:swExemption47 = 'Article 4(7) has not been applied']
            let $swEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWEcologicalExemptionType[wfd:swEcologicalExemptionType = 'Article4(7) - New modification' or wfd:swEcologicalExemptionType = 'Article4(7) - Sustainable human development']
            let $qeEcologicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:QualityElement[wfd:qeEcologicalExemptionType = 'Article4(7) - New modification' or wfd:qeEcologicalExemptionType = 'Article4(7) - Sustainable human development']
            let $protectedAreaExemption := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWAssociatedProtectedArea[wfd:protectedAreaExemption = 'Article4(7) - New modification' or wfd:protectedAreaExemption = 'Article4(7) - Sustainable human development']
            let $swChemicalExemptionType := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody/wfd:SWPrioritySubstance/wfd:SWChemicalExemptionType[wfd:swChemicalExemptionType = 'Article4(7) - New modification' or wfd:swChemicalExemptionType = 'Article4(7) - Sustainable human development']
            return if(count($swDisproport) != 0 and(count($swEcologicalExemptionType) != 0 or count($qeEcologicalExemptionType) != 0 or count($protectedAreaExemption) != 0 or count($swChemicalExemptionType) != 0))then(
                xmlutil:generateOutput_withError_level($outputMode, "", 10026, 0, "swExemption47", "SWMET/SWExemptions/swExemption47",$xmlutil:LEVEL_ERROR2)
            )else()
};
(:==================================================================:)
(:RBMPPoM:)
(:==================================================================:)
(: #10001 The reported euRBDCode must be consistent with the codes reported in RBDSUCA schema, element euRBDCode :)
declare function xmlconv:checkeuRBDCode_RBMPPoM_RBDSUCA($source_list) {
    for $source in $source_list
        for $i in doc($source)/wfd:RBMPPoM/wfd:euRBDCode
            for $RBDSUCA in $RBDSUCAlist
            let $ii := doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD[wfd:euRBDCode = $i]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i, 10001, 0, "euRBDCode", "RBMPPoM",$xmlutil:LEVEL_ERROR2)
};
(: #10013 euSubUnitCode must be reported if RBDSUCA/RBD/subUnitsDefined is ‘Yes’ :)
declare function xmlconv:checkEURBDSubUnitCode_RBMPPoM_RBDSUCA($source_list) {
    for $source in $source_list
        for $i in doc($source)/wfd:RBMPPoM
            for $RBDSUCA in $RBDSUCAlist
                for $j in doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD[wfd:euRBDCode = $i/wfd:euRBDCode]
                    for $k in $i/wfd:InputInventory
                        return if ($j/wfd:subUnitsDefined = 'Yes' and not(exists($k/wfd:euSubUnitCode)) or ($k/wfd:euSubUnitCode = '')) then
                            (xmlutil:generateOutput_withError_level($outputMode, "", 10013, 0, "euSubUnitCode", "RBMPPoM/InputInventory",$xmlutil:LEVEL_ERROR2) 
                        )
                        else()
};
(: #10014 EUSubUnitCode must be consistent with codes reported in schema RBDSUCA, element EUSubUnitCode. :)
declare function xmlconv:checkEUSubUnitCode_RBMPPoM_RBDSUCA($source_list) {
    for $source in $source_list
        for $i in doc($source)/wfd:RBMPPoM/wfd:InputInventory[exists(wfd:euSubUnitCode)]
            for $RBDSUCA in $RBDSUCAlist
            let $ii := doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD[wfd:euRBDCode = doc($source)/wfd:RBMPPoM/wfd:euRBDCode]/wfd:SubUnit[wfd:euSubUnitCode = $i/wfd:euSubUnitCode]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i/wfd:euSubUnitCode, 10014, 0, "euSubUnitCode", "RBMPPoM/InputInventory",$xmlutil:LEVEL_ERROR2)
};
(: #10015 riverineLoadMonitoringSite must be consistent with codes reported in schema SWMonitoring, element EUSWMonitoringSiteCode :)
declare function xmlconv:checkRiverineLoadCode_RBMPPoM_SWM($source_list) {
    for $source in $source_list
    for $i in doc($source)//wfd:RBMPPoM/wfd:InputInventory/wfd:InputPollutant/wfd:InputCategory/wfd:riverineLoadMonitoringSite
        for $Monitoring in $Monitoringlist
        let $ii := doc($Monitoring)//wfd:Monitoring/wfd:MonitoringSite[wfd:euMonitoringSiteCode = $i]  
        where count($ii) = 0
        return xmlutil:generateOutput_withError_level($outputMode, $i, 10015, 0, "riverineLoadMonitoringSite", "RBMPPoM/InputInventory/InputPollutant/InputCategory",$xmlutil:LEVEL_ERROR2)  
};
(: #10016 EUSubUnitCode Report if RBDSUCA/RBD/SubUnitsDefined is ‘Yes’ and RBMPPoM/PoM/SurfaceWaterOrGroundwater is Surface Water :)
declare function xmlconv:checkEUSubUnitCode_RBDSUCA_SWB($source_list) {
    for $source in $source_list
        for $i in doc($source)/wfd:RBMPPoM
            for $RBDSUCA in $RBDSUCAlist
                for $j in doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD[wfd:euRBDCode = $i/wfd:euRBDCode and wfd:subUnitsDefined = 'Yes']
                    for $k in $i/wfd:PoM
                        return if ($k/wfd:surfaceWaterOrGroundwater = 'Surface water' and (not(exists($k/wfd:euSubUnitCode)) or ($k/wfd:euSubUnitCode = ''))) then (
                            xmlutil:generateOutput_withError_level($outputMode, "", 10016, 0, "euSubUnitCode", "RBMPPoM/PoM",$xmlutil:LEVEL_ERROR2)
                        )else()
};
(: #10017 EUSubUnitCode must be consistent with codes reported in schema RBDSUCA, element EUSubUnitCode. :)
declare function xmlconv:checkEUSubUnitCode_RBMPPoM_RBDSUCA2($source_list) {
    for $source in $source_list
        for $i in doc($source)/wfd:RBMPPoM/wfd:PoM[exists(wfd:euSubUnitCode)]
            for $RBDSUCA in $RBDSUCAlist
            let $ii := doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD[wfd:euRBDCode = doc($source)/wfd:RBMPPoM/wfd:euRBDCode]/wfd:SubUnit[wfd:euSubUnitCode = $i/wfd:euSubUnitCode]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i/wfd:euSubUnitCode, 10017, 0, "euSubUnitCode", "RBMPPoM/PoM",$xmlutil:LEVEL_ERROR2)
};
(:==================================================================:)
(:Monitoring:)
(:==================================================================:)
(: #10001 The reported euRBDCode must be consistent with the codes reported in RBDSUCA schema, element euRBDCode :)
declare function xmlconv:checkeuRBDCode_Monitoring_RBDSUCA($source_list) {
    for $source in $source_list
        for $i in doc($source)/wfd:Monitoring/wfd:euRBDCode
            for $RBDSUCA in $RBDSUCAlist
            let $ii := doc($RBDSUCA)//wfd:RBDSUCA/wfd:RBD[wfd:euRBDCode = $i]
            where count($ii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i, 10001, 0, "euRBDCode", "Monitoring",$xmlutil:LEVEL_ERROR2)
};
(: #10010 The reported EUSurfaceWaterBodyCode must be consistent with the codes reported in schema SWB, element EUSurfaceWaterBodyCode. :)
declare function xmlconv:checkeuSurfaceWaterBodyCode_Monitoring_RBDSUCA($source_list) {
    for $source in $source_list
        for $i in doc($source)/wfd:Monitoring/wfd:MonitoringSite
            for $SWB in $SWBlist
            let $ii := doc($SWB)//wfd:SWB/wfd:SurfaceWaterBody[wfd:euSurfaceWaterBodyCode = $i/wfd:euWaterBodyCode]
            for $GWB in $GWBlist
            let $iii := doc($GWB)//wfd:GWB/wfd:GroundWaterBody[wfd:euGroundWaterBodyCode = $i/wfd:euWaterBodyCode]
            where count($ii) = 0 and count($iii) = 0
            return xmlutil:generateOutput_withError_level($outputMode, $i/wfd:euWaterBodyCode, 10010, 0, "euSurfaceWaterBodyCode", "SWMonitoring/SWMonitorings",$xmlutil:LEVEL_ERROR2)
};
(:==================================================================:)
(:  Cross-schema Checks:)
(:==================================================================:)
declare function xmlconv:crossSWB(){
let $checkeuRBDCode_SWB_RBDSUCA := if(exists($RBDSUCAlist) and $RBDSUCAlist != "") then (xmlconv:checkeuRBDCodeCode_SWB_RBDSUCA($SWBlist))else()
let $checkEUSubUnitCode_SWB_RBDSUCA := if(exists($RBDSUCAlist)and $RBDSUCAlist != "") then (xmlconv:checkSubUnitcode_SWB_RBDSUCA($SWBlist))else()
let $checkSurfaceWaterBodyTypeCode_SWB_SWMET := if(exists($SWMETlist)and $SWMETlist != "") then (xmlconv:checkSurfaceWaterBodyTypeCode_SWB_SWMET($SWBlist))else()
let $checkSWFailingRBSP_SWB_SWMET := if(exists($SWMETlist)and $SWMETlist != "") then (xmlconv:checkSWFailingRBSP_SWB_SWMET($SWBlist))else()
let $checkSurfaceWaterBodyIntercalibrationType_SWB_SWMET := if(exists($SWMETlist)and $SWMETlist != "") then (xmlconv:checkSurfaceWaterBodyIntercalibrationType_SWB_SWMET($SWBlist))else()
let $hasErrors :=  exists($checkeuRBDCode_SWB_RBDSUCA) or
                   exists($checkEUSubUnitCode_SWB_RBDSUCA) or
                   exists($checkSurfaceWaterBodyTypeCode_SWB_SWMET) or
                   exists($checkSWFailingRBSP_SWB_SWMET) or
                   exists($checkSurfaceWaterBodyIntercalibrationType_SWB_SWMET) 
return
        if( $hasErrors and $format != 'fme_xml') then
        (
            <div>
                {
                    xmlutil:buildTableErrorReport_CrossXML($checkeuRBDCode_SWB_RBDSUCA
                                                  union $checkEUSubUnitCode_SWB_RBDSUCA
                                                  union $checkSurfaceWaterBodyTypeCode_SWB_SWMET
                                                  union $checkSWFailingRBSP_SWB_SWMET
                                                  union $checkSurfaceWaterBodyIntercalibrationType_SWB_SWMET)
                }
            </div>
        )
        else if($hasErrors = false() and $format != 'fme_xml')then
        (
            <div>
                {xmlutil:buildDescription("All validation checks were passed successfully.")}
            </div>
         )
         else if($hasErrors and $format = 'fme_xml')then(
          <xmlErrors>
            {($checkeuRBDCode_SWB_RBDSUCA
                                                  union $checkEUSubUnitCode_SWB_RBDSUCA
                                                  union $checkSurfaceWaterBodyTypeCode_SWB_SWMET
                                                  union $checkSWFailingRBSP_SWB_SWMET
(:union $checkEUProtectedAreaCode_SWB_ProtectecdArea:)
                                                  union $checkSurfaceWaterBodyIntercalibrationType_SWB_SWMET)}
            </xmlErrors>
         )
         else()
};
declare function xmlconv:crossGWB(){
let $checkeuRBDCode_GWB_RBDSUCA := if(exists($RBDSUCAlist)and $RBDSUCAlist != "") then (xmlconv:checkeuRBDCodeCode_GWB_RBDSUCA($GWBlist))else()
let $checkLinkSurfaceWaterBodiesCode_GWB_SWB := if(exists($SWBlist)and $SWBlist != "") then (xmlconv:checkLinkSurfaceWaterBodiesCode_GWB_SWB($GWBlist))else()
let $hasErrors :=  exists($checkeuRBDCode_GWB_RBDSUCA) or
                   exists($checkLinkSurfaceWaterBodiesCode_GWB_SWB) 
return
        if( $hasErrors and $format != 'fme_xml') then
        (
            <div>
                {
                                        xmlutil:buildTableErrorReport_CrossXML($checkeuRBDCode_GWB_RBDSUCA
                                                  union $checkLinkSurfaceWaterBodiesCode_GWB_SWB)
                 }
            </div>
        )
        else if($hasErrors = false() and $format != 'fme_xml')then
        (
            <div>
                {xmlutil:buildDescription("All validation checks were passed successfully.")}
            </div>
         )
         else if($hasErrors and $format = 'fme_xml')then(
          <xmlErrors>
                             {( $checkeuRBDCode_GWB_RBDSUCA
                                                  union $checkLinkSurfaceWaterBodiesCode_GWB_SWB)}
                  </xmlErrors>
         )
         else()
};
declare function xmlconv:crossGWMET(){
let $checkeuRBDCodeCode_GWMET_RBDSUCA := if(exists($RBDSUCAlist) and $RBDSUCAlist != "") then (xmlconv:checkeuRBDCodeCode_GWMET_RBDSUCA($GWMETlist))else()
let $check_diminutionDamage_with_GWB := if(exists($GWBlist) and $GWBlist != "")then(xmlconv:check_diminutionDamage_with_GWB($GWMETlist))else()
let $check_methodCriterionExtentExceedance_with_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:check_methodCriterionExtentExceedance_with_GWB($GWMETlist))else()
let $check_transboundaryGWBPresent_with_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:check_transboundaryGWBPresent_with_GWB($GWMETlist))else()
let $check_pollutantIndicatorCode_with_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:check_pollutantIndicatorCode_with_GWB($GWMETlist))else()
let $check_gwExemption44Impact_with_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:check_gwExemption44Impact_with_GWB($GWMETlist))else()
let $check_gwExemption45Impact_with_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:check_gwExemption45Impact_with_GWB($GWMETlist))else()
let $check_gwDisproportionateCost_with_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:check_gwDisproportionateCost_with_GWB($GWMETlist))else()
let $check_gwTechnicalInfeasibility_with_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:check_gwTechnicalInfeasibility_with_GWB($GWMETlist))else()
let $check_gwNaturalConditions_with_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:check_gwNaturalConditions_with_GWB($GWMETlist))else()
let $check_gwExemption46_with_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:check_gwExemption46_with_GWB($GWMETlist))else()
let $gwExemption47_with_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:gwExemption47_with_GWB($GWMETlist))else()
let $check_gwExemptionsTransboundary_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:check_gwExemptionsTransboundary_GWB($GWMETlist))else()
let $check_pollutantIndicatorCoderOther_with_GWB := if(exists($GWBlist)and $GWBlist != "")then(xmlconv:check_pollutantIndicatorCoderOther_with_GWB($GWMETlist))else()
let $hasErrors :=  exists($checkeuRBDCodeCode_GWMET_RBDSUCA) or 
                   exists($check_diminutionDamage_with_GWB) or
                   exists($check_methodCriterionExtentExceedance_with_GWB) or
                   exists($check_transboundaryGWBPresent_with_GWB) or
                   exists($check_pollutantIndicatorCode_with_GWB)or
                   exists($check_gwExemption44Impact_with_GWB) or
                   exists($check_gwExemption45Impact_with_GWB) or
                   exists($check_gwDisproportionateCost_with_GWB) or
                   exists($check_gwTechnicalInfeasibility_with_GWB) or
                   exists($check_gwNaturalConditions_with_GWB) or
                   exists($check_gwExemption46_with_GWB) or
                   exists($gwExemption47_with_GWB) or
                   exists($check_gwExemptionsTransboundary_GWB) or
                   exists($check_pollutantIndicatorCoderOther_with_GWB)
return
        if( $hasErrors and $format != 'fme_xml') then
        (
            <div>
                {
                                       xmlutil:buildTableErrorReport_CrossXML($checkeuRBDCodeCode_GWMET_RBDSUCA
                                                  union $check_diminutionDamage_with_GWB
                                                  union $check_methodCriterionExtentExceedance_with_GWB
                                                  union $check_transboundaryGWBPresent_with_GWB
                                                  union $check_pollutantIndicatorCode_with_GWB
                                                  union $check_gwExemption44Impact_with_GWB
                                                  union $check_gwExemption45Impact_with_GWB
                                                  union $check_gwDisproportionateCost_with_GWB
                                                  union $check_gwTechnicalInfeasibility_with_GWB
                                                  union $check_gwNaturalConditions_with_GWB
                                                  union $check_gwExemption46_with_GWB
                                                  union $gwExemption47_with_GWB
                                                  union $check_gwExemptionsTransboundary_GWB
                                                  union $check_pollutantIndicatorCoderOther_with_GWB)
                   }
            </div>
        )
        else if($hasErrors = false() and $format != 'fme_xml')then
        (
            <div>
                {xmlutil:buildDescription("All validation checks were passed successfully.")}
            </div>
         )
         else if($hasErrors and $format = 'fme_xml')then(
          <xmlErrors>
                              {($checkeuRBDCodeCode_GWMET_RBDSUCA
                                                  union $check_diminutionDamage_with_GWB
                                                  union $check_methodCriterionExtentExceedance_with_GWB
                                                  union $check_transboundaryGWBPresent_with_GWB
                                                  union $check_pollutantIndicatorCode_with_GWB
                                                  union $check_gwExemption44Impact_with_GWB
                                                  union $check_gwExemption45Impact_with_GWB
                                                  union $check_gwDisproportionateCost_with_GWB
                                                  union $check_gwTechnicalInfeasibility_with_GWB
                                                  union $check_gwNaturalConditions_with_GWB
                                                  union $check_gwExemption46_with_GWB
                                                  union $gwExemption47_with_GWB
                                                  union $check_gwExemptionsTransboundary_GWB
                                                  union $check_pollutantIndicatorCoderOther_with_GWB)}
                    </xmlErrors>
         )
         else()
};
declare function xmlconv:crossSWMET(){
let $checkeuRBDCodeCode_SWMET_RBDSUCA := if(exists($RBDSUCAlist)) then (xmlconv:checkeuRBDCodeCode_SWMET_RBDSUCA($SWMETlist))else()
(:let $checksurfaceWaterBodyTypeCode_SWMET_SWB := if(exists($SWBlist)) then (xmlconv:checksurfaceWaterBodyTypeCode_SWMET_SWB($SWMETlist))else():)
let $checkIntercalibrationType_SWMET_SWB := if(exists($SWBlist)and $SWBlist != "") then (xmlconv:checkIntercalibrationType_SWMET_SWB($SWMETlist))else()
let $checkIRBDTypologyCoOrdinationReference_SWMET_RBDSUCA := if(exists($RBDSUCAlist)and $RBDSUCAlist != "") then (xmlconv:checkIRBDTypologyCoOrdinationReference_SWMET_RBDSUCA($SWMETlist))else()
let $check_swExemption44Impact_with_SWB := if(exists($SWBlist)and $SWBlist != "") then (xmlconv:check_swExemption44Impact_with_SWB($SWMETlist)) else()
let $check_swExemption45Impact_with_SWB := if(exists($SWBlist)and $SWBlist != "") then (xmlconv:check_swExemption45Impact_with_SWB($SWMETlist)) else()
let $check_swDisproportionateCost_with_SWB := if(exists($SWBlist)and $SWBlist != "") then (xmlconv:check_swDisproportionateCost_with_SWB($SWMETlist))else()
let $check_swTechnicalInfeasibility_with_SWB := if(exists($SWBlist)and $SWBlist != "") then (xmlconv:check_swTechnicalInfeasibility_with_SWB($SWMETlist))else()
let $check_swNaturalConditions_with_SWB := if(exists($SWBlist)and $SWBlist != "") then(xmlconv:check_swNaturalConditions_with_SWB($SWMETlist))else()
let $check_swExemption46_with_SWB := if(exists($SWBlist)and $SWBlist != "")then (xmlconv:check_swExemption46_with_SWB($SWMETlist))else()
let $check_swExemption47_with_SWB := if(exists($SWBlist)and $SWBlist != "")then (xmlconv:check_swExemption47_with_SWB($SWMETlist))else()
let $hasErrors :=  exists($checkeuRBDCodeCode_SWMET_RBDSUCA) or
(: exists($checksurfaceWaterBodyTypeCode_SWMET_SWB) or:)
(:                   :)
                   exists($checkIntercalibrationType_SWMET_SWB) or
                   exists($checkIRBDTypologyCoOrdinationReference_SWMET_RBDSUCA) or
                   exists($check_swExemption44Impact_with_SWB) or
                   exists($check_swExemption45Impact_with_SWB) or
                   exists($check_swDisproportionateCost_with_SWB) or
                   exists($check_swTechnicalInfeasibility_with_SWB) or
                   exists($check_swNaturalConditions_with_SWB) or
                   exists($check_swExemption46_with_SWB) or
                   exists($check_swExemption47_with_SWB) 
return
      if( $hasErrors and $format != 'fme_xml') then
        (
            <div>
                {
                                      xmlutil:buildTableErrorReport_CrossXML($checkeuRBDCodeCode_SWMET_RBDSUCA
(: union $checksurfaceWaterBodyTypeCode_SWMET_SWB:)
                                                  union $checkIntercalibrationType_SWMET_SWB
                                                  union $checkIRBDTypologyCoOrdinationReference_SWMET_RBDSUCA
                                                  union $check_swExemption44Impact_with_SWB
                                                  union $check_swExemption45Impact_with_SWB
                                                  union $check_swDisproportionateCost_with_SWB
                                                  union $check_swTechnicalInfeasibility_with_SWB
                                                  union $check_swNaturalConditions_with_SWB
                                                  union $check_swExemption46_with_SWB
                                                  union $check_swExemption47_with_SWB
                                                  )
                }
            </div>
        )
        else if($hasErrors = false() and $format != 'fme_xml')then
        (
            <div>
                {xmlutil:buildDescription("All validation checks were passed successfully.")}
            </div>
         )
         else if($hasErrors and $format = 'fme_xml')then(
          <xmlErrors>
                             {($checkeuRBDCodeCode_SWMET_RBDSUCA
(: union $checksurfaceWaterBodyTypeCode_SWMET_SWB:)
                                                  union $checkIntercalibrationType_SWMET_SWB
                                                  union $checkIRBDTypologyCoOrdinationReference_SWMET_RBDSUCA
                                                  union $check_swExemption44Impact_with_SWB
                                                  union $check_swExemption45Impact_with_SWB
                                                  union $check_swDisproportionateCost_with_SWB
                                                  union $check_swTechnicalInfeasibility_with_SWB
                                                  union $check_swNaturalConditions_with_SWB
                                                  union $check_swExemption46_with_SWB
                                                  union $check_swExemption47_with_SWB
                                                  )}
                  </xmlErrors>
         )
         else()
};
declare function xmlconv:crossRBMPPoM(){
let $checkeuRBDCode_RBMPPoM_RBDSUCA := if(exists($RBDSUCAlist)and $RBDSUCAlist != "") then (xmlconv:checkeuRBDCode_RBMPPoM_RBDSUCA($RBMPPoMlist))else()
let $checkEURBDSubUnitCode_RBMPPoM_RBDSUCA := if(exists($RBDSUCAlist)and $RBDSUCAlist != "") then (xmlconv:checkEURBDSubUnitCode_RBMPPoM_RBDSUCA($RBMPPoMlist))else()
let $checkEUSubUnitCode_RBMPPoM_RBDSUCA := if(exists($RBDSUCAlist)and $RBDSUCAlist != "") then (xmlconv:checkEUSubUnitCode_RBMPPoM_RBDSUCA($RBMPPoMlist))else()
let $checkRiverineLoadCode_RBMPPoM_SWM := if(exists($Monitoringlist)and $RBDSUCAlist != "") then (xmlconv:checkRiverineLoadCode_RBMPPoM_SWM($RBMPPoMlist))else()
let $checkEUSubUnitCode_RBMPPoM_RBDSUCA2 := if(exists($RBDSUCAlist)and $RBDSUCAlist != "") then (xmlconv:checkEUSubUnitCode_RBMPPoM_RBDSUCA2($RBMPPoMlist))else()
let $checkEUSubUnitCode_RBDSUCA_SWB := if(exists($RBDSUCAlist)and $RBDSUCAlist != "") then (xmlconv:checkEUSubUnitCode_RBDSUCA_SWB($RBMPPoMlist))else()
let $hasErrors :=  exists($checkeuRBDCode_RBMPPoM_RBDSUCA) or
                   exists($checkEURBDSubUnitCode_RBMPPoM_RBDSUCA) or
                   exists($checkEUSubUnitCode_RBMPPoM_RBDSUCA) or
                   exists($checkRiverineLoadCode_RBMPPoM_SWM) or
                   exists($checkEUSubUnitCode_RBMPPoM_RBDSUCA2) or
                   exists($checkEUSubUnitCode_RBDSUCA_SWB)
return
      if( $hasErrors and $format != 'fme_xml') then
        (
            <div>
                {
                                     xmlutil:buildTableErrorReport_CrossXML($checkeuRBDCode_RBMPPoM_RBDSUCA
                                                  union $checkEURBDSubUnitCode_RBMPPoM_RBDSUCA
                                                  union $checkEUSubUnitCode_RBMPPoM_RBDSUCA
                                                  union $checkRiverineLoadCode_RBMPPoM_SWM
                                                  union $checkEUSubUnitCode_RBMPPoM_RBDSUCA2
                                                  union $checkEUSubUnitCode_RBDSUCA_SWB)
                }
            </div>
        )
        else if($hasErrors = false() and $format != 'fme_xml')then
        (
            <div>
                {xmlutil:buildDescription("All validation checks were passed successfully.")}
            </div>
         )
         else if($hasErrors and $format = 'fme_xml')then(
          <xmlErrors>
                             {($checkeuRBDCode_RBMPPoM_RBDSUCA
                                                  union $checkEURBDSubUnitCode_RBMPPoM_RBDSUCA
                                                  union $checkEUSubUnitCode_RBMPPoM_RBDSUCA
                                                  union $checkRiverineLoadCode_RBMPPoM_SWM
                                                  union $checkEUSubUnitCode_RBMPPoM_RBDSUCA2
                                                  union $checkEUSubUnitCode_RBDSUCA_SWB
                                                  )}
                  </xmlErrors>
         )
         else()
};
declare function xmlconv:crossMonitoring(){ 
let $checkeuRBDCode_Monitoring_RBDSUCA := if(exists($SWBlist)) then (xmlconv:checkeuRBDCode_Monitoring_RBDSUCA($Monitoringlist))else()
let $checkeuSurfaceWaterBodyCode_Monitoring_RBDSUCA := if(exists($SWBlist)) then (xmlconv:checkeuSurfaceWaterBodyCode_Monitoring_RBDSUCA($Monitoringlist))else()
let $hasErrors :=  exists($checkeuRBDCode_Monitoring_RBDSUCA) or
                   exists($checkeuSurfaceWaterBodyCode_Monitoring_RBDSUCA) 
return
      if( $hasErrors and $format != 'fme_xml') then
        (
            <div>
                {
                                      xmlutil:buildTableErrorReport_CrossXML($checkeuRBDCode_Monitoring_RBDSUCA union
                                                                             $checkeuSurfaceWaterBodyCode_Monitoring_RBDSUCA)
                }
            </div>
        )
        else if($hasErrors = false() and $format != 'fme_xml')then
        (
            <div>
                {xmlutil:buildDescription("All validation checks were passed successfully.")}
            </div>
         )
         else if($hasErrors and $format = 'fme_xml')then(
          <xmlErrors>
                             {($checkeuRBDCode_Monitoring_RBDSUCA union          
                               $checkeuSurfaceWaterBodyCode_Monitoring_RBDSUCA)}
                  </xmlErrors>
         )
         else()
};
declare function xmlconv:crossRBDSUCA(){
let $checkEUSubUnitCode_RBDSUCA_RBMPPoM := if(exists($RBMPPoMlist)) then ()else()(:xmlconv:checkEUSubUnitCode_RBDSUCA_RBMPPoM($RBDSUCAlist):)
let $hasErrors :=  exists($checkEUSubUnitCode_RBDSUCA_RBMPPoM) 
return
    if( $hasErrors and $format != 'fme_xml') then
        (
            <div>
                {
                                    xmlutil:buildTableErrorReport_CrossXML($checkEUSubUnitCode_RBDSUCA_RBMPPoM)
                }
            </div>
        )
        else if($hasErrors = false() and $format != 'fme_xml')then
        (
            <div>
                {xmlutil:buildDescription("All validation checks were passed successfully.")}
            </div>
         )
         else if($hasErrors and $format = 'fme_xml')then(
          <xmlErrors>
                             {((:$checkEUSubUnitCode_RBDSUCA_RBMPPoM:)
                              )}
                  </xmlErrors>
         )
         else()
};
declare function xmlconv:envelope_all_XML(){
let $checkxml := xmlconv:getAQFiles()
let $filetype := xmlconv:control_fileType()
let $hasErrors :=  exists($checkxml) or 
                   exists($filetype)
return
        if( $hasErrors ) then
        (
            <div>
                {
                    xmlutil:buildTableErrorReport_CrossXML($checkxml union
                                                            $filetype) 
                }
            </div>
        )
        else
        (
            <div>
                {xmlutil:buildDescription("All XML in Envelope")}
            </div>
        )
};
(:==================================================================:)
(: *******  RUN VALIDATIONS AND GENERATE OUTPUT ******************* :)
(:==================================================================:)
(:===================================================================:)
(: Variable given as an external parameter by the QA service         :)
(:===================================================================:)
declare function xmlconv:proceed() {
let $resultXMLFilesBlocker := xmlconv:envelope_all_XML()
let $resultXML :=  ($resultXMLFilesBlocker)
let $resultSWB :=
    if (exists($SWBlist)) then
           (xmlconv:crossSWB())
    else
           (xmlutil:buildDescription("No 'SWB' xml files have been found"))
let $resultGWB :=
    if (exists($GWBlist)) then
           (xmlconv:crossGWB())
    else
           (xmlutil:buildDescription("No 'GWB' xml files have been found"))
let $resultGWMET :=
    if (exists($GWMETlist)) then
           (xmlconv:crossGWMET())
    else
           (xmlutil:buildDescription("No 'GWMET' xml files have been found"))
let $resultSWMET :=
    if (exists($SWMETlist)) then
           (xmlconv:crossSWMET())
    else
           (xmlutil:buildDescription("No 'SWMET' xml files have been found"))
let $resultRBMPPoM :=
    if (exists($RBMPPoMlist)) then
           (xmlconv:crossRBMPPoM())
    else
           (xmlutil:buildDescription("No 'RBMPPoM' xml files have been found"))
let $resultMonitoring :=
    if (exists($Monitoringlist)) then
           (xmlconv:crossMonitoring())
    else
           (xmlutil:buildDescription("No 'Monitoring' xml files have been found"))
let $resultRBDSUCA :=
    if (exists($RBDSUCAlist)) then
        (xmlconv:crossRBDSUCA())
    else
        (xmlutil:buildDescription("No 'RBDSUCA' xml files have been found"))
let $result := ($resultSWB, $resultGWB, $resultGWMET, $resultSWMET, $resultRBMPPoM, $resultMonitoring, $resultRBDSUCA, $resultXML)
let $list   := ($SWBlist, $GWBlist, $GWMETlist, $SWMETlist, $RBMPPoMlist, $Monitoringlist, $RBDSUCAlist)
    let $feedbackStatus :=
    if (empty($list)) then
        <span id="feedbackStatus" class="INFO" style="display:none">No relevant xml files have been found</span>
    else
        (xmlutil:feedbackStatus($result))
return
 if( $format != 'fme_xml') then
<div class="feedbacktext">
    {$feedbackStatus}
    <h1>Cross-schema validation</h1>
    <h2>ENVELOPE</h2>
    {$resultXML}
    <h2>SWB</h2>
    { $resultSWB }
    <h2>GWB</h2>
    { $resultGWB }
    <h2>GWMET</h2>
    { $resultGWMET }
    <h2>SWMET</h2>
    { $resultSWMET }
    <h2>RBMPPoM</h2>
    { $resultRBMPPoM }
    <h2>Monitoring</h2>
    { $resultMonitoring }
    <!--<h2>RBDSUCA</h2>
    { $resultRBDSUCA }-->
</div>
 else if($format = 'fme_xml')then(
    <Errors>
    {$resultSWB}
    {$resultGWB}
    {$resultGWMET}
    {$resultSWMET}
    {$resultRBMPPoM}
    {$resultMonitoring}
    <!--{$resultRBDSUCA}-->
    {$resultXML}
    </Errors>
    )
 else()
};
(: Main function:)
if ($test = 1) then(
<h2>RBDSCUCA: {doc($RBDSUCAlist)}</h2>
)
else  xmlconv:proceed()