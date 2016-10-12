package webshop;

import java.text.ParseException;

public class WebShop {

	public static void main(String[] args) throws ParseException {

		Address mAddress = new Address("Sweden", "Stockholm", "Odengatan", "20", "2500");
		
		Customer mary = new Customer("Mary", "Smith", mAddress, "1980-01-01");  
		
		System.out.println(mary.toString());
	}

}