<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" 
	xmlns:h="http://java.sun.com/jsf/html" 
	xmlns:f="http://java.sun.com/jsf/core" 
	xmlns:ws="http://xmlns.idega.com/com.idega.workspace" 
	xmlns:article="http://xmlns.idega.com/com.idega.block.article" 
	xmlns:builder="http://xmlns.idega.com/com.idega.builder" 
	xmlns:x="http://myfaces.apache.org/extensions" version="1.2">
	
	<jsp:directive.page contentType="text/html;charset=UTF-8" 
		pageEncoding="UTF-8"/>
	
	<f:view>
		<ws:page id="dainissearch">
			<h:form id="dainispagesform1">
				<!-- search form -->
				<h:form id="searchForm">
					<h:panelGrid columns="5" border="0" cellspacing="5" 
						footerClass="SearchFormFooterClass" >
						<!-- 1. row -->
						<h:panelGroup>
							<h:outputLabel for="firstName">
								<h:outputText value="First name"/>
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
								<h:outputText value="Surname"/>
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
								<h:outputText value="Date of birth"/>
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="dateOfBirthFrom" 
								value="#{graveyardSearchBean.dateOfBirthFrom}" 
								maxlength="12" >
								<f:validateLength maximum="12" minimum="0" />
							</h:inputText>
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
								maxlength="12" >
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
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="dateOfDeceaseFrom" 
								value="#{graveyardSearchBean.dateOfDeceaseFrom}" 
								maxlength="8" >
								<f:validateLength maximum="8" minimum="0" />
							</h:inputText>
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
								maxlength="8" >
								<f:validateLength maximum="8" minimum="0" />
							</h:inputText>
						</h:panelGroup>
						<!-- end of 2. row -->
						<!-- 3. row -->
						<h:panelGroup>
							<h:outputLabel for="hometown">
								<h:outputText value="Hometown"/>
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
								<h:outputText value="Graveyard"/>
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
									value="#{graveyardSearchBean.graveyardsItems}" 
									/>
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
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:selectOneMenu id="database" 
								value="#{graveyardSearchBean.database}">
								<f:selectItem itemValue="1" itemLabel="Malmö"/>
								<f:selectItem itemValue="2" itemLabel="Övriga"/>
							</h:selectOneMenu>
						</h:panelGroup>
						<!-- end of 3. row -->
						<!-- 4. row -->
						<f:facet name="footer">
							<h:panelGroup>
								<h:commandButton id="clearButton" value="Clear" 
									action="#{graveyardSearchBean.clear}" 
									immediate="false" />
								<h:commandButton id="searchButton" 
									value="Search" 
									action="#{graveyardSearchBean.search}" 
									immediate="false" />
								<!-- 
                    <h:commandButton id="clearButton"  value="Clear"  action="#{graveyardSearchBean.clear}"  immediate="true" />                        
                    -->
							</h:panelGroup>
						</f:facet>
						<!-- end of 4. row -->
					</h:panelGrid >
				</h:form>
				<f:verbatim>
					<br />
					<br />
				</f:verbatim>
				<x:dataTable id="searchResultsTable" var="person" 
					value="#{graveyardSearchBean.searchResults}" rows="10">
					<h:column>
						<h:outputText value="x. " />
					</h:column>
					<h:column>
						<h:outputText value="#{person.lastName}" />
						<h:outputText value=", #{person.firstName}" />
					</h:column>
					<h:column>
						<h:outputText value="(#{person.dateOfBirth}" />
					</h:column>
					<h:column>
						<h:outputText value=" - " />
					</h:column>
					<h:column>
						<h:outputText value="#{person.dateOfDeath})" />
					</h:column>
					<h:column>
						<h:commandLink action="showObituaryAction" 
							immediate="true">
							<h:outputText value="show" />
						</h:commandLink>
					</h:column>
				</x:dataTable>
                
                <!--
                <h:outputText value="blah: #{graveyardSearchBean.searchResultCount} " />
                -->
                
				<x:dataScroller id="scroll_1" for="searchResultsTable" 
					fastStep="10" pageCountVar="pageCount" 
					pageIndexVar="pageIndex" styleClass="scroller" 
					paginator="true" paginatorMaxPages="9" 
					paginatorActiveColumnStyle="font-weight:bold;" 
                   rendered="#{graveyardSearchBean.searchResultCount==0?false:true}"
                    >
					<!--
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
			        -->
					<f:facet name="previous">
						<h:outputText value="&lt; previous" />
					</f:facet>
					<f:facet name="next">
						<h:outputText value="next page &gt;" />
					</f:facet>
				</x:dataScroller>
			</h:form>
		</ws:page>
	</f:view>
</jsp:root>