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
	
	public static boolean checkIfDigits (String digits, int expectedLength) {
		int length = digits.length();
		if (expectedLength == length) {
			char c;
			for (int i = 0; i < length; i++) {
				c = digits.charAt(i);
				if (Character.isDigit(c)) {
					continue;
				} else {
					System.out.println("The " + (i+1) + ". character is not a number");
					return false;
				} 
			}
			System.out.println("Number is valid");
			return true;
		} else {
			System.out.println("Incorrect lenght");
			return false;
		}
	}
	
}
