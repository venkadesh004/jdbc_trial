package programs;
import java.util.*;
import java.sql.*;

public class ShowProducts {
    public static void printProducts(Connection conn) throws Exception {
        String query = "select p_id, name, price, availability, quantity, review, rating from products";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        System.out.println("P_ID\tName\tPrice\tAvailability\tQuantity\tReview\tRating");
        
        while (rs.next()) {
            System.out.println(rs.getString("p_id") + " " +rs.getString("name")+"\t"+rs.getString("price")+"\t"+rs.getString("availability")+"\t"+rs.getString("quantity")+"\t"+rs.getString("review")+"\t"+rs.getString("rating"));
        }
    }
}
