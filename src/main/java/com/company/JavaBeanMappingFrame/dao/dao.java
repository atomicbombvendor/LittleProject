package com.company.JavaBeanMappingFrame.dao;

import com.company.JavaBeanMappingFrame.utils.C3P0Util;
import com.company.JavaBeanMappingFrame.utils.ColumnToFieldMappingUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class dao {
    private final static Logger logger = Logger.getLogger(dao.class);

    public static <T> List<T> getEntitys(String dataBase, String sql, Class<T> clazz){

        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<T> result = new ArrayList<>();
        try{
            conn = C3P0Util.getConnection(dataBase);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            result = ColumnToFieldMappingUtil.ColumnFiled(rs, clazz);
        } catch (SQLException e) {
            logger.error("Query exception", e);
        }finally {
            C3P0Util.close(conn, ps, rs);
        }
        return result;
    }

}
