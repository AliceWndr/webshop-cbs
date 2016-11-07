package webshop;

public class Product {
	
	private String size;
	private String color;
	private String gender;
	private double price;
	
	public Product(String size, String color, String gender, double price) {
		this.size = size;
		this.color = color;
		this.gender = gender;
		this.price = price;
	}
	
	public Product(Product sample) {
		this.size = sample.size;
		this.color = sample.color;
		this.gender = sample.gender;
		this.price = sample.price;
	}
	
	@Override
	public String toString() {
		return size + ", " + color + ", " +
				gender + ", " + price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


}
