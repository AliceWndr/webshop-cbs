package webshop;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.*;

public class Catalogue {
	
	protected HashMap<Product, Integer> stock;
	
	public Catalogue(String path) throws IOException {
		this.stock = loadStock(path);
	}
	
	public Catalogue(){}
	
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
	
	public void addProduct(Product product, int quantity) {
		if (stock.containsKey(product)) {
			stock.put(product, stock.get(product) + quantity);
		} else {
			stock.put(product, quantity);
		}
	}
	
	public boolean removeProduct(Product product, int quantity) {
		if (stock.containsKey(product)) {
			int quantityLeft = stock.get(product) - quantity;
			if (quantityLeft > 0) {
				stock.put(product, quantityLeft);
				return true;
			} else if (quantityLeft == 0) {
				stock.remove(product);
				return true;
			} else {
				System.out.println(
						"Quantity given (" + quantity + ") "
						+ "is higher than the "
						+ "currently available amount (" + stock.get(product) + ")!");
				return false;
			}
		} else {
			System.out.println("Product not found!");
			return false;
		}
	}
	
	public ArrayList<Product> printItems() {
		ArrayList<Product> productList = new ArrayList<>();
		int id = 0;
		for (Map.Entry<Product, Integer> entry : this.stock.entrySet()) {
			Product product = entry.getKey();
			Integer amount = entry.getValue();
			System.out.print("Product ID: " + id + " -- "+ product.toString() + " ");
			System.out.print("Amount: " + amount);
			System.out.println();
			productList.add(product);
			id += 1;
		}
		
		return productList;
	}

}