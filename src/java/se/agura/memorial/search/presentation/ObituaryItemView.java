package se.agura.memorial.search.presentation;


import javax.faces.component.UIComponent;

import com.idega.content.bean.ContentItemBean;



public abstract class ObituaryItemView extends UIComponent{
	
	private final static String FIELD_CREATION_DATE = ContentItemBean.FIELDNAME_CREATION_DATE;
	private final static String facetIdPrefix = "image_";
	private final static String styleClassPrefix = "image_";

	
	/**
	 * 
	 */
//	public ImageItemViewer() {
//		super();
//		this.setStyleClass("image_item");
//	}
	
	
	
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
//		super.initializeContent();
//		((HtmlOutputText)getFieldViewerComponent(FIELD_CREATION_DATE)).setConverter(new WFTimestampConverter());
	}
	public void loadContentItem(String contentItem){
		//
	}
}
