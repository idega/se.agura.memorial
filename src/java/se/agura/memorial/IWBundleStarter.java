/*
/*
 * $Id: IWBundleStarter.java,v 1.14 2005/11/02 14:48:58 igors Exp $
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
 *  Last modified: $Date: 2005/11/02 14:48:58 $ by $Author: igors $
 * 
 * @author <a href="mailto:gummi@idega.com">Gudmundur Agust Saemundsson</a>
 * @version $Revision: 1.14 $
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
		memorialNode.setJspUri(bundle.getJSPURI("ws_search.jsp"));

		DefaultViewNode searchMemorialNode = new DefaultViewNode("Search",memorialNode);
		searchMemorialNode.setJspUri(bundle.getJSPURI("search.jsp"));
		
		DefaultViewNode obituaryMemorialNode = new DefaultViewNode("Obituary",memorialNode);
		obituaryMemorialNode.setJspUri(bundle.getJSPURI("obituary.jsp"));

		DefaultViewNode obituaryCreateMemorialNode = new DefaultViewNode("Obituary_Create",memorialNode);
		obituaryCreateMemorialNode.setJspUri(bundle.getJSPURI("obituary_create.jsp"));

		DefaultViewNode obituaryPreviewMemorialNode = new DefaultViewNode("Obituary_Preview",memorialNode);
		obituaryPreviewMemorialNode.setJspUri(bundle.getJSPURI("obituary_preview.jsp"));
		
		DefaultViewNode newPersonMemorialNode1 = new DefaultViewNode("New_Person",memorialNode);
		newPersonMemorialNode1.setJspUri(bundle.getJSPURI("new_person.jsp"));
	
		

		
		

	}
}
