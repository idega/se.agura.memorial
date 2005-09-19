package se.agura.memorial.obituary.presentation;


import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputText;
import com.idega.content.bean.ContentItemBean;
import com.idega.content.presentation.ContentItemViewer;
import com.idega.webface.convert.WFTimestampConverter;
import se.agura.memorial.obituary.data.ObituaryImageItemBean;

public class ObituaryImageItemViewer extends ContentItemViewer {

	private final static String FIELD_IMAGE = ObituaryImageItemBean.FIELDNAME_IMAGE;
	private final static String FIELD_CREATION_DATE = ContentItemBean.FIELDNAME_CREATION_DATE;
	private final static String FIELD_DESCRIPTION = ObituaryImageItemBean.FIELDNAME_DESCRIPTION;
	
	private final static String[] FIELD_ARRAY = new String[] {FIELD_IMAGE,FIELD_CREATION_DATE,FIELD_DESCRIPTION};
	private final static String facetIdPrefix = "image_";
	private final static String styleClassPrefix = "image_";

	
	public ObituaryImageItemViewer() {
		super();
		this.setStyleClass("image_item");
	}
	
	
	
	public String[] getViewerFieldNames(){
		return FIELD_ARRAY;
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
		if(FIELD_IMAGE.equals(fieldName)){
			return new HtmlGraphicImage();
		} else {
			return new HtmlOutputText();
		}
	}
	
	protected void initializeContent() {	
		super.initializeContent();
		((HtmlOutputText)getFieldViewerComponent(FIELD_CREATION_DATE)).setConverter(new WFTimestampConverter());
	}
	
}
