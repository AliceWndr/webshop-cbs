package webshop;

import java.util.ArrayList;

public class ShoppingCart {
		
	ArrayList<Product> products;
	//Payment payment;
	
	public ShoppingCart() {
		this.products = new ArrayList<Product>();
		//this.payment = new Payment();
	}
	
	public double totalCost() {
		double sum = 0;
		for (Product p : products){
			sum = sum + p.getPrice();
		}
		return sum;
	}
	
	public void addProduct(Product p) {
		products.add(p);
	}
	
	public void removeProduct(Product p) {
		products.remove(p);
	}
	
	
	public void addToCart(Product product, int n) {
		for (int i = 0; i < n; i++) {
			Product copyOfProduct = new Product(product);
			this.addProduct(copyOfProduct);
		}
	}
	
	public void removeFromCart(Product product, int n) {
		for (int i = 0; i < n; i++) {
			Product copyOfProduct = new Product(product);
			this.removeProduct(copyOfProduct);
		}
	}
	
}
