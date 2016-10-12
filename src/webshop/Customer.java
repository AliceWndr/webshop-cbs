package webshop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {

	private String firstName;
	private String lastName;
	private Address address;
	private String username;
	private String password;
	private Date dateOfBirth;
	
	
	public Customer(String firstName, String lastName, Address address, String birthDate) throws ParseException {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.setUsername();
		this.setPassword();
		this.setDateOfBirth(birthDate);
	}

	
	@Override
	public String toString() {
		return "First name: " + firstName + ";" +
				   "Last name: "  + lastName + "; " +
				   "Address: " + address.toString() + "; " +
				   "Username: " + username + "; " +
				   "Password: " + password + "; " +
				   "Date of birth: " + getDateOfBirth();
	}
	

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername() {
		this.username = this.firstName.substring(0, 3) + this.lastName.substring(0, 3);
	}


	public String getPassword() {
		return password;
	}


	public void setPassword() {
		this.password = this.lastName.substring(0, 3) + this.address.getZip();
	}
	
	public String getDateOfBirth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(dateOfBirth);
	}


	public void setDateOfBirth(String birthDate) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	    this.dateOfBirth = fmt.parse(birthDate);
	} 
		
}
