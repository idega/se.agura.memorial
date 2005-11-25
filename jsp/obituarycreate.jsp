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
<jsp:directive.page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
<f:view>
	<ws:page id="obituary_create">
			<h:form id="obituary_createform"  enctype="multipart/form-data">     
 			<wf:wfblock title="Obituary Edit page">
			<h:panelGrid columns="1" border="0" cellspacing="5" footerClass="SearchFormFooterClass" >
					<h:panelGroup>			
			                 <h:outputText styleClass="headline" value="#{obituaryCreateBackingBean.personFullName}" />
							 <f:verbatim>
            			        <br />
			                    <br />
			                  </f:verbatim>
		            </h:panelGroup>
            
					<h:panelGroup>			
							<h:panelGrid columns="2" border="0" cellspacing="5" footerClass="SearchFormFooterClass"  width = "1000">
				  					 <h:panelGroup>			
			 							         <h:panelGrid columns="1" border="0" cellspacing="5" footerClass="SearchFormFooterClass" width = "800" >
									  					 <h:panelGroup>			
												                <h:outputText styleClass="headline" value="Obituary" />
												 			    <f:verbatim>
										            	            <br />
											    	                <br />
												                </f:verbatim>
	 												     </h:panelGroup>

									  					 <h:panelGroup>			
										                 		 <h:inputTextarea id="textArea"  rows="8" cols="150"  value="#{obituaryCreateBackingBean.obituaryText}" />
						 									 	<f:verbatim>
												                    <br />
												                    <br />
												                </f:verbatim>
													     </h:panelGroup>
								                
									  					 <h:panelGroup>			
											                   <h:outputText styleClass="headline" value="Detailed Grave Information" />
												 			   <f:verbatim>
												                    <br />
												                    <br />
											                   </f:verbatim>
													     </h:panelGroup>

									  					 <h:panelGroup>			
											  				   <memorial:GraveDetails id="theobject"></memorial:GraveDetails>
									  						   <f:verbatim>
												                    <br />
												                    <br />
											                   </f:verbatim>
													    </h:panelGroup>
										 		 </h:panelGrid >								                
									</h:panelGroup>	
	
			  					 	<h:panelGroup>			
 							                    <h:panelGrid columns="1" border="0" cellspacing="5" footerClass="SearchFormFooterClass" width = "200">
											         <h:panelGroup>	
											                    <h:graphicImage value="#{obituaryCreateBackingBean.graveImageResourcePath}" width = "200"/>
 									  						    <f:verbatim>
												                    <br />

												                    <br />
 											                    </f:verbatim>											                    
 										                      <x:inputFileUpload id="personImagePath"  value="#{obituaryCreateBackingBean.graveImage}" storage="file" />
			 				                         </h:panelGroup>							                   

											         <h:panelGroup>	
												                <h:outputText styleClass="text" value="Picture of grave" />
                  
												  			    <f:verbatim>
												                    <br />
												                    <br />
												                </f:verbatim>
			 				                         </h:panelGroup>							                   

											         <h:panelGroup>	
											                   <h:graphicImage value="#{obituaryCreateBackingBean.personImageResourcePath}" width = "200" />
   									  						    <f:verbatim>
												                    <br />
												                    <br />
 											                    </f:verbatim>
                                                                <x:inputFileUpload id="graveImagePath" value="#{obituaryCreateBackingBean.personImage}" storage="file" /> 											                    
			 				                         </h:panelGroup>							                   

											         <h:panelGroup>	
											                   <h:outputText styleClass="text" value="Picture of person" />
			
												 			   <f:verbatim>
												                    <br />
											    	                <br />
											                   </f:verbatim>

			 				                         </h:panelGroup>							                   
		 									  </h:panelGrid >
			                         </h:panelGroup>
				 		 </h:panelGrid >
                 </h:panelGroup>

         
         <h:panelGroup>	
                 <h:panelGrid columns="3" border="0" cellspacing="5" footerClass="SearchFormFooterClass" >
					     <h:panelGroup>
        				          <h:commandButton value="#{localizedStrings['se.agura.memorial']['new person']}" action="showNewPersonAction" id="cbNewPerson" />
                         </h:panelGroup>

					     <h:panelGroup>
				                  <h:commandButton value="#{localizedStrings['se.agura.memorial']['preview']}" action="#{obituaryCreateBackingBean.preview}" id="cbPreview" />
                         </h:panelGroup>

					     <h:panelGroup>
				                  <h:commandButton value="#{localizedStrings['se.agura.memorial']['cancel']}" action="obituaryCreateCancelAction" id="cbCancel" />
                         </h:panelGroup>
				  </h:panelGrid >
         </h:panelGroup>

		</h:panelGrid >

	
			</wf:wfblock >

	    </h:form>    

	</ws:page>
</f:view>
</jsp:root>