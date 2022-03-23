package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect();

    }

    @Test
    void testGetCountry()
    {
        Country ctr = app.getCountry("Eastern Europe");
        assertEquals(ctr.code, "Eastern Europe");
        assertEquals(ctr.name, "Poland");
        assertEquals(ctr.continent, "Europe");
    }


    @Test
    void testGetAllCountries()
    {
        Country ctr = app.getCountry("ctr.code,ctr.name");
        assertEquals(ctr.name, "country.name");
        assertEquals(ctr.code, "country.code");
        assertEquals(ctr.region, "country.region");
        assertEquals(ctr.population, "country.population");
        assertEquals(ctr.capital, "city.name");
    }

    @Test
    void testGetAllCities()
    {
        City cty = app.getAllCities();
        assertEquals(cty.name,"city.name");
        assertEquals(cty.country_name, "country.name");
        assertEquals(cty.district, "city.district");
        assertEquals(cty.population, "city.population");
    }
}


