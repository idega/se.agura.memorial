package se.agura.memorial.obituary.presentation;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

public class ObituaryItemTag extends UIComponentTag {

	
	private String title;
	private String contentItem;
	private String obituaryText;
	
	/**
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
		return null;
	}
		
	/**
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		return "ObituaryDetails";
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}

	public void release() {      
		super.release();      
		title = null ;
	}

    protected void setContentItem(String contentItem){
		
		this.contentItem = contentItem;
    }
	
    protected String getContentItem(){
		
		
		return contentItem;
    }	
	
	protected void setProperties(UIComponent component) {
		super.setProperties(component);
		
		if (component != null) {
			if(contentItem!=null){
				if(isValueReference(contentItem)){
					ValueBinding vb = getFacesContext().getApplication().createValueBinding(contentItem);
					component.setValueBinding(contentItem, vb);
				}
				else {
					ObituaryItemView viewer = (ObituaryItemView)component;
					viewer.loadContentItem(contentItem);  //We have no other way to resolve String variable in this case.
				}
			}
		}
	}
	
	
}
