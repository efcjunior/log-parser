package com.ef;

import com.ef.infra.JOOQManager;
import com.ef.model.Arguments;
import com.ef.model.RequestHttpLogRepository;
import com.ef.service.LogLoader;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Parser {
    private static final Logger LOG = Logger.getLogger(Parser.class.getName());
    private static DSLContext dslContext;
    private static LogLoader logLoader;
    private static RequestHttpLogRepository requestHttpLogRepository;

    public static void main(String[] args) {
        Optional<Arguments> arguments = Optional.empty();
        try {

            LOG.log(Level.INFO,"Parser program starting....");
            dslContext = JOOQManager.getInstance().create();
            requestHttpLogRepository = new RequestHttpLogRepository(dslContext);
            logLoader = new LogLoader(dslContext,requestHttpLogRepository);

            LOG.log(Level.INFO,"Getting and validating arguments....");
             arguments = Optional.of((Arguments.builder(args)));
            LOG.log(Level.INFO,"Arguments....OK");

            arguments.ifPresent(
                    arg -> {
                        arg.getPathAcessLog().ifPresent(path -> {
                            try {
                                logLoader.load(path);
                            } catch (IOException e) {
                                LOG.log(Level.WARNING,e.getMessage());
                                LOG.log(Level.WARNING,"Skipping File Load.....");
                            }
                        });
                    });

        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOG.log(Level.INFO,"Executing Query.....");
        LOG.log(Level.INFO,"Query Result.....");
        Result<Record2<String,Integer>> result = requestHttpLogRepository.findAllBy(arguments.get());
        System.out.println(result);
        System.out.println(result.size() + " Total IP distinct in result");
    }
}
