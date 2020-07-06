package ass_jdbctests;

import java.sql.*;

public class MainX {

    public static void main(String[] args) throws SQLException {

        String dbUrl ="jdbc:oracle:thin:@18.233.65.51:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";


        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT first_name from employees");


        while (resultSet.next()){

            System.out.println(resultSet.getString(1));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
