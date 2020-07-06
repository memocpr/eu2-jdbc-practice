package ass_jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_listX {

    @Test

    public void test1() throws SQLException {

        String dbUrl ="jdbc:oracle:thin:@18.233.65.51:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("select * from departments");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        int columnCount = resultSetMetaData.getColumnCount();


        List<Map<String,Object>> mapList=new ArrayList<>();




        while (resultSet.next()){

            for (int i = 1; i <=columnCount; i++) {

                Map<String,Object> row=new HashMap<>();

                row.put(resultSetMetaData.getColumnName(i),resultSet.getString(i));

                mapList.add(row);
            }


        }

        for (Map<String, Object> row : mapList) {

//            System.out.println(row.toString());
        }

        connection.close();
        statement.close();
        resultSet.close();

    }
}
