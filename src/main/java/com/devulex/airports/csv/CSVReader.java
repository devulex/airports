package com.devulex.airports.csv;

import com.devulex.airports.entity.Airport;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Компонент для получения списка аэропортов из CSV-файла.
 *
 * @author Aleksandr Uhanov
 * @since 2018-08-28
 */
@Component
public class CSVReader {

    private static final int NUMBER_OF_COLUMNS = 14;

    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.withQuote('"').withNullString("\\N").withEscape('\\');

    /**
     * Прочитать список аэропортов из потока
     *
     * @param inputStream поток csv-файла
     * @return спискок аэропортов
     */
    public List<Airport> read(InputStream inputStream) throws IOException {
        List<Airport> airports = new ArrayList<>();
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, CSV_FORMAT)) {
            for (var csvRecord : csvParser) {
                if (csvRecord.size() != NUMBER_OF_COLUMNS) {
                    throw new IOException("Invalid file format. The number of columns must be " + NUMBER_OF_COLUMNS
                            + ". Record number is " + csvRecord.getRecordNumber());
                }
                String country = csvRecord.get(3);
                if (country == null || country.isEmpty()) {
                    throw new IOException("Country can not be null. Record number is " + csvRecord.getRecordNumber());
                }
                Airport airport = new Airport();
                airport.setId(Long.parseLong(csvRecord.get(0)));
                airport.setName(csvRecord.get(1));
                airport.setCity(csvRecord.get(2));
                airport.setCountry(country);
                airport.setIATA(csvRecord.get(4));
                airport.setICAO(csvRecord.get(5));
                airport.setLatitude(Double.parseDouble(csvRecord.get(6)));
                airport.setLongitude(Double.parseDouble(csvRecord.get(7)));
                airport.setAltitude(Integer.parseInt(csvRecord.get(8)));
                airport.setTimezoneOffset(csvRecord.get(9) != null ? new BigDecimal(csvRecord.get(9)) : null);
                airport.setDST(csvRecord.get(10));
                airport.setTimezoneName(csvRecord.get(11));
                airport.setType(csvRecord.get(12));
                airport.setSource(csvRecord.get(13));
                airports.add(airport);
            }
        }
        return airports;
    }
}
