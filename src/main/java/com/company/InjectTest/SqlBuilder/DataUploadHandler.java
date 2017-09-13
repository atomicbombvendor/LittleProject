package com.company.InjectTest.SqlBuilder;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by eli9 on 9/8/2017.
 */
@Component
public class DataUploadHandler {

    private SqlBuilder sqlBuilder;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DataUploadHandler(SqlBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

}
