package webshop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {

	private String firstName;
	private String lastName;
	private Address address;
	private Date dateOfBirth;
	private Account account;
	private ShoppingCart cart;
	
	public Customer(String firstName, String lastName, Address address, String birthDate) throws ParseException {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.setDateOfBirth(birthDate);
		this.account = new Account(this.firstName, this.lastName, this.address.getZip());
		this.cart = new ShoppingCart();
	}
	
	public Customer(String failureCheck) {
		this.firstName = failureCheck;
	}

	@Override
	public String toString() {
		return "PERSONAL - First name: " + firstName + "; " +
				   "Last name: "  + lastName + "; " +
				   "Address: " + address.toString() + "; " +
				   "Date of birth: " + getDateOfBirth() +
				   "\n" +
				   "ACCOUNT - " + this.account.toString();
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
	
	
	public String getDateOfBirth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(dateOfBirth);
	}


	public void setDateOfBirth(String birthDate) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	    this.dateOfBirth = fmt.parse(birthDate);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public ShoppingCart getCart() {
		return this.cart;
	}
	
	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}
}
