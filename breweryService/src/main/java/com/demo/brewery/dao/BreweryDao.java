package com.demo.brewery.dao;

import com.demo.brewery.pojo.Brewery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class BreweryDao {

    private static final String BREWERY_DB_URL = "https://api.openbrewerydb.org/breweries";

    @Autowired
    RestTemplate restTemplate;

    public Brewery getBrewery(Integer id) {
        String url = String.format("%s/%d", BREWERY_DB_URL, id);
        Brewery brewery = restTemplate.getForObject(url, Brewery.class);
        System.out.println(url);
        System.out.println(brewery.toString());
        return brewery;
    }

    public List<Brewery> searchBreweries(String keyWord) {
        String url = String.format("%s/%s%s", BREWERY_DB_URL, "search?query=", keyWord);
        List<Brewery> breweries = restTemplate.getForObject(url, List.class);
        return breweries;
    }

    public List<Brewery> filterBreweries(String filterName, String filterValue) {
        String url = String.format("%s?%s=%s", BREWERY_DB_URL, filterName, filterValue);
        List<Brewery> breweries = restTemplate.getForObject(url, List.class);
        return breweries;
    }
}

