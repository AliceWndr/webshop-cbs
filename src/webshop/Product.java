package webshop;

public class Product {
	
	private String type;
	private String size;
	private String color;
	private String gender;
	private double price;
	
	public Product(String type, String size, String color, String gender, double price) {
		this.type = type;
		this.size = size;
		this.color = color;
		this.gender = gender;
		this.price = price;
	}
	
	public Product(Product sample) {
		this.type = sample.type;
		this.size = sample.size;
		this.color = sample.color;
		this.gender = sample.gender;
		this.price = sample.price;
	}
	
	@Override
	public String toString() {
		return  type.toUpperCase() + ": "+ 
				size + ", " + color + ", " +
				gender + ", " + price;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
