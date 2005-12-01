/**
 * 
 */
package se.agura.memorial.obituary.bussiness;


import com.idega.business.IBOHomeImpl;

/**
 * @author is
 *
 */
public class ObituarySessionHomeImpl extends IBOHomeImpl implements
		ObituarySessionHome {
	protected Class getBeanInterfaceClass() {
		return ObituarySession.class;
	}

	public ObituarySession create() throws javax.ejb.CreateException {
		return (ObituarySession) super.createIBO();
	}

}
