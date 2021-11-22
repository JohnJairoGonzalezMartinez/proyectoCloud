package com.marketplace.Service.models;

public class Location {

    public String country;
    public String city;
    public String exactPlace;
    public CountryInformation countryExtraInformation;

    public Location(String country, String city, String exactPlace, CountryInformation countryExtraInformation) {
        this.country = country;
        this.city = city;
        this.exactPlace = exactPlace;
        this.countryExtraInformation = countryExtraInformation;
    }

    public Location() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExactPlace() {
        return exactPlace;
    }

    public void setExactPlace(String exactPlace) {
        this.exactPlace = exactPlace;
    }

    public CountryInformation getCountryExtraInformation() {
        return countryExtraInformation;
    }

    public void setCountryExtraInformation(CountryInformation countryExtraInformation) {
        this.countryExtraInformation = countryExtraInformation;
    }
}
