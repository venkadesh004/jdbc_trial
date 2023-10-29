package programs;

import java.sql.*;

public class OrderHandler {
    public static void printOrders(Connection conn, String username) throws Exception {
        String query = "select products.name, order_table.quantity, products.price*order_table.quantity as total_price, order_table.o_date as order_date from order_table, products where order_table.p_id = products.p_id and order_table.user_id = (select user_id from users where username = '"+username+"')";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getString("quantity") + " " + rs.getString("total_price") + " " + rs.getString("order_date"));
        }
    }

    public static void addOrder(Connection conn, String username, String p_id, int quantity) throws Exception {
        String query = "select order_id from order_table";
        PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery();

        rs.last(); 

        String order_id = rs.getString("order_id");
        int index = 8;
        order_id = order_id.substring(0, index)+String.valueOf(Integer.parseInt(Character.toString(order_id.charAt(8)))+1);

        System.out.println("New order: "+order_id);

        query = "insert into order_table (order_id, p_id, user_id, o_date, amt, delivered, quantity) values ('"+order_id+"', '"+p_id+"', (select user_id from users where username = '"+username+"'), '28-OCT-23', (select price from products where p_id = '"+p_id+"')*"+quantity+", 0, "+quantity+")";

        // System.out.println(query);
        
        PreparedStatement stmtnew = conn.prepareStatement(query);
        ResultSet rsnew = stmtnew.executeQuery();

        System.out.println("Order Added!");
        printOrders(conn, username);
    }
}
