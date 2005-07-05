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
	<ws:page id="obituary_preview">
		<h:form id="obituary_previewform">    
			
					<wf:wfblock title="Obituary preview page">
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
                 
                 <memorial:ObituaryDetails id="theobject"></memorial:ObituaryDetails>
				 <f:verbatim>
                    <br />
                    <br />
                  </f:verbatim>
                  <h:outputText styleClass="headline" value="#{localizedStrings['se.agura.memorial']['obituary']}" style="font-weight: bold; " />


                  <f:verbatim escape="false">
<p>
                    
                    The date was correct and the words were spelt right,
                    <br />
                    And it really gave me one hell of a fright.
                    <br />
                    My name and age in a place none too merry,
                    <br />
                    No other spot than today's obituary!
</p>
<p>
                    What a crazy mistake, I thought to myself,
                    <br />
                    As I picked up the phonebook from off the shelf,
                    <br />
                    To see my own death in the newspaper post;
                    <br />
                    It was somewhat bizarre, like being a ghost!
</p>
                  </f:verbatim>

                  <h:outputText  styleClass="headline" value="#{localizedStrings['se.agura.memorial']['picture_of_person']}" style="font-weight: bold; " />
                  <f:verbatim>
                    <br />
                    <br />
                  </f:verbatim>
                  <h:graphicImage alt="" url="/#{bundles['se.agura.memorial'].resourcesPath}/images/jimi.jpg" />

                  <f:verbatim>
                    <br />
                    <br />
                  </f:verbatim>
                  <h:outputText styleClass="headline" value="#{localizedStrings['se.agura.memorial']['picture_of_grave']}" style="font-weight: bold; " />
                  <f:verbatim>
                    <br />
                    <br />
                  </f:verbatim>
                  <h:graphicImage alt="" url="/#{bundles['se.agura.memorial'].resourcesPath}/images/jimi_headstone.jpg" />
             </wf:container>
				
			</wf:wfblock >

	    </h:form>    
	</ws:page>
</f:view>
</jsp:root>