package ass_jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class dbutils_practiceX {

    @Test

    public void test(){


        DBUtils.createConnection();

        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap("select * from departments");

        for (Map<String, Object> rows : queryData) {

            System.out.println(rows);
        }

        DBUtils.destroy();
    }
}
