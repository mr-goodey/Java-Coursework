package Coursework;

import java.util.*;

public class RetailPriceCompare implements Comparator<Product> {
	public int compare(Product p1, Product p2) {
		if (p1.getRetailPrice() < p2.getRetailPrice()) {
			return -1;
		} else if (p1.getRetailPrice() > p2.getRetailPrice()) {
			return 1;
		} else {
			return 0;
		}
	}
}