package com.devulex.airports.entity;

import java.math.BigDecimal;

/**
 * Информация об аэропорте.
 *
 * @author Aleksandr Uhanov
 * @since 2018-08-28
 */
public class Airport {

    private long id;
    private String name;
    private String city;
    private String country;
    private String IATA;
    private String ICAO;
    private double latitude;
    private double longitude;
    private int altitude;
    private BigDecimal timezoneOffset;
    private String DST;
    private String timezoneName;
    private String type;
    private String source;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public String getICAO() {
        return ICAO;
    }

    public void setICAO(String ICAO) {
        this.ICAO = ICAO;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public BigDecimal getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(BigDecimal timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public String getDST() {
        return DST;
    }

    public void setDST(String DST) {
        this.DST = DST;
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
