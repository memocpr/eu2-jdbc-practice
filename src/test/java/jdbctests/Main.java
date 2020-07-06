package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

            String dbUrl ="jdbc:oracle:thin:@18.233.65.51:1521:xe";
            String dbUsername = "hr";
            String dbPassword = "hr";

        //create the connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement();
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //move pointer to first row
//        resultSet.next();

//        System.out.println(resultSet.getInt(1)+" "+resultSet.getString("region_name"));

        //resultSet.next();

        //System.out.println(resultSet.getInt(1)+" "+resultSet.getString("region_name"));

        while(resultSet.next()){ // it ll continune until next row is not exist
            System.out.println(resultSet.getString(2)+" "+ resultSet.getString("last_name"));

        }

        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}