package com.segera.sufeeds;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class databaseconnection {
        static String user = "root";
        static String password = "";
        static String url = "jdbc:mysql://localhost/db_rooney_segera_189735";
        static String driver = "com.mysql.cj.jdbc.Driver";

        public static Connection getCon() throws ClassNotFoundException, SQLException {
            Connection con = null;
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            return con;
        }
    }

// Rooney Segera Mogaka 189735 icsb