package main;

public class Hotel {
	private int id;
	private int star;
	private String locality;
	private String address;
	private int totalPrice;
	
	public Hotel(int id, int star, String locality, String address, int totalPrice) {
		this.id = id;
		this.star = star;
		this.locality = new String(locality);
		this.address = new String(address);
		this.totalPrice = totalPrice;
	}
	
	public String toString() {
		String ret = "";
		ret += "id = ";
		ret += id;
		ret += ", star = ";
		ret += star;
		ret += ", locality = ";
		ret += locality;
		ret += ", address = ";
		ret += address;
		ret += ", totalPrice = ";
		ret += totalPrice;
		return ret;
	}
}
