package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class UnitTests
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCountriesTestNull()
    {
        app.printCountries(null);
    }

    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        app.printCountries(countries);
    }
}