package Coursework;

public class Keyboard extends Product {
	private String type;
	private String layout;
	public Keyboard(int barcode, String type, String brand, String colour, String connectivity, int quantity, float originalPrice, float retailPrice, String layout) {
		super(barcode, brand, colour, connectivity, quantity, originalPrice, retailPrice);
		this.type = type;
		this.layout = layout;
	}
	public String getType() {
		return type;
	}
	public String getLayout() {
		return layout;
	}
	public String adminView() {
		return getBarcode() + ", keyboard, " + getType() + ", " + getBrand() + ", " + getColour() + ", " + getConnectivity() + ", " + getInStock() + ", " + getOriginalPrice() + ", " + getRetailPrice() + ", " + getLayout();
	}
	public String customerView() {
		return getBarcode() + ", keyboard, " + getType() + ", " + getBrand() + ", " + getColour() + ", " + getConnectivity() + ", " + getInStock() + ", " + getRetailPrice() + ", " + getLayout();
	}
	public String basketView() {
		if (getInBasket() > 0) {
			return getBarcode() + ", keyboard, " + getType() + ", " + getBrand() + ", " + getColour() + ", " + getConnectivity() + ", " + getInBasket() + ", " + getRetailPrice() + ", " + getLayout();
		} else {
			return null;
		}
	}
}