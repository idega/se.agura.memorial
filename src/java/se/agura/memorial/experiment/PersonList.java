package se.agura.memorial.experiment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;


public class PersonList {
	private List list = new ArrayList(); 
	
	private DataModel model;


	public PersonList() {
		
		String[] firstNames = {"John", "Peter", "Bunny", "Martin", "Anders", "Pete", "Freddy", "Dido"};
		String[] surnames = {"Johnson", "Peterson", "Bunnyson", "Martinson", "Andersson", "Parker", "Scrollson", "Newman"};
		String[] birthDates = {"1910", "1930", "1940", "1960","1877", "1955"};
		
		Random rand = new Random();	 
		
	    rand.nextInt(firstNames.length);
		
		Person p;
		
		for (int i=0; i < 1000; i++ ) {
			p = new Person(
					firstNames[rand.nextInt(firstNames.length)] + "(" + i + ".) " , 
					surnames[rand.nextInt(surnames.length)], 
					birthDates[rand.nextInt(birthDates.length)]  );	
			
			list.add(p);
		}
		
		model = new ListDataModel(list);
	
		
		
	}
	
	public List getList() {
		return this.list;
	}
	
		
	public DataModel getModel() { return model; }
    public void setModel(DataModel model) { this.model = model; }	
	
	public int getCurrentRowIndex() { return model.getRowIndex(); }
}
