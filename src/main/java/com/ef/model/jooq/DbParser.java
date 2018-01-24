/*
 * This file is generated by jOOQ.
*/
package com.ef.model.jooq;


import com.ef.model.jooq.tables.FlywaySchemaHistory;
import com.ef.model.jooq.tables.RequestHttpLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbParser extends SchemaImpl {

    private static final long serialVersionUID = 685585945;

    /**
     * The reference instance of <code>db_parser</code>
     */
    public static final DbParser DB_PARSER = new DbParser();

    /**
     * The table <code>db_parser.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = com.ef.model.jooq.tables.FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>db_parser.request_http_log</code>.
     */
    public final RequestHttpLog REQUEST_HTTP_LOG = com.ef.model.jooq.tables.RequestHttpLog.REQUEST_HTTP_LOG;

    /**
     * No further instances allowed
     */
    private DbParser() {
        super("db_parser", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            RequestHttpLog.REQUEST_HTTP_LOG);
    }
}
