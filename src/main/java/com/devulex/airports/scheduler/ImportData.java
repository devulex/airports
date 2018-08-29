package com.devulex.airports.scheduler;

import com.devulex.airports.cache.AirportsCache;
import com.devulex.airports.csv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.FileInputStream;

/**
 * Планировщик задач импорта данных.
 *
 * @author Aleksandr Uhanov
 * @since 2018-08-28
 */
@Component
public class ImportData {

    private static final Logger log = LoggerFactory.getLogger(ImportData.class);

    private final CSVReader csvReader;

    private final AirportsCache airportsCache;

    @Value("${inputFilePath}")
    private String inputFilePath;

    @Value("classpath:airports.dat")
    private Resource defaultInputFile;

    public ImportData(CSVReader csvReader, AirportsCache airportsCache) {
        this.csvReader = csvReader;
        this.airportsCache = airportsCache;
    }

    /**
     * Выполнить импорт списка аэропортов каждый час.
     * Метод также будет выполнен при запуске приложения.
     */
    @Scheduled(fixedRate = 3600_000)
    public void periodicImport() {
        StopWatch sw = new StopWatch();
        sw.start();
        try {
            if (inputFilePath == null || inputFilePath.isEmpty()) {
                airportsCache.setAirports(csvReader.read(defaultInputFile.getInputStream()));
            } else {
                File initialFile = new File(inputFilePath);
                airportsCache.setAirports(csvReader.read(new FileInputStream(initialFile)));
            }
        } catch (Exception e) {
            log.error("Ошибка при импорте списка аэропортов из файла", e);
            return;
        }
        sw.stop();
        log.info(String.format("Импорт списка аэропортов из файла выполнен успешно за %d мс", sw.getTotalTimeMillis()));
    }
}
