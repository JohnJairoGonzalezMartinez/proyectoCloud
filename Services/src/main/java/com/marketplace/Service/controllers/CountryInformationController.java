package com.marketplace.Service.controllers;

import com.marketplace.Service.models.CountryInformation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CountryInformationController {

    public CountryInformation getCountryInformation(String country){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://restcountries.com/v3.1/name/" + country + "?fields=population,region,currencies,languages";
        ResponseEntity<List> apiResponse = restTemplate.getForEntity(url, List.class);
        if ( apiResponse.getStatusCode().isError() ){
            throw new RuntimeException("An error has occurred on Rest Api Invocation: " + apiResponse.getBody() );
        }
        Map<String, Object> firstResponse = (Map<String, Object>) apiResponse.getBody().get(0);
        String[] currencies = getCurrenciesStringArray((Map)firstResponse.get("currencies"));
        String[] languages = getLanguagesStringArray((Map)firstResponse.get("languages"));
        String region = firstResponse.get("region").toString();
        int population = (int)firstResponse.get("population");
        return new CountryInformation(currencies, languages, population, region);
    }

    private String[] getCurrenciesStringArray(Map currenciesMap){
        Object[] currenciesAsObject = (currenciesMap.values().stream().map( currency-> ((Map)currency).get("name") )  ).toArray();
        String[] currencies = new String[ currenciesAsObject.length ];
        for(int i=0; i<currenciesAsObject.length; i++){
            currencies[i] = currenciesAsObject[i].toString();
        }
        return currencies;
    }
    private String[] getLanguagesStringArray(Map languagesMap){
        Object[] languagesAsObject = languagesMap.values().toArray();
        String[] languages = new String[ languagesAsObject.length ];
        for(int i=0; i<languagesAsObject.length; i++){
            languages[i] = languagesAsObject[i].toString();
        }
        return languages;
    }

}
