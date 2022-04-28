package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppIntegrationTests
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);

    }

    //Tests the getCountry method to see if it returns the correct answer
    @Test
    void testGetCountry()
    {
        Country ctr = app.getCountry("POL");
        assertEquals(ctr.code, "POL");
        assertEquals(ctr.name, "Poland");
        assertEquals(ctr.continent, "Europe");
    }

    //Tests the getAllCountries method to see if it returns the correct number of rows (countries)
    @Test
    void testGetAllCountries()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        String countriesWorld = "WHERE country.capital = city.id ";
        String returnAll = "";
        countries = app.getAllCountries(countriesWorld, returnAll);
        assertEquals(countries.size(), 232);
    }

    //Tests the getAllCities method to see if it returns the correct number of rows (cities)
    @Test
    void testGetAllCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        String citiesWorld = "WHERE country.code = city.countrycode ";
        String returnAll = "";
        cities = app.getAllCities(citiesWorld, returnAll);
        assertEquals(cities.size(), 4079);
    }


    //Tests the getAllCapitalCities method to see if it returns the correct number of rows (capital cities)
    @Test
    void testGetAllCapitalCities() {
        ArrayList<City> capitalCities = new ArrayList<City>();
        String capitalCitiesWorld = "WHERE country.capital = city.id ";
        String returnAll = "";
        capitalCities = app.getAllCapitalCities(capitalCitiesWorld, returnAll);
        assertEquals(capitalCities.size(), 232);
    }

    //
    @Test
    void testGetPopulation() {
        ArrayList<Population> thePopulation = new ArrayList<Population>();
        String populationContinent = "continent";
        thePopulation = app.getPopulation(populationContinent);
        assertEquals(thePopulation.size(), 7);
    }
}


