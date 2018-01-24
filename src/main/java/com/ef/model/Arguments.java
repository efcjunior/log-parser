package com.ef.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Arguments {

    private static final Logger LOG = Logger.getLogger(Arguments.class.getName());
    private static final DateTimeFormatter dtFormatter;
    private static final String ACCESS_LOG_ARG_NAME = "--accesslog";
    private static final String START_DATE_ARG_NAME = "--startDate";
    private static final String DURATION_ARG_NAME = "--duration";
    private static final String THRESHOLD_ARG_NAME = "--threshold";
    private static Arguments arguments;
    enum Duration {hourly, daily};

    private class Argument{
        private String name;
        private String value;

        Argument(String arg){
            String[] str = arg.split("=");
            this.name = str[0];
            this.value = str[1];
        }
    }

    private Optional<Path> pathAcessLog = Optional.empty();
    private InputStream inputStreamAccessLog;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Duration duration;
    private Integer threshold;

    static {
        dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    private Arguments(String[] args) {
        Stream.of(args).map(arg -> {
            Argument argument = new Argument(arg);
            return argument;
        }).forEach(item -> {
            if(isAccessLog.test(item.name)){
                toPathAccessLog(item.value);
            }else if (isStarDate.test(item.name)){
                toStartDate(item.value);
                checkPresentArgument(getStartDate(),item.name);
            }else if (isDuration.test(item.name)){
                duration = Duration.valueOf(item.value);
                checkPresentArgument(duration,item.name);
            }else if (isThreshold.test(item.name)){
                threshold = Integer.valueOf(item.value) >= 0 ? Integer.valueOf(item.value) : null;
                checkPresentArgument(threshold,item.name);
            }else {
                throw new RuntimeException(item.name + " is invalid argument");
            }
        });

        setEndDate();
    }

    public static Arguments builder(String[] args){
        if(arguments == null){
            return new Arguments(args);
        }
        return arguments;
    }

    public Optional<Path> getPathAcessLog() {
        return pathAcessLog;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    private void checkPresentArgument(Object arg, String argName) throws RuntimeException{
        if(arg == null || !Optional.of(arg).isPresent()){
            throw new RuntimeException("Value not found or invalid for argument: " + argName);
        }
    }

    private Predicate<String> isAccessLog = (arg) -> {
        return isValidArgument(arg,ACCESS_LOG_ARG_NAME);
    };

    private Predicate<String>  isStarDate = (arg) -> {
        return isValidArgument(arg,START_DATE_ARG_NAME);

    };

    private Predicate<String>  isDuration = (arg) -> {
        return isValidArgument(arg,DURATION_ARG_NAME);
    };

    private Predicate<String>  isThreshold = (arg) -> {
        return isValidArgument(arg,THRESHOLD_ARG_NAME);
    };

    private Boolean isValidArgument(String arg, String nameArg){
        return arg.split("=")[0].equalsIgnoreCase(nameArg);
    }

    private void toPathAccessLog(String uri) {
        pathAcessLog = Optional.of(Paths.get(uri));
    }

    private void toStartDate(String dt){
        startDate = ofDatePattern(dt);
    }

    private LocalDateTime ofDatePattern(String dt){
        return LocalDateTime.parse(dt.replace("."," "),dtFormatter);
    }

    private void setEndDate(){
        switch (duration){
            case daily:
                endDate = startDate.plusHours(24).minusSeconds(1);;
                break;
            case hourly:
                endDate = startDate.plusHours(1).minusSeconds(1);
        }
    }
}