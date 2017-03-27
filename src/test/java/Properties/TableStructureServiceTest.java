package Properties;

import Properties.Entity.TableStructureEntity;
import Properties.TableStructureService.TableStructureService;
import Properties.TableStructureService.TableStructureServiceImpl;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by ZZ on 2017/3/26.
 */
public class TableStructureServiceTest {
    @Test
    public void setProperty() throws Exception {
        String path = "cdctables.properties";
        String tableName = "CentralEndData.cdc.dbo_PriceTrend_CT";
        TableStructureService tableStructureService = new TableStructureServiceImpl(path,tableName);
        String key = tableName + ".TimeStamp";
        String value = "2017/03/27 12:05:12";
        tableStructureService.setProperty(key,value);
    }

    @Test
    public void getSql() throws Exception {
        String path = "cdctables.properties";
        String tableName = "CentralEndData.cdc.dbo_PriceTrend_CT";
        TableStructureService tableStructureService = new TableStructureServiceImpl(path,tableName);
        System.out.println(tableStructureService.getSql());
    }

    @Test
    public void getTableEntity() throws Exception {
        String path = "cdctables.properties";
        String tableName = "CentralEndData.cdc.dbo_PriceTrend_CT";
        TableStructureService tableStructureService = new TableStructureServiceImpl(path, tableName);
        TableStructureEntity entity = tableStructureService.getTableStructureEntity();
        Arrays.stream(entity.getFields()).forEach(s-> System.out.println(s));
    }

}