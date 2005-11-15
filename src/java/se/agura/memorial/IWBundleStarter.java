/*
/*
 * $Id: IWBundleStarter.java,v 1.15 2005/11/15 14:23:25 igors Exp $
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
 *  Last modified: $Date: 2005/11/15 14:23:25 $ by $Author: igors $
 * 
 * @author <a href="mailto:gummi@idega.com">Gudmundur Agust Saemundsson</a>
 * @version $Revision: 1.15 $
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

		DefaultViewNode searchMemorialNode = new DefaultViewNode("Search",memorialNode);
		searchMemorialNode.setJspUri(bundle.getJSPURI("search.jsp"));
		
		DefaultViewNode obituaryMemorialNode = new DefaultViewNode("Obituary",memorialNode);
		obituaryMemorialNode.setJspUri(bundle.getJSPURI("obituary.jsp"));

		DefaultViewNode obituaryCreateMemorialNode = new DefaultViewNode("ObituaryCreate",memorialNode);
		obituaryCreateMemorialNode.setJspUri(bundle.getJSPURI("obituarycreate.jsp"));

		DefaultViewNode obituaryPreviewMemorialNode = new DefaultViewNode("ObituaryPreview",memorialNode);
		obituaryPreviewMemorialNode.setJspUri(bundle.getJSPURI("obituarypreview.jsp"));
		
		DefaultViewNode newPersonMemorialNode1 = new DefaultViewNode("NewPerson",memorialNode);
		newPersonMemorialNode1.setJspUri(bundle.getJSPURI("newperson.jsp"));
	
		

		
		

	}
}
