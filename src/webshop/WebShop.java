package webshop;

public class WebShop {

	public static void main(String[] args) {

		Address mAddress = new Address("Sweden", "Stockholm", "Odengatan", "20", "2500");
		
		Customer mary = new Customer("Mary", "Smith", mAddress);  
		
		System.out.println(mary.toString());
	}

}