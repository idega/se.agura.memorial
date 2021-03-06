<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" 
	xmlns:h="http://java.sun.com/jsf/html" 
	xmlns:f="http://java.sun.com/jsf/core" 
	xmlns:ws="http://xmlns.idega.com/com.idega.workspace" 
	xmlns:wf="http://xmlns.idega.com/com.idega.webface" 
	xmlns:article="http://xmlns.idega.com/com.idega.block.article" 
	xmlns:builder="http://xmlns.idega.com/com.idega.builder" 
	xmlns:core="http://xmlns.idega.com/com.idega.core"
	xmlns:x="http://myfaces.apache.org/extensions" version="1.2">
	
	<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
	  
	<f:view>
		<ws:page id="dainissearch">
			<!-- if this form is here, then nothing works in IE <h:form id="dainispagesform1">  -->
				<!-- search form  -->
				<h:form id="searchForm">
					
					<wf:wfblock title="#{localizedStrings['se.agura.memorial']['search_for_person']}">
					
					<h:panelGrid columns="5" border="0" cellspacing="5" 
						footerClass="SearchFormFooterClass" >
						<!-- 1. row -->
						
						<h:panelGroup>
							<h:outputLabel for="surname">
								<h:outputText value="#{localizedStrings['se.agura.memorial']['surname']}"/>
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="surname" value="#{graveyardSearchBean.surname}" maxlength="25" >
								<f:validateLength maximum="25" minimum="0" />
							</h:inputText>
						</h:panelGroup>


						<h:panelGroup>
							<h:outputLabel for="surname">
								<h:outputText value="#{localizedStrings['se.agura.memorial']['first_name']}"/>
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
							<h:inputText id="firstName"  value="#{graveyardSearchBean.firstName}" maxlength="25" >
								<f:validateLength maximum="25" minimum="0" />
							</h:inputText>
						</h:panelGroup>
						
						
						<h:panelGroup>
						
 
						
							<h:outputLabel for="dateOfBirthFrom">
							<!--	<h:outputText value="#{localizedStrings['se.agura.memorial']['date_of_birth']}" /> -->
								<h:outputText value="Date of birth" />							
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>

							
							<h:inputText id="dateOfBirthFrom_year" 	value="#{graveyardSearchBean.dateOfBirthFrom_year}" maxlength="4"  size="4">
							</h:inputText>	
							
                           <h:selectOneMenu id="dateOfBirthFrom_month" value="#{graveyardSearchBean.dateOfBirthFrom_month}" onchange="this.form.submit();" >								
								<f:selectItems value="#{graveyardSearchBean.monthSelectItemList}" />
							</h:selectOneMenu>							
													
                           <h:selectOneMenu id="dateOfBirthFrom_day" value="#{graveyardSearchBean.dateOfBirthFrom_day}" >								
								<f:selectItems value="#{graveyardSearchBean.dateOfBirthFromDaySelectItemList}" />
							</h:selectOneMenu>							

							<h:message for="dateOfBirthFrom_year"  showSummary="false" showDetail="true" />
														
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

	  					   <h:inputText id="dateOfBirthTo_year" value="#{graveyardSearchBean.dateOfBirthTo_year}" 	maxlength="4"  size="4"></h:inputText>	
							
                           <h:selectOneMenu id="dateOfBirthTo_month" value="#{graveyardSearchBean.dateOfBirthTo_month}" onchange="this.form.submit();">								
								<f:selectItems   value="#{graveyardSearchBean.monthSelectItemList}" />
						    </h:selectOneMenu>							
													
                           <h:selectOneMenu id="dateOfBirthTo_day" value="#{graveyardSearchBean.dateOfBirthTo_day}" >								
								<f:selectItems   value="#{graveyardSearchBean.dateOfBirthToDaySelectItemList}" />
						    </h:selectOneMenu>							

							<h:message for="dateOfBirthTo_year"  showSummary="false" showDetail="true" />
							
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
	  					   <h:inputText id="dateOfDeceaseFrom_year" value="#{graveyardSearchBean.dateOfDeceaseFrom_year}" 	maxlength="4"  size="4"></h:inputText>	
							
                           <h:selectOneMenu id="dateOfDeceaseFrom_month" value="#{graveyardSearchBean.dateOfDeceaseFrom_month}" onchange="this.form.submit();">								
								<f:selectItems   value="#{graveyardSearchBean.monthSelectItemList}" />
						    </h:selectOneMenu>							
													
                           <h:selectOneMenu id="dateOfDeceaseFrom_day" value="#{graveyardSearchBean.dateOfDeceaseFrom_day}" >								
								<f:selectItems   value="#{graveyardSearchBean.dateOfDeceaseFromDaySelectItemList}" />
						    </h:selectOneMenu>							

							<h:message for="dateOfDeceaseFrom_year"  showSummary="false" showDetail="true" />
							
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
	  					   <h:inputText id="dateOfDeceaseTo_year" value="#{graveyardSearchBean.dateOfDeceaseTo_year}" 	maxlength="4"  size="4"></h:inputText>	
							
                           <h:selectOneMenu id="dateOfDeceaseTo_month" value="#{graveyardSearchBean.dateOfDeceaseTo_month}" onchange="this.form.submit();">								
								<f:selectItems   value="#{graveyardSearchBean.monthSelectItemList}" />
						    </h:selectOneMenu>							
													
                           <h:selectOneMenu id="dateOfDeceaseTo_day" value="#{graveyardSearchBean.dateOfDeceaseTo_day}" >								
								<f:selectItems   value="#{graveyardSearchBean.dateOfDeceaseToDaySelectItemList}" />
						    </h:selectOneMenu>							

							<h:message for="dateOfDeceaseTo_year"  showSummary="false" showDetail="true" />
							
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
								<h:outputText value="#{localizedStrings['se.agura.memorial']['database']}s"/>
							</h:outputLabel>
							<f:verbatim escape="false">
								<br />
							</f:verbatim>
                                                      
                           <h:selectOneMenu id="database" 
								value="#{graveyardSearchBean.databaseId}" onchange="this.form.submit();" 
								valueChangeListener="#{graveyardSearchBean.changeDatabase}"
								>								
								<f:selectItems 
									value="#{graveyardSearchBean.databaseSelectItemList}" />
							</h:selectOneMenu>
                           
                            
						</h:panelGroup>
						<!-- end of 3. row -->
						<!-- 4. row -->
						<!-- end of 4. row -->
					</h:panelGrid >


         
        <h:commandButton value="#{localizedStrings['se.agura.memorial']['help']}" action="#{graveyardSearchBean.helpinfo}" id="cbHelpLinkInfoBtn" />
         
        <h:commandButton value="#{localizedStrings['se.agura.memorial']['search']}" action="#{graveyardSearchBean.search}" id="cbSearchBtn" />
  
        <h:commandButton value="#{localizedStrings['se.agura.memorial']['clear']}" action="#{graveyardSearchBean.clear}" id="cbClearBtn" />

				
				<!-- here are error messages and warnings -->					
				<h:messages id="messageList" showSummary="false" showDetail="true" globalOnly="true"/> 			
				
				
				<!-- </h:form>  -->
				


				
				
				<f:verbatim>					
					<br />
				</f:verbatim>				
				<x:dataTable id="searchResultsTable" var="grave" 
					value="#{graveyardSearchBean.searchResults}" rows="10">
					<h:column>
						<!-- <h:outputText value="#{person.rowNr}." /> -->
                        <!-- <h:outputText value="[TODO]" /> -->
                        <h:outputText value="" />
					</h:column>
					<h:column>

						<h:outputText value="#{grave.lastName}" >
							<f:converter converterId="se.agura.memorial.util.EmptyStringConverter" /> 
						</h:outputText>
						
						<h:outputText value=", " />
						
						<h:outputText value="#{grave.firstName}" >
							<f:converter converterId="se.agura.memorial.util.EmptyStringConverter" /> 
						</h:outputText>						
						
					</h:column>
					<h:column>
						<h:outputText value="(" />
						<h:outputText value="#{grave.dateOfBirth}" >
							<f:converter converterId="se.agura.memorial.util.DateConverter" /> 
						</h:outputText>
					</h:column>
					<h:column>
						<h:outputText value=" - " />
					</h:column>
					<h:column>
						<h:outputText value="#{grave.dateOfDeath}" >
							<f:converter converterId="se.agura.memorial.util.DateConverter" /> 
						</h:outputText>
						<h:outputText value=")" />
					</h:column>
					<h:column>
					
						<h:commandLink action="#{graveyardSearchBean.showObiaturyPage}" immediate="true">							
							<h:outputText value=" #{localizedStrings['se.agura.memorial']['show']}" />							
							<f:param name="graveId" value="#{grave.graveId}" />
							<f:param name="databaseId" value="#{graveyardSearchBean.databaseId}" />														
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

				
				<f:verbatim>
					<br />
					<br />
				</f:verbatim>
								
				


				<x:dataTable id="helpTopics" var="mhi" 
					value="#{graveyardSearchBean.helpTopics}" rows="1">

			<h:column>

					<h:panelGrid columns="1" border="0" cellspacing="5" footerClass="SearchFormFooterClass" >

					<h:panelGroup>
						<h:outputText value="" ></h:outputText>
			        </h:panelGroup>
			        
					<h:panelGroup>
						<h:outputText value="#{localizedStrings['se.agura.memorial']['help_title1']}"  style="font-weight: bold; " ></h:outputText>
			        </h:panelGroup>
			
					<h:panelGroup>
						<h:outputText value="#{localizedStrings['se.agura.memorial']['help_line1_1']}"  styleClass="Text"></h:outputText>
			        </h:panelGroup>

					<h:panelGroup>
						<h:outputText value="#{localizedStrings['se.agura.memorial']['help_line1_2']}" ></h:outputText>
			        </h:panelGroup>
			        
					<h:panelGroup>
						<h:outputText value="#{localizedStrings['se.agura.memorial']['help_line1_3']}" ></h:outputText>
			        </h:panelGroup>

					<h:panelGroup>
						<h:outputText value="#{localizedStrings['se.agura.memorial']['help_line1_4']}" ></h:outputText>
			        </h:panelGroup>

					<h:panelGroup>
						<h:outputText value="#{localizedStrings['se.agura.memorial']['help_line1_5']}" ></h:outputText>
			        </h:panelGroup>

					<h:panelGroup>
						<h:outputText value="#{localizedStrings['se.agura.memorial']['help_title2']}" style="font-weight: bold; " ></h:outputText>
			        </h:panelGroup>
			
					<h:panelGroup>
						<h:outputText value="#{localizedStrings['se.agura.memorial']['help_line2_1']}" ></h:outputText>
			        </h:panelGroup>

					<h:panelGroup>
						<h:outputText value="#{localizedStrings['se.agura.memorial']['help_line2_2']}" ></h:outputText>
			        </h:panelGroup>

					<h:panelGroup>
						<h:outputText value="#{localizedStrings['se.agura.memorial']['help_line2_3']}" ></h:outputText>
			        </h:panelGroup>



					</h:panelGrid >

			</h:column>
					
				</x:dataTable>
				
				</wf:wfblock >
			</h:form>
		</ws:page>
	</f:view>
</jsp:root>