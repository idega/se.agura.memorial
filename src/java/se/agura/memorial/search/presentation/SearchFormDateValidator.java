package se.agura.memorial.search.presentation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.idega.idegaweb.IWBundle;
import com.idega.presentation.IWContext;

public class SearchFormDateValidator implements Validator {
	
	final static String DATE_PATTERN = "^[0-9]*$";
	final static String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	
	public void validate(FacesContext facesContext, UIComponent arg1, Object arg2) throws ValidatorException {
		
		String s = (String) arg2; 
		
		if (!isStringMatchingRegexp(s, DATE_PATTERN)) {	
			
			IWContext iwContext = IWContext.getIWContext(facesContext);
    		IWBundle bundle = iwContext.getIWMainApplication().getBundle(IW_BUNDLE_IDENTIFIER);
    		ValueBinding vbSummary = bundle.getValueBinding("messages.invalid_date_summary"); 
			ValueBinding vbDetail = bundle.getValueBinding("messages.invalid_date_detail");  
			
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					vbSummary.getValue(facesContext).toString(), // summary
					vbDetail.getValue(facesContext).toString()// detail
			);											
			
//	        FacesMessage facesMsg =
//	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong date", "Your input doesn't look like valid date");
	
	        throw new ValidatorException(facesMsg);
		}
		
	}
	
	
	private boolean isStringMatchingRegexp(String s, String regexp){
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}	
}
