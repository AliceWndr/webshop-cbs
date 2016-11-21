package webshop;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart extends Catalogue {
	
	public static final double GIFT_LIMIT = 300;
	
	public ShoppingCart() {
		super();
		this.stock = new HashMap<Product, Integer>();
	}
	
	public boolean addToCart(Catalogue catalogue, Product product, int quantity) {
		boolean removedFromCatalogue = catalogue.removeProduct(product, quantity);
		if (removedFromCatalogue) {
			this.addProduct(product, quantity);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeFromCart(Catalogue catalogue, Product product, int quantity) {
		boolean removedFromCart = this.removeProduct(product, quantity);
		if (removedFromCart) {
			catalogue.addProduct(product, quantity);
			return true;
		} else {
			return false;
		}
	}
	
	public double totalCost() {
		double sum = 0;
		for (Map.Entry<Product, Integer> entry : stock.entrySet()) {
			sum = sum + entry.getKey().getPrice() * entry.getValue();
		}
		return sum;
	}
	
	public void addGift() {
		if (this.totalCost() >= GIFT_LIMIT) {
			Product gift = new Product("Gift Scarf", "onesize", "black", "unisex", 0.00);
			addProduct(gift, 1);
			System.out.println("You've just received a gift!");
		}
	}
	
}