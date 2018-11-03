package com.company.Properties.Process;

import com.company.Properties.Entity.TableStructureEntity;

/**
 * Created by atomic on 3/27/2017.
 */
public interface TableStructureService {
    void setProperty(String key, String value);
    String getSql();
    TableStructureEntity getTableStructureEntity();
}
