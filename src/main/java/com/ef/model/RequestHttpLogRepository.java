package com.ef.model;

import com.ef.model.jooq.tables.RequestHttpLog;
import org.jooq.*;

import java.sql.Timestamp;
import java.util.Optional;

import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.max;

public class RequestHttpLogRepository {

    private DSLContext create;

    public RequestHttpLogRepository(DSLContext create){
        this.create = create;
    }

    public Optional<Timestamp> findDateOfLastHttpRequest(){
         return Optional.ofNullable(create.select(max(RequestHttpLog.REQUEST_HTTP_LOG.DT))
                .from(RequestHttpLog.REQUEST_HTTP_LOG).fetchOne().value1());
    }

    public Result<Record2<String,Integer>> findAllBy(Arguments arguments){
        Result<Record2<String,Integer>> result = create.select(RequestHttpLog.REQUEST_HTTP_LOG.IP.as("IP"),count().as("Resquests Total"))
                .from(RequestHttpLog.REQUEST_HTTP_LOG)
                .where(RequestHttpLog.REQUEST_HTTP_LOG.DT.
                        between(Timestamp.valueOf(arguments.getStartDate()),Timestamp.valueOf(arguments.getEndDate())))
                .groupBy(RequestHttpLog.REQUEST_HTTP_LOG.IP)
                .having(count().gt(arguments.getThreshold())).fetch();

        return result;
    }
}
