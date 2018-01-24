package com.ef.infra;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static final Logger LOG = Logger.getLogger(ConnectionFactory.class.getName());
    private static Properties dbProperties;
    private static String url;
    private static String dbName;
    private static String driverClassName;
    private static Driver dbDriver;

    static{
        try{
            dbProperties = new Properties();
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            InputStream inputStream = classLoader.getResourceAsStream("db/jdbc.properties");
            InputStream inputStream = new FileInputStream("./jdbc.properties");
            dbProperties.load(inputStream);

            driverClassName = dbProperties.getProperty("driver");
            dbName = dbProperties.getProperty("database");
            url = dbProperties.getProperty("url").concat(dbName);
            dbProperties.getProperty("user");
            dbProperties.getProperty("password");

            dbDriver = (Driver)Class.forName(driverClassName).newInstance();

        }catch(Exception e){
            LOG.log(Level.SEVERE,e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return dbDriver.connect(url,dbProperties);
    }

    public static void closeConnection(Connection con, Statement st, ResultSet rs) throws Exception{
        if(con!=null)
            con.close();
        if(st!=null)
            st.close();
        if(rs!=null)
            rs.close();
    }
}
