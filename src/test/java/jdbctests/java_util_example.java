package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class java_util_example {


    String dbUrl ="jdbc:oracle:thin:@18.233.65.51:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";



    @Test
    public void metadataMapList() throws SQLException {
        //create the connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from countries");

//        //get the database related data inside the dbMetadata object
//        DatabaseMetaData dbMetadata = connection.getMetaData();
//
//        System.out.println("User= "+dbMetadata.getUserName());
//        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
//        System.out.println("Database Product version = " + dbMetadata.getDatabaseProductVersion());
//        System.out.println("Driver =" + dbMetadata.getDriverName());
//        System.out.println("Driver =" + dbMetadata.getDriverVersion());
//
//        //get the resultset object metadata
//        ResultSetMetaData rsMetadata = resultSet.getMetaData();
//
//        //how many columns we have ?
//        System.out.println("Column count = " + rsMetadata.getColumnCount());

//======================================================================================================
        //CREATE A LIST OF MAP and PUT ALL DATA


        // 1 --> list for keeping all rows as a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        //======================================

        // 2 --> first row / Map
        Map<String,Object> row1 = new HashMap<>(); //first parameter: Key (String), second parameter Object (it can be anything : int , String)

        row1.put("first_name","StevenX");
        row1.put("last_name","KingX");
        row1.put("salary",240009);
        row1.put("job_id","AD_PRESSX");

        System.out.println(row1.toString());
        System.out.println(row1.get("first_name"));

        //second row / Map
        Map<String,Object> row2 = new HashMap<>();

        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");

        //======================================

        // 3 --> adding rows to my list
        queryData.add(row1);
        queryData.add(row2);


        //======================================

//        System.out.println(queryData.get(0).get("salary"));


        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void metadataNavigate() throws SQLException {
        //create the connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        //to navigate up and down in query result and to read only, not to get database updates --> pass parameters
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("Select first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum<6");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list for keeping all rows as a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        //-----------------------

        Map<String,Object> row1 = new HashMap<>();

        //move to first row
        resultSet.next();

        row1.put(rsMetadata.getColumnName(1),resultSet.getString(1));//first part of put is name of column(KEY), second one VALUE
        row1.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row1.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row1.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        System.out.println(row1.toString());

        System.out.println(row1.get("FIRST_NAME"));

        Map<String,Object> row2 = new HashMap<>();
        //move to second row
        resultSet.next();

        row2.put(rsMetadata.getColumnName(1),resultSet.getString(1));
        row2.put(rsMetadata.getColumnName(2),resultSet.getString(2));
        row2.put(rsMetadata.getColumnName(3),resultSet.getString(3));
        row2.put(rsMetadata.getColumnName(4),resultSet.getString(4));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);





        System.out.println("JOB_ID " + queryData.get(1).get("JOB_ID"));// first get() --> row of query and second get() --> column of query

        System.out.println(queryData.toString());

        System.out.println(queryData.get(0).get("SALARY"));


        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
