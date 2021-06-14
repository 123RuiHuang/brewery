package com.demo.brewery.pojo;

import lombok.Data;

@Data
public class Brewery {
    private Integer id;
    private String name;
    private String brewery_type;
    private String street;
    private String address_2;
    private String address_3;
    private String city;
    private String county_province;
    private String state;
    private String postal_code;
    private String country;
    private String longitude;
    private String latitude;
    private String phone;
    private String website_url;
    private String updated_at;
    private String created_at;

    public Boolean equals(Brewery brewery) {
        return (this.id.equals(brewery.getId()) && ((this.name == null && brewery.getName() == null) || this.name.equals(brewery.getName()))
                && ((this.brewery_type == null && brewery.getBrewery_type() == null) || this.brewery_type.equals(brewery.getBrewery_type()))
                && ((this.street == null && brewery.getStreet() == null) || this.getStreet().equals(brewery.getStreet()))
                && ((this.address_2 == null && brewery.getAddress_2() == null) || this.address_2.equals(brewery.getAddress_2()))
                && ((this.address_3 == null && brewery.getAddress_3() == null) || this.getAddress_3().equals(brewery.getAddress_3()))
                && ((this.city == null && brewery.getCity() == null) || this.city.equals(brewery.getCity()))
                && ((this.county_province == null && brewery.getCounty_province() == null) || this.county_province.equals(brewery.getCounty_province()))
                && ((this.state == null && brewery.getState() == null) || this.state.equals(brewery.getState()))
                && ((this.postal_code == null && brewery.getPostal_code() == null) || this.postal_code.equals(brewery.getPostal_code()))
                && ((this.country == null && brewery.getCountry() == null) || this.country.equals(brewery.getCountry()))
                && ((this.longitude == null && brewery.getLongitude() == null) || this.longitude.equals(brewery.getLongitude()))
                && ((this.latitude == null && brewery.getLatitude() == null) || this.latitude.equals(brewery.getLatitude()))
                && ((this.phone == null && brewery.getPhone() == null) || this.phone.equals(brewery.getPhone()))
                && ((this.website_url == null && brewery.getWebsite_url() == null) || this.website_url.equals(brewery.getWebsite_url()))
                && ((this.updated_at == null && brewery.getUpdated_at() == null) || this.updated_at.equals(brewery.getUpdated_at()))
                && ((this.created_at == null && brewery.getCreated_at() == null) ||  this.created_at.equals(brewery.getCreated_at())));
    }
}
