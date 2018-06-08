package ru.job4j.bufferedfilereader;

import java.io.*;

import org.slf4j.Logger;

public class BufferedFileReader extends InputStream {

    private Logger logger;

    private String line = "";

    private BufferedReader reader;


    private BufferedFileReader(BufferedReader reader, Logger logger) throws IOException {
        this.reader = reader;
        this.logger = logger;
    }
    /**
     * @param <T>
     * @param file
     * @param encoding кодировка файла, если null, то UTF-8 по умолчанию
     */
    public static <T> BufferedFileReader instanceWithLogging(T file, String encoding, Logger logger) {
        try {
            File files = file instanceof String ? new File((String) file) : (File) file;
            return new BufferedFileReader(new BufferedReader(new InputStreamReader(new FileInputStream(files), encoding)), logger);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        throw new IllegalStateException("BufferedReader not started, watch LOGGER, That's possible file not found");
    }

    public boolean hasNext() {
        return line != null;
    }

    public String nextLine() {
        try {
            line = reader.readLine();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return line;
    }

    @Override
    public int read() throws IOException {
        return reader.read();
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
