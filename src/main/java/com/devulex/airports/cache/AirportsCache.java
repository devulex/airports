package com.devulex.airports.cache;

import com.devulex.airports.entity.Airport;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Хранилище списка аэропортов в памяти
 *
 * @author Aleksandr Uhanov
 * @since 2018-08-28
 */
@Component
public class AirportsCache {

    private Map<String, List<Airport>> airportsMap = Collections.emptyMap();

    /**
     * Получить список стран
     */
    public List<String> getCountries() {
        return airportsMap.keySet().stream().sorted().collect(Collectors.toList());
    }

    /**
     * Получить полный список аэропортов
     */
    public List<Airport> getAllAirports() {
        return airportsMap.values().stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Airport::getId))
                .collect(Collectors.toList());
    }

    /**
     * Получить список аэропортов с учетом фильтра
     *
     * @param country фильтр по стране
     */
    public List<Airport> getAirports(String country) {
        return airportsMap.getOrDefault(country, Collections.emptyList());
    }

    /**
     * Поместить в хранилище список аэропортов
     */
    public void setAirports(List<Airport> airportsList) {
        this.airportsMap = airportsList.stream().collect(Collectors.groupingBy(Airport::getCountry));
    }
}
