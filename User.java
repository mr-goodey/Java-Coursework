package Coursework;

public class User {
	private int id;
	private String username;
	private String name;
	private int houseNumber;
	private String postcode;
	private String city;
	public User(int id, String username, String name, int houseNumber, String postcode, String city) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.houseNumber = houseNumber;
		this.postcode = postcode;
		this.city = city;
	}
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getName() {
		return name;
	}
	public int getHouseNumber() {
		return houseNumber;
	}
	public String getPostcode() {
		return postcode;
	}
	public String getCity() {
		return city;
	}
}