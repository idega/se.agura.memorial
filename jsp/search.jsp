<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" 
	xmlns:h="http://java.sun.com/jsf/html" 
	xmlns:f="http://java.sun.com/jsf/core" 
	xmlns:ws="http://xmlns.idega.com/com.idega.workspace" 
	xmlns:article="http://xmlns.idega.com/com.idega.block.article" 
	xmlns:builder="http://xmlns.idega.com/com.idega.builder" 
	xmlns:x="http://myfaces.apache.org/extensions" version="1.2">
	
	<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
	
	<f:view>
		<ws:page id="dainissearch">
			<!-- if this form is here, then nothing works in IE <h:form id="dainispagesform1">  -->
				<!-- search form -->
				<h:form id="searchForm">
					<h:panelGrid columns="5" border="0" cellspacing="5" 
						footerClass="SearchFormFooterClass" >
						<!-- 1. row -->
						<h:panelGroup>
							<h:outputLabel for="firstName">
								<h:outputText value="#{localizedStrings['se.agura.memorial']['first_name']}"/>
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="firstName" 
								value="#{graveyardSearchBean.firstName}" 
								maxlength="25" >
								<f:validateLength maximum="25" minimum="0" />
							</h:inputText>
						</h:panelGroup>
						<h:panelGroup>
							<h:outputLabel for="surname">
								<h:outputText value="#{localizedStrings['se.agura.memorial']['surname']}"/>
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="surname" 
								value="#{graveyardSearchBean.surname}" 
								maxlength="25" >
								<f:validateLength maximum="25" minimum="0" />
							</h:inputText>
						</h:panelGroup>
						<h:panelGroup>
							<h:outputLabel for="dateOfBirthFrom">
								<h:outputText value="#{localizedStrings['se.agura.memorial']['date_of_birth']}" />
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="dateOfBirthFrom" 
								value="#{graveyardSearchBean.dateOfBirthFrom}" 
								maxlength="8"  size="8">
								<f:validateLength maximum="8" minimum="0" />
								<f:validator validatorId="se.agura.memorial.search.presentation.SearchFormDateValidator" />
							</h:inputText>	
													
							<h:message for="dateOfBirthFrom"  showSummary="false" showDetail="true" />
														
						</h:panelGroup>
						<h:panelGroup>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:outputText value="-" />
						</h:panelGroup>
						<h:panelGroup>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="dateOfBirthTo" 
								value="#{graveyardSearchBean.dateOfBirthTo}" 
								maxlength="8"  size="8">
								<f:validateLength maximum="8" minimum="0" />
								<f:validator validatorId="se.agura.memorial.search.presentation.SearchFormDateValidator" />
							</h:inputText>
							
							<h:message for="dateOfBirthTo"  showSummary="false" showDetail="true" />
							
						</h:panelGroup>
						<!-- end of 1. row -->
						<!-- 2. row -->
						<h:panelGroup >
                            <h:outputLabel for="personIdentifier">
								<h:outputText value="#{localizedStrings['se.agura.memorial']['person_identifier']}" />
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="personIdentifier" 
								value="#{graveyardSearchBean.personIdentifier}" 
								maxlength="12" size="12" >
								<f:validateLength maximum="12" minimum="0" />
							</h:inputText> 
                       </h:panelGroup >
                        
						<h:panelGroup />
                        
						<h:panelGroup>
							<h:outputLabel for="dateOfDeceaseFrom">
								<h:outputText value="#{localizedStrings['se.agura.memorial']['date_of_decease']}"/>
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="dateOfDeceaseFrom" 
								value="#{graveyardSearchBean.dateOfDeceaseFrom}" 
								maxlength="8" size="8" >
								<f:validateLength maximum="8" minimum="0" />
								<f:validator validatorId="se.agura.memorial.search.presentation.SearchFormDateValidator" />
							</h:inputText>
							
							<h:message for="dateOfDeceaseFrom"  showSummary="false" showDetail="true" />
							
						</h:panelGroup>
						<h:panelGroup>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:outputText value="-" />
						</h:panelGroup>
						<h:panelGroup>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="dateOfDeceaseTo" 
								value="#{graveyardSearchBean.dateOfDeceaseTo}" 
								maxlength="8"  size="8">
								<f:validateLength maximum="8" minimum="0" />
								<f:validator validatorId="se.agura.memorial.search.presentation.SearchFormDateValidator" />
							</h:inputText>
							
							<h:message for="dateOfDeceaseTo"  showSummary="false" showDetail="true" />
							
						</h:panelGroup>
						<!-- end of 2. row -->
						<!-- 3. row -->
						<h:panelGroup>
							<h:outputLabel for="hometown">
								<h:outputText value="#{localizedStrings['se.agura.memorial']['hometown']}"/>
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="hometown" 
								value="#{graveyardSearchBean.hometown}" 
								maxlength="30" >
								<f:validateLength maximum="30" minimum="0" />
							</h:inputText>
						</h:panelGroup>
						<h:panelGroup>
							<h:outputLabel for="graveyard">
								<h:outputText value="#{localizedStrings['se.agura.memorial']['graveyard']}"/>
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:selectOneMenu id="graveyard" 
								value="#{graveyardSearchBean.graveyard}" 
								converter="se.agura.memorial.search.presentation.GraveyardConverter" 
								>
								<f:selectItem itemValue="" itemLabel="" />
								<f:selectItems 
									value="#{graveyardSearchBean.graveyardsItems}" />
							</h:selectOneMenu>
						</h:panelGroup>
						<h:panelGroup>
						</h:panelGroup>
						<h:panelGroup>
						</h:panelGroup>
						<h:panelGroup>
							<h:outputLabel for="database">
								<h:outputText value="#{localizedStrings['se.agura.memorial']['database']}"/>
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:selectOneMenu id="database" 
								value="#{graveyardSearchBean.database}" onchange="this.form.submit();" >
								<f:selectItem itemValue="1" itemLabel="Malmö"/>
								<f:selectItem itemValue="2" itemLabel="Övriga"/>
							</h:selectOneMenu>
						</h:panelGroup>
						<!-- end of 3. row -->
						<!-- 4. row -->
						<f:facet name="footer">
							<h:panelGroup>								
								<h:commandButton id="searchButton" 
									value="#{localizedStrings['se.agura.memorial']['search']}" 
									action="#{graveyardSearchBean.search}" 
									immediate="false" />
                               <h:commandButton id="clearButton" value="#{localizedStrings['se.agura.memorial']['clear']}" 
									action="#{graveyardSearchBean.clear}" 
									immediate="false" />                                    
                                    
							</h:panelGroup>
						</f:facet>
						<!-- end of 4. row -->
					</h:panelGrid >
				
				<!-- </h:form>  -->
				
				<f:verbatim>
					<br />
					<br />
				</f:verbatim>
				
				<!-- here are error messages and warnings -->					
				<h:messages id="messageList" showSummary="false" showDetail="true" globalOnly="true"/> 			
				
				<f:verbatim>					
					<br />
				</f:verbatim>				
				<x:dataTable id="searchResultsTable" var="person" 
					value="#{graveyardSearchBean.searchResults}" rows="10">
					<h:column>
						<h:outputText value="#{person.rowNr}." />
					</h:column>
					<h:column>
						
						<h:outputText value="#{person.lastName}" >
							<f:converter converterId="se.agura.memorial.search.presentation.EmptyStringConverter" /> 
						</h:outputText>
						
						<h:outputText value=", " />
						
						<h:outputText value="#{person.firstName}" >
							<f:converter converterId="se.agura.memorial.search.presentation.EmptyStringConverter" /> 
						</h:outputText>						
						
					</h:column>
					<h:column>
						<h:outputText value="(" />
						<h:outputText value="#{person.dateOfBirth}" >
							<f:converter converterId="se.agura.memorial.search.presentation.DateConverter" /> 
						</h:outputText>
					</h:column>
					<h:column>
						<h:outputText value=" - " />
					</h:column>
					<h:column>
						<h:outputText value="#{person.dateOfDeath}" >
							<f:converter converterId="se.agura.memorial.search.presentation.DateConverter" /> 
						</h:outputText>
						<h:outputText value=")" />
					</h:column>
					<h:column>
						<h:commandLink action="showObituaryAction" immediate="true">							
							<h:outputText value="#{localizedStrings['se.agura.memorial']['show']}" />
							<f:param name="graveID" value="#{person.graveID}"/>
							<f:param name="lopNr" value="#{person.lopNr}"/>
						</h:commandLink>
					</h:column>
				</x:dataTable>
                
				<x:dataScroller id="scroll_1" for="searchResultsTable" 
					fastStep="10" pageCountVar="pageCount" 
					pageIndexVar="pageIndex" styleClass="scroller" 
					paginator="true" paginatorMaxPages="10" 
					paginatorActiveColumnStyle="font-weight:bold;" 
                   rendered="#{graveyardSearchBean.searchResultCount==0?false:true}" >
					<f:facet name="previous">
						<h:outputText value="#{localizedStrings['se.agura.memorial']['previous_page']}" />
					</f:facet>
					<f:facet name="next">
						<h:outputText value="#{localizedStrings['se.agura.memorial']['next_page']}" />
					</f:facet>
				</x:dataScroller>
			</h:form>
		</ws:page>
	</f:view>
</jsp:root>