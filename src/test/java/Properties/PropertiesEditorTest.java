package Properties;

import Properties.Properties.PropertiesEditorImpl;
import org.junit.Test;
import java.util.Arrays;

/**
 * Created by ZZ on 2017/3/26.
 */
public class PropertiesEditorTest {
    @Test
    public void getPrimaryKey() throws Exception {
            String path = "cdctables.properties";
            PropertiesEditorImpl p = new PropertiesEditorImpl(path);
            String primaryKey = "CentralEndData.dbo_QuantitativeData_CT.PrimaryKey";
            String fields = "CentralEndData.dbo_QuantitativeData_CT.Fields";
            /**
             * primaryKey: CompanyId;TrailingType;Period
             * primaryKey2: null
             * not exits hierarchy in key
             */
            Arrays.stream(p.getPrimaryKey(primaryKey)).forEach(s -> System.out.println(s));

            String[] str = p.getFields(fields);
            //for (String s: str) {System.out.println(s);}
            //System.out.println(Arrays.asList(str));
            Arrays.stream(str).forEach(s-> System.out.println(s));
    }

}