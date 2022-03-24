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
        app.connect("localhost:33060", 30000);

    }

    @Test
    void testGetCountry()
    {
        Country ctr = app.getCountry("POL");
        assertEquals(ctr.code, "POL");
        assertEquals(ctr.name, "Poland");
        assertEquals(ctr.continent, "Europe");
    }

}


