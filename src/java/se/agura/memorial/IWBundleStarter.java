/*
 * $Id: IWBundleStarter.java,v 1.10 2005/06/13 14:10:08 gummi Exp $
 * Created on 15.5.2005
 *
 * Copyright (C) 2005 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package se.agura.memorial;


import com.idega.content.view.ContentViewManager;
import com.idega.core.view.DefaultViewNode;
import com.idega.core.view.ViewNode;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWBundleStartable;
import com.idega.idegaweb.include.GlobalIncludeManager;


/**
 * 
 *  Last modified: $Date: 2005/06/13 14:10:08 $ by $Author: gummi $
 * 
 * @author <a href="mailto:gummi@idega.com">Gudmundur Agust Saemundsson</a>
 * @version $Revision: 1.10 $
 */
public class IWBundleStarter implements IWBundleStartable {
	private static final String STYLE_SHEET_URL = "/style/memorial.css";
	private static final String ANOTHER_STYLE_SHEET_URL = "/style/style_image_l10n.css";
	private static final String BUNDLE_IDENTIFIER="se.agura.memorial";

	/**
	 * 
	 */
	public IWBundleStarter() {
	}

	/* (non-Javadoc)
	 * @see com.idega.idegaweb.IWBundleStartable#start(com.idega.idegaweb.IWBundle)
	 */
	public void start(IWBundle starterBundle) {
		addMemorialView(starterBundle);
		//Add the stylesheet:
		GlobalIncludeManager.getInstance().addBundleStyleSheet(BUNDLE_IDENTIFIER,STYLE_SHEET_URL);
		GlobalIncludeManager.getInstance().addBundleStyleSheet(BUNDLE_IDENTIFIER,ANOTHER_STYLE_SHEET_URL);
	
	}



	/* (non-Javadoc)
	 * @see com.idega.idegaweb.IWBundleStartable#stop(com.idega.idegaweb.IWBundle)
	 */
	public void stop(IWBundle starterBundle) {
	}
	
	public void addMemorialView(IWBundle bundle){
		ContentViewManager cViewManager = ContentViewManager.getInstance(bundle.getApplication());
		ViewNode contentNode = cViewManager.getContentNode();
		
		DefaultViewNode memorialNode = new DefaultViewNode("memorial",contentNode);
		memorialNode.setJspUri(bundle.getJSPURI("search.jsp"));
		
		//DefaultViewNode searchMemorialNode = new DefaultViewNode("search",memorialNode);
		//String jspUri = bundle.getJSPURI("search_component.jsp");
		//searchMemorialNode.setJspUri(jspUri);
		
		DefaultViewNode obituaryNode = new DefaultViewNode("obituary",memorialNode);
		obituaryNode.setJspUri(bundle.getJSPURI("search.jsp"));
		
		DefaultViewNode obituaryNodeSearch = new DefaultViewNode("search",obituaryNode);
		obituaryNodeSearch.setJspUri(bundle.getJSPURI("search.jsp"));
		obituaryNodeSearch.setVisibleInMenus(false);
		
		DefaultViewNode obituaryNodeCreate = new DefaultViewNode("create",obituaryNode);
		obituaryNodeCreate.setJspUri(bundle.getJSPURI("create_obituary.jsp"));
		obituaryNodeCreate.setVisibleInMenus(false);
		
		DefaultViewNode obituaryNodeEdit = new DefaultViewNode("edit",obituaryNode);
		obituaryNodeEdit.setJspUri(bundle.getJSPURI("create_obituary.jsp"));
		obituaryNodeEdit.setVisibleInMenus(false);
		
		DefaultViewNode obituaryNodeView = new DefaultViewNode("view",obituaryNode);
		obituaryNodeView.setJspUri(bundle.getJSPURI("obituary.jsp"));
		obituaryNodeView.setVisibleInMenus(false);
		
		DefaultViewNode obituaryNodePreview = new DefaultViewNode("preview",obituaryNode);
		obituaryNodePreview.setJspUri(bundle.getJSPURI("obituary.jsp"));
		obituaryNodePreview.setVisibleInMenus(false);
		
		
		DefaultViewNode graveNode = new DefaultViewNode("grave",memorialNode);
		graveNode.setJspUri(bundle.getJSPURI("new_person.jsp"));
		
		DefaultViewNode graveNodeCreate = new DefaultViewNode("create",graveNode);
		graveNodeCreate.setJspUri(bundle.getJSPURI("new_person.jsp"));
		graveNodeCreate.setVisibleInMenus(false);
		
		DefaultViewNode graveNodeEdit = new DefaultViewNode("edit",graveNode);
		graveNodeEdit.setJspUri(bundle.getJSPURI("new_person.jsp"));
		graveNodeEdit.setVisibleInMenus(false);
		

	}
}
