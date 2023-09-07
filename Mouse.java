package Coursework;

public class Mouse extends Product {
	private String type;
	private int buttons;
	public Mouse(int barcode, String type, String brand, String colour, String connectivity, int quantity, float originalPrice, float retailPrice, int buttons) {
		super(barcode, brand, colour, connectivity, quantity, originalPrice, retailPrice);
		this.type = type;
		this.buttons = buttons;
	}
	public String getType() {
		return type;
	}
	public int getButtons() {
		return buttons;
	}
	public String adminView() {
		return getBarcode() + ", mouse, " + getType() + ", " + getBrand() + ", " + getColour() + ", " + getConnectivity() + ", " + getInStock() + ", " + getOriginalPrice() + ", " + getRetailPrice() + ", " + getButtons();
	}
	public String customerView() {
		return getBarcode() + ", mouse, " + getType() + ", " + getBrand() + ", " + getColour() + ", " + getConnectivity() + ", " + getInStock() + ", " + getRetailPrice() + ", " + getButtons();
	}
	public String basketView() {
		if (getInBasket() > 0) {
			return getBarcode() + ", keyboard, " + getType() + ", " + getBrand() + ", " + getColour() + ", " + getConnectivity() + ", " + getInBasket() + ", " + getRetailPrice() + ", " + getButtons();
		} else {
			return null;
		}
	}
}