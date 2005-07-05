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
	<ws:page id="new_person">

	<h:form id="new_personform">

					<wf:wfblock title="Create Grave">
						<f:facet name="wf_block_toolbar">
							<wf:toolbar id="toolbar">
								<wf:toolbarbutton id="button1" displayText="#{localizedStrings['com.idega.builder']['create_simple_template.Create']}/#{localizedStrings['com.idega.builder']['create_simple_template.Edit']}" styleClass="page_create_edit_link"/>
							   	<h:commandLink id="cbSave" value="#{localizedStrings['com.idega.builder']['create_simple_template.Save']}" action="#{newPersonBean.save}" styleClass="page_preview_link"/>
							</wf:toolbar>
						</f:facet>

    <h:panelGrid columns="2" cellspacing="7" border="0"> 
        <h:outputLabel for="firstName">
            <h:outputText value="First name"/>
        </h:outputLabel>    	                                        
        <h:inputText id="firstName" value="#{newPersonBean.firstName}" maxlength="25" >
            <f:validateLength maximum="25" minimum="0" />
        </h:inputText>	
        
        <h:outputLabel for="lastName">
            <h:outputText value="Last name"/>
        </h:outputLabel>    	                                        
        <h:inputText id="lastName" value="#{newPersonBean.lastName}" maxlength="25" >
            <f:validateLength maximum="25" minimum="0" />
        </h:inputText>	
    
        <h:outputLabel for="dateOfBirth">
            <h:outputText value="Date of birth (YYYYMMDD)"/>
        </h:outputLabel>    	                                        
        <h:inputText id="dateOfBirth" value="#{newPersonBean.dateOfBirth}" maxlength="8" size="8">
            <f:validateLength maximum="8" minimum="0" />
        </h:inputText> 
        
        <h:outputLabel for="dateOfDeath">
            <h:outputText value="Date of death (YYYYMMDD)"/>
        </h:outputLabel>    	                                        
        <h:inputText id="dateOfDeath" value="#{newPersonBean.dateOfDeath}" maxlength="8"  size="8">
            <f:validateLength maximum="8" minimum="0" />
        </h:inputText>  
    
        <h:outputLabel for="hometown">
            <h:outputText value="Hometown"/>
        </h:outputLabel>    	                                        
        <h:inputText id="hometown" value="#{newPersonBean.hometown}" maxlength="25" >
            <f:validateLength maximum="25" minimum="0" />
        </h:inputText>
    
        <h:outputLabel for="burialPlace">
            <h:outputText value="Burial place"/>
        </h:outputLabel>    	                                        
        <h:inputText id="burialPlace" value="#{newPersonBean.burialPlace}" maxlength="25" >
            <f:validateLength maximum="25" minimum="0" />
        </h:inputText> 
    
        <h:outputLabel for="newGraveyard">
            <h:outputText value="Graveyard (create new)"/>
        </h:outputLabel>    	                                        
        <h:inputText id="newGraveyard" value="#{newPersonBean.newGraveyard}" maxlength="25" >
            <f:validateLength maximum="25" minimum="0" />
        </h:inputText>         
        
    
        <h:outputLabel for="existingGraveyard">
            <h:outputText value="Graveyard (choose existing)"/>
        </h:outputLabel>    
    
        <h:panelGroup>
            
            <h:panelGrid columns="7" cellspacing="6" border="0">
               
                <!--
                <h:inputText id="existingGraveyard" value="#{newPersonBean.existingGraveyardId}" maxlength="25" >
                    <f:validateLength maximum="25" minimum="0" />
                </h:inputText>
                -->                
                <h:selectOneMenu id="existingGraveyard" value="#{newPersonBean.existingGraveyardId}" >								
				    <f:selectItems 	value="#{newPersonBean.graveyardSelectItemList}" />
				</h:selectOneMenu>  
                
                <f:verbatim>					
					<br />
				</f:verbatim>	
            
                <h:outputLabel for="department">
                    <h:outputText value="Department"/>
                </h:outputLabel> 
                <h:inputText id="department" value="#{newPersonBean.department}" maxlength="10" >
                    <f:validateLength maximum="10" minimum="0" />
                </h:inputText>
                
                <h:outputLabel for="block">
                    <h:outputText value="Block"/>
                </h:outputLabel> 
                <h:inputText id="block" value="#{newPersonBean.block}" maxlength="5" >
                    <f:validateLength maximum="5" minimum="0" />
                </h:inputText>
                
                <h:outputLabel for="graveNumber">
                    <h:outputText value="Grave number"/>
                </h:outputLabel> 
                <h:inputText id="graveNumber" value="#{newPersonBean.graveNumber}" maxlength="5" >
                    <f:validateLength maximum="50" minimum="0" />
                </h:inputText>    
            
            </h:panelGrid>         
        </h:panelGroup>                   
    
    </h:panelGrid> 
    
    
    <h:panelGrid columns="3" cellspacing="7" border="0"> 
        
        <h:outputLabel for="createObituaryAfterSaving">
            <h:outputText value="Create obituary after saving?"/>
        </h:outputLabel>       
        <h:selectBooleanCheckbox id="createObituaryAfterSaving" value="#{newPersonBean.createObituaryAfterSaving}" />
        
        <h:commandButton value="Save" action="#{newPersonBean.save}" id="cbSave" />
        
    </h:panelGrid>    

	</wf:wfblock>

    </h:form>
  
	</ws:page>
</f:view>
</jsp:root>