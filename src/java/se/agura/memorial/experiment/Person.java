package se.agura.memorial.experiment;

public class Person {
	String firstName;
	String surname;
	String birthDate;
	
	
	public Person(String name, String surname, String date) {
		super();
		
		firstName = name;
		this.surname = surname;
		birthDate = date;
	}
	
	
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
