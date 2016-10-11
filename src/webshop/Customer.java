package webshop;

public class Customer {

	private String firstName;
	private String lastName;
	private Address address;
	private String username;
	private String password;
	
	
	public Customer(String firstName, String lastName, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.setUsername();
		this.setPassword();
	}

	
	@Override
	public String toString() {
		return "First name: " + firstName + ";" +
				   "Last name: "  + lastName + "; " +
				   "Address: " + address.toString() + "; " +
				   "Username: " + username + "; " +
				   "Password: " + password;
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
		this.username = this.firstName.substring(0, 2) + this.lastName.substring(0, 2);
	}


	public String getPassword() {
		return password;
	}


	public void setPassword() {
		this.password = this.lastName.substring(0, 3) + this.address.getZip();
	}
		
}
