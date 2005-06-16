package se.agura.memorial.search.presentation;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import se.agura.memorial.search.api.Grave;


import com.idega.content.bean.ContentItemBean;
import com.idega.data.IDOStoreException;
import com.idega.xml.XMLDocument;
import com.idega.xml.XMLElement;
import com.idega.xml.XMLException;
import com.idega.xml.XMLOutput;
import com.idega.xml.XMLParser;


public abstract class ObituaryItemBean extends ContentItemBean {
	
    private Integer obituaryId;
	private String obituaryText; 
	private String presonPicturePath; 
	private String gravePicturePath;	
	private String uniqueAvenyId;
    private Integer graveId;
	
	private String FIELDNAME_BODY="";
	private static final String idegaXMLNameSpace="";
    private Grave grave;	
	
	
	
	
	public String getAsXML() throws IOException, XMLException {


		XMLParser builder = new XMLParser();
		XMLDocument bodyDoc = null;
		//prettifyBody();
		bodyDoc = builder.parse(new ByteArrayInputStream(getBody().getBytes()));
		XMLElement bodyElement = bodyDoc.getRootElement();
		
		//XMLElement root = new XMLElement("article",idegaXMLNameSpace);
		XMLElement root = new XMLElement("article");		
//		XMLElement contentLanguage = new XMLElement(FIELDNAME_CONTENT_LANGUAGE,idegaXMLNameSpace).setText(getContentLanguage());
//		XMLElement headline = new XMLElement(FIELDNAME_HEADLINE,idegaXMLNameSpace).setText(getHeadline());
//		XMLElement teaser = new XMLElement(FIELDNAME_TEASER,idegaXMLNameSpace).setText(getTeaser());
//		XMLElement author = new XMLElement(FIELDNAME_AUTHOR,idegaXMLNameSpace).setText(getAuthor());
//		XMLElement source = new XMLElement(FIELDNAME_SOURCE,idegaXMLNameSpace).setText(getSource());
//		XMLElement articleComment = new XMLElement("article_comment",idegaXMLNameSpace).setText(getComment());

//		XMLElement body = new XMLElement(FIELDNAME_BODY,idegaXMLNameSpace).addContent(bodyElement);
		XMLElement body = new XMLElement(FIELDNAME_BODY).addContent(bodyElement);

		
//		root.addContent(contentLanguage);
//		root.addContent(headline);
//		root.addContent(teaser);
//		root.addContent(body);
//		root.addContent(author);
//		root.addContent(source);
//		root.addContent(articleComment);
		XMLDocument doc = new XMLDocument(root);
		XMLOutput outputter = new XMLOutput();
		return outputter.outputString(doc);		
	}

	
	
	public ObituaryItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getBody() {
		String str = null;
		return str;
	}
	



	public void setBody(Grave grave) {
		this.grave = grave;
	}
	



	public Integer getGraveId() {
		return graveId;
	}
	



	public void setGraveId(Integer graveId) {
		this.graveId = graveId;
	}
	



	public String getGravePicturePath() {
		return gravePicturePath;
	}
	



	public void setGravePicturePath(String gravePicturePath) {
		this.gravePicturePath = gravePicturePath;
	}
	



	public Integer getObituaryId() {
		return obituaryId;
	}
	



	public void setObituaryId(Integer obituaryId) {
		this.obituaryId = obituaryId;
	}
	



	public String getObituaryText() {
		return obituaryText;
	}
	



	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}
	



	public String getPresonPicturePath() {
		return presonPicturePath;
	}
	



	public void setPresonPicturePath(String presonPicturePath) {
		this.presonPicturePath = presonPicturePath;
	}
	



	public String getUniqueAvenyId() {
		return uniqueAvenyId;
	}
	



	public void setUniqueAvenyId(String uniqueAvenyId) {
		this.uniqueAvenyId = uniqueAvenyId;
	}

	
	
	public void store() throws IDOStoreException{
		boolean storeOk = true;
		//clearErrorKeys();

//		if (getHeadline().trim().equals("")) {
//			addErrorKey(KEY_ERROR_HEADLINE_EMPTY);
//			storeOk = false;
//		}
//		if (getBody().trim().equals("")) {
//			addErrorKey(KEY_ERROR_BODY_EMPTY);
//			storeOk = false;
//		}
		
		if(storeOk){
//			try {
//				IWUserContext iwuc = IWContext.getInstance();
//				IWSlideSession session = (IWSlideSession)IBOLookup.getSessionInstance(iwuc,IWSlideSession.class);
//			    WebdavRootResource rootResource = session.getWebdavRootResource();
//	
//
//				//Setting the path for creating new file or creating localized version or updating existing file
//				String filePath=getResourcePath();
//				String articleFolderPath="";//getArticlePath();
//				if(articleFolderPath!=null) {
//					filePath=articleFolderPath+"/"+"";//getArticleName();
//				}else {
//					filePath="";//getArticleResourcePath();
//					articleFolderPath = "";//getArticlePath();
//				}
//		
//				boolean hadToCreate = session.createAllFoldersInPath(articleFolderPath);
//	
//				if(hadToCreate){
//					String fixedFolderURL = session.getURI(articleFolderPath);
//					rootResource.proppatchMethod(fixedFolderURL,"PROPERTY_CONTENT_TYPE","LocalizedFile",true);
//				}
//				else{
//					rootResource.proppatchMethod(articleFolderPath,"PROPERTY_CONTENT_TYPE","LocalizedFile",true);
//				}
				
				
				String article = "";//getAsXML();
	//			System.out.println(article);
				
				//Conflict fix: uri for creating but path for updating
				//Note! This is a patch to what seems to be a bug in WebDav
				//Apparently in verion below works in some cases and the other in other cases.
				//Seems to be connected to creating files in folders created in same tomcat session or similar
				//not quite clear...
				
//				if(rootResource.putMethod(filePath,article)){
//					rootResource.proppatchMethod(filePath,"PROPERTY_CONTENT_TYPE","ARTICLE_FILENAME_SCOPE",true);
//				}
//				else{
//					String fixedURL = session.getURI(filePath);
//					rootResource.putMethod(fixedURL,article);
//					rootResource.proppatchMethod(fixedURL,"PROPERTY_CONTENT_TYPE","ARTICLE_FILENAME_SCOPE",true);
//				}
//				
//				rootResource.close();
//				try {
//					load(filePath);
//				}
//				catch (Exception e) {
//					e.printStackTrace();
//				}
	
//			}
//			catch (IOException e1) {
//				storeOk = false;
//				e1.printStackTrace();
//			}
//			catch (XMLException e) {
//				storeOk = false;
//				e.printStackTrace();
//			}
	}

		if (storeOk) {
			if (getRequestedStatus() != null) {
				setStatus(getRequestedStatus());
				setRequestedStatus(null);
			}
//		}else {
//			throw new ArticleStoreException();
//		}
	}

	}
	

}
	

		
	
