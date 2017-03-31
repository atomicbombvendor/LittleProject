package Properties.Process;

import org.junit.Test;

/**
 * Created by eli9 on 3/27/2017.
 */
public class TableStructureServiceImplTest {
    @org.junit.jupiter.api.Test
    void getSql1() {
    }

    @Test
    public void getTableStructureEntity() throws Exception {
    }

    @Test
    public void getSql() throws Exception {
        String root =Thread.currentThread().getContextClassLoader().getResource("").getPath().toString();
        String path = root + "cdctables.properties";
        String tableName = "CentralEndData.cdc.dbo_PriceTrend_CT";
        TableStructureService tableStructureService = new TableStructureServiceImpl(path,tableName);
        System.out.println(tableStructureService.getSql());
    }

    @Test
    public void setProperty() throws Exception {
    }

}