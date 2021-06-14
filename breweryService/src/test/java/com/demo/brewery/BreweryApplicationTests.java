package com.demo.brewery;

import com.demo.brewery.controller.Controller;
import com.demo.brewery.pojo.Brewery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BreweryApplicationTests {

	public static final String BREWERY_SERVICE = "http://localhost";
	public static final String AUTH_SERVICE = "http://localhost:8081/oauth/token?client_secret=beer&grant_type=password" +
			"&username=demoUser&password=demoPassword&client_id=beer";

	public static final String ACCESS_TOKEN = "access_token";

	@Autowired
	private Controller controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void requestWillBeDeniedWhenAccessTokenNotProvided() {
		Brewery expectedBrewery = buildBrewery();
		String url = String.format("%s:%s/%s/%s", BREWERY_SERVICE, port, "brewery", expectedBrewery.getId());
		Map<String, String> response = restTemplate.getForObject(url, Map.class);
		assertThat(response.get("error")).isEqualTo("unauthorized");
		assertThat(response.get("error_description")).isEqualTo("Full authentication is required to access this resource");
	}

	@Test
	public void controllerShouldReturnExpectedBreweryWhenGivenValidID() {
		Brewery expectedBrewery = buildBrewery();
		String url = String.format("%s:%s/%s/%s?%s=%s", BREWERY_SERVICE, port, "brewery", expectedBrewery.getId(), ACCESS_TOKEN, getAccessToken());
		Brewery brewery = restTemplate.getForObject(url, Brewery.class);
		assertThat(brewery.equals(expectedBrewery)).isEqualTo(Boolean.TRUE);
	}

	@Test
	public void controllerShouldReturnExceptionWhenGivenInvalidID() {
		Integer invalidBreweryID = 1;
		String url = String.format("%s:%s/%s/%s?%s=%s", BREWERY_SERVICE, port, "brewery", invalidBreweryID, ACCESS_TOKEN, getAccessToken());
		Exception exception = restTemplate.getForObject(url, Exception.class);
		assertThat(exception.getMessage()).isEqualTo(String.format("%s%s","invalid brewery id: ", invalidBreweryID));
	}

	@Test
	public void controllerShouldReturnExpectedBreweryWhenGivenKeyword() {
		String keyword = "Bnaf, LLC";
		String url = String.format("%s:%s/%s/%s?%s=%s", BREWERY_SERVICE, port, "breweries/search", keyword, ACCESS_TOKEN, getAccessToken());
		Brewery[] breweries = restTemplate.getForObject(url, Brewery[].class);
		assertThat(breweries.length).isEqualTo(1);
		Brewery brewery =  breweries[0];

		Brewery expectedBrewery = buildBrewery();

		assertThat(brewery.equals(expectedBrewery)).isEqualTo(Boolean.TRUE);
	}

	@Test
	public void controllerShouldReturnExpectedBreweryWhenGivenValidFilter() {
		String filterName = "by_name", filterValue = "Bnaf, LLC";
		String url = String.format("%s:%s/%s/%s/%s?%s=%s", BREWERY_SERVICE, port, "breweries/filter", filterName, filterValue, ACCESS_TOKEN, getAccessToken());
		Brewery[] breweries = restTemplate.getForObject(url, Brewery[].class);
		assertThat(breweries.length).isEqualTo(1);
		Brewery brewery =  breweries[0];
		Brewery expectedBrewery = buildBrewery();

		assertThat(brewery.equals(expectedBrewery)).isEqualTo(Boolean.TRUE);
	}

	@Test
	public void controllerShouldReturnExceptionWhenGivenInvalidFilterName() {
		String filterName = "invalidFilter", filterValue = "Bnaf, LLC";
		String url = String.format("%s:%s/%s/%s/%s?%s=%s", BREWERY_SERVICE, port, "breweries/filter", filterName, filterValue, ACCESS_TOKEN, getAccessToken());
		Exception exception = restTemplate.getForObject(url, Exception.class);
		assertThat(exception.getMessage()).contains(String.format("%s%s.","invalid Filter: ", filterName));
	}

	@Test
	public void controllerShouldReturnExceptionWhenGivenInvalidFilterValue() {
		String filterName = "by_type", filterValue = "invalidValue";
		String url = String.format("%s:%s/%s/%s/%s?%s=%s", BREWERY_SERVICE, port, "breweries/filter", filterName, filterValue, ACCESS_TOKEN, getAccessToken());
		Exception exception = restTemplate.getForObject(url, Exception.class);
		assertThat(exception.getMessage()).contains(String.format("%s%s.","invalid type value: ", filterValue));
	}

	@Test
	public void controllerShouldReturnExceptionWhenGivenInvalidPostalCode() {
		String filterName = "by_postal", filterValue = "1234";
		String url = String.format("%s:%s/%s/%s/%s?%s=%s", BREWERY_SERVICE, port, "breweries/filter", filterName, filterValue, ACCESS_TOKEN, getAccessToken());
		Exception exception = restTemplate.getForObject(url, Exception.class);
		assertThat(exception.getMessage()).contains(String.format("%s%s","invalid postal code: ", filterValue));
	}

	@Test
	public void controllerShouldReturnExpectedBreweryWhenGivenValidPostalCode() {
		String filterName = "by_postal", filterValue = "78727-7602";
		String url = String.format("%s:%s/%s/%s/%s?%s=%s", BREWERY_SERVICE, port, "breweries/filter", filterName, filterValue, ACCESS_TOKEN, getAccessToken());
		Brewery[] breweries = restTemplate.getForObject(url, Brewery[].class);
		assertThat(breweries.length).isEqualTo(1);
		Brewery expectedBrewery = buildBrewery();
		assertThat(breweries[0].equals(expectedBrewery)).isEqualTo(Boolean.TRUE);
	}

	private String getAccessToken() {
		Map<String, String> token = restTemplate.getForObject(AUTH_SERVICE, Map.class);
		return token.get("access_token");
	}

	private Brewery buildBrewery() {
		Brewery brewery = new Brewery();
		brewery.setId(9094);
		brewery.setName("Bnaf, LLC");
		brewery.setBrewery_type("planning");
		brewery.setStreet(null);
		brewery.setAddress_2(null);
		brewery.setAddress_3(null);
		brewery.setCity("Austin");
		brewery.setCounty_province(null);
		brewery.setState("Texas");
		brewery.setPostal_code("78727-7602");
		brewery.setCountry("United States");
		brewery.setLongitude(null);
		brewery.setLatitude(null);
		brewery.setPhone(null);
		brewery.setWebsite_url(null);
		brewery.setUpdated_at("2018-07-24T00:00:00.000Z");
		brewery.setCreated_at("2018-07-24T00:00:00.000Z");
		return brewery;
	}

}
