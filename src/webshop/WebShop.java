package webshop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;

public class WebShop {
	
	public static Catalogue catalogue;
	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public static void main(String[] args) throws ParseException, IOException {
		
		
		System.out.println(Payment.checkIfDigits("12341234123a", 12));
		
		System.out.println(Payment.checkCardExpiry("10/13"));
		
		System.out.println(Payment.checkName("Mrs. Mary Smith"));
		
		init();
		// Andre --> /Users/andrejansson/git/webshop-cbs/CatalogueRaw
		// Aliz  --> C:\\Users\\alice\\git\\webshop-cbs\\CatalogueRaw
		
		//catalogue.printCatalogue();
		
		Address mAddress = new Address("Sweden", "Stockholm", "Odengatan", "20", "2500");
		
		Customer mary = new Customer("Mary", "Smith", mAddress, "1980-01-01");
		
		System.out.println(mary);
				

		
/*		ShoppingCart cart = new ShoppingCart();
		cart.addProduct();
		cart.addProduct();
		
		System.out.println(cart.totalCost());*/
	}
	

	public static void init() throws ParseException {
		Scanner input = new Scanner(System.in);
		
		//catalogue = importCatalogue(input);
		
		starterScreen(input);
		
		input.close();
	}
	
	public static void starterScreen(Scanner input) throws ParseException {
		System.out.print("Please, enter \"1\" if you are a new customer, or enter \"2\" if you already have an account! ");
		int choice = input.nextInt();
		if (choice == 1){
			newCustomer(input);
			starterScreen(input);
		} else {
			System.out.println("Enter your username: ");
			String username = input.next();
			System.out.println("Enter your password: ");
			String pass = input.next();
			boolean userFound = customerLogin(username, pass, input);
			if (userFound) {
				System.out.println("Implement BROWSE!!!!!!");
			} else {
				starterScreen(input);
			}
		}
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
	
	public static void newCustomer(Scanner in) throws ParseException {
		System.out.print("Please, enter your country: ");
		String country = in.next();
		System.out.print("Please, enter your city: ");
		String city = in.next();
		System.out.print("Please, enter your street name: ");
		String street = in.next();
		System.out.print("Please, enter your house number: ");
		String houseNr = in.next();
		System.out.print("Please, enter your zip code: ");
		String zip = in.next();
		
		Address address = new Address(country, city, street, houseNr, zip);
		
		System.out.print("Please, enter your first name: ");
		String first = in.next();
		System.out.print("Enter your last name: ");
		String last = in.next();
		System.out.print("Enter your birth date in the format of yyyy-mm-dd: ");
		String birthday = in.next();
		
		Customer customer = new Customer(first, last, address, birthday);
		customers.add(customer);
		
		System.out.println("This is the only time your username and password is printed, so make sure you write it down!");
		System.out.println("Your username is: " + customer.getAccount().getUsername());
		System.out.println("Your password is: " + customer.getAccount().getPassword());
	}
	
	public static boolean customerLogin(String username, String pass, Scanner input) {
		int i = 0;
		boolean userFound = false;
		Customer c;
		Account a;
		while (i < customers.size() && !userFound) {
			c = customers.get(i);
			a = c.getAccount();
			if (a.isUserMatch(username)) { //check name
				userFound = true;
				int triesLeft;
				while (!a.isLoggedIn() && a.getTries() < Account.PASS_LIMIT) {
					triesLeft = a.login(pass); //check password
					if (triesLeft > 0) {
						System.out.println("Try to reenter your password: ");
						pass = input.next(); //reassign password to check
					}
				}
				if (!a.isLoggedIn()) {
					System.out.println("Sorry, you got banned out of our system!");
				}
			} else {
				i++;
			}
		}
		if (!userFound) {
			System.out.println("Username not registered!");
			return false;
		} else {
			return true;
		} 
	}

}