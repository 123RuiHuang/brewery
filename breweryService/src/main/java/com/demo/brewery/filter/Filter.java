package com.demo.brewery.filter;

import com.demo.brewery.exception.clientException.ClientException;

import java.util.Set;

public class Filter {

    public static Set<String> FILTERS = Set.of("by_city", "by_dist", "by_name", "by_state", "by_postal", "by_type");
    public static Set<String> TYPES = Set.of("micro", "nano", "regional", "brewpub", "large", "planning", "bar", "contract",
            "proprietor", "closed");
    public static Integer BASIC_POSTAL_LENGTH = 5;
    public static Integer PRECISE_POSTAL_LENGTH = 10;

    public static void validate(String filterName, String filterValue) throws ClientException {
        if (!FILTERS.contains(filterName)) throw new ClientException(String.format("%s%s. %s%s","invalid Filter: ", filterName,
                "valid Filter: ", FILTERS.toString()));
        else if (filterName.equals("by_type") && !TYPES.contains(filterValue)) throw new ClientException(String.format("%s%s. %s%s",
                "invalid type value: ", filterValue, "valid type value: ", TYPES.toString()));
        else if (filterName.equals("by_postal") && filterValue.length() != BASIC_POSTAL_LENGTH &&
                filterValue.length() != PRECISE_POSTAL_LENGTH) {
            throw new ClientException(String.format("%s%s", "invalid postal code: ", filterValue));
        }
    }
}
