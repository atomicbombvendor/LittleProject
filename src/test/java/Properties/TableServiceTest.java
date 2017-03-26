package Properties;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by ZZ on 2017/3/26.
 */
public class TableServiceTest {
    @Test
    public void getTableEnity() throws Exception {
        String path = "cdctables.properties";
        String tableName = "CentralEndData.dbo_QuantitativeData_CT";
        TableService tableService = new TableService();
        TableEntity entity = tableService.getTableEnity(path, tableName);
        Arrays.stream(entity.getFields()).forEach(s-> System.out.println(s));
    }

}