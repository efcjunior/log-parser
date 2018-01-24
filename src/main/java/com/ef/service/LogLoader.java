package com.ef.service;

import com.ef.model.Arguments;
import com.ef.model.RequestHttpLogRepository;
import com.ef.model.jooq.tables.RequestHttpLog;
import com.ef.model.jooq.tables.records.RequestHttpLogRecord;
import org.jooq.DSLContext;
import org.jooq.Loader;
import org.jooq.Record1;
import org.jooq.Result;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ef.model.jooq.tables.RequestHttpLog.REQUEST_HTTP_LOG;

public class LogLoader {
    private static final Logger LOG = Logger.getLogger(LogLoader.class.getName());
    private DSLContext create;
    private RequestHttpLogRepository requestHttpLogRepository;

    public LogLoader(DSLContext create, RequestHttpLogRepository requestHttpLogRepository){
        this.create = create;
        this.requestHttpLogRepository = requestHttpLogRepository;
    }

    public void load(Path path) throws IOException {
        LOG.log(Level.INFO,"Initializing Load File Process....");
        Integer ignoreRows = 0;
        InputStream inputFS = new FileInputStream(path.toFile());

        Optional<Integer> numberLine = getNumberLineOfLastDatePersistedInFile(path);

        if(numberLine.isPresent()) {
            LOG.log(Level.INFO, "The last last line number of loaded file was: " + numberLine.get() + " ....");
            ignoreRows = numberLine.get();
        }

        Loader<RequestHttpLogRecord> loader = null;
        try {
            LOG.log(Level.INFO,"Wait while the program load the file from line number:  " + numberLine.get() + " ....");
            loader = create.loadInto(REQUEST_HTTP_LOG)
                    .loadCSV(inputFS)
                    .fields(REQUEST_HTTP_LOG.DT,
                            REQUEST_HTTP_LOG.IP,
                            null,
                            REQUEST_HTTP_LOG.STATUS_CODE,
                            REQUEST_HTTP_LOG.USER_AGENT)
                    .separator('|')
                    .ignoreRows(ignoreRows)
                    .execute();
            loadingSummary(loader);
        } catch (Exception error) {
            LOG.log(Level.SEVERE, error.getMessage());
        }
    }

    private Optional<Integer> getNumberLineOfLastDatePersistedInFile(Path path) throws IOException {
        LOG.log(Level.INFO,"Checking if the file had loaded before....");

        Optional<Timestamp> dateOfLastHttpRequest = this.requestHttpLogRepository.findDateOfLastHttpRequest();

        if(!dateOfLastHttpRequest.isPresent()){
            return Optional.of(0);
        }

        LineNumberReader numberRdr = new LineNumberReader(Files.newBufferedReader(path));
        Optional<Integer> numberLineFound = numberRdr.lines()
                .filter(w -> w.contains(dateOfLastHttpRequest.get().toString()))
                .map(w -> numberRdr.getLineNumber()).findFirst();

        return numberLineFound;
    }

    private void loadingSummary(Loader<RequestHttpLogRecord> loader) {
        StringBuilder log = new StringBuilder();

        log.append("\nThe number of processed rows: " + loader.processed());
        log.append("\nThe number of stored rows (INSERT or UPDATE): " + loader.stored());
        log.append("\nThe number of ignored rows (due to errors, or duplicate rule): " + loader.ignored());

        LOG.log(Level.INFO, "Loading Summary: " + log.toString());
    }
}
