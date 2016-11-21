package webshop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class WebShop {
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static Catalogue catalogue;
	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public static void main(String[] args) throws ParseException, IOException {
		
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
		input.nextLine();
		if (choice == 1){
			newCustomer(input);
			starterScreen(input);
		} else {
			System.out.println("Enter your username: ");
			String username = input.nextLine();
			System.out.println("Enter your password: ");
			String pass = input.nextLine();
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
				String filePath = input.nextLine();
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
		String country = in.nextLine();
		System.out.print("Please, enter your city: ");
		String city = in.nextLine();
		System.out.print("Please, enter your street name: ");
		String street = in.nextLine();
		System.out.print("Please, enter your house number: ");
		String houseNr = in.nextLine();
		System.out.print("Please, enter your zip code: ");
		String zip = in.nextLine();
		
		Address address = new Address(country, city, street, houseNr, zip);
		
		System.out.print("Please, enter your first name: ");
		String first = in.nextLine();
		System.out.print("Enter your last name: ");
		String last = in.nextLine();
		System.out.print("Enter your birth date in the format of yyyy-mm-dd: ");
		String birthday = in.nextLine();
		
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
						pass = input.nextLine(); //reassign password to check
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
		input.nextLine();
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
	
	public static void modifyCartScreen(Customer c, ArrayList<Product> productList, Scanner input) throws ParseException {
		System.out.print("Please, enter \"1\" if you'd like to add items to your cart, "
				+ "or enter \"2\" if you'd like to remove items from your cart, "
				+ "or enter \"3\" if you'd like to proceed to checkout! ");
		int choice = input.nextInt();
		input.nextLine();
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
			System.out.println("CONTENTS OF YOUR CART:");
			c.getCart().printItems();
			checkoutScreen(c, productList, input);
		}
	}
	
	public static void checkoutScreen(Customer c, ArrayList<Product> productList, Scanner input) throws ParseException {
		System.out.print("Please, enter \"1\" if you'd like to finalize your order, "
				+ "or enter \"2\" if you'd still like to modify your cart, "
				+ "or enter \"3\" if you'd like to log out (contents of cart are preserved)!");
		int choice = input.nextInt();
		input.nextLine();
		if (choice == 1) {
			// 1. define address to ship to
			Address shippingAddress;
			System.out.println("Sould we ship to your default address? 1 - Yes, 2 - No");
			int choiceAd = input.nextInt();
			input.nextLine();
			if (choiceAd == 1) {
				shippingAddress = c.getAddress();
			} else {
				System.out.println("Please, enter your shipping address as the system prompts you.");
				System.out.print("Please, enter your country: ");
				String country = input.nextLine();
				System.out.print("Please, enter your city: ");
				String city = input.nextLine();
				System.out.print("Please, enter your street name: ");
				String street = input.nextLine();
				System.out.print("Please, enter your house number: ");
				String houseNr = input.nextLine();
				System.out.print("Please, enter your zip code: ");
				String zip = input.nextLine();
				shippingAddress = new Address(country, city, street, houseNr, zip);
			}
			// 2. ask for card details
			System.out.println("Please, enter your card details as the system prompts you.");
			System.out.print("Enter name written on card: ");
			String name = input.nextLine();
			while (!Payment.checkName(name)) {
				System.out.println("Please, try to reenter your name: ");
				name = input.nextLine();
			}
			System.out.print("Enter cardnumber (12 digits): ");
			String cardNr = input.nextLine();
			while (!Payment.checkIfDigits(cardNr)) {
				System.out.println("Please, try to reenter your cardnumber: ");
				cardNr = input.nextLine();				
			};
			System.out.print("Enter expiry date in the format \"MM/YY\": ");
			String expiry = input.nextLine();
			while (!Payment.checkCardExpiry(expiry)) {
				System.out.println("Please, try to reenter the expiry date: ");
				expiry = input.nextLine();				
			};
			receiptScreen(c, input, shippingAddress, name, cardNr, expiry);
		} else if (choice == 2) {
			modifyCartScreen(c, productList, input);
		} else {
			c.getAccount().setLoggedIn(false);
			starterScreen(input);
		}
	}
	
	public static void receiptScreen(Customer c, Scanner input, Address shippingAddress, String name, String cardNr, String expiry) throws ParseException {
		ShoppingCart cart = c.getCart();
		System.out.println("Order succesfully placed! (We also emptied your cart)");
		cart.addGift(); // adding gift if limit exceeded
		System.out.println("Thank you for shopping here! Here's your receipt:");
		System.out.println("\n\t\t--- RECEIPT ---\n");
		cart.printItems();
		System.out.println("\nTOTAL COST: " + cart.totalCost());
		System.out.println("SHIPPING ADDRESS: " + shippingAddress.toString());
		System.out.println("CARD DETAILS: " + name + " " + cardNr + " " + expiry);
		Date date = new Date();
        System.out.println("DATE OF PURCHASE: " + DATE_FORMAT.format(date));
		System.out.println("\n\t\t--- END OF RECEIPT ---\n");
		c.setCart(new ShoppingCart()); // deleting contents of shopping cart
		System.out.println("Please, enter \"1\" if you'd like to log out, "
				+ "or enter \"2\" if you'd like to place another orther!");
		int choice = input.nextInt();
		input.nextLine();
		if (choice == 1) {
			c.getAccount().setLoggedIn(false);
			starterScreen(input);
		} else {
			browsingScreen(c, input);
		}
	}
	
}