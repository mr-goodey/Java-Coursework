package Coursework;

public class Product {
	private int barcode;
	private String brand;
	private String colour;
	private String connectivity;
	private int quantityInStock;
	private int inStock;
	private int inBasket;
	private float originalPrice;
	private float retailPrice;
	public Product(int barcode, String brand, String colour, String connectivity, int quantity, float originalPrice, float retailPrice) {
		this.barcode = barcode;
		this.brand = brand;
		this.colour = colour;
		this.connectivity = connectivity;
		this.quantityInStock = quantity;
		this.inStock = quantity;
		this.inBasket = 0;
		this.originalPrice = originalPrice;
		this.retailPrice = retailPrice;
	}
	public int getBarcode() {
		return barcode;
	}
	public String getBrand() {
		return brand;
	}
	public String getColour() {
		return colour;
	}
	public String getConnectivity() {
		return connectivity;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public int getInStock() {
		return inStock;
	}
	public int getInBasket() {
		return inBasket;
	}
	public float getOriginalPrice() {
		return originalPrice;
	}
	public float getRetailPrice() {
		return retailPrice;
	}
	public void addToBasket() {
		if (inStock > 0) {
			inStock -= 1;
			inBasket += 1;
		} else {
			System.out.println("Out of stock");
		}
	}
	public void cancel() {
		inStock = quantityInStock;
		inBasket = 0;
	}
	public void pay() {
		quantityInStock = inStock;
		inBasket = 0;
	}
}