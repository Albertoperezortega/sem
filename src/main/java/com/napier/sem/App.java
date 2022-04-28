package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

/**
 * In this App, we run through a database to access and compare different values
 * It is made to access with ease information about population
 * In addition to this, it provides information about languages that people speak
 * Finally it produces several reports containing this information.
 * @authors: Milan Mucha, Ariel Girs, Noah Saleh, Alberto Perez
 * @since: 02/02/2022
 */
public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        if(args.length < 1){
            a.connect("localhost:33060", 30000);
        }else{
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        // This String is used to return all rows of the query
        String returnAll = "";
        // This String is used to return N number of rows of the query
        String numberOfCountries = "LIMIT 3";
        // This String is used for the 1st and 4th query - it chooses all countries in the world
        String countriesWorld = "WHERE country.capital = city.id ";
        // This String is used for the 2nd and 5th query - it chooses all countries in a continent
        String countriesContinent = "WHERE country.capital = city.id AND country.continent = 'Europe' ";
        // This String is used for the 3rd and 6th query - it chooses all countries in a region
        String countriesRegion = "WHERE country.capital = city.id AND country.region = 'Eastern Europe' ";

        /**
         * We use the method getAllCountries to generate a country report, so basically run the first 6 queries from the assessment description
         * This method has two inputs:
         * - the first one is queryPart, which is the WHERE clause that chooses all the countries in the world/continent/region
         * - the second one is queryPart2, which is just the LIMIT clause that chooses the number of top rows for the query
         * So the first variable in the brackets will be countriesWorld, countriesContinent or countriesRegion
         * The second variable in the brackets will be either numberOfCountries or returnAll
         */
        // We create an ArrayList that consists of classes Country and we call the method getAllCountries to fill this ArrayList
        ArrayList<Country> countries = a.getAllCountries(countriesRegion, numberOfCountries);

        // We call the method printCountries which creates and prints the output for the Arraylist countries
        // a.printCountries(countries);

        // This String is used to return N number of rows of the query
        String numberOfCities = "LIMIT 3";
        // This String is used for the 7th and 12th query - it chooses all cities in the world
        String citiesWorld = "WHERE country.code = city.countrycode ";
        // This String is used for the 8th and 13th query - it chooses all cities in a continent
        String citiesContinent = "WHERE country.code = city.countrycode AND country.continent = 'Europe' ";
        // This String is used for the 9th and 14th query - it chooses all cities in a region
        String citiesRegion = "WHERE country.code = city.countrycode AND country.region = 'Eastern Europe' ";
        // This String is used for the 10th and 15th query - it chooses all cities in a country
        String citiesCountry = "WHERE country.code = city.countrycode AND country.name = 'Poland' ";
        // This String is used for the 11th and 16th query - it chooses all cities in a district
        String citiesDistrict = "WHERE country.code = city.countrycode AND city.district = 'Mazowieckie' ";

        /**
         * We use the method getAllCities to generate a city report, so basically run the queries from 7 to 16 in the assessment description
         * This method has two inputs:
         * - the first one is queryPart, which is the WHERE clause that chooses all the cities in the world/continent/region/country/district
         * - the second one is queryPart2, which is just the LIMIT clause that chooses the number of top rows for the query
         * So the first variable in the brackets will be citiesWorld, citiesContinent, citiesRegion, citiesCountries or citiesDistrict
         * The second variable in the brackets will be either numberOfCities or returnAll
         */
        // We create an ArrayList that consists of classes City and we call the method getAllCities to fill this ArrayList
        ArrayList<City> cities = a.getAllCities(citiesContinent, numberOfCities);

        // We call the method printCities which creates and prints the output for the Arraylist cities
        // a.printCities(cities);

        // This String is used to return N number of rows of the query
        String numberOfCapitalCities = "LIMIT 3";
        // This String is used for the 17th and 20th query - it chooses all capital cities in the world
        String capitalCitiesWorld = "WHERE city.id = country.capital ";
        // This String is used for the 18th and 21st query - it chooses all capital cities in a continent
        String capitalCitiesContinent = "WHERE city.id = country.capital AND country.continent = 'Europe' ";
        // This String is used for the 19th and 22nd query - it chooses all capital cities in a region
        String capitalCitiesRegion = "WHERE city.id = country.capital AND country.region = 'Eastern Europe' ";

        /**
         * We use the method getAllCapitalCities to generate a capital city report, so basically run the queries from 17 to 22 in the assessment description
         * This method has two inputs:
         * - the first one is queryPart, which is the WHERE clause that chooses all the capital cities in the world/continent/region
         * - the second one is queryPart2, which is just the LIMIT clause that chooses the number of top rows for the query
         * So the first variable in the brackets will be capitalCitiesWorld, capitalCitiesContinent or capitalCitiesRegion
         * The second variable in the brackets will be either numberOfCapitalCities or returnAll
         */
        // We create an ArrayList that consists of classes City and we call the method getAllCapitalCities to fill this ArrayList
        ArrayList<City> capitalCities = a.getAllCapitalCities(capitalCitiesRegion, numberOfCapitalCities);

        // We call the method printCapitalCountries which creates and prints the output for the Arraylist capitalCities
        // a.printCapitalCities(capitalCities);

        // This String is used for the 23rd query - it groups the population by continents
        String populationContinent = "continent";
        // This String is used for the 24th query - it groups the population by regions
        String populationRegion = "region";
        // This String is used for the 25th query - it groups the population by countries
        String populationCountry = "name";

        // We create an ArrayList that consists of classes Population and we call the method getPopulation to fill this ArrayList
        ArrayList<Population> thePopulation = a.getPopulation(populationContinent);

        // We call the method printCapitalCountries which creates and prints the output for the Arraylist thePopulation
        a.printPopulation(thePopulation);

        // Below there are 6 queries which are used to get the population of the world, continent, region, country, district or city respectively
        String selectionWorld = "SELECT SUM(country.population) AS population "
                + "FROM country";
        String selectionContinent = "SELECT continent AS selection, SUM(country.population) AS population "
                + "FROM country "
                + "WHERE continent = 'Europe'";
        String selectionRegion = "SELECT region AS selection, SUM(country.population) AS population "
                + "FROM country "
                + "WHERE region = 'Eastern Europe'";
        String selectionCountry = "SELECT name AS selection, SUM(country.population) AS population"
                + "FROM country "
                + "WHERE name = 'Finland'";
        String selectionDistrict = "SELECT district AS selection, SUM(city.population) AS population "
                + "FROM city "
                + "WHERE district = 'Mazowieckie'";
        String selectionCity = "SELECT name AS selection, SUM(city.population) AS population "
                + "FROM city "
                + "WHERE name = 'Warszawa'";

        // We call the method getAndPrintThePopulation which executes an SQL query and prints the result
        // a.getAndPrintThePopulation(selectionCity);

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " +                                  Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Contructs the SQL statements
     * @param code
     * @return if SQL statement valid, and null if invalid or error
     */
    public Country getCountry(String code)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT code, name, continent "
                            + "FROM country "
                            + "WHERE code = '" + code +"'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            if (rset.next())
            {
                Country ctr = new Country();
                ctr.code = rset.getString("code");
                ctr.name = rset.getString("name");
                ctr.continent = rset.getString("continent");
                return ctr;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }


    public void displayCountry(Country ctr)
    {
        if (ctr != null)
        {
            System.out.println(
                    ctr.code + " "
                            + ctr.name + " "
                            + ctr.continent+ "\n");
        }
    }

    /**
     * Method for getting the country report
     * Gets all the countries and their population.
     * @return A list of all countries and their population, or null if there is an error.
     */
    public ArrayList<Country> getAllCountries(String queryPart, String queryPart2)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.code, country.name, country.continent, country.region, country.population, city.name "
                            + "FROM country, city "
                            + queryPart
                            + "ORDER BY country.population DESC "
                            + queryPart2;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                // We create a new instance of class Country each time this while loop runs, and we fill it with the output from the query
                Country ctr = new Country();
                ctr.code = rset.getString("country.code");
                ctr.name = rset.getString("country.name");
                ctr.continent = rset.getString("country.continent");
                ctr.region = rset.getString("country.region");
                ctr.population = rset.getInt("country.population");
                ctr.capital = rset.getString("city.name");
                countries.add(ctr);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Prints a list of countries.
     * @param countries The list of countries to print.
     */
    public void printCountries(ArrayList<Country> countries)
    {
        // Check countries is not null
        if (countries == null)
        {
            System.out.println("No countries");
            return;
        }
        // Print header
        System.out.println(String.format("%-5s %-45s %-15s %-30s %-10s %-5s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all countries in the list
        for (Country ctr : countries)
        {
            if (ctr == null)
                continue;
            String ctr_string =
                    String.format("%-5s %-45s %-15s %-30s %-10s %-5s",
                            ctr.code, ctr.name, ctr.continent, ctr.region, ctr.population, ctr.capital);
            System.out.println(ctr_string);
        }
    }

    /**
     * Method for getting the city report
     * Gets all cities with their population.
     * @return List of cities with their populations, or null if there is an error.
     */
    public ArrayList<City> getAllCities(String queryPart, String queryPart2)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name, city.district, city.population "
                            + "FROM country, city "
                            + queryPart
                            + "ORDER BY city.population DESC "
                            + queryPart2;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                // We create a new instance of class City each time this while loop runs, and we fill it with the output from the query
                City cty = new City();
                cty.name = rset.getString("city.name");
                cty.country_name = rset.getString("country.name");
                cty.district = rset.getString("city.district");
                cty.population = rset.getInt("city.population");
                cities.add(cty);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    /**
     * Prints list of cities
     * @param cities List of cities to print
     */
    public void printCities(ArrayList<City> cities)
    {
        // Check cities is not null
        if (cities == null)
        {
            System.out.println("No cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-30s %-35s %-20s %-10s", "Name", "Country", "District", "Population"));
        // Loop over all cities in the list
        for (City cty : cities)
        {
            if (cty == null)
                continue;
            String cty_string =
                    String.format("%-30s %-35s %-20s %-10s",
                            cty.name, cty.country_name, cty.district, cty.population);
            System.out.println(cty_string);
        }
    }

    /**
     * Method for getting the capital city report
     * Gets all capital cities with their country and population.
     * @return List of capital cities with their populations, or null if there is an error.
     */
    public ArrayList<City> getAllCapitalCities(String queryPart, String queryPart2)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.name, country.name, city.population "
                            + "FROM city, country "
                            + queryPart
                            + "ORDER BY city.population DESC "
                            + queryPart2;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract capital city information
            ArrayList<City> capitalCities = new ArrayList<City>();
            while (rset.next())
            {
                // We create a new instance of class City each time this while loop runs, and we fill it with the output from the query
                City cpt = new City();
                cpt.name = rset.getString("city.name");
                cpt.country_name = rset.getString("country.name");
                cpt.population = rset.getInt("city.population");
                capitalCities.add(cpt);
            }
            return capitalCities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city details");
            return null;
        }
    }

    /**
     * Prints list of capital cities
     * @param capitalCities List of capital cities to print
     */
    public void printCapitalCities(ArrayList<City> capitalCities)
    {
        // Check capitalCities is not null
        if (capitalCities == null)
        {
            System.out.println("No capital cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-35s %-35s %-20s", "Name", "Country", "Population"));
        // Loop over all capital cities in the list
        for (City cpt : capitalCities)
        {
            if (cpt == null)
                continue;
            String cpt_string =
                    String.format("%-35s %-35s %-20s",
                            cpt.name, cpt.country_name, cpt.population);
            System.out.println(cpt_string);
        }
    }

    public ArrayList<Population> getPopulation(String queryPart)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country." + queryPart + " AS the_selection, SUM(country.population) AS population, CONCAT(joined.ctypop, ' ', FORMAT(joined.ctypop/SUM(country.population)*100, 1), '%') AS cities_population, CONCAT(SUM(country.population)-joined.ctypop, ' ', FORMAT((SUM(country.population)-joined.ctypop)/SUM(country.population)*100, 1), '%') AS not_cities_population "
                        + "FROM country "
                        + "JOIN (SELECT country." + queryPart + " AS selection, SUM(city.population) AS ctypop FROM country, city WHERE country.code = city.countrycode GROUP BY country." + queryPart + ") joined ON joined.selection  = country." + queryPart + " "
                        + "GROUP BY country." + queryPart;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract population information
            ArrayList<Population> thePopulation = new ArrayList<Population>();
            while (rset.next())
            {
                // We create a new instance of class Population each time this while loop runs, and we fill it with the output from the query
                Population pop = new Population();
                pop.selection = rset.getString("country.the_selection");
                pop.population = rset.getString("population");
                pop.cities_population = rset.getString("cities_population");
                pop.not_cities_population = rset.getString("not_cities_population");
                thePopulation.add(pop);
            }
            return thePopulation;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Prints grouped population
     * @param thePopulation List of grouped population to print
     */
    public void printPopulation(ArrayList<Population> thePopulation)
    {
        // Check capitalCities is not null
        if (thePopulation == null)
        {
            System.out.println("No population");
            return;
        }
        // Print header
        System.out.println(String.format("%-35s %-35s %-20s %20s", "Selection", "Population", "In the cities", "Not in the cities"));
        // Loop over population in the list
        for (Population pop : thePopulation)
        {
            if (pop == null)
                continue;
            String pop_string =
                    String.format("%-35s %-35s %-20s %20s",
                            pop.selection, pop.population, pop.cities_population, pop.not_cities_population);
            System.out.println(pop_string);
        }
    }

    /**
     * Executes a query and then prints the result
     * @param query A query summing up the population of the world, continent, region, country, district or city
     */
    public void getAndPrintThePopulation(String query)
    {
        try
        {
            // Initialising the variables
            String selection = "World";
            String selectedPopulation = "Example";
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = query;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            while (rset.next())
            {
                // If the selectionWorld is used as query then this line will be skipped aand the selection variable will stay as "World"
                if (strSelect != "SELECT SUM(country.population) AS population " + "FROM country") {
                    selection = rset.getString("selection");
                }
                selectedPopulation = rset.getString("population");
            }
            // Printing the output
            System.out.println(String.format("%-30s %-30s", "Selection", "Population"));
            System.out.println(String.format("%-30s %-30s", selection, selectedPopulation));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
        }
    }


    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

}
