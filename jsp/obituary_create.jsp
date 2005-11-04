<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:ws="http://xmlns.idega.com/com.idega.workspace"
        xmlns:wf="http://xmlns.idega.com/com.idega.webface"
        xmlns:article="http://xmlns.idega.com/com.idega.block.article" 
        xmlns:builder="http://xmlns.idega.com/com.idega.builder"
        xmlns:x="http://myfaces.apache.org/extensions"       
        xmlns:memorial="http://xmlns.idega.com/se.agura.memorial"  
        
version="1.2">
<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
<f:view>
	<ws:page id="obituary_create">
		<h:form id="obituary_createform">    
			
 			 <wf:wfblock title="Obituary Edit page">
             <wf:container styleClass="obituary_part">

                  <h:outputText styleClass="headline" value="Detailed Grave Information" />

  				   <memorial:GraveDetails id="theobject"></memorial:GraveDetails>

                   <h:outputText styleClass="headline" value="#{localizedStrings['se.agura.memorial']['obituary']}" style="font-weight: bold; " />

							<f:verbatim escape="false">
								<br />
							</f:verbatim>
                   <h:inputTextarea id="textArea"  rows="4" cols="40"  value="#{obituaryCreateBackingBean.obituaryText}" />

							<f:verbatim escape="false">
								<br />
							</f:verbatim>

                   <h:outputText  styleClass="Text" value="Picture of person:" />                  

							<f:verbatim escape="false">
								<br />
							</f:verbatim>

                   <x:inputFileUpload id="personImagePath"  value="#{obituaryCreateBackingBean.personImagePath}" storage="file" required="true"/>

							<f:verbatim escape="false">
								<br />
							</f:verbatim>

                   <h:outputText  styleClass="Text" value="Picture of grave:" />                  

							<f:verbatim escape="false">
								<br />
							</f:verbatim>

                   <x:inputFileUpload id="graveImagePath" value="#{obituaryCreateBackingBean.graveImagePath}" storage="file" required="true"/>
                   
							<f:verbatim escape="false">
								<br />
							</f:verbatim>

							
                   <h:commandButton value="#{localizedStrings['se.agura.memorial']['new person']}" action="showNewPersonAction" id="cbNewPerson" />

                   <h:commandButton value="#{localizedStrings['se.agura.memorial']['preview']}" action="#{obituaryCreateBackingBean.preview}" id="cbPreview" />

                   <h:commandButton value="#{localizedStrings['se.agura.memorial']['cancel']}" action="obituaryCreateCancelAction" id="cbCancel" />
                  
                  
             </wf:container>
				
			</wf:wfblock >

	    </h:form>    
	</ws:page>
</f:view>
</jsp:root>