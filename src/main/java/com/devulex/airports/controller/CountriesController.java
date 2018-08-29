package com.devulex.airports.controller;

import com.devulex.airports.cache.AirportsCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * REST контроллер для ресурса Страна
 *
 * @author Aleksandr Uhanov
 * @since 2018-08-28
 */
@RestController
public class CountriesController {

    private static final Logger log = LoggerFactory.getLogger(CountriesController.class);

    private final AirportsCache airportsCache;

    public CountriesController(AirportsCache airportsCache) {
        this.airportsCache = airportsCache;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<String>> getCountries() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(airportsCache.getCountries());
        } catch (Exception e) {
            log.error("При запросе списка стран произошла ошибка", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
