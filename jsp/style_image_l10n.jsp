<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:ws="http://xmlns.idega.com/com.idega.workspace"
        xmlns:article="http://xmlns.idega.com/com.idega.block.article" 
        xmlns:builder="http://xmlns.idega.com/com.idega.builder"
        xmlns:x="http://myfaces.apache.org/extensions"        
version="1.2">
<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
<f:view >

<!--  locale choose doesnt work this way <f:view locale="sv_SE" > --> 

	<ws:page id="aaobituary11asfgh">

	<h:form id="aanewPersonForm">

	    <f:verbatim>  
	    <h1>test-5: style, images, l10n</h1>
	    
	    </f:verbatim>
	         <h:commandLink  action="login" immediate="true">
	    	     <h:outputText value="login" />				
	    	 </h:commandLink>
	    <f:verbatim>	 
	    
	    </f:verbatim>
	    
	    <f:verbatim><br /><br /></f:verbatim>   
	    
	    <f:verbatim>
	        <div class="MySpecialDiv">
	            div with class MySpecialDiv                    <br /> 
	            style is in file images/style_image_l10n.css   <br /> 
	            border: 2px dashed red;
	        </div>
	    </f:verbatim>
	    
	    <f:verbatim><br /><br /></f:verbatim>   
	    
	    <h:graphicImage url="/idegaweb/bundles/se.agura.memorial.bundle/resources/images/style_image_l10n.png"
	        alt="Image test"
	        title="Image test" />	        
	        
	    <f:verbatim><br /><br />Image that uses #{bundles['identifier'].resourcesPath}/your_path_in_resources_folder</f:verbatim>   
	    
	    <h:graphicImage url="#{bundles['se.agura.memorial'].resourcesPath}/images/style_image_l10n.png"
	        alt="Image test 2"
	        title="Image test 2" />	       
	       
	       
	    <f:verbatim><br /><br /></f:verbatim>    
	    
	    <h:commandLink action="navigationTest" immediate="true">
	        <h:outputText value="Navigation test" />				
	    </h:commandLink> 
	    
	    <f:verbatim><br /><br /></f:verbatim>  
	    
	    <!-- 
	    <f:loadBundle basename="dainis" var="l10nBundle"/> 
	    this is not needed
	    --> 
	    
	    <h:outputText value="l10n test: " /><h:outputText value="#{localizedStrings['se.agura.memorial']['message.one']}" />
	    
	    <f:verbatim><br /></f:verbatim>     
	     
	    <h:outputText value="l10n test: " /> 
	    <h:outputFormat value="#{localizedStrings['se.agura.memorial']['message_two']}">  
	        <f:param value="Happy Tree friends"/>
	    </h:outputFormat> 
	    
	    <f:verbatim><br /></f:verbatim>   
	    
	    <h:outputText value="l10n test: " /><h:outputText value="#{localizedStrings['se.agura.memorial']['message_three']}" />

    </h:form>
  
	</ws:page>
</f:view>
</jsp:root>