package ass_jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_exampleX {

    String dbUrl ="jdbc:oracle:thin:@18.233.65.51:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test

    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("select * from departments");


        resultSet.last();

        System.out.println(resultSet.getRow());

        System.out.println("resultSet.getMetaData().getColumnName(2) = " + resultSet.getMetaData().getColumnName(2));


        System.out.println("resultSet.getMetaData().getColumnCount() = " + resultSet.getMetaData().getColumnCount());

    }
}
