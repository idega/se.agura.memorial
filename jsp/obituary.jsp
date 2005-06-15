<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:ws="http://xmlns.idega.com/com.idega.workspace"
        xmlns:wf="http://xmlns.idega.com/com.idega.webface"
        xmlns:article="http://xmlns.idega.com/com.idega.block.article" 
        xmlns:builder="http://xmlns.idega.com/com.idega.builder"
        xmlns:x="http://myfaces.apache.org/extensions"        
version="1.2">
<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
<f:view>
	<ws:page id="obituary">
		<h:form id="obituaryform1">    
			
					<wf:wfblock title="Obituary page">
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

							   	<h:commandLink id="test" 
									value="TEST " 
									action="#{showObituaryCreateAction}" 
									immediate="true"  
									styleClass="page_preview_link"/>

							   	<h:commandLink id="test2" 
									value="TEST2 "  
									action="showObituaryCreateAction" 
									immediate="true"  
									styleClass="page_preview_link"/>
									
								
								
							</wf:toolbar>
						</f:facet>

		    <h:panelGrid columns="2" border="0" cellspacing="5">
		        <h:panelGroup>   
		            
		            <h:panelGrid columns="1" border="0" cellspacing="5" >   
		                <h:panelGroup>
		                	
		                    <h:outputText value="Detailed  grave information#{localizedStrings['se.agura.memorial']['detailed_grave_information']}" style="font-weight: bold; "/>
		                    
		                </h:panelGroup>
		            </h:panelGrid>   
		           
		            <h:panelGrid columns="2" border="0" cellspacing="5"> 
		                <h:panelGroup>               
		                    <h:outputText value="#{localizedStrings['se.agura.memorial']['first_name']}" />
		                </h:panelGroup>
		                <h:panelGroup>               
		                    <h:outputText value="#{obituaryInformationDisplayBackingBean.grave.firstName}" >
								<f:converter converterId="se.agura.memorial.search.presentation.EmptyStringConverter" /> 
							</h:outputText>				                    
		                </h:panelGroup>
		                
		                <h:panelGroup>               
		                    <h:outputText value="#{localizedStrings['se.agura.memorial']['surname']}" />
		                </h:panelGroup>
		                <h:panelGroup>               
		                    <h:outputText value="#{obituaryInformationDisplayBackingBean.grave.lastName}" >
								<f:converter converterId="se.agura.memorial.search.presentation.EmptyStringConverter" /> 
							</h:outputText>				                    
		                </h:panelGroup>                
		                
		                <h:panelGroup>               
		                    <h:outputText value="#{localizedStrings['se.agura.memorial']['date_of_birth']}" />
		                </h:panelGroup>
		                <h:panelGroup>               
		                    <h:outputText value="#{obituaryInformationDisplayBackingBean.grave.dateOfBirth}" >
		                    	<f:converter converterId="se.agura.memorial.search.presentation.DateConverter" /> 
		                    </h:outputText>
		                </h:panelGroup>   
		                
		                <h:panelGroup>               
		                    <h:outputText value="#{localizedStrings['se.agura.memorial']['date_of_decease']}" />		                    
		                </h:panelGroup>
		                <h:panelGroup>               
		                    <h:outputText value="#{obituaryInformationDisplayBackingBean.grave.dateOfDeath}" >
		                    	<f:converter converterId="se.agura.memorial.search.presentation.DateConverter" /> 
		                    </h:outputText>		                    
		                </h:panelGroup> 
		                
		                <h:panelGroup>               
		                    <h:outputText value="#{localizedStrings['se.agura.memorial']['hometown']}: " />
                            <h:outputText value="#{obituaryInformationDisplayBackingBean.grave.hometown}" >
							     <f:converter converterId="se.agura.memorial.search.presentation.EmptyStringConverter" /> 
							 </h:outputText>		                            
                            
		                </h:panelGroup>
		                <h:panelGroup>                  
		                    <h:panelGrid columns="3" border="0" cellspacing="5">             
		                        <h:outputText value="#{localizedStrings['se.agura.memorial']['county']}: [todo]Skane" /> 
		                        <h:outputText value="#{localizedStrings['se.agura.memorial']['commune']}: [todo]Malmoe" /> 
		                        <h:outputText value="#{localizedStrings['se.agura.memorial']['parish']}: [todo]Limhamn" />
		                    </h:panelGrid>
		                </h:panelGroup>
		                
		                
		                <h:panelGroup >               
		                    <h:outputText value="#{localizedStrings['se.agura.memorial']['grave_information']}" />
		                </h:panelGroup>
		                <h:panelGroup>               
		                    <h:panelGrid columns="2" border="0" cellspacing="5" > 
		                         <h:panelGroup>
		                             <h:outputText value="#{localizedStrings['se.agura.memorial']['grave_no']}:" />
		                         </h:panelGroup>
		                         <h:panelGroup>
		                             <h:outputText value="#{obituaryInformationDisplayBackingBean.grave.graveInfo.graveNumber}" >
										<f:converter converterId="se.agura.memorial.search.presentation.EmptyStringConverter" /> 
									</h:outputText>			                             
		                         </h:panelGroup>  
		                         
		                         <h:panelGroup>
		                             <h:outputText value="#{localizedStrings['se.agura.memorial']['block']}:" />
		                         </h:panelGroup>
		                         <h:panelGroup>
		                             <h:outputText value="#{obituaryInformationDisplayBackingBean.grave.graveInfo.block}" >
										<f:converter converterId="se.agura.memorial.search.presentation.EmptyStringConverter" /> 
									</h:outputText>			                             
		                         </h:panelGroup>                         
		                         
		                         <h:panelGroup>
		                             <h:outputText value="#{localizedStrings['se.agura.memorial']['department']}" />
		                         </h:panelGroup>
		                         <h:panelGroup>
		                             <h:outputText value="#{obituaryInformationDisplayBackingBean.grave.graveInfo.department}" >
										<f:converter converterId="se.agura.memorial.search.presentation.EmptyStringConverter" /> 
									</h:outputText>			                             
		                         </h:panelGroup>                          
		                         
		                         <h:panelGroup>
		                             <h:outputText value="#{localizedStrings['se.agura.memorial']['graveyard']}" />
		                         </h:panelGroup>
		                         <h:panelGroup>
		                             <h:outputText value="#{obituaryInformationDisplayBackingBean.grave.graveInfo.cemetery}" >
										<f:converter converterId="se.agura.memorial.search.presentation.EmptyStringConverter" /> 
									</h:outputText>			                             
		                         </h:panelGroup>                          
		                                                                           
		                         <h:panelGroup>
		                             <h:outputText value="#{localizedStrings['se.agura.memorial']['other_burial_place']}" />
		                         </h:panelGroup>
		                         <h:panelGroup>
		                             <h:outputText value="[TODO]" >
										
									</h:outputText>			                             
		                         </h:panelGroup>  
		                                                  
		                    </h:panelGrid>
		                </h:panelGroup>                                
		                
		            </h:panelGrid> 
		            
		            
		            
		            <h:panelGrid columns="1" border="0" cellspacing="5">   
		                <h:panelGroup>
		                    <f:verbatim><br /><br /></f:verbatim>  
		                   
		                    <h:outputText value="#{localizedStrings['se.agura.memorial']['obituary']}"  style="font-weight: bold; "/>
		                   
		                </h:panelGroup>
		            </h:panelGrid>
		            
		            <h:panelGrid columns="1" border="0" cellspacing="5">   
		                <h:panelGroup>                    
		                    <f:verbatim escape="false">
		                        The date was correct and the words were spelt right,<br />
		                        And it really gave me one hell of a fright.<br />
		                        My name and age in a place none too merry,<br />
		                        No other spot than today's obituary!<br />
		                        <br />
		                        What a crazy mistake, I thought to myself,<br />
		                        As I picked up the phonebook from off the shelf,<br />
		                        To see my own death in the newspaper post;<br />
		                        It was somewhat bizarre, like being a ghost!
		                    </f:verbatim>
		                </h:panelGroup>
		            </h:panelGrid>
		            
		        </h:panelGroup> 
		        
		        
		        <h:panelGroup> 
		            <h:panelGrid columns="1" border="0" cellspacing="5">
		                <h:panelGroup>
		                	
		                    <h:outputText value="#{localizedStrings['se.agura.memorial']['picture_of_person']}"  style="font-weight: bold; " />
		                    
		                    <f:verbatim><br /><br /></f:verbatim> 
		                    <h:graphicImage 
		                       alt=""
		                       url="#{bundles['se.agura.memorial'].resourcesPath}/images/jimi.jpg" />                    
		                </h:panelGroup>
		            </h:panelGrid> 
		            
		             
		            
		            <h:panelGrid columns="1" border="0" cellspacing="5">   
		                <h:panelGroup>
		                    <f:verbatim><br /><br /></f:verbatim> 
		                    
		                    <h:outputText value="#{localizedStrings['se.agura.memorial']['picture_of_grave']}"  style="font-weight: bold; "/>
		                    
		                    <f:verbatim><br /><br /></f:verbatim> 
		                    <h:graphicImage 
		                       alt=""
		                       url="#{bundles['se.agura.memorial'].resourcesPath}/images/jimi_headstone.jpg" />  
		                </h:panelGroup>
		            </h:panelGrid>    
		        </h:panelGroup>  
		        
		    </h:panelGrid>
				
			</wf:wfblock >

	    </h:form>    
	</ws:page>
</f:view>
</jsp:root>