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
  	<ws:page id="obituary" >
			<h:form id="obituaryform">    
 			<wf:wfblock title="Obituary page">
			<h:panelGrid columns="1" border="0" cellspacing="5" footerClass="SearchFormFooterClass" >
					<h:panelGroup>			
			                 <h:outputText styleClass="headline" value="#{obituaryBackingBean.personFullName}" />
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
										                 		<h:outputText styleClass="Text" value="#{obituaryBackingBean.obituaryText}" />
						 									 	<f:verbatim>
												                    <br />
												                    <br />
												                </f:verbatim>
													     </h:panelGroup>
								                
									  					 <h:panelGroup>			
											                   <h:outputText styleClass="headline" value="Detailed grave information" />
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
											                    <h:graphicImage value="#{obituaryBackingBean.graveImagePath}" width = "200" rendered="#{obituaryBackingBean.graveImageRendered}"/>
			 				                         </h:panelGroup>							                   

											         <h:panelGroup>	
												                <h:outputText styleClass="text" value="Picture of grave" rendered="#{obituaryBackingBean.graveImageRendered}" />
                  
												  			    <f:verbatim>
												                    <br />
												                    <br />
												                </f:verbatim>
			 				                         </h:panelGroup>							                   

											         <h:panelGroup>	
											                   <h:graphicImage value="#{obituaryBackingBean.personImagePath}" width = "200" rendered="#{obituaryBackingBean.personImageRendered}"/>
			 				                         </h:panelGroup>							                   

											         <h:panelGroup>	
											                   <h:outputText styleClass="text" value="Picture of person" rendered="#{obituaryBackingBean.personImageRendered}" />
			
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
        				           <h:commandButton value="#{localizedStrings['se.agura.memorial']['print']}" action="showObituaryPrint" id="cbPrint"  />
                         </h:panelGroup>

					     <h:panelGroup >
				                   <h:commandButton value=" #{localizedStrings['se.agura.memorial']['edit']}" action="showObituaryCreateAction" immediate="true" id="cbEdit" />
                         </h:panelGroup>

					     <h:panelGroup>
				                   <h:commandButton value="save as pdf" action="showObituaryPDF" id="cbPDF"  />
                         </h:panelGroup>
				  </h:panelGrid >
         </h:panelGroup>

		</h:panelGrid >

	
			</wf:wfblock >

	    </h:form>    
	</ws:page>
</f:view>
</jsp:root>