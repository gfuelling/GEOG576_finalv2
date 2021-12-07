import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) {
        Connection conn;
        Statement stmt;
        try {
            // load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // establish connection
            //note, I have port 5433 for this
            String url = "jdbc:postgresql://localhost:5433/Races";
            conn = DriverManager.getConnection(url, "postgres", "admin");

            // query the database
            String sql = "select race_name, surface, city, state, distance, elevation, " +
                    "ST_AsText(geom) as geom from races";
            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            // print the result
            if (res != null) {
                while (res.next()) {
                    System.out.println("race_name: " + res.getString("race_name"));
                    System.out.println("surface: " + res.getString("surface"));
                    System.out.println("city: " + res.getString("city"));
                    System.out.println("state: " + res.getString("state"));
                    System.out.println("distance: " + res.getString("distance"));
                    System.out.println("elevation: " + res.getString("elevation"));
                    System.out.println("geom: " + res.getString("geom"));
                }
            }

            // clean up
            stmt.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

