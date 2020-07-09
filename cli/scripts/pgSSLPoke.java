//
// pgSSLPoke
//
// Example PostgreSQL Java client for SSL verification methods testing

/*
 * Compile example:
 * javac pgSSLPoke.java
 *
 * Usage examples:
 * java -cp .:postgresql-42.2.8.jar pgSSLPoke 'jdbc:postgresql://10.20.30.40:5432/dbname' username password
 * java -cp .:postgresql-42.2.8.jar pgSSLPoke 'jdbc:postgresql://10.20.30.40:5432/dbname?sslmode=verify-ca&sslrootcert=/etc/pki/tls/certs/ca-bundle.crt' username password
 *
 */
import java.sql.*;
import java.io.*;
public class pgSSLPoke {
    public static void main(String[] args) {
        if (args.length != 3) {
                System.out.println("Usage: "+pgSSLPoke.class.getName()+" <jdbc:postgresql://<host>:<port>/<database> <username> <password>");
                System.exit(1);
        }
        try (Connection connection = DriverManager.getConnection(args[0], args[1], args[2])) {
            System.out.println("Java JDBC PostgreSQL Test");
            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT 1");
            while (resultSet.next()) {
                System.out.printf("%-30.30s%n", resultSet.getString("?column?"));
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}
