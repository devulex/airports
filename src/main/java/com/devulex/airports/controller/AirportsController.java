package com.devulex.airports.controller;

import com.devulex.airports.cache.AirportsCache;
import com.devulex.airports.entity.Airport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST контроллер для ресурса Аэропорт
 *
 * @author Aleksandr Uhanov
 * @since 2018-08-28
 */
@RestController
public class AirportsController {

    private static final Logger log = LoggerFactory.getLogger(AirportsController.class);

    /**
     * TODO: Не все клиенты способны обработать большие списки данных.
     * TODO: В будущем необходимо предусмотреть пагинацию.
     */
    private static final int LIST_LIMIT = 1000;

    private final AirportsCache airportsCache;

    public AirportsController(AirportsCache airportsCache) {
        this.airportsCache = airportsCache;
    }

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> getAirports(@RequestParam(value = "country", required = false) String country) {
        try {
            List<Airport> airports;
            if (country == null || country.isEmpty()) {
                airports = airportsCache.getAllAirports();
            } else {
                airports = airportsCache.getAirports(country);
            }
            return ResponseEntity.status(HttpStatus.OK).body(airports.stream().limit(LIST_LIMIT).collect(Collectors.toList()));
        } catch (Exception e) {
            log.error("При запросе списка аэропортов произошла ошибка", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
