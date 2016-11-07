package webshop;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.text.ParseException;

public class WebShop {
	
	public static Catalogue catalogue;

	public static void main(String[] args) throws ParseException, IOException {
		
		
		System.out.println(Payment.checkIfDigits("12341234123a", 12));
		
		System.out.println(Payment.checkCardExpiry("10/13"));	
		
		System.out.println(Payment.checkName("Mrs. Mary Smith"));
		
		init();
		// Andre --> /Users/andrejansson/git/webshop-cbs/CatalogueRaw
		// Aliz  --> C:\\Users\\alice\\git\\webshop-cbs\\CatalogueRaw
		
		catalogue.printCatalogue();
		
		Address mAddress = new Address("Sweden", "Stockholm", "Odengatan", "20", "2500");
		
		Customer mary = new Customer("Mary", "Smith", mAddress, "1980-01-01");
		
		System.out.println(mary);
				

		
/*		ShoppingCart cart = new ShoppingCart();
		cart.addProduct();
		cart.addProduct();
		
		System.out.println(cart.totalCost());*/
	}
	

	public static void init() {
		Scanner input = new Scanner(System.in);
		
		catalogue = importCatalogue(input);
		
		input.close();
	}
	
	public static Catalogue importCatalogue(Scanner input) {
		System.out.println("To set up the webshop, please type in the path for the catalogue file:");
		while (true) {
			try {
				String filePath = input.next();
				Catalogue c = new Catalogue(filePath);
				System.out.println("Catalogue successfully imported!");
				return c;
			} catch (IOException e) {
				System.out.println("File not found. Try to set the path again:");
			}
		}
	}

}