/*
 * $Id: GraveDetailsTag.java,v 1.1 2005/07/01 12:42:37 gummi Exp $
 * Created on 30.6.2005
 *
 * Copyright (C) 2005 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package se.agura.memorial.obituary.presentation;

import org.apache.myfaces.taglib.UIComponentTagBase;


/**
 * 
 *  Last modified: $Date: 2005/07/01 12:42:37 $ by $Author: gummi $
 * 
 * @author <a href="mailto:gummi@idega.com">Gudmundur Agust Saemundsson</a>
 * @version $Revision: 1.1 $
 */
public class GraveDetailsTag extends UIComponentTagBase {

	/**
	 * 
	 */
	public GraveDetailsTag() {
		super();
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getComponentType()
	 */
	public String getComponentType() {
		return "GraveDetails";
	}

	/* (non-Javadoc)
	 * @see javax.faces.webapp.UIComponentTag#getRendererType()
	 */
	public String getRendererType() {
		return null;
	}
}
