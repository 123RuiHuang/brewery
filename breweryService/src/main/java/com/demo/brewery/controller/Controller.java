package com.demo.brewery.controller;

import com.demo.brewery.exception.clientException.ClientException;
import com.demo.brewery.exception.severException.SeverException;
import com.demo.brewery.pojo.Brewery;
import com.demo.brewery.service.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    BreweryService breweryService;

    @GetMapping(value = "/breweries/search/{keyWord}")
    public List<Brewery> searchBreweries(@PathVariable String keyWord) throws SeverException {
        return breweryService.searchBreweries(keyWord);
    }

    @GetMapping(value = "brewery/{id}")
    public Brewery getBrewery(@PathVariable Integer id) throws ClientException, SeverException {
        return breweryService.getBrewery(id);
    }

    @GetMapping(value = "/breweries/filter/{filterName}/{filterValue}")
    public List<Brewery> filterBreweries(@PathVariable String filterName, @PathVariable String filterValue)
            throws ClientException, SeverException {
        return breweryService.filterBreweries(filterName, filterValue);
    }

}
