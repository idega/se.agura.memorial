package se.agura.memorial.search.presentation;

import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

public class ObituaryItemTag extends UIComponentTag {

	
	private String title;
	
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
		return "WFBlock";
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void release() {      
		super.release();      
		title = null ;
	}

	protected void setProperties(UIComponent component) {      
		super.setProperties(component);
		if (component != null) {
			if(title!=null){
				if(isValueReference(title)){
					ValueBinding vb = getFacesContext().getApplication().createValueBinding(title);
					component.setValueBinding("title", vb);
				} else {
					component.getAttributes().put("title", title);
				}
			}
		}
	}

	
}
