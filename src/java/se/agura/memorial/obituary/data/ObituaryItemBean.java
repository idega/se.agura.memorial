package se.agura.memorial.obituary.data;


import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Logger;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.apache.webdav.lib.PropertyName;
import org.apache.webdav.lib.WebdavException;
import org.apache.webdav.lib.WebdavResource;

import com.idega.business.IBOLookup;
import com.idega.content.bean.ContentItemBean;
import com.idega.data.IDOEntity;
import com.idega.data.IDOStoreException;
import com.idega.idegaweb.IWApplicationContext;
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

public class ObituaryItemBean extends ContentItemBean implements IDOEntity{

	private String graveId;
	private Integer databaseId;
	private Integer obituaryId;
	private String obituaryText;
	
	private String personImagePath;
	private String graveImagePath;
	
	private String tmpPersonImagePath;
	private String tmpGraveImagePath;
	
	private UploadedFile personImageFile;
	private UploadedFile graveImageFile;

	private UploadedFile tmpPersonImageFile;
	private UploadedFile tmpGraveImageFile;

	private Boolean removeOldPersonImage;
	private Boolean removeOldGraveImage;	



	
	private String uniqueAvenyId;
	
	private String contentLanguage;
	
	private final static String FIELDNAME_BODY = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <article> </article>";

	public final static String ARTICLE_FILENAME_SCOPE = "obituary";

	public final static String IMAGE_FILENAME_SCOPE = "image";

	public final static String TMP_IMAGE_FILENAME_SCOPE = "tmpimage";

	public final static String CONTENT_TYPE = "ContentType";

	public final static String FIELDNAME_OBITUARY = "ObituaryText";

	public final static String FIELDNAME_GRAVE_IMAGE_PATH = "GraveImagePath";

	public final static String FIELDNAME_PERSON_IMAGE_PATH = "PersonImagePath";

	public final static String ARTICLE_NAME= ".xml";

	public final static String FIELDNAME_CONTENT_LANGUAGE = "english";

	public static final PropertyName PROPERTY_CONTENT_TYPE = new PropertyName("IW:", CONTENT_TYPE);

	public static final String FILE_PATH = "files/cms/obituary/";

	XMLNamespace idegaXMLNameSpace = new XMLNamespace("http://xmlns.agura.se/memorial");

	private static final String CONTENT_PATH = "/files/cms/obituary/";

	private static final String TMP_CONTENT_PATH = "/files/cms/obituary/temp/";


	
	public UploadedFile getPersonImageFile() {
		return personImageFile;
	}
	


	public void setPersonImageFile(UploadedFile personImageFile) {
		this.personImageFile = personImageFile;
	}
	


	public String getRootPath() {
		String rootPath;
		
		rootPath = CONTENT_PATH;
		rootPath += getDatabaseId().toString();
		rootPath += "/";
		rootPath += getGraveId().toString();
		rootPath += ".obituary/";
		rootPath += getContentLanguage();
		rootPath += getArticleName();
		
		
		
		return rootPath;
	}
	
	public String createGraveImagePath() {
		String path;

		path = CONTENT_PATH;
		path += String.valueOf(getDatabaseId());
		path += "/" + getGraveId();
		path += ".GraveImage/";
		path += "grave.jpg";
		this.graveImagePath = "/content" + path;


		return path;
	}
		
	
	public String createPersonImagePath(){
		String path;

		path = CONTENT_PATH;
		path += String.valueOf(getDatabaseId());
		path += "/" + getGraveId();
		path += ".PersonImage/";
		path += "person.jpg";		
		this.personImagePath = "/content" + path;
			
		
		return path;
	}


	public String createTmpGraveImagePath() {
		String path ;
		path = TMP_CONTENT_PATH;
		path += String.valueOf(getDatabaseId());
		path += "/" + getGraveId();
		path += ".GraveImage/";
		path += "grave.jpg";
		this.tmpGraveImagePath = "/content" + path;
		
	

		return path;
	}


	public String createTmpPersonImagePath(){
		String path;
		path = TMP_CONTENT_PATH;
		path += String.valueOf(getDatabaseId());
		path += "/" + getGraveId();
		path += ".PersonImage/";
		path += "person.jpg";		
		this.tmpPersonImagePath = "/content" + path;
		

		return path;
	}


	public String getTmpGraveResourcePath() {
		String path;
		path = TMP_CONTENT_PATH;
		path += String.valueOf(getDatabaseId());
		path += "/" + getGraveId();
		path += ".GraveImage/";

		return path;
	}

	public String getTmpPersonResourcePath() {
		String path;
		path = TMP_CONTENT_PATH;
		path += String.valueOf(getDatabaseId());
		path += "/" + getGraveId();
		path += ".PersonImage/";

		return path;
	}

	
	public String getGraveResourcePath() {
		String path;
		path = CONTENT_PATH;
		path += String.valueOf(getDatabaseId());
		path += "/" + getGraveId();
		path += ".GraveImage/";

		return path;
	}

	public String getPersonResourcePath() {
		String path;
		path = CONTENT_PATH;
		path += String.valueOf(getDatabaseId());
		path += "/" + getGraveId();
		path += ".PersonImage/";

		return path;
	}


	
	public String getResourcePath() {
		return CONTENT_PATH;
	}

	public String getTmpResourcePath() {
		return TMP_CONTENT_PATH;
	}




	
	private String getImageType() {
		return ".jpg";
	}

	


	private String getContentLanguage() {
		return contentLanguage;
	}

	
	public void setContentLanguage(String contentLanguage) {
		this.contentLanguage = contentLanguage;
	}
	


	public UploadedFile getGraveImageFile() {
		return graveImageFile;
	}
	



	public void setGraveImageFile(UploadedFile graveImageFile) {
		this.graveImageFile = graveImageFile;
	}
	



	public Boolean isRemoveOldGraveImage() {
		return removeOldGraveImage;
	}
	



	public void setRemoveOldGraveImage(Boolean removeOldGraveImage) {
		this.removeOldGraveImage = removeOldGraveImage;
	}
	



	public Boolean isRemoveOldPersonImage() {
		return removeOldPersonImage;
	}
	



	public void setRemoveOldPersonImage(Boolean removeOldPersonImage) {
		this.removeOldPersonImage = removeOldPersonImage;
	}
	



	public UploadedFile getTmpGraveImageFile() {
		return tmpGraveImageFile;
	}
	



	public void setTmpGraveImageFile(UploadedFile tmpGraveImageFile) {
		this.tmpGraveImageFile = tmpGraveImageFile;
	}
	



	



	public UploadedFile getTmpPersonImageFile() {
		return tmpPersonImageFile;
	}
	



	public void setTmpPersonImageFile(UploadedFile tmpPersonImageFile) {
		this.tmpPersonImageFile = tmpPersonImageFile;
	}
	

	



	public ObituaryItemBean() {
		super();
	}

	public String getBody() {
		return FIELDNAME_BODY;
	}

	
	public String getGraveId() {
		return graveId;
	}



	public Integer getObituaryId() {
		return obituaryId;
	}


	public String getObituaryText() {
		return obituaryText;
	}



	public String getUniqueAvenyId() {
		return uniqueAvenyId;
	}


	public String getArticlePath() {

		String databaseId = String.valueOf(getDatabaseId());
		String graveId = getGraveId().toString();
		return databaseId + "/" + graveId + ".obituary/" + getContentLanguage();
	}

	public String getArticleName() {
		return ARTICLE_NAME;
	}

	public String[] getContentFieldNames() {
		return null;
	}

	public String getContentItemPrefix() {
		return null;
	}
	
	public void setDatasource(String arg0) {

	}	
	public void setUniqueAvenyId(String uniqueAvenyId) {
		this.uniqueAvenyId = uniqueAvenyId;
	}
	

	
	public void setBody(String body) {
		//
	}


	public void setObituaryId(Integer obituaryId) {
		this.obituaryId = obituaryId;
	}

	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}

	public String getAsXML() throws IOException, XMLException {

		
		
		
		String pip = null;
		String gip = null;
		
		if((this.personImageFile==null)||(this.graveImageFile==null))
		{
			IWUserContext iwuc = IWContext.getInstance();
			IWSlideSession session = (IWSlideSession) IBOLookup.getSessionInstance(iwuc, IWSlideSession.class);
			this.load(session.getWebdavResource(this.getRootPath()));
			
			if(this.removeOldPersonImage.booleanValue()==true) this.setPersonImagePath(null);
			if(this.removeOldGraveImage.booleanValue()==true) this.setGraveImagePath(null);
			
			if(this.personImageFile!=null) this.createPersonImagePath();
			if(this.graveImageFile!=null) this.createGraveImagePath();
			
		}
		else
		{
			this.createPersonImagePath();
			this.createGraveImagePath();
		}
		
		XMLParser builder = new XMLParser();
		XMLDocument bodyDoc = null;
		bodyDoc = builder.parse(new ByteArrayInputStream(getBody().getBytes()));
        
		pip = this.getPersonImagePath();
		gip = this.getGraveImagePath();

		
		XMLElement bodyElement = bodyDoc.getRootElement();
		XMLElement root = new XMLElement("article", idegaXMLNameSpace);
		XMLElement obituary = new XMLElement(FIELDNAME_OBITUARY,idegaXMLNameSpace).setText(this.getObituaryText());
		XMLElement personImagePath = new XMLElement(FIELDNAME_PERSON_IMAGE_PATH, idegaXMLNameSpace).setText(pip);
		XMLElement graveImagePath = new XMLElement(FIELDNAME_GRAVE_IMAGE_PATH,idegaXMLNameSpace).setText(gip);
		XMLElement contentLanguage = new XMLElement(FIELDNAME_CONTENT_LANGUAGE,idegaXMLNameSpace).setText(this.getContentLanguage());

		root.addContent(obituary);
		root.addContent(personImagePath);
		root.addContent(graveImagePath);
		root.addContent(contentLanguage);

		XMLDocument doc = new XMLDocument(root);
		XMLOutput outputter = new XMLOutput();
		return outputter.outputString(doc);
	}
//-------------------------------------------------------------------------------------------------------------------------------------
	
	public void store() throws IDOStoreException, WebdavException {
		boolean storeOk = true;

		if (storeOk) {
			try {

				IWUserContext iwuc = IWContext.getInstance();
				IWSlideSession session = (IWSlideSession) IBOLookup.getSessionInstance(iwuc, IWSlideSession.class);
				WebdavRootResource rootResource = session.getWebdavRootResource();

				//Setting the path for creating new file or creating localized version or updating existing file

				String folderPath = getResourcePath() + getArticlePath();
				String filePath = folderPath + getArticleName();

				boolean hadToCreate = session.createAllFoldersInPath(folderPath);
				if (hadToCreate) {
					String fixedFolderURL = session.getURI(folderPath);
					rootResource.proppatchMethod(fixedFolderURL,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
				} else {
					rootResource.proppatchMethod(folderPath,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
				}
				String article = getAsXML();
				if (rootResource.putMethod(filePath, article)) {
					rootResource.proppatchMethod(filePath, PROPERTY_CONTENT_TYPE,ARTICLE_FILENAME_SCOPE, true);
				} else {
					String fixedURL = session.getURI(filePath);
					rootResource.putMethod(fixedURL, article); 
					rootResource.proppatchMethod(fixedURL, PROPERTY_CONTENT_TYPE,ARTICLE_FILENAME_SCOPE, true);
					//rootResource.checkinMethod();
				}

				/// saving images				
			

				if((getGraveImageFile()!=null) || (isRemoveOldGraveImage().booleanValue() ) ){
					folderPath = getGraveResourcePath();
					filePath = createGraveImagePath();
					hadToCreate = session.createAllFoldersInPath(folderPath);
					if (hadToCreate) {
						String fixedFolderURL = session.getURI(folderPath);
						rootResource.proppatchMethod(fixedFolderURL,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
					} else {
						rootResource.proppatchMethod(folderPath,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
					}
				
					if(isRemoveOldGraveImage().booleanValue()) {
						String fixedURL = session.getURI(filePath);
						rootResource.deleteMethod(fixedURL);
					}
					else{

						if (rootResource.putMethod(filePath,getGraveImageFile().getInputStream())) {
							rootResource.proppatchMethod(filePath, PROPERTY_CONTENT_TYPE,TMP_IMAGE_FILENAME_SCOPE, true);
						} else {
							String fixedURL = session.getURI(filePath);
							rootResource.putMethod(fixedURL,getGraveImageFile().getInputStream());
							rootResource.proppatchMethod(fixedURL, PROPERTY_CONTENT_TYPE,TMP_IMAGE_FILENAME_SCOPE, true);
						}	
					}
				}
				
				if((getPersonImageFile()!=null) || (isRemoveOldPersonImage().booleanValue() ) ){
					folderPath = getPersonResourcePath();
					filePath = createPersonImagePath();
					hadToCreate = session.createAllFoldersInPath(folderPath);
					if (hadToCreate) {
						String fixedFolderURL = session.getURI(folderPath);
						rootResource.proppatchMethod(fixedFolderURL,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
					} else {
						rootResource.proppatchMethod(folderPath,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
					}	
				

					if(isRemoveOldPersonImage().booleanValue()  ){
						String fixedURL = session.getURI(filePath);
						rootResource.deleteMethod(fixedURL);
					}
					else{
						if (rootResource.putMethod(filePath,getPersonImageFile().getInputStream())) {
							rootResource.proppatchMethod(filePath, PROPERTY_CONTENT_TYPE,TMP_IMAGE_FILENAME_SCOPE, true);
						} else {
							String fixedURL = session.getURI(filePath);
							rootResource.putMethod(fixedURL,getPersonImageFile().getInputStream());
							rootResource.proppatchMethod(fixedURL, PROPERTY_CONTENT_TYPE,TMP_IMAGE_FILENAME_SCOPE, true);
						}
					}
				}
			} 
			catch (IOException e1) {
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

//--------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean isPersonImageExist(){

		boolean result = false;
			try {

				IWUserContext iwuc = IWContext.getInstance();
				IWSlideSession session = (IWSlideSession) IBOLookup.getSessionInstance(iwuc, IWSlideSession.class);
				WebdavRootResource rootResource = session.getWebdavRootResource();

				String folderPath = getGraveResourcePath();
				String filePath = createGraveImagePath();
				String fixedURL = session.getURI(filePath);
				
				result = rootResource.bindMethod(fixedURL);  //checkinMethod(fixedURL);
			}
			catch (IOException e1) {

			} 

		return result;	
	}

//--------------------------------------------------------------------------------------------------------------------------------------
	

	public void storeTMP() throws IDOStoreException, WebdavException {
		boolean storeOk = true;

		if (storeOk) {
			try {

				IWUserContext iwuc = IWContext.getInstance();
				IWSlideSession session = (IWSlideSession) IBOLookup.getSessionInstance(iwuc, IWSlideSession.class);
				WebdavRootResource rootResource = session.getWebdavRootResource();

				// saving temporary image files				

				String str =null;
				String folderPath;
				String filePath;
				boolean hadToCreate;
				
				folderPath = this.getTmpGraveResourcePath();
				filePath = this.createTmpGraveImagePath();
				hadToCreate = session.createAllFoldersInPath(folderPath);
				if (hadToCreate) {
					String fixedFolderURL = session.getURI(folderPath);
					rootResource.proppatchMethod(fixedFolderURL,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
				} else {
					rootResource.proppatchMethod(folderPath,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
				}

				if(this.getTmpGraveImageFile()!=null){
					if (rootResource.putMethod(filePath,getTmpGraveImageFile().getInputStream())) {
						rootResource.proppatchMethod(filePath, PROPERTY_CONTENT_TYPE,TMP_IMAGE_FILENAME_SCOPE, true);
					} else {
						String fixedURL = session.getURI(filePath);
						rootResource.putMethod(fixedURL,getTmpGraveImageFile().getInputStream());
						rootResource.proppatchMethod(fixedURL, PROPERTY_CONTENT_TYPE,TMP_IMAGE_FILENAME_SCOPE, true);
					}
				}

				
				folderPath = getTmpPersonResourcePath();
				filePath = createTmpPersonImagePath();
				hadToCreate = session.createAllFoldersInPath(folderPath);
				if (hadToCreate) {
					String fixedFolderURL = session.getURI(folderPath);
					rootResource.proppatchMethod(fixedFolderURL,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
				} else {
					rootResource.proppatchMethod(folderPath,PROPERTY_CONTENT_TYPE, "LocalizedFile", true);
				}

				if(this.getTmpPersonImageFile()!=null){

					if (rootResource.putMethod(filePath,getTmpPersonImageFile().getInputStream())) {
						rootResource.proppatchMethod(filePath, PROPERTY_CONTENT_TYPE,TMP_IMAGE_FILENAME_SCOPE, true);
					} else {
						String fixedURL = session.getURI(filePath);
						rootResource.putMethod(fixedURL,getTmpPersonImageFile().getInputStream());
						rootResource.proppatchMethod(fixedURL, PROPERTY_CONTENT_TYPE,TMP_IMAGE_FILENAME_SCOPE, true);
					}
				}
				
				rootResource.close();
				

			} catch (IOException e1) {
				storeOk = false;
				e1.printStackTrace();
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
//--------------------------------------------------------------------------------------------------------------------------	
	

	public boolean load(WebdavExtendedResource webdavResource) throws IOException {
		XMLParser builder = new XMLParser();
		XMLDocument bodyDoc = null;
		boolean result = false;
		try {
			WebdavResource theArticle = null;
			theArticle = webdavResource;
			
			if(theArticle!=null && !theArticle.isCollection()){
				bodyDoc = builder.parse(new ByteArrayInputStream(theArticle.getMethodDataAsString().getBytes()));
			} else {
				bodyDoc = null;
			}
		} catch (XMLException e) {
			//e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
					setGraveImagePath(gravePicturePath.getText());
					result = true;
				} else {
					setGraveImagePath(null);
				}
			}catch(Exception e) {		//Nullpointer could occur if field isn't used
				
				e.printStackTrace();
				setGraveImagePath(null);
			}


			try {
				XMLElement personPicturePath = rootElement.getChild(FIELDNAME_PERSON_IMAGE_PATH,idegaXMLNameSpace);
				if(personPicturePath != null){
					setPersonImagePath(personPicturePath.getText());
					result = true;
					
				} else {
					setGraveImagePath(null);
				}
			}catch(Exception e) {		//Nullpointer could occur if field isn't used
				e.printStackTrace();
				setGraveImagePath(null);
			}

			

			
		} else {
			//article not found
			Logger log = Logger.getLogger(this.getClass().toString());
			log.warning("Article xml file was not found");
			setRendered(false);
		}
		return result;
	}


//	public boolean loadPersonImage(WebdavExtendedResource webdavResource) throws IOException {
//		XMLParser builder = new XMLParser();
//		XMLDocument bodyDoc = null;
//		boolean result = false;
//		try {
//			WebdavResource theArticle = null;
//			theArticle = webdavResource;
//			
//			if(theArticle!=null && !theArticle.isCollection()){
//				bodyDoc = builder.parse(new ByteArrayInputStream(theArticle.getMethodDataAsString().getBytes()));
//			} else {
//				bodyDoc = null;
//			}
//		} catch (XMLException e) {
//			//e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//		}
//		if(bodyDoc!=null){
//			XMLElement rootElement = bodyDoc.getRootElement();
//	
//			try {
//				XMLElement personPicturePath = rootElement.getChild(FIELDNAME_PERSON_IMAGE_PATH,idegaXMLNameSpace);
//				if(personPicturePath != null){
//					setPersonPicturePath(personPicturePath.getText());
//					result = true;
//					
//				} else {
//					setGravePicturePath("");
//				}
//			}catch(Exception e) {		//Nullpointer could occur if field isn't used
//				e.printStackTrace();
//				setGravePicturePath("");
//			}
//
//			
//
//			
//		} else {
//			Logger log = Logger.getLogger(this.getClass().toString());
//			log.warning("person Image was not found");
//			setRendered(false);
//		}
//		return result;
//	}


//	public boolean loadGraveImage(WebdavExtendedResource webdavResource) throws IOException {
//		XMLParser builder = new XMLParser();
//		XMLDocument bodyDoc = null;
//		boolean result = false;
//		try {
//			WebdavResource theArticle = null;
//			theArticle = webdavResource;
//			
//			if(theArticle!=null && !theArticle.isCollection()){
//				bodyDoc = builder.parse(new ByteArrayInputStream(theArticle.getMethodDataAsString().getBytes()));
//			} else {
//				bodyDoc = null;
//			}
//		} catch (XMLException e) {
//			//e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//		}
//		if(bodyDoc!=null){
//			XMLElement rootElement = bodyDoc.getRootElement();
//			
//			try {
//				XMLElement gravePicturePath = rootElement.getChild(FIELDNAME_GRAVE_IMAGE_PATH,idegaXMLNameSpace);
//				if(gravePicturePath != null){
//					setGravePicturePath(gravePicturePath.getText());
//					result = true;
//				} else {
//					setGravePicturePath("");
//				}
//			}catch(Exception e) {		//Nullpointer could occur if field isn't used
//				
//				e.printStackTrace();
//				setPersonPicturePath("");
//				setGravePicturePath("");
//			}
//			
//
//			
//		} else {
//			Logger log = Logger.getLogger(this.getClass().toString());
//			log.warning("graave Image was not found");
//			setRendered(false);
//		}
//		return result;
//	}
	
	
	public Iterator getChildrenIterator(String arg0, boolean arg1) {
		return null;
	}




	public EJBLocalHome getEJBLocalHome(String arg0) {

		return null;
	}
	public void setGraveId(String graveId) {		
		this.graveId = graveId;
	}


	public Integer getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;		
	}



	public void setUserContext(IWUserContext arg0) throws RemoteException {

		
	}

	public IWUserContext getUserContext() throws RemoteException {

		return null;
	}

	public void initializeBean() {

		
	}

	public void addActionListener(ActionListener arg0) {

		
	}

	public String getServiceDescription() throws RemoteException {

		return null;
	}

	public String getLocalizedServiceDescription(Locale arg0) throws RemoteException {

		return null;
	}

	public IWApplicationContext getIWApplicationContext() throws RemoteException {

		return null;
	}

	public EJBHome getEJBHome() throws RemoteException {

		return null;
	}

	public Handle getHandle() throws RemoteException {

		return null;
	}

	public boolean isIdentical(EJBObject arg0) throws RemoteException {

		return false;
	}



	public String getGraveImagePath() {
		return graveImagePath;
	}
	



	public void setGraveImagePath(String graveImagePath) {
		this.graveImagePath = graveImagePath;
	}
	



	public String getPersonImagePath() {
		return personImagePath;
	}
	



	public void setPersonImagePath(String personImagePath) {
		this.personImagePath = personImagePath;
	}
	



	public String getTmpGraveImagePath() {
		return tmpGraveImagePath;
	}
	



	public void setTmpGraveImagePath(String tmpGraveImagePath) {
		this.tmpGraveImagePath = tmpGraveImagePath;
	}
	



	public String getTmpPersonImagePath() {
		return tmpPersonImagePath;
	}
	



	public void setTmpPersonImagePath(String tmpPersonImagePath) {
		this.tmpPersonImagePath = tmpPersonImagePath;
	}
	


}
