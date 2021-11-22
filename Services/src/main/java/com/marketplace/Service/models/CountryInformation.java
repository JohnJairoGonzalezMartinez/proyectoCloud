package com.marketplace.Service.models;

import java.util.Arrays;

public class CountryInformation {

    public String[] currencies;
    public String[] languages;
    public int population;
    public String region;

    public CountryInformation(String[] currencies, String[] languages, int population, String region) {
        this.currencies = currencies;
        this.languages = languages;
        this.population = population;
        this.region = region;
    }

    public CountryInformation() {
    }

    public String[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String[] currencies) {
        this.currencies = currencies;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "CountryInformation{" +
                "currencies=" + Arrays.toString(currencies) +
                ", languages=" + Arrays.toString(languages) +
                ", population=" + population +
                ", region='" + region + '\'' +
                '}';
    }
}
