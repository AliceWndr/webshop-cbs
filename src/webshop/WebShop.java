package webshop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.text.ParseException;
import webshop.Product;
import webshop.ShoppingCart;

public class WebShop {

	public static void main(String[] args) throws ParseException, IOException {
		
		Catalogue cat = new Catalogue();

		Address mAddress = new Address("Sweden", "Stockholm", "Odengatan", "20", "2500");
		
		Customer mary = new Customer("Mary", "Smith", mAddress, "1980-01-01");  
		
		System.out.println(mary);
				
		for (Entry<Product, Integer> entry : cat.getStock().entrySet()) {
			  Product prod = entry.getKey();
			  Integer quant = entry.getValue();
			  System.out.println(prod);
			  System.out.println(quant);
			}
		
/*		ShoppingCart cart = new ShoppingCart();
		cart.addProduct();
		cart.addProduct();
		
		System.out.println(cart.totalCost());*/
	}

}