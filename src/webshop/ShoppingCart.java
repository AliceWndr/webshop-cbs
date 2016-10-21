package webshop;

import java.util.ArrayList;
public class ShoppingCart {
	
	ArrayList<Product> products;
	
	public ShoppingCart(){
		this.products = new ArrayList<Product>();
	}
	
	public double totalCost(){
		double sum = 0;
		for (Product p : products){
			sum = sum + p.getPrice();
		}
		return sum;
	}
	
	public void addProduct(Product p){
		products.add(p);
		
		
	}
	
}
