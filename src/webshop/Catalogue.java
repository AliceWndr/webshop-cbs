package webshop;

import java.util.HashMap;
import java.io.*;

public class Catalogue {
	
	private static final String PATH = "/Users/andrejansson/git/webshop-cbs/CatalogueRaw";
	
	private HashMap<Product, Integer> stock;
	
	public Catalogue() throws IOException {
		this.stock = loadStock(PATH);
	}
	
	private static HashMap<Product, Integer> loadStock(String path) throws IOException {
		FileReader fileReader = new FileReader(path);
		BufferedReader fileBufferedReader = new BufferedReader(fileReader);
		String line = fileBufferedReader.readLine();
		line = fileBufferedReader.readLine(); // skip header
		
		HashMap<Product, Integer> stocks = new HashMap<>();
		
		while (line != null) {
			String[] attributes = line.split(",");
			
			String size = attributes[0];
			String color = attributes[1];
			String gender = attributes[2];
			double price = Double.parseDouble(attributes[3]);
			Integer quantity = Integer.parseInt(attributes[4]);
			
			stocks.put(new Product(size, color, gender, price), quantity);
				
			//next line
			line = fileBufferedReader.readLine();
		}
	
		fileBufferedReader.close();
		
		return stocks;
	}

	public HashMap<Product, Integer> getStock() {
		return stock;
	}
	
	

}


