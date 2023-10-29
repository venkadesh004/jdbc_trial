import java.sql.*;
import java.util.*;

import programs.VerifyUser;
import programs.ShowProducts;
import programs.CartHandler;
import programs.OrderHandler;

class Main {
	public static void main(String args[]) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "system", "venkadesh");
			// PreparedStatement stmt = con.prepareStatement("select * from login");
			PreparedStatement stmt = null;
			// String[] table_names = { "login", "users", "seller", "customer_service", "products", "cart",
			// 		"order_table" };
			// for (String table_name : table_names) {
			// 	System.out.println("Table Name: " + table_name);
			// 	String query = "select * from " + table_name;

			// 	stmt = con.prepareStatement(query);

			// 	ResultSet rs = stmt.executeQuery();
			// 	ResultSetMetaData rsmd = rs.getMetaData();
			// 	int colCount = rsmd.getColumnCount();

			// 	System.out.println("Col count: " + colCount);

			// 	while (rs.next()) {
			// 		for (int i = 1; i <= colCount; i++) {
			// 			System.out.print(rs.getString(rsmd.getColumnName(i)) + " ");
			// 		}
			// 		System.out.println();
			// 	}
			// 	System.out.println();
			// }

			Scanner sc = new Scanner(System.in);

			System.out.println("Welcome to ECommerce Website!!!");

			System.out.print("Login\n1. User\n2. Seller\n3. Exit\nChoose your Option: ");

			int ch = sc.nextInt();
			int subCH;

			if (ch == 1) {
				System.out.println("Verifying account: ");

				String username, password;

				System.out.print("Enter the username: ");
				username = sc.next();

				System.out.print("Enter the password: ");
				password = sc.next();

				if (VerifyUser.checkUser(con, username, password)) {
					System.out.println("Account Verified");

					while (true) {
						System.out.println("\n\n\n");
						System.out.print("1. List the Products\n2. Show Cart\n3. Show Orders\n4. Exit\nChoose your option: ");
						ch = sc.nextInt();

						if (ch == 1) {
							System.out.println("Products available: ");
							ShowProducts.printProducts(con);
							while (true) {
								System.out.println("\n\n");
								System.out.print("1. Add items to Cart\n2. Exit\nChoose any option: ");
								subCH = sc.nextInt();
								
								if (subCH == 1) {
									System.out.println("Adding items to Cart\n Please procide the information below");
									System.out.print("Product ID: ");
									String p_id = sc.next();
									CartHandler.addToCart(con, username, p_id);
								} else {
									break;
								}
							}
						} else if (ch == 2) {
							System.out.println("User Cart: ");
							CartHandler.showCart(con, username);
							while (true) {
								System.out.println("\n\n");
								System.out.print("1. Remove Item from Cart\n2. Order and Item\n3.Exit\nChoose any Option: ");
								subCH = sc.nextInt();

								if (subCH == 1) {
									System.out.print("Enter the P_ID of the item: ");
									String p_id = sc.next();
									CartHandler.removeFromCart(con, username, p_id);
								} else if (subCH == 2) {
									System.out.print("Enter the P_ID of the item to Order: ");
									String p_id = sc.next();
									System.out.print("Enter the Quantity: ");
									int quantity = sc.nextInt();
									OrderHandler.addOrder(con, username, p_id, quantity);
								} else {
									break;
								}
							}
						} else if (ch == 3) {
							System.out.println("User Orders: ");
							OrderHandler.printOrders(con, username);
						} else {
							break;
						}
					}

				} else {
					System.out.println("Error in verifying user Account");
				}
			} else if (ch == 2) {
				System.out.println("Verifying account: ");

				String username, password;

				System.out.print("Enter the username: ");
				username = sc.next();

				System.out.print("Enter the password: ");
				password = sc.next();

				if (VerifyUser.checkSeller(con, username, password)) {
					System.out.println("Account Verified");
				} else {
					System.out.println("Error in verifying seller Account");
				}
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}