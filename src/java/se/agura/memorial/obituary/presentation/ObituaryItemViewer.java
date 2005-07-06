package se.agura.memorial.obituary.presentation;


import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.convert.Converter;
import javax.faces.el.ValueBinding;

import se.agura.memorial.search.presentation.EmptyStringConverter;
import se.agura.memorial.util.MemorialUtil;

import com.idega.content.bean.ContentItemBean;
import com.idega.idegaweb.IWBundle;
import com.idega.webface.WFContainer;
import com.idega.webface.WFUtil;



public abstract class ObituaryItemViewer extends UIComponent{
	
	private final static String FIELD_CREATION_DATE = ContentItemBean.FIELDNAME_CREATION_DATE;
	private final static String facetIdPrefix = "image_";
	private final static String styleClassPrefix = "image_";


	public ObituaryItemViewer() {
		super();
	}

	
	public String[] getViewerFieldNames(){
//		return FIELD_ARRAY;
		return null;
	}
	
	/**
	 * @return Returns the facetIdPrefix.
	 */
	protected String getFacetIdPrefix() {
		return facetIdPrefix;
	}

	/**
	 * @return Returns the styleClassPrefix.
	 */
	protected String getDefaultStyleClassPrefix() {
		return styleClassPrefix;
	}
	
	protected UIComponent createFieldComponent(String fieldName){
//		if(FIELD_IMAGE.equals(fieldName)){
//			return new HtmlGraphicImage();
//		} else {
//			return new HtmlOutputText();
//		}
	    return null;	
	}
	
	protected void initializeContent() {
		String managedBean = "ObituaryItemBean";
		IWBundle bundle = MemorialUtil.getBundle();
		addLabelAndValue(bundle,"obituaryText","Obituary gg",WFUtil.createValueBinding("#{" + managedBean+".obituaryText" + "}"),new EmptyStringConverter(),this,"obituarydetail");
		
	
	
	}
	public void loadContentItem(String contentItem){
		//
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
