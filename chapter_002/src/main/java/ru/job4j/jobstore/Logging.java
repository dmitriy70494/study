package ru.job4j.jobstore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;


public class Logging {

    private StoreJob store;

    private Logger logger;

    Timestamp stopDate;

    public Logging(StoreJob store, Logger logger, Timestamp stopDate) {
        this.store = store;
        this.logger = logger;
        this.stopDate = stopDate;
    }

    /**
     * Пишет в файл log.log данные стринг логгинг
     */
    public void logging() {
       logger.info(this.store.stringLogging(stopDate));
    }
}
