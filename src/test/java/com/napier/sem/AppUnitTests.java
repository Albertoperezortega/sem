package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AppUnitTests
{
    static App app;

    /**
     * Initializes a new app instance
     */
    @BeforeAll
    static void init()
    {
        app = new App();
    }

    /**
     * Tests if printCountries method handles a null value of countries array
     */
    @Test
    void printCountriesTestNull()
    {
        app.printCountries(null);
    }

    /**
     * Tests if printCountries handles an empty countries array
     */
    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        app.printCountries(countries);
    }

    /**
     * Tests if printCountries can handle printing a list that contains null values
     */
    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        app.printCountries(countries);
    }

    /**
     * This final test is for normal conditions in method printCountries.
     */
    @Test
    void printCountries()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country ctr = new Country();
        ctr.code = "POL";
        ctr.name = "Poland";
        ctr.continent = "Europe";
        ctr.region = "Eastern Europe";
        ctr.population = 38653600;
        ctr.capital = "Warszawa";
        countries.add(ctr);
        app.printCountries(countries);
    }

    /**
     * Tests if printCities method handles a null value of cities array
     */
    @Test
    void printCitiesTestNull()
    {
        app.printCities(null);
    }

    /**
     * Tests if printCities handles an empty cities array
     */
    @Test
    void printCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<City>();
        app.printCities(cities);
    }

    /**
     * Tests if printCities can handle printing a list that contains null values
     */
    @Test
    void printCitiesTestContainsNull()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        app.printCities(cities);
    }

    /**
     * This final test is for normal conditions for method printCities.
     */
    @Test
    void printCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City cty = new City();
        cty.name = "Helsinki";
        cty.country_name = "Finland";
        cty.district = "Newmaa";
        cty.population = 555474;
        cities.add(cty);
        app.printCities(cities);
    }

    /**
     * Tests if printCapitalCities method handles a null value of capitalCities array
     */
    @Test
    void printCapitalCitiesTestNull()
    {
        app.printCapitalCities(null);
    }

    /**
     * Tests if printCapitalCities handles an empty cities array
     */
    @Test
    void printCapitalCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<City>();
        app.printCapitalCities(cities);
    }

    /**
     * Tests if printCapitalCities can handle printing a list that contains null values
     */
    @Test
    void printCapitalCitiesTestContainsNull()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        app.printCapitalCities(cities);
    }

    /**
     * This final test is for normal conditions for method printCapitalCities.
     */
    @Test
    void printCapitalCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City cpt = new City();
        cpt.name = "Helsinki";
        cpt.country_name = "Finland";
        cpt.district = "Newmaa";
        cpt.population = 555474;
        cities.add(cpt);
        app.printCapitalCities(cities);
    }
}