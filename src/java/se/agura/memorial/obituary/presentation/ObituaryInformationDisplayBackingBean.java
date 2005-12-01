package se.agura.memorial.obituary.presentation;


import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpException;

import se.agura.memorial.obituary.bussiness.ObituarySessionBean;
import se.agura.memorial.obituary.data.ObituaryItemBean;
import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.business.SearchImplBroker;
import se.agura.memorial.util.MemorialHeplInfo;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.idegaweb.IWUserContext;
import com.idega.presentation.IWContext;
import com.idega.slide.business.IWSlideSession;
import com.idega.xml.XMLException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.pdf.MultiColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter;



public class ObituaryInformationDisplayBackingBean {

	private String obituaryText = null;
	private String graveImagePath = null;
	private String personImagePath = null;
	
	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	private static final String CONTENT_PATH = "/content/files/cms/obituary/";

	private String graveId = null;
	private String personFullName = null;	
	private Integer databaseId = null;
	private Grave grave = null;
	private Graveyard graveyard;
	private boolean graveyardSetToNull = false;
	private Map graveyards;	
	
	private Boolean personImageRendered;
	private Boolean graveImageRendered;	
	
	private Boolean personImageRemove;
	private Boolean graveImageRemove;

	private MemorialHeplInfo mhi = null;

	private ObituarySessionBean obituarySessionBean = null;
	
	public ObituaryInformationDisplayBackingBean() throws HttpException, IOException, XMLException {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			obituarySessionBean = (ObituarySessionBean) IBOLookup.getSessionInstance(iwc, ObituarySessionBean.class);
		} catch (IBOLookupException e) {
			e.printStackTrace();
		}
		
		
		initObituary(); 
		
	}
	
	public String getGraveImagePath() {
		return graveImagePath;
	}
	

	public void setGraveImagePath(String graveImageResourcePath) {
		this.graveImagePath = graveImageResourcePath;
	}
	

	public String getPersonImagePath() {
		return personImagePath;
	}
	

	public String getPersonFullName() {
		//if (this.databaseId == null) return "";
		return personFullName;

	}
	

	public void setPersonFullName(String personFullName) {
		this.personFullName = personFullName;
	}
	

	public void setPersonImagePath(String personImageResourcePath) {
		this.personImagePath = personImageResourcePath;
	}
	

	private void initObituary() throws HttpException, IOException, XMLException {



		
 	    if (obituarySessionBean.getGraveId()!=null)
		{
			this.obituaryText = this.obituarySessionBean.getObituaryText();
			this.graveId = this.obituarySessionBean.getGraveId();
			this.databaseId = this.obituarySessionBean.getDatabaseId();
			this.personFullName = this.obituarySessionBean.getPersonFullName();
			this.personImageRendered = this.obituarySessionBean.getPersonImageRendered();
			this.graveImageRendered = this.obituarySessionBean.getGraveImageRendered();
			this.graveImageRemove = this.obituarySessionBean.getRemoveOldGraveImage();
			this.personImageRemove = this.obituarySessionBean.getRemoveOldPersonImage();
			this.graveImagePath = this.obituarySessionBean.getGraveImagePath();
			this.personImagePath = this.obituarySessionBean.getPersonImagePath();
			
		}
		if (this.databaseId == null) return;
			
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);

		try {
			
			SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
			ObituarySearch os = sib.getSearch(getDatabaseId().intValue());
			List listOfGraveyards = (List) os.getGraveyards();
			
			this.grave = os.findGrave(getGraveId());
			if(this.grave!=null)
			{	
				this.personFullName = "";
				if(this.grave.getFirstName()!=null)	this.personFullName = this.grave.getFirstName();
				if(this.grave.getLastName()!=null) this.personFullName = this.personFullName + " " + this.grave.getLastName();
				this.obituarySessionBean.setPersonFullName(this.personFullName);
			
				ObituaryItemBean oib = new ObituaryItemBean(); 
				oib.setDatabaseId(getDatabaseId());  
				oib.setGraveId(getGraveId());
				oib.setContentLanguage("en"); //TODO multilanguage
				
				IWUserContext iwuc = IWContext.getInstance();
				IWSlideSession session = (IWSlideSession) IBOLookup.getSessionInstance(iwuc, IWSlideSession.class);
			
				boolean result = oib.load(session.getWebdavResource(oib.getRootPath()));
			
				this.obituaryText = oib.getObituaryText();
				this.graveImagePath = oib.getGraveImagePath();
				this.personImagePath = oib.getPersonImagePath();

				this.obituarySessionBean.setObituaryText(this.getObituaryText());
				this.obituarySessionBean.setGraveImagePath(this.getGraveImagePath());
				this.obituarySessionBean.setPersonImagePath(this.getPersonImagePath());
			
				if((this.graveImagePath!=null)&&(this.graveImagePath!=""))  
					this.setGraveImageRendered(Boolean.TRUE);
				else
					this.setGraveImageRendered(Boolean.FALSE);					
				if((this.personImagePath!=null)&&(this.personImagePath!=""))  
					this.setPersonImageRendered(Boolean.TRUE);
				else
					this.setPersonImageRendered(Boolean.FALSE);
				
				if(this.graveImageRemove==null)this.setGraveImageRemove(Boolean.FALSE);
				if(this.personImageRemove==null)this.setPersonImageRemove(Boolean.FALSE);
				
				this.obituarySessionBean.setGraveImageRendered(this.getGraveImageRendered());
				this.obituarySessionBean.setPersonImageRendered(this.getPersonImageRendered());
				
				this.obituarySessionBean.setRemoveOldGraveImage(this.getGraveImageRemove());
				this.obituarySessionBean.setRemoveOldPersonImage(this.getPersonImageRemove());
				
			}

			
		} catch (IBOLookupException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();	
		} catch(MalformedURLException e) {
			//e.printStackTrace();
		}
			
	}

	public Boolean getGraveImageRendered() {
		return graveImageRendered;
	}

	public void setGraveImageRendered(Boolean graveImageRendered) {
		this.graveImageRendered = graveImageRendered;
	}

	public Boolean getPersonImageRendered() {
		return personImageRendered;
	}

	public void setPersonImageRendered(Boolean personImageRendered) {
		this.personImageRendered = personImageRendered;
	}


	public MemorialHeplInfo getMhi() {
		return mhi;
	}
	

	public void setMhi(MemorialHeplInfo mhi) {
		this.mhi = mhi;
	}
	

	public String getObituaryText() {
		return obituaryText;
	}
	

	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}
	

	public String getGraveId() {
		return graveId;
	}

	
	public Boolean getGraveImageRemove() {
		return graveImageRemove;
	}
	

	public void setGraveImageRemove(Boolean graveImageRemove) {
		this.graveImageRemove = graveImageRemove;
	}
	

	public Boolean getPersonImageRemove() {
		return personImageRemove;
	}
	

	public void setPersonImageRemove(Boolean personImageRemove) {
		this.personImageRemove = personImageRemove;
	}
	

	public void setGraveId(String graveId) throws HttpException, IOException, XMLException {		

		if((graveId == null)||(graveId == "")) return;
		
		this.graveId = graveId;
		
		obituarySessionBean.setDatabaseId(getDatabaseId()); 
		obituarySessionBean.setGraveId(getGraveId()); 

		initObituary();	

	}
	
	public Grave getGrave() {
		return grave;
	}

	public Integer getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;		
	}
	
    private static Element newPara(String text, int alignment, int type) {
	       Font font = FontFactory.getFont("Helvetica", 10, type, Color.BLACK);
	       Paragraph p = new Paragraph(text, font);
	       p.setAlignment(alignment);
	       p.setLeading(font.size() * 1.2f);
	       return p;
	    }

	    static Random rand = new Random();


	    static String[] verb = {"flows", "draws", "renders", "throws exception", "runs",
	        "crashes", "downloads", "usurps", "vexes", "whispers", "boils",
	        "capitulates", "crashes", "craves", "looks", "defies", "defers",
	        "defines", "envelops", "entombs", "falls", "fails", "halts",
	        "appears", "nags", "overflows", "burns", "dies", "writes",
	        "flushes"};

	    static String[] noun = {"ColumnText", "paragraph", "phrase", "chunk", "PdfContentByte",
	        "PdfPTable", "iText", "color", "vertical alignment", "horizontal alignment", "PdfWriter",
	        "ListItem", "PdfStamper", "PDF", "HTML", "XML", "column", "font",
	        "table", "FDF", "field", "NullPointerException", "CJK font"};

	    static String[] adjective = {"foul", "broken", "gray", "slow", "beautiful",
	       "throbbing", "sharp", "stout", "soundless", "neat",
	       "swift", "uniform", "upright", "vibrant", "dingy",
	       "vestigal", "messy", "sloppy", "baleful", "boastful",
	       "dark", "capricious", "concrete", "deliberate", "sharp",
	        "drunken", "undisciplined", "perfect", "bloated"};

	    static String[] adverb = {"randomly", "quickly", "triumphantly", "suggestively",
	       "slowly", "angrily", "uncomfortably", "finally", "unexpectedly",
	       "hysterically", "thinly", "dryly", "blazingly",
	       "terribly", "bleakly", "irritably", "dazzlingly", "expectantly",
	       "impersonally", "abruptly", "awfully", "caressingly", "completely",
	       "undesirably", "drolly", "hypocritically", "blankly",
	       "dimly"};

	    private static String randomWord(String[] type)
	    {
	       return type[rand.nextInt(type.length)];
	    }

	    /**
	     * Generates a random poem line.
	     * @return a poem that is generated with some keywords.
	     */
	    public static String poemLine()
	    {
	       StringBuffer results = new StringBuffer(150);
	       results.append(randomWord(adjective));
	       results.append(" ");
	       results.append(randomWord(noun));
	       results.append(" ");
	       results.append(randomWord(verb));
	       results.append(" ");
	       results.append(randomWord(adverb));
	       results.append(", ");
	       return results.toString();
	 }
		
	public void doGet (HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
        String presentationtype = request.getParameter("presentationtype");
        
        // step 1
        Document document = new Document();
        try {
            // step 2: we set the ContentType and create an instance of the corresponding Writer
            if ("pdf".equals(presentationtype)) {
                response.setContentType("application/pdf");
                PdfWriter.getInstance(document, response.getOutputStream());
            }
            else if ("html".equals(presentationtype)) {
                response.setContentType("text/html");
                HtmlWriter.getInstance(document, response.getOutputStream());
            }
            else if ("rtf".equals(presentationtype)) {
                response.setContentType("text/rtf");
                RtfWriter.getInstance(document, response.getOutputStream());
            }
            else {
                response.sendRedirect("http://itextdocs.lowagie.com/tutorial/general/webapp/index.html#HelloWorld");
            }
            
            // step 3
            document.open();
            
            // step 4
            document.add(new Paragraph("Hello World"));
            document.add(new Paragraph(new Date().toString()));
        }
        catch(DocumentException de) {
            de.printStackTrace();
            System.err.println("document: " + de.getMessage());
        }
        
        // step 5: we close the document (the outputstream is also closed internally)
        document.close();

		
	    }
		
    public void saveToPDF(){
        Document document = new Document(PageSize.A4);

		try {
			

			 
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("c:\\HelloWorld.pdf"));
			document.open();
            MultiColumnText mct = new MultiColumnText();

			mct.addRegularColumns(document.left(), document.right(), 10f, 2);

            PdfContentByte cb = writer.getDirectContent();
            Image img = Image.getInstance("C:\\logo1.jpg");
			
			cb.addImage(img, 200, 0, 0, 100, 300,600);
            cb.addImage(img, 200, 0, 0, 100, 300, 300);

			
			mct.addElement(new Paragraph("Anders Anderson"));
			mct.addElement(new Paragraph("Obituary"));
			mct.addElement(new Paragraph("Obituary text fdjsk  skfjd kj dsdfkj s jfdkj kjsdf jsdf jksd fj d jkfs js sdkf dskf kdsf kjdsfkj sdkjf"));
			mct.addElement(new Paragraph("Detailed grave information"));
			mct.addElement(new Paragraph("Date of birth:"));// ( YYYY/MM/DD ): ???? ?? ??
			mct.addElement(new Paragraph("Date of decease:"));// ( YYYY/MM/DD ): ???? ?? ??
			mct.addElement(new Paragraph("burial_date:"));// ???? ?? ??
			mct.addElement(new Paragraph("Parish:"));// ?
			mct.addElement(new Paragraph("Commune:"));// ?
			mct.addElement(new Paragraph("County:"));// ?
			mct.addElement(new Paragraph("Graveyard:"));// ?
			mct.addElement(new Paragraph("Block:"));// ?
			mct.addElement(new Paragraph("Department:"));// ?
			mct.addElement(new Paragraph("Grave no:"));// ?
			
            document.add(mct);
			
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
		
    }
	
	
}
