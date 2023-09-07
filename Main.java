package Coursework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	private static final DecimalFormat df = new DecimalFormat("0.00");
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome!");
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<String> usernames = new ArrayList<String>();
		FileReader inputFile = new FileReader("UserAccounts.txt");
		BufferedReader br = new BufferedReader(inputFile);
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] data = line.split(", ");
			int id = Integer.parseInt(data[0]);
			String username = data[1];
			usernames.add(username);
			String name = data[2];
			int houseNumber = Integer.parseInt(data[3]);
			String postcode = data[4];
			String city = data[5];
			if (data[6].equals("admin")) {
				Admin user = new Admin(id, username, name, houseNumber, postcode, city);
				users.add(user);
			} else if (data[6].equals("customer")) {
				Customer user = new Customer(id, username, name, houseNumber, postcode, city);
				users.add(user);
			}
		}
		br.close();
		ArrayList<Product> products = new ArrayList<Product>();
		FileReader newInputFile = new FileReader("Stock.txt");
		BufferedReader bufferedR = new BufferedReader(newInputFile);
		String line1 = null;
		while ((line1 = bufferedR.readLine()) != null) {
			String[] data1 = line1.split(", ");
			int barcode = Integer.parseInt(data1[0]);
			String brand = data1[3];
			String colour = data1[4];
			String connectivity = data1[5];
			int quantity = Integer.parseInt(data1[6]);
			float originalPrice = Float.parseFloat(data1[7]);
			float retailPrice = Float.parseFloat(data1[8]);
			if (data1[1].equals("keyboard")) {
				String type = data1[2];
				String layout = data1[9];
				Keyboard product = new Keyboard(barcode, type, brand, colour, connectivity, quantity, originalPrice, retailPrice, layout);
				products.add(product);
			} else if (data1[1].equals("mouse")) {
				String type = data1[2];
				int buttons = Integer.parseInt(data1[9]);
				Mouse product = new Mouse(barcode, type, brand, colour, connectivity, quantity, originalPrice, retailPrice, buttons);
				products.add(product);
			}
		}
		bufferedR.close();
		for (int i = 0; i < usernames.size(); i++) {
			System.out.println((i + 1) + ") " + usernames.get(i));
		}
		User currentUser = null;
		boolean validUsername = false;
		while (validUsername == false) {
			try {
				System.out.println("Who are you?");
				Scanner inputScanner = new Scanner(System.in);
				int input = inputScanner.nextInt();
				if ((input >= 1) && (input <= 4)) {
					currentUser = users.get(input - 1);
					validUsername = true;
					System.out.println("Hello, " + currentUser.getName());
				} else {
					System.out.println("No valid username selected");
				}
			} catch (InputMismatchException ex) {
				System.out.println("Expecting integer");
			}
		}
		boolean carryOn = true;
		while (carryOn == true) {
			if (currentUser != null) {
				if (currentUser instanceof Admin) {
					try {
						System.out.println("Select Function:\n1) View Products\n2) Add product\n3) Quit");
						Scanner inputScanner = new Scanner(System.in);
						int input = inputScanner.nextInt();
						if (input == 1) {
							RetailPriceCompare retailComp = new RetailPriceCompare();
							Collections.sort(products, retailComp);
							for (int i = 0; i < products.size(); i++) {
								if (products.get(i) instanceof Keyboard) {
									Keyboard key = (Keyboard)products.get(i);
									System.out.println(key.adminView());
								} else if (products.get(i) instanceof Mouse) {
									Mouse mouse = (Mouse)products.get(i);
									System.out.println(mouse.adminView());
								}
							}
						} else if (input == 2) {
							try {
								System.out.println("Enter barcode:");
								boolean validBarcode = false;
								Scanner input1 = new Scanner(System.in);
								int barcode = input1.nextInt();
								int length = String.valueOf(barcode).length();
								if (length == 6) {
									validBarcode = true;
								} else {
									System.out.println("Invalid barcode");
								}
								for (int i = 0; i < products.size(); i++) {
									if (products.get(i).getBarcode() == barcode) {
										validBarcode = false;
										System.out.println("Barcode already exists");
										break;
									}
								}
								System.out.println("Enter brand:");
								Scanner input2 = new Scanner(System.in);
								String brand = input2.nextLine();
								System.out.println("Enter colour:");
								Scanner input3 = new Scanner(System.in);
								String colour = input3.nextLine();
								System.out.println("Enter connectivity:");
								boolean validConnectivity = false;
								Scanner input4 = new Scanner(System.in);
								String connectivity = input4.nextLine();
								if (connectivity.equals("wired") || connectivity.equals("wireless")) {
									validConnectivity = true;
								} else {
									System.out.println("Invalid connectivity");
								}
								System.out.println("Enter quantity:");
								boolean validQuantity = false;
								Scanner input5 = new Scanner(System.in);
								int quantity = input5.nextInt();
								if (quantity >= 0) {
									validQuantity = true;
								} else {
									System.out.println("Invalid quantity");
								}
								System.out.println("Enter original cost:");
								boolean validOriginalCost = false;
								Scanner input6 = new Scanner(System.in);
								float originalPrice = input6.nextFloat();
								if (originalPrice > 0) {
									validOriginalCost = true;
								} else {
									System.out.println("Invalid original cost");
								}
								System.out.println("Enter retail price:");
								boolean validRetailPrice = false;
								Scanner input7 = new Scanner(System.in);
								float retailPrice = input7.nextFloat();
								if (retailPrice > 0) {
									validRetailPrice = true;
								} else {
									System.out.println("Invalid retail price");
								}
								System.out.println("Enter product type:");
								Scanner input8 = new Scanner(System.in);
								String productType = input8.nextLine();
								if (productType.equals("keyboard")) {
									System.out.println("Enter keyboard type:");
									boolean validKeyboardType = false;
									Scanner input9 = new Scanner(System.in);
									String keyboardType = input9.nextLine();
									if (keyboardType.equals("standard") || keyboardType.equals("flexible") || keyboardType.equals("gaming")) {
										validKeyboardType = true;
									} else {
										System.out.println("Invalid keyboard type");
									}
									System.out.println("Enter layout:");
									boolean validLayout = false;
									Scanner input10 = new Scanner(System.in);
									String layout = input10.nextLine();
									if (layout.equals("US") || layout.equals("UK")) {
										validLayout = true;
									} else {
										System.out.println("Invalid layout");
									}
									if (validBarcode == true && validConnectivity == true && validQuantity == true && validOriginalCost == true && validRetailPrice == true && validKeyboardType == true && validLayout == true) {
										Keyboard keyboard = new Keyboard(barcode, keyboardType, brand, colour, connectivity, quantity, originalPrice, retailPrice, layout);
										products.add(keyboard);
										FileWriter fw = new FileWriter("Stock.txt", true);
										BufferedWriter bw = new BufferedWriter(fw);
										bw.write("\n" + barcode + ", " + productType + ", " + keyboardType + ", " + brand + ", " + colour + ", " + connectivity + ", " + quantity + ", " + originalPrice + ", " + retailPrice + ", " + layout);
										bw.close();
									}
								} else if (productType.equals("mouse")) {
									System.out.println("Enter mouse type:");
									boolean validMousetype = false;
									Scanner input9 = new Scanner(System.in);
									String mouseType = input9.nextLine();
									if (mouseType.equals("standard") || mouseType.equals("gaming")) {
										validMousetype = true;
									} else {
										System.out.println("Invalid mouse type");
									}
									System.out.println("Enter number of buttons:");
									boolean validButtons = false;
									Scanner input10 = new Scanner(System.in);
									int buttons = input10.nextInt();
									if (buttons >= 0) {
										validButtons = true;
									} else {
										System.out.println("Invalid buttons");
									}
									if (validBarcode == true && validConnectivity == true && validQuantity == true && validOriginalCost == true && validRetailPrice == true && validMousetype == true && validButtons == true) {
										Mouse mouse = new Mouse(barcode, mouseType, brand, colour, connectivity, quantity, originalPrice, retailPrice, buttons);
										products.add(mouse);
										FileWriter fw = new FileWriter("Stock.txt", true);
										BufferedWriter bw = new BufferedWriter(fw);
										bw.write("\n" + barcode + ", " + productType + ", " + mouseType + ", " + brand + ", " + colour + ", " + connectivity + ", " + quantity + ", " + originalPrice + ", " + retailPrice + ", " + buttons);
										bw.close();
									}
								} else {
									System.out.println("Invalid product type");
								}
							} catch (InputMismatchException ex) {
								System.out.println("Expecting integer");
							}
						} else if (input == 3) {
							System.out.println("Goodbye");
							carryOn = false;
						} else {
							System.out.println("No valid function selected");
						}
					} catch (InputMismatchException ex) {
						System.out.println("Expecting integer");
					}
				} else if (currentUser instanceof Customer) {
					try {
						System.out.println("Select Function:\n1) View Products\n2) Add to Basket\n3) View Basket\n4) Pay\n5) Cancel Basket\n6) Search\n7) Quit");
						Scanner inputScanner = new Scanner(System.in);
						int input = inputScanner.nextInt();
						if (input == 1) {
							RetailPriceCompare retailComp = new RetailPriceCompare();
							Collections.sort(products, retailComp);
							for (int i = 0; i < products.size(); i++) {
								if (products.get(i) instanceof Keyboard) {
									Keyboard key = (Keyboard)products.get(i);
									System.out.println(key.customerView());
								} else if (products.get(i) instanceof Mouse) {
									Mouse mouse = (Mouse)products.get(i);
									System.out.println(mouse.customerView());
								}
							}
						} else if (input == 2) {
							try {
								System.out.println("Enter barcode:");
								Scanner inputB = new Scanner(System.in);
								int barcode = inputB.nextInt();
								int length = String.valueOf(barcode).length();
								if (length != 6) {
									System.out.println("Invalid barcode");
								}
								for (int i = 0; i < products.size(); i++) {
									if (products.get(i).getBarcode() == barcode) {
										products.get(i).addToBasket();
										break;
									}
								}
							} catch (InputMismatchException ex) {
								System.out.println("Expecting integer");
							}
						} else if (input == 3) {
							System.out.println("Basket:");
							float total = 0;
							for (int i = 0; i < products.size(); i++) {
								if (products.get(i).getInBasket() > 0) {
									if (products.get(i) instanceof Mouse) {
										float amount = ((float)products.get(i).getInBasket()) * (products.get(i).getRetailPrice());
										total += amount;
										Mouse mouse = (Mouse)products.get(i);
										System.out.println(mouse.basketView());
									} else if (products.get(i) instanceof Keyboard) {
										float amount = ((float)products.get(i).getInBasket()) * (products.get(i).getRetailPrice());
										total += amount;
										Keyboard keyboard = (Keyboard)products.get(i);
										System.out.println(keyboard.basketView());
									}
								}
							}
							System.out.println("Total: £" + df.format(total));
						} else if (input == 4) {
							float total = 0;
							for (int i = 0; i < products.size(); i++) {
								if (products.get(i).getInBasket() > 0) {
									float amount = ((float)products.get(i).getInBasket()) * (products.get(i).getRetailPrice());
									total += amount;
								}
							}
							String address = currentUser.getHouseNumber() + ", " + currentUser.getPostcode() + ", " + currentUser.getCity();
							try {
								if (total > 0) {
									System.out.println("Pay using...\n1) PayPal\n2) Credit Card");
									Scanner input1 = new Scanner(System.in);
									int option = input1.nextInt();
									if (option == 1) {
										System.out.println("Enter PayPal email address:");
										Scanner input2 = new Scanner(System.in);
										String email = input2.nextLine();
										Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
										Matcher mat = pattern.matcher(email);
										if (email.endsWith("@paypal.com") && mat.matches()) {
											String str = "";
											for (int i = 0; i < products.size(); i++) {
												products.get(i).pay();
											}
											FileWriter fileWriter = new FileWriter("Stock.txt");
											BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
											int length3 = products.size() - 1;
											for (int i = 0; i < length3; i++) {
												if (products.get(i) instanceof Mouse) {
													Mouse mouse = (Mouse)products.get(i);
													str += mouse.adminView() + "\n";
												} else if (products.get(i) instanceof Keyboard) {
													Keyboard keyboard = (Keyboard)products.get(i);
													str += keyboard.adminView() + "\n";
												}
											}
											if (products.get(length3) instanceof Mouse) {
												Mouse mouse = (Mouse)products.get(length3);
												str += mouse.adminView();
											} else if (products.get(length3) instanceof Keyboard) {
												Keyboard keyboard = (Keyboard)products.get(length3);
												str += keyboard.adminView();
											}
											bufferedWriter.write(str);
											bufferedWriter.close();
											System.out.println("£" + df.format(total) + " paid using PayPal, and the delivery address is " + address);
										} else {
											System.out.println("Invalid PayPal email address");
										}
									} else if (option == 2) {
										System.out.println("Enter card number:");
										boolean validCardNumber = false;
										Scanner input2 = new Scanner(System.in);
										int cardNumber = input2.nextInt();
										int length1 = String.valueOf(cardNumber).length();
										if (length1 == 6) {
											validCardNumber = true;
										} else {
											System.out.println("Invalid card number");
										}
										System.out.println("Enter security code:");
										boolean validSecurityCode = false;
										Scanner input3 = new Scanner(System.in);
										int securityCode = input3.nextInt();
										int length2 = String.valueOf(securityCode).length();
										if (length2 == 3) {
											validSecurityCode = true;
										} else {
											System.out.println("Invalid security code");
										}
										if (validCardNumber == true && validSecurityCode == true) {
											String str = "";
											for (int i = 0; i < products.size(); i++) {
												products.get(i).pay();
											}
											FileWriter fileWriter = new FileWriter("Stock.txt");
											BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
											int length3 = products.size() - 1;
											for (int i = 0; i < length3; i++) {
												if (products.get(i) instanceof Mouse) {
													Mouse mouse = (Mouse)products.get(i);
													str += mouse.adminView() + "\n";
												} else if (products.get(i) instanceof Keyboard) {
													Keyboard keyboard = (Keyboard)products.get(i);
													str += keyboard.adminView() + "\n";
												}
											}
											if (products.get(length3) instanceof Mouse) {
												Mouse mouse = (Mouse)products.get(length3);
												str += mouse.adminView();
											} else if (products.get(length3) instanceof Keyboard) {
												Keyboard keyboard = (Keyboard)products.get(length3);
												str += keyboard.adminView();
											}
											bufferedWriter.write(str);
											bufferedWriter.close();
											System.out.println("£" + df.format(total) + " paid using Credit Card, and the delivery address is " + address);
										}
									} else {
										System.out.println("No valid option selected");
									}
								} else {
									System.out.println("Nothing to pay for");
								}
							} catch (InputMismatchException ex) {
								System.out.println("Expecting integer");
							}
						} else if (input == 5) {
							for (int i = 0; i < products.size(); i++) {
								products.get(i).cancel();
							}
							System.out.println("Basket emptied");
						} else if (input == 6) {
							try {
								System.out.println("Search by...\n1) Brand\n2) Number of buttons\n3) Both");
								Scanner input1 = new Scanner(System.in);
								int option = input1.nextInt();
								if (option == 1) {
									System.out.println("Enter brand:");
									Scanner input2 = new Scanner(System.in);
									String brand = input2.nextLine();
									for (int i = 0; i < products.size(); i++) {
										if (products.get(i).getBrand().equals(brand)) {
											if (products.get(i).getInStock() > 0) {
												if (products.get(i) instanceof Mouse) {
													Mouse mouse = (Mouse)products.get(i);
													System.out.println(mouse.customerView());
												} else if (products.get(i) instanceof Keyboard) {
													Keyboard keyboard = (Keyboard)products.get(i);
													System.out.println(keyboard.customerView());
												}
											}
										}
									}
								} else if (option == 2) {
									System.out.println("Enter number of buttons:");
									Scanner input2 = new Scanner(System.in);
									int number = input2.nextInt();
									if (number < 0) {
										System.out.println("Negative number of buttons");
									} else {
										for (int i = 0; i < products.size(); i++) {
											if (products.get(i) instanceof Mouse) {
												Mouse mouse = (Mouse)products.get(i);
												if (mouse.getButtons() == number) {
													if (mouse.getInStock() > 0) {
														System.out.println(mouse.customerView());
													}
												}
											}
										}
									}
								} else if (option == 3) {
									System.out.println("Enter brand:");
									Scanner input2 = new Scanner(System.in);
									String brand = input2.nextLine();
									System.out.println("Enter number of buttons:");
									Scanner input3 = new Scanner(System.in);
									int number = input3.nextInt();
									if (number < 0) {
										System.out.println("Negative number of buttons");
									} else {
										for (int i = 0; i < products.size(); i++) {
											if (products.get(i) instanceof Mouse) {
												Mouse mouse = (Mouse)products.get(i);
												if (mouse.getButtons() == number && mouse.getBrand().equals(brand)) {
													if (mouse.getInStock() > 0) {
														System.out.println(mouse.customerView());
													}
												}
											}
										}
									}
								} else {
									System.out.println("No valid option selected");
								}
							} catch (InputMismatchException ex) {
								System.out.println("Expecting integer");
							}
						} else if (input == 7) {
							System.out.println("Goodbye");
							carryOn = false;
						} else {
							System.out.println("No valid function selected");
						}
					} catch (InputMismatchException ex) {
						System.out.println("Expecting integer");
					}
				}
			}
		}
	}
}