package webshop;

public class Address {
	
	private String country;
	private String city;
	private String street;
	private String houseNr;
	private String zip;
	
	public Address(String country, String city, String street, String houseNr, String zip) {
		this.country = country;
		this.city = city;
		this.street = street;
		this.houseNr = houseNr;
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return houseNr + " " + street + ", "
			   + city + ", "
			   + zip + " " + country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNr() {
		return houseNr;
	}

	public void setHouseNr(String houseNr) {
		this.houseNr = houseNr;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
}
