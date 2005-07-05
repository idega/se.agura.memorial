package se.agura.memorial.obituary.presentation;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.convert.Converter;
import javax.faces.el.ValueBinding;
import com.idega.idegaweb.IWBundle;
import com.idega.webface.WFContainer;
import com.idega.webface.WFUtil;
import se.agura.memorial.search.presentation.EmptyStringConverter;
import se.agura.memorial.util.MemorialUtil;


/**
 * 
 *  Last modified: $Date: 2005/07/05 09:35:42 $ by $Author: igors $
 * 
 */
public class ObituaryDetails extends WFContainer {

	public ObituaryDetails() {
		super();
	}

	
	protected void initializeContent() {
		String managedBean = "ObituaryItemBean";

		IWBundle bundle = MemorialUtil.getBundle();
		
		addLabelAndValue(bundle,"obituaryText","Obituary gg",WFUtil.createValueBinding("#{" + managedBean+".getObituaryText()" + "}"),new EmptyStringConverter(),this,"obituarydetail");

		
//		WFContainer hometownContainer = new WFContainer();
//		hometownContainer.setStyleClass("label_value_group");
//		getChildren().add(hometownContainer);
//		
//		HtmlOutputText hometown = new HtmlOutputText();
//		hometown.setStyleClass("sub_headline");
//		bundle.getLocalizedUIComponent("home_region",hometown,"Home region");
//		hometownContainer.getChildren().add(hometown);
//		
//		
//		addLabelAndValue(bundle,"county","County",WFUtil.createValueBinding("#{" + managedBean+".grave.hometown" + "}"),new EmptyStringConverter(),hometownContainer,"gravedetail");

		
	}
	

	protected void addLabelAndValue(IWBundle bundle, String labelLocalizationKey, String labelDefaultValue, ValueBinding value, Converter valueConverter, UIComponent parent, String styleClass){		
		addLabelAndValue(bundle,labelLocalizationKey,labelDefaultValue,value,valueConverter,parent,styleClass,null);
	}
	
	protected void addLabelAndValue(IWBundle bundle, String labelLocalizationKey, String labelDefaultValue, ValueBinding value, Converter valueConverter, UIComponent parent, String styleClass, ValueBinding rendered){		
		HtmlOutputText labelText = createLabelObject(bundle, labelLocalizationKey,labelDefaultValue);
		HtmlOutputText valueText = createValueObject(value, valueConverter);
		WFContainer labelValuePair = createLabelValuePair(bundle,styleClass, labelText, valueText);
		if(rendered != null){
			labelValuePair.setValueBinding("rendered",rendered);
		}
		parent.getChildren().add(labelValuePair);
	}

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

	protected HtmlOutputText createValueObject(ValueBinding value, Converter valueConverter) {
		HtmlOutputText valueText = new HtmlOutputText();
		valueText.setStyleClass("value");
		valueText.setValueBinding("value",value);
		if(valueConverter != null){
			valueText.setConverter(valueConverter);
		}
		return valueText;
	}

	private HtmlOutputText createLabelObject(IWBundle bundle, String labelLocalizationKey, String labelDefaultValue) {
		HtmlOutputText labelText = new HtmlOutputText();
		bundle.getLocalizedUIComponent(labelLocalizationKey,labelText,labelDefaultValue);
		labelText.setStyleClass("label");
		return labelText;
	}

}
