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
        <ws:page id="dainissearch">
        	<h:form id="dainispagesform1">    
        	
        	   <!-- search form -->
 			   	       	
        	
    <h:form id="searchForm"> 
        
        <h:panelGrid columns="5"  border="0" cellspacing="5" footerClass="SearchFormFooterClass" >  
          
            <!-- 1. row -->
            
            <h:panelGroup>  
                <h:outputLabel for="firstName">
                   <h:outputText value="First name"/>
                </h:outputLabel>    	         
                  
                <f:verbatim escape="false"><br /></f:verbatim> 
                              
                <h:inputText id="firstName" value="#{graveyardSearchBean.firstName}" maxlength="25" >
                    <f:validateLength maximum="25" minimum="0" />
                </h:inputText>	
            </h:panelGroup> 

          
            <h:panelGroup>
                <h:outputLabel for="surname">
                	<h:outputText value="Surname"/>
                </h:outputLabel>    
                
                <f:verbatim escape="false"><br /></f:verbatim>	
                         
                <h:inputText id="surname" value="#{graveyardSearchBean.surname}" maxlength="25" >
                	<f:validateLength maximum="25" minimum="0" />
                </h:inputText>           
            </h:panelGroup>  
            
          
            <h:panelGroup>
                <h:outputLabel for="dateOfBirthFrom">
                	<h:outputText value="Date of birth"/>
                </h:outputLabel>	
                <f:verbatim escape="false"><br /></f:verbatim>         
                <h:inputText id="dateOfBirthFrom" value="#{graveyardSearchBean.dateOfBirthFrom}" maxlength="12" >
                	<f:validateLength maximum="12" minimum="0" />
                </h:inputText>	
            </h:panelGroup>  
              
            <h:panelGroup>
                <f:verbatim escape="false"><br /></f:verbatim>
                <h:outputText value="-" />
            </h:panelGroup>  
            
            <h:panelGroup>
                <f:verbatim escape="false"><br /></f:verbatim>
                <h:inputText id="dateOfBirthTo" value="#{graveyardSearchBean.dateOfBirthTo}" maxlength="12" >
                	<f:validateLength maximum="12" minimum="0" />
                </h:inputText>
            </h:panelGroup> 
            <!-- end of 1. row -->
            
                
            <!-- 2. row --> 
            <h:panelGroup />
            <h:panelGroup />
                
            <h:panelGroup>
                <h:outputLabel for="dateOfDeceaseFrom">
                	<h:outputText value="Date of decease"/>
                </h:outputLabel>    
                <f:verbatim escape="false"><br /></f:verbatim>   	         
                <h:inputText id="dateOfDeceaseFrom" value="#{graveyardSearchBean.dateOfDeceaseFrom}" maxlength="8" >
                	<f:validateLength maximum="8" minimum="0" />
                </h:inputText>            
            </h:panelGroup>  
            
            <h:panelGroup>
                <f:verbatim escape="false"><br /></f:verbatim>
                <h:outputText value="-" />
            </h:panelGroup>  
            
            <h:panelGroup>
                <f:verbatim escape="false"><br /></f:verbatim>
                <h:inputText id="dateOfDeceaseTo" value="#{graveyardSearchBean.dateOfDeceaseTo}" maxlength="8" >
                	<f:validateLength maximum="8" minimum="0" />
                </h:inputText>	                
            </h:panelGroup>
            <!-- end of 2. row -->  
            
            
            <!-- 3. row --> 
            <h:panelGroup>
                <h:outputLabel for="hometown">
                	<h:outputText value="Hometown"/>
                </h:outputLabel> 
                <f:verbatim escape="false"><br /></f:verbatim>
                <h:inputText id="hometown" value="#{graveyardSearchBean.hometown}" maxlength="30" >
                	<f:validateLength maximum="30" minimum="0" />
                </h:inputText>		
            </h:panelGroup>  
            
            <h:panelGroup>
                <h:outputLabel for="cemetery">
                	<h:outputText value="Cemetery"/>
                </h:outputLabel>                
                <f:verbatim escape="false"><br /></f:verbatim>                	
                <h:selectOneMenu id="cemetery" value="#{graveyardSearchBean.cemetery}"
                        converter="se.agura.memorial.experiment.CemeteryConverter" >  
                    <f:selectItem itemValue=""  itemLabel="" />
                    <f:selectItems value="#{graveyardSearchBean.cemeteriesItems}" />
                </h:selectOneMenu>
            </h:panelGroup>  
            
            <h:panelGroup>
            </h:panelGroup>                         
            
            <h:panelGroup>
            </h:panelGroup>  
            
            <h:panelGroup>
                <h:outputLabel for="database">
                	<h:outputText value="Database"/>
                </h:outputLabel>
                <f:verbatim escape="false"><br /></f:verbatim>	 				
                <h:selectOneMenu id="database" value="#{graveyardSearchBean.database}">			          
                    <f:selectItem itemValue="Malmö"  itemLabel="Malmö"/>
                    <f:selectItem itemValue="Övriga" itemLabel="Övriga"/>
                </h:selectOneMenu>	            
            </h:panelGroup>             
            <!-- end of 3. row --> 
            
            <!-- 4. row -->
                      
            <f:facet name="footer"> 
                <h:panelGroup>                    
                    <h:commandButton id="clearButton"  value="Clear"  action="#{graveyardSearchBean.clear}"  immediate="false" />
                    <h:commandButton id="searchButton" value="Search" action="#{graveyardSearchBean.search}" immediate="false" />
                    
                    <!-- 
                    <h:commandButton id="clearButton"  value="Clear"  action="#{graveyardSearchBean.clear}"  immediate="true" />                        
                    -->
                    
                </h:panelGroup>     
            </f:facet>  
            
            <!-- end of 4. row --> 
          
        </h:panelGrid >  
        
									
    </h:form>       
        	
        	
        	             
                <f:verbatim>Dainis is here!<br /></f:verbatim>
                
			     <x:dataTable id="searchResultsTable"                              
			        var="person"
			        value="#{PersonList.list}"            
			        rows="10">
			        
			       <h:column>
			           <f:facet name="header">
			               <h:outputText value="Number" />
			           </f:facet>
			           <h:outputText value="todo :)" />
			       </h:column>        
			                    
			       <h:column>
			           <f:facet name="header">
			               <h:outputText value="First name" />
			           </f:facet>
			           <h:outputText value="#{person.firstName}" />
			       </h:column>
			    
			       <h:column>
			           <f:facet name="header">
			              <h:outputText value="Surname" />
			           </f:facet>
			           <h:outputText value="#{person.surname}" />
			       </h:column>
			    
			       <h:column>
			           <f:facet name="header">
			              <h:outputText value="Birth date" />
			           </f:facet>
			           <h:outputText value="#{person.birthDate}" />
			       </h:column>
			   </x:dataTable> 
			   
			    <x:dataScroller id="scroll_1"
			            for="searchResultsTable"
			            fastStep="10"
			            pageCountVar="pageCount"
			            pageIndexVar="pageIndex"
			            styleClass="scroller"
			            paginator="true"
			            paginatorMaxPages="9"            
			            paginatorActiveColumnStyle="font-weight:bold;"
			            >
			        
			        <f:facet name="first" >
			            <h:outputText value="|&lt;" />
			        </f:facet>
			        <f:facet name="last">
			            <h:outputText value="&gt;|" />
			        </f:facet>
			        <f:facet name="previous">
			            <h:outputText value="&lt;" />
			        </f:facet>
			        <f:facet name="next">
			            <h:outputText value="&gt;" />
			        </f:facet>
			        <f:facet name="fastforward">
			            <h:outputText value="&gt;&gt;" />
			        </f:facet>
			        <f:facet name="fastrewind">
			            <h:outputText value="&lt;&lt;" />
			        </f:facet>
			        
			    </x:dataScroller>  
                
                
                
            </h:form>    
        </ws:page>
</f:view>
</jsp:root>