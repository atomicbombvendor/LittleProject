package Properties.TableStructureService;

import Properties.Entity.TableStructureEntity;
import Properties.Entity.TableStructureEntityImpl;
import Properties.PropertiesEditor.PropertiesEditor;
import Properties.PropertiesEditor.PropertiesEditorImpl;

/**
 * Created by ZZ on 2017/3/26.
 */
public class TableStructureServiceImpl implements TableStructureService {
    private String tableName;
    private TableStructureEntity tableStructureEntity;
    private PropertiesEditor propertiesEditor;

    public TableStructureServiceImpl(String path, String tableName){
        tableStructureEntity = new TableStructureEntityImpl();
        propertiesEditor = new PropertiesEditorImpl(path);
        this.tableName = tableName;
    }

    public TableStructureEntity getTableStructureEntity(){
        String primaryKey = tableName + ".PrimaryKey";
        String fields = tableName + ".Fields";
        String timeStamp = tableName + ".TimeStamp";
        if(propertiesEditor != null) {
            tableStructureEntity.setPrimaryKeys(propertiesEditor.getPrimaryKey(primaryKey));
            tableStructureEntity.setFields(propertiesEditor.getFields(fields));
            tableStructureEntity.setTimestamp(propertiesEditor.getTimestamp(timeStamp));
            return tableStructureEntity;
        }else{
            return null;
        }
    }

    public String getSql(){
        TableStructureEntity tableStructureEntity = getTableStructureEntity();
        return buildSql(tableStructureEntity);
    }

    public String buildSql(TableStructureEntity tableStructureEntity){
        String sql;
        String selectSql = "Select ";
        String fromSql;
        String whereSql;
        String dataBaseName  = tableName.split("\\.")[0];
        selectSql += String.join(",", tableStructureEntity.getPrimaryKeys()) + ",";
        selectSql += String.join(",", tableStructureEntity.getFields());
        selectSql += ",row_number() over(partition by "+ String.join(",", tableStructureEntity.getPrimaryKeys()) +" order by __$start_lsn desc,__$seqval " +
                "desc) as rn";
        fromSql = " From " + tableName + " join " + dataBaseName + ".cdc.lsn_time_mapping "
                + "On " + tableName + ".__$start_lsn = " + dataBaseName + ".cdc.lsn_time_mapping.start_lsn";
        whereSql = " Where __$operation In (1,2,4) and " +
                "tran_begin_time >= '" + tableStructureEntity.getTimestamp() + "'";
        sql = "Select * From (" + selectSql + fromSql + whereSql + ") R Where rn=1;";
        return sql;
    }

    public void setProperty(String key, String value){
        propertiesEditor.setProperty(key, value);
    }

}
