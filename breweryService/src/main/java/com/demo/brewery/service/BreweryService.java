package com.demo.brewery.service;

import com.demo.brewery.dao.BreweryDao;
import com.demo.brewery.exception.clientException.ClientException;
import com.demo.brewery.exception.severException.SeverException;
import com.demo.brewery.filter.Filter;
import com.demo.brewery.pojo.Brewery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class BreweryService {

    @Autowired
    BreweryDao breweryDao;

    public List<Brewery> searchBreweries(String keyWord) throws SeverException {
        try {
            return breweryDao.searchBreweries(keyWord);
        } catch (Exception e) {
            throw new SeverException(String.format("%s%s","server internal error: ", e.getMessage()));
        }
    }

    public List<Brewery> filterBreweries(String filterName, String filterValue) throws ClientException, SeverException {
        Filter.validate(filterName, filterValue);
        try {
            return breweryDao.filterBreweries(filterName, filterValue);
        } catch (Exception e) {
            throw new SeverException(String.format("%s%s","server internal error: ", e.getMessage()));
        }
    }

    public Brewery getBrewery(Integer id) throws ClientException, SeverException {
        try {
            return breweryDao.getBrewery(id);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException.NotFound) {
                throw new ClientException(String.format("%s%d", "invalid brewery id: ", id));
            } else {
                throw new SeverException(String.format("%s%s","server internal error: ", e.getMessage()));
            }
        }
    }
}
