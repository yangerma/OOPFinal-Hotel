package main;
import java.util.*;

public class Hotel{
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
	
	public int getPrice() {
		return this.totalPrice;
	}
	
	//A comparator to sort hotels in ascending order
	public static Comparator<Hotel> cmpPrice = new Comparator<Hotel>() {
		public int compare(Hotel h_1, Hotel h_2) {
			int p_1 = h_1.getPrice();
			int p_2 = h_2.getPrice();
			return p_1 - p_2;
		}
	};

	public String toString() {
		String ret = "id = " + id + ", star = " + star + ", locality = " + locality +
				", address = " + address + ", totalPrice = " + totalPrice;
		return ret;
	}
}
