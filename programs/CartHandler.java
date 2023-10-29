package programs;

import java.sql.*;
import java.util.*;

public class CartHandler {
    public static void showCart(Connection conn, String username) throws Exception {
        String query = "select products.p_id, products.name, products.price, products.availability, products.quantity, products.review, products.rating from cart, products where cart.p_id = products.p_id and cart.user_id = (select user_id from users where username = '"+username+"')";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("p_id") + " " + rs.getString("name") + " " + rs.getString("price") + " " + rs.getString("availability") + " " + rs.getString("quantity") + " " + rs.getString("review") + " " + rs.getString("rating"));
        }
    }

    public static void addToCart(Connection conn, String username, String p_id) throws Exception {
        String query = "insert into cart (user_id, p_id, cart_id) values ((select user_id from users where username='"+username+"'), '"+p_id+"', (select cart_id from users where username='"+username+"'))";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        System.out.println("Cart Updated!!!");
        showCart(conn, username);
    }

    public static void removeFromCart(Connection conn, String username, String p_id) throws Exception {
        String query = "delete from cart where user_id = (select user_id from users where username = '"+username+"') and p_id = '"+p_id+"'";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        System.out.println("Item Deleted!!");
        showCart(conn, username);
    }
}
