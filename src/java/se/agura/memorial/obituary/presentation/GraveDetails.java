/*
 * $Id: GraveDetails.java,v 1.3 2005/07/05 14:38:36 igors Exp $
 * Created on 29.6.2005
 *
 * Copyright (C) 2005 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package se.agura.memorial.obituary.presentation;

//import java.rmi.RemoteException;
//import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.convert.Converter;
import javax.faces.el.ValueBinding;
//import com.idega.business.IBOLookup;
//import com.idega.business.IBOLookupException;
import com.idega.idegaweb.IWBundle;
//import com.idega.idegaweb.UnavailableIWContext;
//import com.idega.presentation.IWContext;
import com.idega.webface.WFContainer;
import com.idega.webface.WFUtil;
//import se.agura.memorial.search.api.Grave;
//import se.agura.memorial.search.api.ObituarySearch;
//import se.agura.memorial.search.business.SearchImplBroker;
import se.agura.memorial.search.presentation.DateConverter;
import se.agura.memorial.search.presentation.EmptyStringConverter;
import se.agura.memorial.util.MemorialUtil;


/**
 * 
 *  Last modified: $Date: 2005/07/05 14:38:36 $ by $Author: igors $
 *  
 * @author <a href="mailto:gummi@idega.com">Gudmundur Agust Saemundsson</a>
 * @version $Revision: 1.3 $
 */
public class GraveDetails extends WFContainer {

	/**
	 * 
	 */
	public GraveDetails() {
		super();
	}

//	/* (non-Javadoc)
//	 * @see javax.faces.component.UIComponent#getFamily()
//	 */
//	public String getFamily() {
//		return null;
//	}
	
	
	protected void initializeContent() {
		String managedBean = "obituaryInformationDisplayBackingBean";

		IWBundle bundle = MemorialUtil.getBundle();
		
		addLabelAndValue(bundle,"first_name","First name",WFUtil.createValueBinding("#{" + managedBean+".grave.firstName" + "}"),new EmptyStringConverter(),this,"gravedetail");
		addLabelAndValue(bundle,"surname","Last name",WFUtil.createValueBinding("#{" + managedBean+".grave.lastName" + "}"),new EmptyStringConverter(),this,"gravedetail");
		addLabelAndValue(bundle,"date_of_birth","Date of birth",WFUtil.createValueBinding("#{" + managedBean+".grave.dateOfBirth" + "}"),new DateConverter(),this,"gravedetail");
		addLabelAndValue(bundle,"date_of_decease","Date of decease",WFUtil.createValueBinding("#{" + managedBean+".grave.dateOfDeath" + "}"),new DateConverter(),this,"gravedetail");
		addLabelAndValue(bundle,"burial_date","Burial date",WFUtil.createValueBinding("#{" + managedBean+".grave.dateOfBurial" + "}"),new DateConverter(),this,"gravedetail");

		
		WFContainer hometownContainer = new WFContainer();
		hometownContainer.setStyleClass("label_value_group");
		getChildren().add(hometownContainer);
		
		HtmlOutputText hometown = new HtmlOutputText();
		hometown.setStyleClass("sub_headline");
		bundle.getLocalizedUIComponent("home_region",hometown,"Home region");
		hometownContainer.getChildren().add(hometown);
		
		
		addLabelAndValue(bundle,"county","County",WFUtil.createValueBinding("#{" + managedBean+".grave.hometown" + "}"),new EmptyStringConverter(),hometownContainer,"gravedetail");
		addLabelAndValue(bundle,"commune","Commune",WFUtil.createValueBinding("#{" + managedBean+".grave.hometown" + "}"),new EmptyStringConverter(),hometownContainer,"gravedetail");
		addLabelAndValue(bundle,"parish","Parish",WFUtil.createValueBinding("#{" + managedBean+".grave.hometown" + "}"),new EmptyStringConverter(),hometownContainer,"gravedetail");

		
		WFContainer graveInfoContainer = new WFContainer();
		graveInfoContainer.setStyleClass("grave_info");
		getChildren().add(graveInfoContainer);
		
		HtmlOutputText graveInfo = new HtmlOutputText();
		graveInfo.setStyleClass("sub_headline");
		bundle.getLocalizedUIComponent("grave_information",graveInfo,"Grave information");
		graveInfoContainer.getChildren().add(graveInfo);
		
		addLabelAndValue(bundle,"grave_no","Grave number",WFUtil.createValueBinding("#{" + managedBean+".grave.graveInfo.graveNumber" + "}"),new EmptyStringConverter(),graveInfoContainer,"gravedetail");
		addLabelAndValue(bundle,"block","Block",WFUtil.createValueBinding("#{" + managedBean+".grave.graveInfo.block" + "}"),new EmptyStringConverter(),graveInfoContainer,"gravedetail");
		addLabelAndValue(bundle,"department","Department",WFUtil.createValueBinding("#{" + managedBean+".grave.graveInfo.department" + "}"),new EmptyStringConverter(),graveInfoContainer,"gravedetail");
		addLabelAndValue(bundle,"graveyard","Graveyard",WFUtil.createValueBinding("#{" + managedBean+".grave.graveInfo.cemetery" + "}"),new EmptyStringConverter(),graveInfoContainer,"gravedetail");
		addLabelAndValue(bundle,"other_burial_place","Other burial place",WFUtil.createValueBinding("#{" + managedBean+".grave.graveInfo.otherBurialPlace" + "}"),new EmptyStringConverter(),graveInfoContainer,"gravedetail",WFUtil.createValueBinding("#{" + managedBean+".grave.graveInfo.otherBurialPlace!=null}"));
		
	}
	
	/**
	 * 
	 * @param labelLocalizationKey
	 * @param labelDefaultValue
	 * @param value
	 * @param parent
	 * @param styleClass  Style class of a div containing the label and value.  Label and value will have the style class 'label' and 'value'
	 */
	protected void addLabelAndValue(IWBundle bundle, String labelLocalizationKey, String labelDefaultValue, ValueBinding value, Converter valueConverter, UIComponent parent, String styleClass){		
		addLabelAndValue(bundle,labelLocalizationKey,labelDefaultValue,value,valueConverter,parent,styleClass,null);
	}
	
	/**
	 * 
	 * @param labelLocalizationKey
	 * @param labelDefaultValue
	 * @param value
	 * @param parent
	 * @param styleClass  Style class of a div containing the label and value.  Label and value will have the style class 'label' and 'value'
	 * @param rendered
	 */
	protected void addLabelAndValue(IWBundle bundle, String labelLocalizationKey, String labelDefaultValue, ValueBinding value, Converter valueConverter, UIComponent parent, String styleClass, ValueBinding rendered){		
		HtmlOutputText labelText = createLabelObject(bundle, labelLocalizationKey,labelDefaultValue);
		HtmlOutputText valueText = createValueObject(value, valueConverter);
		WFContainer labelValuePair = createLabelValuePair(bundle,styleClass, labelText, valueText);
		if(rendered != null){
			labelValuePair.setValueBinding("rendered",rendered);
		}
		parent.getChildren().add(labelValuePair);
	}

	/**
	 * @param styleClass
	 * @param labelText
	 * @param valueText
	 * @return
	 */
	protected WFContainer createLabelValuePair(IWBundle bundle, String styleClass, HtmlOutputText labelText, HtmlOutputText valueText) {
		WFContainer labelValuePair = new WFContainer();
		labelValuePair.setStyleClass(styleClass);
		labelValuePair.getChildren().add(labelText);
		
		HtmlOutputText seperator = new HtmlOutputText();
		seperator.setStyleClass("seperator");
		bundle.getLocalizedUIComponent("grave_details.label-value.seperator",seperator,": ");
		labelValuePair.getChildren().add(seperator);
		
		labelValuePair.getChildren().add(valueText);
		return labelValuePair;
	}

	/**
	 * @param labelLocalizationKey
	 * @param value
	 * @param valueConverter
	 * @return
	 */
	protected HtmlOutputText createValueObject(ValueBinding value, Converter valueConverter) {
		HtmlOutputText valueText = new HtmlOutputText();
		valueText.setStyleClass("value");
		valueText.setValueBinding("value",value);
		if(valueConverter != null){
			valueText.setConverter(valueConverter);
		}
		return valueText;
	}

	/**
	 * @param bundle
	 * @param labelLocalizationKey
	 * @return
	 */
	private HtmlOutputText createLabelObject(IWBundle bundle, String labelLocalizationKey, String labelDefaultValue) {
		HtmlOutputText labelText = new HtmlOutputText();
		bundle.getLocalizedUIComponent(labelLocalizationKey,labelText,labelDefaultValue);
		labelText.setStyleClass("label");
		return labelText;
	}

}
