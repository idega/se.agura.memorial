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


                 <h:outputText styleClass="Text" value="#{obituaryBackingBean.databaseId}" />                 

                 <h:outputText styleClass="Text" value=" | " />

                 <h:outputText styleClass="Text" value="#{obituaryBackingBean.graveId}" />
							
                 <h:outputText styleClass="Text" value=" | " />

                 <h:outputText styleClass="Text" value="#{obituaryBackingBean.obituaryText}" />
    		

				 <f:verbatim>
                    <br />
                    <br />
                  </f:verbatim>

                 <h:outputText styleClass="headline" value="Detailed Grave Information" />

				 <f:verbatim>
                    <br />
                    <br />
                  </f:verbatim>

				 <memorial:GraveDetails id="theobject"></memorial:GraveDetails>

				 <f:verbatim>
                    <br />
                    <br />
                  </f:verbatim>
                  

				 <f:verbatim>
                    <h:outputText styleClass="headline" value="#{localizedStrings['se.agura.memorial']['obituary']}" style="font-weight: bold; " />
                    <br />
                    <br />
                  </f:verbatim>


                  <f:verbatim escape="false">
                     <h:inputTextarea id="textArea"  rows="4" cols="40"  value="#{obituaryBackingBean.obituaryText}"/>
                     <br />
                     <br />
                  </f:verbatim>

                  <f:verbatim>				  
                   <h:outputText  styleClass="Text" value="#{obituaryBackingBean.databaseId}" style="font-weight: bold; " />                  
                    <br />
                    <br />
                  </f:verbatim>

                  <f:verbatim>				  
         
         
                 <x:inputFileUpload id="personImagePath"
                      value="#{obituaryBackingBean.personImagePath}"
                      storage="file"
                  required="true"/>
            
                   <h:commandButton value="Upload picture of person" action="#{obituaryBackingBean.processMyFile}" id="cbUploadGrave" >		      
                   </h:commandButton>
                    <br />
                    <br />
                  </f:verbatim>


                  <f:verbatim>
                    <h:outputText   value="path2" style="font-weight: bold; " />                  
                    <br />
                    <br />
                  </f:verbatim>

                 <x:inputFileUpload id="graveImagePath"
                      value="#{obituaryBackingBean.graveImagePath}"
                      storage="file"
                  required="true"/>

                  <f:verbatim>
                     <h:commandButton value="Upload picture of grave" action="" id="cbUploadPerson" />		      
                     <br />
                     <br />
                  </f:verbatim>
	        


                   <h:commandButton value="#{localizedStrings['se.agura.memorial']['new person']}" action="showNewPersonAction" id="cbNewPerson" >
						<f:param name="graveId" value="#{obituaryCreateBackingBean.graveId}" />
						<f:param name="databaseId" value="#{obituaryCreateBackingBean.databaseId}" />														                        
					</h:commandButton>

                   <h:commandButton value="#{localizedStrings['se.agura.memorial']['preview']}" action="#{obituaryBackingBean.preview" id="cbPreview" >
						<f:param name="graveId" value="#{obituaryCreateBackingBean.graveId}" />
						<f:param name="databaseId" value="#{obituaryCreateBackingBean.databaseId}" />														                        
					</h:commandButton>

                   <h:commandButton value="#{localizedStrings['se.agura.memorial']['cancel']}" action="obituaryCreateCancelAction" id="cbCancel" >
					</h:commandButton>
                  
             </wf:container>
				
			</wf:wfblock >

	    </h:form>    
	</ws:page>
</f:view>
</jsp:root>