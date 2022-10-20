package com.example.restfulconnection;

public class Country {
    int id;
    String countryName;

    public Country(int id, String country) {
        this.id = id;
        this.countryName = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
