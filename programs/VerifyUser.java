package programs;

import java.sql.*;

public class VerifyUser {
    public static boolean checkUser(Connection conn, String username, String password) {
        try {
            String query = "select user_type from login where username='" + username + "' and password='" + password
                    + "'";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            String type = rs.getString("user_type");

            if (type.equals("user")) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean checkSeller(Connection conn, String username, String password) {
        try {
            String query = "select user_type from login where username='" + username + "' and password='" + password
                    + "'";

            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            String type = rs.getString("user_type");

            if (type.equals("seller")) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
