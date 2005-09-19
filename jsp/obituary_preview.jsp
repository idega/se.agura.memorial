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
	<ws:page id="obituaryPreview">
		<h:form id="obituaryPreviewform">    
			
			<wf:wfblock title="Obituary Preview page">

             <wf:container styleClass="obituary_part">

                 <h:outputText  styleClass="headline" value="Obituary" style="font-weight: bold; " />                  
				 <f:verbatim>
                    <br />
                    <br />
                  </f:verbatim>

                 <h:outputText  styleClass="Text" value="#{obituaryBackingBean.obituaryText}"  />                  

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
                  </wf:container>

                   <h:outputLink value="Obituary_Create"> 
                        <h:commandButton value="BACK" action="" id="cbBack" />
						<f:param name="graveId" value="#{obituaryBackingBean.graveId}" />
						<f:param name="databaseId" value="#{obituaryBackingBean.databaseId}" />														                        
						<f:param name="actionId" value="5" />														                        						
                   </h:outputLink>
					
                   <h:outputLink value="Obituary"> 
                        <h:commandButton value="SAVE" action="" id="cbSave" />
						<f:param name="graveId" value="#{obituaryBackingBean.graveId}" />
						<f:param name="databaseId" value="#{obituaryBackingBean.databaseId}" />														                        
						<f:param name="actionId" value="4" />														                        						
                   </h:outputLink>


			</wf:wfblock >

	    </h:form>    
	</ws:page>
</f:view>
</jsp:root>