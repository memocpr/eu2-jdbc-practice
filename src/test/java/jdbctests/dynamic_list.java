package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {


    String dbUrl ="jdbc:oracle:thin:@18.233.65.51:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void metadataDynamic() throws SQLException {
        //create the connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //main list
        List<Map<String,Object>> queryData = new ArrayList<>();

        //number of columns
        int colCount = rsMetadata.getColumnCount();

        //loop through each row
        while(resultSet.next()){

            //for each row of query we create one map inside the list
            Map<String,Object> row = new HashMap<>();

            //some code to get values dynamically

            for (int i = 1; i <=colCount ; i++) {
                row.put(rsMetadata.getColumnName(i),resultSet.getObject(i)); //first one name of column(KEY), second one VALUE
            }

            //put map to main list
            queryData.add(row);
        }

        //print the result
        for (Map<String, Object> allRows : queryData) {
            System.out.println(allRows.toString());
        }

        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

}

