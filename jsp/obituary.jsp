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
	<ws:page id="obituary">
		<h:form id="obituaryform">    
			
 			<wf:wfblock title="Obituary page">

             <wf:container styleClass="obituary_part">

                 <h:outputText styleClass="headline" value="Obituary" />

				 <f:verbatim>
                    <br />
                    <br />
                  </f:verbatim>

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
                  </wf:container>


                   <h:commandButton value="Print" action="#{obituaryCreateBackingBean.onClick}"  />


                   <h:outputLink value="Obituary_Create" > 
                        <h:commandButton value="Edit" action="#{obituaryCreateBackingBean.onClick}" id="cbCreate" />
						<f:param name="graveId" value="#{obituaryBackingBean.graveId}" />
						<f:param name="databaseId" value="#{obituaryBackingBean.databaseId}" />														                        
						<f:param name="actionId" value="5" />														                        						
                   </h:outputLink>

                   <h:commandButton value="Save as PDF" action="#{obituaryCreateBackingBean.onClick}"  />

                   

			</wf:wfblock >

	    </h:form>    
	</ws:page>
</f:view>
</jsp:root>