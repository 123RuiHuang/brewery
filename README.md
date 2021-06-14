1: Brewery Service is a RESTFul API that allows end users to search and retrieve information about breweries from [Open Brewery DB](https://www.openbrewerydb.org/documentation).

2: Below three functions are supported
* Get a specific brewery by ID
  - Endpoint: 54.147.145.145:8080/brewery/BreweryID?access_token=YourToken  
  - For example: 54.147.145.145:8080/brewery/9094?access_token=b6c4f432-6312-4619-a462-25b763969203 returns below brewery
  ```
  {
    "id": 9094,
    "name": "Bnaf, LLC",
    "brewery_type": "planning",
    "street": null,
    "address_2": null,
    "address_3": null,
    "city": "Austin",
    "county_province": null,
    "state": "Texas",
    "postal_code": "78727-7602",
    "country": "United States",
    "longitude": null,
    "latitude": null,
    "phone": null,
    "website_url": null,
    "updated_at": "2018-07-24T00:00:00.000Z",
    "created_at": "2018-07-24T00:00:00.000Z"
  }
  ```
* Get list of breweries by keywords
  - Endpoint: 54.147.145.145:8080/breweries/search/YourKeyword?access_token=YourToken
  - For example: 54.147.145.145:8080/breweries/search/Bnaf, LLC?access_token=b6c4f432-6312-4619-a462-25b763969203 returns below breweries
  ```
  [
    {
        "id": 9094,
        "obdb_id": "bnaf-llc-austin",
        "name": "Bnaf, LLC",
        "brewery_type": "planning",
        "street": null,
        "address_2": null,
        "address_3": null,
        "city": "Austin",
        "state": "Texas",
        "county_province": null,
        "postal_code": "78727-7602",
        "country": "United States",
        "longitude": null,
        "latitude": null,
        "phone": null,
        "website_url": null,
        "updated_at": "2018-07-24T00:00:00.000Z",
        "created_at": "2018-07-24T00:00:00.000Z"
    }
  ]
  ```
* Get list of breweries by filters
  - Endpoint: 54.147.145.145:8080/breweries/filter/YourFilter/filterValue?access_token=YourToken
  - For example: 54.147.145.145:8080/breweries/filter/by_postal/78727-7602?access_token=b6c4f432-6312-4619-a462-25b763969203 returns below breweries
  ```
  [
    {
        "id": 9094,
        "obdb_id": "bnaf-llc-austin",
        "name": "Bnaf, LLC",
        "brewery_type": "planning",
        "street": null,
        "address_2": null,
        "address_3": null,
        "city": "Austin",
        "state": "Texas",
        "county_province": null,
        "postal_code": "78727-7602",
        "country": "United States",
        "longitude": null,
        "latitude": null,
        "phone": null,
        "website_url": null,
        "updated_at": "2018-07-24T00:00:00.000Z",
        "created_at": "2018-07-24T00:00:00.000Z"
    }
  ]
  ```
  
3: Access token is needed to access the API(securied by Spring Cloud OAuth2). Access token can be retrieved through this [endpoint](http://54.147.145.145:8081/oauth/token?client_secret=beer&grant_type=password&username=demoUser&password=demoPassword&client_id=beer).
If not provided, for example: 54.147.145.145:8080/breweries/filter/by_postal/78727-7602 returns below result
```
{
    "error": "unauthorized",
    "error_description": "Full authentication is required to access this resource"
}
```
