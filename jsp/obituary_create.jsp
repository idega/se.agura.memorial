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
			
					<wf:wfblock title="Obituary create|edit page">
						<f:facet name="wf_block_toolbar">
							<wf:toolbar id="toolbar">
							   	
							   	<h:commandLink id="searchButton" 
									value="#{localizedStrings['se.agura.memorial']['search']}" 
									action="#{graveyardSearchBean.search}" 
									immediate="true" 
									styleClass="page_preview_link"/>
								
								<h:outputLink value="" 
									styleClass="page_preview_link">
								  <f:verbatim>Back</f:verbatim>
								</h:outputLink> 

							</wf:toolbar>
						</f:facet>

             <wf:container styleClass="obituary_part">

                  <h:outputText styleClass="headline" value="Detailed  grave information#{localizedStrings['se.agura.memorial']['detailed_grave_information']}" />
                  
				 <memorial:GraveDetails id="theobject"></memorial:GraveDetails>
                 

				 <f:verbatim>
                    <h:outputText styleClass="headline" value="#{localizedStrings['se.agura.memorial']['obituary']}" style="font-weight: bold; " />
                    <br />
                    <br />
                  </f:verbatim>

				 <memorial:ObituaryItemViewer id="theobject"></memorial:ObituaryItemViewer>

                  <f:verbatim escape="false">
                     <h:inputTextarea id="textArea"  rows="4" cols="40"  value="Write text for obituary here..."/>
                     <br />
                     <br />
                  </f:verbatim>

                  <f:verbatim>				  
                   <h:outputText  styleClass="Text" value="path1" style="font-weight: bold; " />                  
                    <br />
                    <br />
                  </f:verbatim>

                  <f:verbatim>				  
                   <h:commandButton value="Upload picture of person" action="" id="cbUploadGrave" />		      
                    <br />
                    <br />
                  </f:verbatim>


                  <f:verbatim>
                    <h:outputText   value="path2" style="font-weight: bold; " />                  
                    <br />
                    <br />
                  </f:verbatim>

                  <f:verbatim>
                     <h:commandButton value="Upload picture of grave" action="" id="cbUploadPerson" />		      
                     <br />
                     <br />
                  </f:verbatim>
	        

			      <h:commandButton value="Preview" action="showObituaryPreviewAction" id="cbPreview" />		      
		                   
		                
		                
		           
                  
             </wf:container>
				
			</wf:wfblock >

	    </h:form>    
	</ws:page>
</f:view>
</jsp:root>