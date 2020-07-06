package ass_jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class java_util_exampleX {

    @Test

    public void test1() throws SQLException {

        String dbUrl ="jdbc:oracle:thin:@18.233.65.51:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //create the connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        //to navigate up and down in query result and to read only, not to get database updates --> pass parameters
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum<6");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        List < Map < String, Object >> queryData = new ArrayList<>();

        Map<String,Object> row1=new HashMap<>();

        resultSet.next();

        row1.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row1.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));

        Map<String,Object> row2=new HashMap<>();

        resultSet.next();

        row2.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row2.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));

        queryData.add(row1);
        queryData.add(row2);

        System.out.println("queryData.size() = " + queryData.size());

        System.out.println(queryData.toString());


        connection.close();
        statement.close();
        resultSet.close();


    }
}
