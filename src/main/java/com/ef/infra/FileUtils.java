package com.ef.infra;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    private Path pathAcessLog;
    private InputStream inputStreamAccessLog;
    private LineNumberReader numberRdr;

    public FileUtils(String uri) throws IOException {
        this.pathAcessLog = Paths.get(uri);
        this.inputStreamAccessLog = new FileInputStream(pathAcessLog.toFile());
        this.numberRdr = new LineNumberReader(Files.newBufferedReader(pathAcessLog));
    }

    public Path getPathAcessLog() {
        return pathAcessLog;
    }

    public InputStream getInputStreamAccessLog() {
        return inputStreamAccessLog;
    }

    public LineNumberReader getNumberRdr() {
        return numberRdr;
    }
}