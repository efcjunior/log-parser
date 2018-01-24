package com.ef.infra;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JOOQManager {

    private static final Logger LOG = Logger.getLogger(JOOQManager.class.getName());
    private static Optional<JOOQManager> jOOPManager = Optional.empty();
    private Connection conn;
    private DSLContext dslContext;

    private JOOQManager () throws SQLException {
            conn = ConnectionFactory.getConnection();
            LOG.log(Level.INFO,"Database Connection....OK");
            dslContext = DSL.using(conn, SQLDialect.MYSQL);
            LOG.log(Level.INFO,"Data source context....OK");

    }

    public static JOOQManager getInstance() throws SQLException {
        if(!jOOPManager.isPresent()){
            jOOPManager = Optional.of(new JOOQManager());
        }
        return jOOPManager.get();
    }

    public void close(){
        try {
            ConnectionFactory.closeConnection(conn,null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DSLContext create(){
        return this.dslContext;
    }

}
