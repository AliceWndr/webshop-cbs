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
		// Andre (Mac) --> /Users/andrejansson/git/webshop-cbs/CatalogueRaw
		// Aliz  (Win) --> C:\\Users\\alice\\git\\webshop-cbs\\CatalogueRaw
	}
	

	public static void init() throws ParseException {
		Scanner input = new Scanner(System.in);
		
		catalogue = importCatalogue(input);
		
		starterScreen(input);
		
		input.close();
	}
	
	public static void starterScreen(Scanner input) throws ParseException {
		System.out.print("Please, enter \"1\" if you are a new customer, "
				+ "or enter \"2\" if you already have an account! ");
		int choice = input.nextInt();
		if (choice == 1){
			newCustomer(input);
			starterScreen(input);
		} else {
			System.out.println("Enter your username: ");
			String username = input.next();
			System.out.println("Enter your password: ");
			String pass = input.next();
			Customer c = customerLogin(username, pass, input);
			if (c.getFirstName() != "fail") {
				browsingScreen(c, input);
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
	
	public static Customer customerLogin(String username, String pass, Scanner input) {
		int i = 0;
		boolean userFound = false;
		Customer c;
		Account a;
		Customer fail = new Customer("fail");
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
					return fail;
				} else {
					return c;
				}
			} else {
				i++;
				System.out.println("Username not registered!");
				return fail;
			}
		}
		System.out.println("We don't have any registered customers yet!");
		return fail;
	}
	
	public static void browsingScreen(Customer c, Scanner input) throws ParseException {
		System.out.print("Please, enter \"1\" if you'd like to browse the catalogue, "
				+ "or enter \"2\" if you'd like to log out! ");
		int choice = input.nextInt();
		if (choice == 1){
			System.out.println("\n\t\t--- CATALOGUE ---\n");
			ArrayList<Product> productList = catalogue.printItems();
			System.out.println("\n\t\t--- END ---\n");
			modifyCartScreen(c, productList, input);
		} else {
			c.getAccount().setLoggedIn(false);
			System.out.println("You've logged out successfully!");
			starterScreen(input);
		}
	}
	
	public static void modifyCartScreen(Customer c, ArrayList<Product> productList, Scanner input) {
		System.out.print("Please, enter \"1\" if you'd like to add items to your cart, "
				+ "or enter \"2\" if you'd like to remove items from your cart, "
				+ "or enter \"3\" if you'd like to proceed to checkout! ");
		int choice = input.nextInt();
		if (choice == 1){
			System.out.println("Type the product id of the item you want to add to your cart!");
			int id = input.nextInt();
			System.out.println("Type the quantity of the product you'd like to add!");
			int quantity = input.nextInt();
			boolean success = c.getCart().addToCart(catalogue, productList.get(id), quantity);
			if (success) {
				System.out.println("Product(s) succesfully ADDED!");
			} else {
				System.out.println("Product(s) NOT added!");
			}
			modifyCartScreen(c, productList, input);
		} else if (choice == 2) {
			System.out.println("Type the product id of the item you want remove from your cart!");
			int id = input.nextInt();
			System.out.println("Type the quantity of the product you'd like to remove!");
			int quantity = input.nextInt();
			boolean success = c.getCart().removeFromCart(catalogue, productList.get(id), quantity);
			if (success) {
				System.out.println("Product(s) succesfully REMOVED!");
			} else {
				System.out.println("Product(s) NOT removed!");
			}
			modifyCartScreen(c, productList, input);
		} else {
			System.out.println("IMPLEMENT CHECKOUT!!!");
		}
	}
	
	
}