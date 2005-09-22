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

    <h:panelGrid columns="2" cellspacing="7" border="0"> 
	     <h:panelGroup>
	        <h:outputLabel for="firstName">
            <h:outputText value="First name"/>
    	    </h:outputLabel>    	                                        
	    </h:panelGroup> 

	     <h:panelGroup>
	        <h:inputText id="firstName" value="#{newPersonBean.firstName}" maxlength="25" >
            <f:validateLength maximum="25" minimum="0" />
	        </h:inputText>	
	    </h:panelGroup> 
    
	     <h:panelGroup>
    	    <h:outputLabel for="lastName">
            <h:outputText value="Last name"/>
	        </h:outputLabel>    	                                        
	    </h:panelGroup> 

	    <h:panelGroup>
     	   <h:inputText id="lastName" value="#{newPersonBean.lastName}" maxlength="25" >
           <f:validateLength maximum="25" minimum="0" />
	       </h:inputText>	
	    </h:panelGroup> 
    
	    <h:panelGroup>
    	    <h:outputLabel for="dateOfBirth">
            <h:outputText value="Date of birth (YYYYMMDD)"/>
	        </h:outputLabel>    	                                        
	    </h:panelGroup> 
    
	    <h:panelGroup>
    		<h:inputText id="dateOfBirth_year" 	value="#{newPersonBean.dateOfBirth_year}" maxlength="4"  size="4"></h:inputText>	
            <h:selectOneMenu id="dateOfBirth_month" value="#{newPersonBean.dateOfBirth_month}" onchange="this.form.submit();" >								
			<f:selectItems value="#{newPersonBean.monthSelectItemList}" /></h:selectOneMenu>							
													
            <h:selectOneMenu id="dateOfBirth_day" value="#{newPersonBean.dateOfBirth_day}" >								
			<f:selectItems value="#{newPersonBean.dateOfBirthDaySelectItemList}" /></h:selectOneMenu>							

			<h:message for="dateOfBirth_year"  showSummary="false" showDetail="true" /> 
	    </h:panelGroup> 
        
    	<h:panelGroup>
	        <h:outputLabel for="dateOfDeath">
            <h:outputText value="Date of death (YYYYMMDD)"/>
    	    </h:outputLabel>  
	    </h:panelGroup>           	                                        

        <h:panelGroup>

    		<h:inputText id="dateOfDeath_year" 	value="#{newPersonBean.dateOfDeath_year}" maxlength="4"  size="4"></h:inputText>	
            <h:selectOneMenu id="dateOfDeath_month" value="#{newPersonBean.dateOfDeath_month}" onchange="this.form.submit();" >								
			<f:selectItems value="#{newPersonBean.monthSelectItemList}" /></h:selectOneMenu>							
													
            <h:selectOneMenu id="dateOfDeath_day" value="#{newPersonBean.dateOfDeath_day}" >								
			<f:selectItems value="#{newPersonBean.dateOfDeathDaySelectItemList}" /></h:selectOneMenu>							

			<h:message for="dateOfDeath_year"  showSummary="false" showDetail="true" />

    	</h:panelGroup>         
    
	    <h:panelGroup>
	        <h:outputLabel for="hometown">
            <h:outputText value="Hometown"/>
      	    </h:outputLabel>    	                                        
	    </h:panelGroup>         

	    <h:panelGroup>
            <h:inputText id="hometown" value="#{newPersonBean.hometown}" maxlength="25" >
            <f:validateLength maximum="25" minimum="0" />
    	    </h:inputText>
	    </h:panelGroup>         
    
    	<h:panelGroup>
	        <h:outputLabel for="burialPlace">
            <h:outputText value="Burial place"/>
    	    </h:outputLabel>    	                                        
	    </h:panelGroup>         
        

    	<h:panelGroup>
        	<h:inputText id="burialPlace" value="#{newPersonBean.burialPlace}" maxlength="25" >
            <f:validateLength maximum="25" minimum="0" />
         	</h:inputText> 
	    </h:panelGroup>         
    
     	<h:panelGroup>
	        <h:outputLabel for="newGraveyard">
            <h:outputText value="Graveyard (create new)"/>
    	    </h:outputLabel>    	                                        
	    </h:panelGroup>         

	    <h:panelGroup>
	        <h:inputText id="newGraveyard" value="#{newPersonBean.newGraveyard}" maxlength="25" >
            <f:validateLength maximum="25" minimum="0" />
    	    </h:inputText>         
	    </h:panelGroup>         
    
        <h:panelGroup>
	        <h:outputLabel for="existingGraveyard">
            <h:outputText value="Graveyard (choose existing)"/>
     	    </h:outputLabel> 
        </h:panelGroup>                   

        <h:panelGroup>
             <h:selectOneMenu id="existingGraveyard" value="#{newPersonBean.existingGraveyardId}" >								
  		     <f:selectItems 	value="#{newPersonBean.graveyardSelectItemList}" />
			 </h:selectOneMenu>  
        </h:panelGroup>                   

        <h:panelGroup>
              <h:outputLabel for="department">
              <h:outputText value="Department"/>
              </h:outputLabel> 
        </h:panelGroup>                   

        <h:panelGroup>
              <h:inputText id="department" value="#{newPersonBean.department}" maxlength="10" >
              <f:validateLength maximum="10" minimum="0" />
              </h:inputText>
        </h:panelGroup>                   

        <h:panelGroup>
              <h:outputLabel for="block">
              <h:outputText value="Block"/>
              </h:outputLabel> 
        </h:panelGroup>                   


        <h:panelGroup>
              <h:inputText id="block" value="#{newPersonBean.block}" maxlength="5" >
              <f:validateLength maximum="5" minimum="0" />
              </h:inputText>
        </h:panelGroup>                   

        <h:panelGroup>
              <h:outputLabel for="graveNumber">
              <h:outputText value="Grave number"/>
              </h:outputLabel> 
        </h:panelGroup>                   

        <h:panelGroup>
              <h:inputText id="graveNumber" value="#{newPersonBean.graveNumber}" maxlength="5" >
              <f:validateLength maximum="50" minimum="0" />
              </h:inputText>    
        </h:panelGroup>                   

        <h:panelGroup>
   	          <h:outputLabel for="createObituaryAfterSaving">
	          <h:outputText value="Create obituary after saving?"/>
	          </h:outputLabel>       
        </h:panelGroup>                   

        <h:panelGroup>
  	          <h:selectBooleanCheckbox id="createObituaryAfterSaving" value="#{newPersonBean.createObituaryAfterSaving}" />
        </h:panelGroup>                   
    
    </h:panelGrid> 

        

	</wf:wfblock>

     <h:outputLink value="Obituary_Create" id="a12345"> 
              <h:commandButton value="CANCEL" action="" id="cbCreateBtn1" />
  			  <f:param name="graveId" value="#{obituaryCreateBackingBean.graveId}" />
			  <f:param name="databaseId" value="#{obituaryCreateBackingBean.databaseId}" />														                        
			  <f:param name="actionId" value="5" />														                        						
     </h:outputLink>

     <h:outputLink value="Obituary_Create" id="a1234567"> 
  			  <f:param name="graveId" value="#{obituaryCreateBackingBean.graveId}" />
			  <f:param name="databaseId" value="#{obituaryCreateBackingBean.databaseId}" />														                        
			  <f:param name="actionId" value="5" />														                        						
              <h:commandButton value="SAVE" type="submit" action="#{newPersonBean.save}" id="cbSaveBtn1" />			  
     </h:outputLink>

     <h:commandButton value="SAVE2" action="#{newPersonBean.onClick}" id="cbSaveBtn2" />

    </h:form>
  
	</ws:page>
</f:view>
</jsp:root>