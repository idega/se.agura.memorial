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
<f:view>
	<ws:page id="obituary22sdfsd">

	<h:form id="newPersonForm">
	        
	    <f:verbatim>
	        <h1>test-5: navigation test</h1>
	        <p>
	            Navigation OK
	            
              	    <h:commandLink action="newPerson" immediate="true">
	        <h:outputText value="New Person test" />				
	            
	        </p>
	    </f:verbatim>

    </h:form>
  
	</ws:page>
</f:view>
</jsp:root>