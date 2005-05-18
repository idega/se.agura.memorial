<?xml version="1.0"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:ws="http://xmlns.idega.com/com.idega.workspace"
        xmlns:article="http://xmlns.idega.com/com.idega.block.article" 
        xmlns:builder="http://xmlns.idega.com/com.idega.builder"
version="1.2">
<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
<f:view>
        <ws:page id="gravesearch">
                <h:form id="gravesearchfrom">
                		<f:subview >
                			<jsp:include page="search_component.jsp">
							<jsp:param name="wspage" value="true" />
						</jsp:include>
                		</f:subview >
                </h:form>
        </ws:page>
</f:view>
</jsp:root>