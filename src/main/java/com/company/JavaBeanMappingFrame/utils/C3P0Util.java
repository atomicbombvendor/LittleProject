package com.company.JavaBeanMappingFrame.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.sql.*;

public class C3P0Util {

    private static Logger log = Logger.getLogger(String.valueOf(C3P0Util.class));
    private static ComboPooledDataSource comboPooledDataSource = null;

    public static Connection getConnection(String server){
        Connection conn = null;

        try{
            comboPooledDataSource = new ComboPooledDataSource(server);
            conn = comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            log.error("server:"+server+" connection failed!",e);
        }

        return conn;
    }

    public static void close(Connection conn, PreparedStatement pst, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("Server close failed!", e);
            }
        }

        if(pst != null){
            try {
                pst.close();
            } catch (SQLException e) {
                log.error("PreparedStatement close failed!", e);
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("Connection close failed!", e);
            }
        }
    }

    public static void close(Connection conn, CallableStatement cst, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("Server close failed!", e);
            }
        }

        if(cst != null){
            try {
                cst.close();
            } catch (SQLException e) {
                log.error("CallableStatement close failed!", e);
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("Connection close failed!", e);
            }
        }
    }

    public static void close(Connection conn, PreparedStatement pst, ResultSet sourceRs, ResultSet targetRs){
        if(sourceRs!=null){
            try {
                sourceRs.close();
            } catch (SQLException e) {
                log.error("Server close failed!", e);
            }
        }

        if(targetRs!=null){
            try {
                targetRs.close();
            } catch (SQLException e) {
                log.error("Server close failed!", e);
            }
        }


        if(pst != null){
            try {
                pst.close();
            } catch (SQLException e) {
                log.error("CallableStatement close failed!", e);
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("Connection close failed!", e);
            }
        }
    }

    //Test getConnection
    public static void main(String[] args) {
        Connection conn=getConnection("GeExoiDevDB8009");
        System.out.println(conn.getClass().getName());
        close(conn,null,null);
    }
}

