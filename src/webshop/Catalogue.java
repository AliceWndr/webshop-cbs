package webshop;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Catalogue {
	
	private HashMap<Product, Integer> stock;
	
	public Catalogue(String path) throws IOException {
		this.stock = loadStock(path);
	}
	
	private static HashMap<Product, Integer> loadStock(String path) throws IOException {
		FileReader fileReader = new FileReader(path);
		BufferedReader fileBufferedReader = new BufferedReader(fileReader);
		String line = fileBufferedReader.readLine();
		line = fileBufferedReader.readLine(); // skip header
		
		HashMap<Product, Integer> stocks = new HashMap<>();
		
		while (line != null) {
			String[] attributes = line.split(",");
			
			String type = attributes[0];
			String size = attributes[1];
			String color = attributes[2];
			String gender = attributes[3];
			double price = Double.parseDouble(attributes[4]);
			Integer quantity = Integer.parseInt(attributes[5]);
			
			stocks.put(new Product(type, size, color, gender, price), quantity);
				
			//next line
			line = fileBufferedReader.readLine();
		}
	
		fileBufferedReader.close();
		
		return stocks;
	}

	public HashMap<Product, Integer> getStock() {
		return stock;
	}
	
	public boolean buyProduct(Product product, int n) {
		int amountLeft = this.stock.get(product);
		if (amountLeft > n) {
			this.stock.put(product, amountLeft-n);
			return true;
		} else if (amountLeft == n) {
			this.stock.remove(product);
			return true;
		} else {
			System.out.println("You couldn't place an order on this product higher than " + amountLeft);
			return false;
		}
	}
	
	public void printCatalogue() {
		for (Map.Entry<Product, Integer> entry : this.stock.entrySet()) {
			Product product = entry.getKey();
			Integer amount = entry.getValue();
			System.out.print("Product details: " + product.toString() + " ");
			System.out.print("Amount: " + amount);
			System.out.println();
		}
		
	}

}


