/*
 * This file is generated by jOOQ.
*/
package com.ef.model.jooq.tables;


import com.ef.model.jooq.DbParser;
import com.ef.model.jooq.tables.records.RequestHttpLogRecord;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class RequestHttpLog extends TableImpl<RequestHttpLogRecord> {

    private static final long serialVersionUID = -1772915228;

    /**
     * The reference instance of <code>db_parser.request_http_log</code>
     */
    public static final RequestHttpLog REQUEST_HTTP_LOG = new RequestHttpLog();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RequestHttpLogRecord> getRecordType() {
        return RequestHttpLogRecord.class;
    }

    /**
     * The column <code>db_parser.request_http_log.dt</code>.
     */
    public final TableField<RequestHttpLogRecord, Timestamp> DT = createField("dt", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>db_parser.request_http_log.ip</code>.
     */
    public final TableField<RequestHttpLogRecord, String> IP = createField("ip", org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>db_parser.request_http_log.status_code</code>.
     */
    public final TableField<RequestHttpLogRecord, Short> STATUS_CODE = createField("status_code", org.jooq.impl.SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>db_parser.request_http_log.user_agent</code>.
     */
    public final TableField<RequestHttpLogRecord, String> USER_AGENT = createField("user_agent", org.jooq.impl.SQLDataType.VARCHAR(200), this, "");

    /**
     * Create a <code>db_parser.request_http_log</code> table reference
     */
    public RequestHttpLog() {
        this(DSL.name("request_http_log"), null);
    }

    /**
     * Create an aliased <code>db_parser.request_http_log</code> table reference
     */
    public RequestHttpLog(String alias) {
        this(DSL.name(alias), REQUEST_HTTP_LOG);
    }

    /**
     * Create an aliased <code>db_parser.request_http_log</code> table reference
     */
    public RequestHttpLog(Name alias) {
        this(alias, REQUEST_HTTP_LOG);
    }

    private RequestHttpLog(Name alias, Table<RequestHttpLogRecord> aliased) {
        this(alias, aliased, null);
    }

    private RequestHttpLog(Name alias, Table<RequestHttpLogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DbParser.DB_PARSER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RequestHttpLog as(String alias) {
        return new RequestHttpLog(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RequestHttpLog as(Name alias) {
        return new RequestHttpLog(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RequestHttpLog rename(String name) {
        return new RequestHttpLog(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RequestHttpLog rename(Name name) {
        return new RequestHttpLog(name, null);
    }
}
