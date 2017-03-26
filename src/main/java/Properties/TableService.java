package Properties;

/**
 * Created by ZZ on 2017/3/26.
 */
public class TableService {
    private TableEntity tableEntity;
    private PropertiesEditor propertiesEditor;
    public TableService(){
        tableEntity = new TableEntity();
    }

    private void setPropertiesEditor(String path){
        propertiesEditor = new PropertiesEditor(path);
    }

    public TableEntity getTableEnity(String path, String tableName){
        setPropertiesEditor(path);
        String primaryKey = tableName + ".PrimaryKey";
        String fields = tableName + ".Fields";
        String timeStamp = tableName + ".Timestamp";
        if(propertiesEditor != null) {
            tableEntity.setPrimaryKeys(propertiesEditor.getPrimaryKey(primaryKey));
            tableEntity.setFields(propertiesEditor.getFields(fields));
            tableEntity.setTimestamp(propertiesEditor.getTimestamp(timeStamp));
            return tableEntity;
        }else{
            return null;
        }
    }

}
