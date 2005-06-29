package se.agura.memorial.search.presentation;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.webdav.lib.PropertyName;
import org.apache.webdav.lib.WebdavException;
import org.apache.webdav.lib.WebdavResource;

import se.agura.memorial.search.api.Grave;

import com.idega.business.IBOLookup;
import com.idega.content.bean.ContentItemBean;
import com.idega.data.IDOEntity;
import com.idega.data.IDOStoreException;
import com.idega.idegaweb.IWUserContext;
import com.idega.presentation.IWContext;
import com.idega.slide.business.IWSlideSession;
import com.idega.slide.util.WebdavRootResource;
import com.idega.xml.XMLDocument;
import com.idega.xml.XMLElement;
import com.idega.xml.XMLException;
import com.idega.xml.XMLNamespace;
import com.idega.xml.XMLOutput;
import com.idega.xml.XMLParser;











public class ObituaryItemBean extends ContentItemBean implements IDOEntity {
	
    private Integer obituaryId;
	private String obituaryText; 
	private String presonPicturePath; 
	private String gravePicturePath;	
	private String uniqueAvenyId;
    private Integer graveId;
	
	private String FIELDNAME_BODY="";

	public final static String ARTICLE_FILENAME_SCOPE = "obituary";
	public final static String CONTENT_TYPE = "ContentType";
	public static final PropertyName PROPERTY_CONTENT_TYPE = new PropertyName("IW:",CONTENT_TYPE);
	
	XMLNamespace idegaXMLNameSpace = new XMLNamespace("http://xmlns.agura.se/memorial");
	
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
		//super();
		// TODO Auto-generated constructor stub
	}



	public String getBody() {
		String str = "TEST TEXT";
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

	public String getArticlePath(){
//		return "files/cms/obituary/1/30130000024.obituary";
		return "";		
	}
	
	public String getArticleName(){
		return "en.xml";
	}
	
	public void store() throws IDOStoreException,WebdavException{
		boolean storeOk = true;
        
		if(storeOk){
			try {
				
				IWUserContext iwuc = IWContext.getInstance();
				IWSlideSession session = (IWSlideSession)IBOLookup.getSessionInstance(iwuc,IWSlideSession.class);
				WebdavRootResource rootResource = session.getWebdavRootResource();
//				WebdavResource rootResource = session.getWebdavResource("");

				//Setting the path for creating new file or creating localized version or updating existing file
				String filePath=getResourcePath();
				String articleFolderPath= "/files/cms/obituary/test/"+((Math.random()*10000000)+10000000);//getArticlePath();   //Some random obituary id for testing

				filePath=articleFolderPath+"/"+getArticleName();
		
				boolean hadToCreate = false;//session.createAllFoldersInPath(articleFolderPath);
	
				if(hadToCreate){
					String fixedFolderURL = session.getURI(articleFolderPath);
//					rootResource.proppatchMethod(fixedFolderURL,PROPERTY_CONTENT_TYPE,"LocalizedFile",true);
					 rootResource.proppatchMethod(fixedFolderURL,"","LocalizedFile",true);					
				}
				else{
//					rootResource.proppatchMethod(articleFolderPath,PROPERTY_CONTENT_TYPE,"LocalizedFile",true);
					rootResource.proppatchMethod(articleFolderPath,"","LocalizedFile",true);
				}

				
				String article = "aaa";//getAsXML();
				boolean test=rootResource.exists();
				String path = rootResource.getPath()+filePath;
                 
				if(rootResource.putMethod(filePath,article)){
					rootResource.proppatchMethod(filePath,PROPERTY_CONTENT_TYPE,ARTICLE_FILENAME_SCOPE,true);			
				} 
				else{
					String fixedURL = session.getURI(filePath);
					rootResource.putMethod(fixedURL,article);
					rootResource.proppatchMethod(fixedURL,PROPERTY_CONTENT_TYPE,ARTICLE_FILENAME_SCOPE,true);
				}
		
				rootResource.close();   
	
			}
			catch (IOException e1) {
				storeOk = false;
				e1.printStackTrace();
			}
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
		}else {
			//throw new ArticleStoreException();
		}
	

	}





	public String[] getContentFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}







	public void setDatasource(String arg0) {
		// TODO Auto-generated method stub
		
	}



	public String getContentItemPrefix() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
	

		
	
