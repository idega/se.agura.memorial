package se.agura.memorial.search.presentation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

import org.apache.webdav.lib.PropertyName;
import org.apache.webdav.lib.WebdavException;
import org.apache.webdav.lib.WebdavResource;
import org.apache.webdav.lib.WebdavResources;

import com.idega.business.IBOLookup;
import com.idega.content.bean.ContentItemBean;
import com.idega.data.IDOEntity;
import com.idega.data.IDOStoreException;
import com.idega.idegaweb.IWUserContext;
import com.idega.presentation.IWContext;
import com.idega.slide.business.IWSlideSession;
import com.idega.slide.util.WebdavExtendedResource;
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

	private String personPicturePath;

	private String gravePicturePath;

	private String uniqueAvenyId;

	private Integer graveId;

	private Integer databaseId;

	private final static String FIELDNAME_BODY = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <article> </article>";

	public final static String ARTICLE_FILENAME_SCOPE = "obituary";

	public final static String CONTENT_TYPE = "ContentType";

	public final static String FIELDNAME_OBITUARY = "ObituaryText";

	public final static String FIELDNAME_GRAVE_IMAGE_PATH = "GraveImagePath";

	public final static String FIELDNAME_PERSON_IMAGE_PATH = "PresonImagePath";

	public final static String FIELDNAME_CONTENT_LANGUAGE = "english";

	public static final PropertyName PROPERTY_CONTENT_TYPE = new PropertyName("IW:", CONTENT_TYPE);

	public static final String FILE_PATH = "files/cms/obituary/";

	XMLNamespace idegaXMLNameSpace = new XMLNamespace("http://xmlns.agura.se/memorial");

	private static final String CONTENT_PATH = "/files/cms/obituary/";

	public Integer getDatabaseId() {
		return databaseId;
	}


	public String getResourcePath() {
		return CONTENT_PATH;
	}

	private String getGraveImagePath() {
		String path = getResourcePath();
		
		path += getDatabaseId().toString();
		path += "/" + getGraveId().toString();
		path += ".GraveImage";
		path += ".jpg";		
		
		return path;
	}

	private String getContentLanguage() {
		// TODO  multilanguages 
		return "en";
	}

	private String getPersonImagePath() {
		
		String path = getResourcePath();
		
		path += getDatabaseId().toString();
		path += "/" + getGraveId().toString();
		path += ".PersonImage";
		path += ".jpg";
		
		return path;
	}

	public ObituaryItemBean() {
		super();
	}

	public String getBody() {
		return FIELDNAME_BODY;
	}

	
	public Integer getGraveId() {
		return graveId;
	}

	public String getGravePicturePath() {
		return gravePicturePath;
	}


	public Integer getObituaryId() {
		return obituaryId;
	}


	public String getObituaryText() {
		return obituaryText;
	}


	public String getPersonPicturePath() {
		return personPicturePath;
	}

	public String getUniqueAvenyId() {
		return uniqueAvenyId;
	}


	public String getArticlePath() {

		//TODO test mode only

		String lang = "en";
		String databaseId = getDatabaseId().toString();
		String graveId = getGraveId().toString();

		return databaseId + "/" + graveId + ".obituary/" + getContentLanguage();
	}

	public String getArticleName() {
		return ".txt";
	}

	public String[] getContentFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContentItemPrefix() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setDatasource(String arg0) {
		// TODO Auto-generated method stub

	}	
	public void setUniqueAvenyId(String uniqueAvenyId) {
		this.uniqueAvenyId = uniqueAvenyId;
	}
	
	public void setPersonPicturePath(String presonPicturePath) {
		this.personPicturePath = presonPicturePath;
	}	
	
	public void setBody(String body) {
		//
	}

	public void setGraveId(Integer graveId) {
		this.graveId = graveId;
	}

	public void setGravePicturePath(String gravePicturePath) {
		this.gravePicturePath = gravePicturePath;
	}

	public void setObituaryId(Integer obituaryId) {
		this.obituaryId = obituaryId;
	}

	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}

	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;
	}


	public String getAsXML() throws IOException, XMLException {

		XMLParser builder = new XMLParser();
		XMLDocument bodyDoc = null;
		bodyDoc = builder.parse(new ByteArrayInputStream(getBody().getBytes()));

		XMLElement bodyElement = bodyDoc.getRootElement();
		XMLElement root = new XMLElement("article", idegaXMLNameSpace);
		XMLElement obituary = new XMLElement(FIELDNAME_OBITUARY,idegaXMLNameSpace).setText(getObituaryText());
		XMLElement personImagePath = new XMLElement(FIELDNAME_PERSON_IMAGE_PATH, idegaXMLNameSpace).setText(getPersonImagePath());
		XMLElement graveImagePath = new XMLElement(FIELDNAME_GRAVE_IMAGE_PATH,idegaXMLNameSpace).setText(getGraveImagePath());
		XMLElement contentLanguage = new XMLElement(FIELDNAME_CONTENT_LANGUAGE,idegaXMLNameSpace).setText(getContentLanguage());

		root.addContent(contentLanguage);
		root.addContent(obituary);
		root.addContent(personImagePath);
		root.addContent(graveImagePath);

		XMLDocument doc = new XMLDocument(root);
		XMLOutput outputter = new XMLOutput();
		return outputter.outputString(doc);
	}


	
	public void store() throws IDOStoreException, WebdavException {
		boolean storeOk = true;

		if (storeOk) {
			try {

				IWUserContext iwuc = IWContext.getInstance();
				IWSlideSession session = (IWSlideSession) IBOLookup.getSessionInstance(iwuc, IWSlideSession.class);
				WebdavRootResource rootResource = session.getWebdavRootResource();

				//Setting the path for creating new file or creating localized version or updating existing file

				String articleFolderPath = getResourcePath() + getArticlePath();
				String filePath = articleFolderPath + getArticleName();

				boolean hadToCreate = session.createAllFoldersInPath(articleFolderPath);
				if (hadToCreate) {
					String fixedFolderURL = session.getURI(articleFolderPath);
					rootResource.proppatchMethod(fixedFolderURL,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
				} else {
					rootResource.proppatchMethod(articleFolderPath,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
				}
				String article = getAsXML();
				if (rootResource.putMethod(filePath, article)) {
					rootResource.proppatchMethod(filePath, PROPERTY_CONTENT_TYPE,ARTICLE_FILENAME_SCOPE, true);
				} else {
					String fixedURL = session.getURI(filePath);
					rootResource.putMethod(fixedURL, article);
					rootResource.proppatchMethod(fixedURL, PROPERTY_CONTENT_TYPE,ARTICLE_FILENAME_SCOPE, true);
				}

				rootResource.close();

			} catch (IOException e1) {
				storeOk = false;
				e1.printStackTrace();
			} catch (XMLException e) {
				storeOk = false;

				e.printStackTrace();

			}
		}

		if (storeOk) {
			if (getRequestedStatus() != null) {
				setStatus(getRequestedStatus());
				setRequestedStatus(null);
			}
		} else {
			//throw new ArticleStoreException();
		}

	}

	
	public boolean load(WebdavExtendedResource webdavResource) throws IOException {
		XMLParser builder = new XMLParser();
		XMLDocument bodyDoc = null;
		try {
			WebdavResource theArticle = null;
			theArticle = webdavResource;
			
			if(theArticle!=null && !theArticle.isCollection()){
				bodyDoc = builder.parse(new ByteArrayInputStream(theArticle.getMethodDataAsString().getBytes()));
			} else {
				bodyDoc = null;
			}
		} catch (XMLException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		if(bodyDoc!=null){
			XMLElement rootElement = bodyDoc.getRootElement();
	
			try {
				XMLElement obituaryText = rootElement.getChild(FIELDNAME_OBITUARY,idegaXMLNameSpace);
				if(obituaryText != null){
					this.setObituaryText(obituaryText.getText());
					
				} else {
					setObituaryText("");
				}
			}catch(Exception e) {		//Nullpointer could occur if field isn't used
				e.printStackTrace();
				setObituaryText("");
			}

			
			try {
				XMLElement gravePicturePath = rootElement.getChild(FIELDNAME_GRAVE_IMAGE_PATH,idegaXMLNameSpace);
				if(gravePicturePath != null){
					setGravePicturePath(gravePicturePath.getText());
					
				} else {
					setGravePicturePath("");
				}
			}catch(Exception e) {		//Nullpointer could occur if field isn't used
				e.printStackTrace();
				setGravePicturePath("");
			}


			try {
				XMLElement personPicturePath = rootElement.getChild(FIELDNAME_PERSON_IMAGE_PATH,idegaXMLNameSpace);
				if(personPicturePath != null){
					setPersonPicturePath(personPicturePath.getText());

					
				} else {
					setGravePicturePath("");
				}
			}catch(Exception e) {		//Nullpointer could occur if field isn't used
				e.printStackTrace();
				setGravePicturePath("");
			}

			

			
		} else {
			//article not found
			Logger log = Logger.getLogger(this.getClass().toString());
			log.warning("Article xml file was not found");
			setRendered(false);
			return false;
		}
		return true;
	}
	

}
