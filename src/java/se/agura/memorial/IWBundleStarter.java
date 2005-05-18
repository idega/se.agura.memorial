/*
 * $Id: IWBundleStarter.java,v 1.1 2005/05/18 07:49:51 gummi Exp $
 * Created on 15.5.2005
 *
 * Copyright (C) 2005 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package se.agura.memorial;


import com.idega.content.view.ContentViewManager;
import com.idega.core.uri.IWActionURIManager;
import com.idega.core.view.DefaultViewNode;
import com.idega.core.view.ViewNode;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWBundleStartable;
import com.idega.idegaweb.include.GlobalIncludeManager;


/**
 * 
 *  Last modified: $Date: 2005/05/18 07:49:51 $ by $Author: gummi $
 * 
 * @author <a href="mailto:gummi@idega.com">Gudmundur Agust Saemundsson</a>
 * @version $Revision: 1.1 $
 */
public class IWBundleStarter implements IWBundleStartable {
	private static final String STYLE_SHEET_URL = "/style/memorial.css";
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
		addArticleIWActionURIHandler();
		
		addArticleViews(starterBundle);
		//Add the stylesheet:
		GlobalIncludeManager.getInstance().addBundleStyleSheet(BUNDLE_IDENTIFIER,STYLE_SHEET_URL);
	}

	/**
	 * 
	 */
	private void addArticleIWActionURIHandler() {
		IWActionURIManager manager = IWActionURIManager.getInstance();
		
//		//so it is called before contenthandler
//		manager.registerHandler(0,new ArticleActionURIHandler());
		
	}

	/* (non-Javadoc)
	 * @see com.idega.idegaweb.IWBundleStartable#stop(com.idega.idegaweb.IWBundle)
	 */
	public void stop(IWBundle starterBundle) {
	}
	
	public void addArticleViews(IWBundle bundle){
		ContentViewManager cViewManager = ContentViewManager.getInstance(bundle.getApplication());
		ViewNode contentNode = cViewManager.getContentNode();
		
		DefaultViewNode memorialNode = new DefaultViewNode("memorial",contentNode);
		memorialNode.setJspUri(bundle.getJSPURI("ws_search.jsp"));
		DefaultViewNode searchMemorialNode = new DefaultViewNode("search",memorialNode);
		String jspUri = bundle.getJSPURI("ws_search.jsp");
		memorialNode.setJspUri(jspUri);
		
	}
}
