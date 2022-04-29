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

        //Variables for the first 6 queries

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

        // Variables for queries 11 to 16

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

        // Variables for queries 17 to 22

        // This String is used to return N number of rows of the query
        String numberOfCapitalCities = "LIMIT 3";
        // This String is used for the 17th and 20th query - it chooses all capital cities in the world
        String capitalCitiesWorld = "WHERE city.id = country.capital ";
        // This String is used for the 18th and 21st query - it chooses all capital cities in a continent
        String capitalCitiesContinent = "WHERE city.id = country.capital AND country.continent = 'Europe' ";
        // This String is used for the 19th and 22nd query - it chooses all capital cities in a region
        String capitalCitiesRegion = "WHERE city.id = country.capital AND country.region = 'Eastern Europe' ";

        // Variables for queries 23 to 25

        // This String is used for the 23rd query - it groups the population by continents
        String populationContinent = "continent";
        // This String is used for the 24th query - it groups the population by regions
        String populationRegion = "region";
        // This String is used for the 25th query - it groups the population by countries
        String populationCountry = "name";

        // Variables for queries 26 to 31

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


        // Just change this variable to the number of the query that you want to see
        int queryNumber = 17;

        switch(queryNumber) {
            case 1:
                // All the countries in the world organised by largest population to smallest.
                ArrayList<Country> one = a.getAllCountries(countriesWorld, returnAll);
                a.printCountries(one);
                break;
            case 2:
                // All the countries in a continent organised by largest population to smallest.
                ArrayList<Country> two = a.getAllCountries(countriesContinent, returnAll);
                a.printCountries(two);
                break;
            case 3:
                // All the countries in a region organised by largest population to smallest.
                ArrayList<Country> three = a.getAllCountries(countriesRegion, returnAll);
                a.printCountries(three);
                break;
            case 4:
                // The top N populated countries in the world where N is provided by the user.
                ArrayList<Country> four = a.getAllCountries(countriesWorld, numberOfCountries);
                a.printCountries(four);
                break;
            case 5:
                // The top N populated countries in a continent where N is provided by the user.
                ArrayList<Country> five = a.getAllCountries(countriesContinent, numberOfCountries);
                a.printCountries(five);
                break;
            case 6:
                // The top N populated countries in a region where N is provided by the user.
                ArrayList<Country> six = a.getAllCountries(countriesRegion, numberOfCountries);
                a.printCountries(six);
                break;
            case 7:
                // All the cities in the world organised by largest population to smallest.
                ArrayList<City> seven = a.getAllCities(citiesWorld, returnAll);
                a.printCities(seven);
                break;
            case 8:
                // All the cities in a continent organised by largest population to smallest.
                ArrayList<City> eight = a.getAllCities(citiesContinent, returnAll);
                a.printCities(eight);
                break;
            case 9:
                // All the cities in a region organised by largest population to smallest.
                ArrayList<City> nine = a.getAllCities(citiesRegion, returnAll);
                a.printCities(nine);
                break;
            case 10:
                // All the cities in a country organised by largest population to smallest.
                ArrayList<City> ten = a.getAllCities(citiesCountry, returnAll);
                a.printCities(ten);
                break;
            case 11:
                // All the cities in a district organised by largest population to smallest.
                ArrayList<City> eleven = a.getAllCities(citiesDistrict, returnAll);
                a.printCities(eleven);
                break;
            case 12:
                // The top N populated cities in the world where N is provided by the user.
                ArrayList<City> twelve = a.getAllCities(citiesWorld, numberOfCities);
                a.printCities(twelve);
                break;
            case 13:
                // The top N populated cities in a continent where N is provided by the user.
                ArrayList<City> thirteen  = a.getAllCities(citiesContinent, numberOfCities);
                a.printCities(thirteen);
                break;
            case 14:
                // The top N populated cities in a region where N is provided by the user.
                ArrayList<City> fourteen = a.getAllCities(citiesRegion, numberOfCities);
                a.printCities(fourteen);
                break;
            case 15:
                // The top N populated cities in a country where N is provided by the user.
                ArrayList<City> fifteen = a.getAllCities(citiesCountry, numberOfCities);
                a.printCities(fifteen);
                break;
            case 16:
                // The top N populated cities in a district where N is provided by the user.
                ArrayList<City> sixteen = a.getAllCities(citiesDistrict, numberOfCities);
                a.printCities(sixteen);
                break;
            case 17:
                // All the capital cities in the world organised by largest population to smallest.
                ArrayList<City> seventeen = a.getAllCapitalCities(capitalCitiesWorld, returnAll);
                a.printCities(seventeen);
                break;
            case 18:
                // All the capital cities in a continent organised by largest population to smallest.
                ArrayList<City> eighteen = a.getAllCapitalCities(capitalCitiesContinent, returnAll);
                a.printCities(eighteen);
                break;
            case 19:
                // All the capital cities in a region organised by largest to smallest.
                ArrayList<City> nineteen = a.getAllCapitalCities(capitalCitiesRegion, returnAll);
                a.printCities(nineteen);
                break;
            case 20:
                // The top N populated capital cities in the world where N is provided by the user.
                ArrayList<City> twenty = a.getAllCapitalCities(capitalCitiesWorld, numberOfCapitalCities);
                a.printCities(twenty);
                break;
            case 21:
                // The top N populated capital cities in a continent where N is provided by the user.
                ArrayList<City> twentyOne = a.getAllCapitalCities(capitalCitiesContinent, numberOfCapitalCities);
                a.printCities(twentyOne);
                break;
            case 22:
                // The top N populated capital cities in a region where N is provided by the user.
                ArrayList<City> twentyTwo = a.getAllCapitalCities(capitalCitiesRegion, numberOfCapitalCities);
                a.printCities(twentyTwo);
                break;
            case 23:
                // The population of people, people living in cities, and people not living in cities in each continent.
                ArrayList<Population> twentyThree = a.getPopulation(populationContinent);
                a.printPopulation(twentyThree);
                break;
            case 24:
                // The population of people, people living in cities, and people not living in cities in each region.
                ArrayList<Population> twentyFour = a.getPopulation(populationRegion);
                a.printPopulation(twentyFour);
                break;
            case 25:
                // The population of people, people living in cities, and people not living in cities in each country.
                ArrayList<Population> twentyFive = a.getPopulation(populationCountry);
                a.printPopulation(twentyFive);
                break;
            case 26:
                // The population of the world.
                a.getAndPrintThePopulation(selectionWorld);
                break;
            case 27:
                // The population of a continent.
                a.getAndPrintThePopulation(selectionContinent);
                break;
            case 28:
                // The population of a region.
                a.getAndPrintThePopulation(selectionRegion);
                break;
            case 29:
                // The population of a country.
                a.getAndPrintThePopulation(selectionCountry);
                break;
            case 30:
                // The population of a district.
                a.getAndPrintThePopulation(selectionDistrict);
                break;
            case 31:
                // The population of a city.
                a.getAndPrintThePopulation(selectionCity);
                break;
            case 32:
                // Language report.
                ArrayList<Language> thirtyTwo = a.getLanguages();
                a.printLanguages(thirtyTwo);
                break;
            default:
                System.out.println("Wrong query number");
        }

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
     * Gets all the countries and their population.
     * @param queryPart the first part of the query which is the WHERE clause that chooses all the countries in the world/continent/region
     * @param queryPart2 the second part of the query which is just the LIMIT clause that chooses the number of top rows for the query
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
     * Gets all cities with their population.
     * @param queryPart the first part of the query which is the WHERE clause that chooses all the cities in the world/continent/region/country/district
     * @param queryPart2 the second part of the query which is just the LIMIT clause that chooses the number of top rows for the query
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
     * Gets all capital cities with their country and population.
     * @param queryPart the first part of the query which is the WHERE clause that chooses all the capital cities in the world/continent/region
     * @param queryPart2 the second part of the query which is just the LIMIT clause that chooses the number of top rows for the query
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

    /**
     * Executes a query and return an ArrayList with Population classes
     * @param queryPart the first part of the query which is one of the Strings: continent, region, name
     * @return an ArrayList that consists of classes Population
     */
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
        // Check thePopulation is not null
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
     * Executes a MySQL query and then prints the result which is the population
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
     * Executes a MySQL query and prints the list of the five languages with the biggest percentage of the total world population
     * @return an ArrayList of consisting of Language classes
     */
    public ArrayList<Language> getLanguages()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT countrylanguage.language, CEILING(SUM(country.population * 0.01 * countrylanguage.percentage)) AS population, CONCAT(ROUND(SUM(country.population * 0.01 * countrylanguage.percentage/division*100), 1), ' %') AS percentage "
                        + "FROM country, countrylanguage, (SELECT SUM(country.population) as division FROM country) AS base "
                        + "WHERE country.code = countrylanguage.countrycode "
                        + "GROUP BY countrylanguage.language "
                        + "ORDER BY population DESC "
                        + "LIMIT 5";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract population information
            ArrayList<Language> languages = new ArrayList<Language>();
            while (rset.next())
            {
                // We create a new instance of class Population each time this while loop runs, and we fill it with the output from the query
                Language lng = new Language();
                lng.name = rset.getString("countrylanguage.language");
                lng.population = rset.getInt("population");
                lng.percentage = rset.getString("percentage");
                languages.add(lng);
            }
            return languages;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get language details");
            return null;
        }
    }

    /**
     * Creates and prints the output for the Arraylist languages
     * @param languages List of languages to print
     */
    public void printLanguages(ArrayList<Language> languages)
    {
        // Check languages is not null
        if (languages == null)
        {
            System.out.println("No language");
            return;
        }
        // Print header
        System.out.println(String.format("%-35s %-35s %-20s", "Language", "Population", "Percentage"));
        // Loop over population in the list
        for (Language lng : languages)
        {
            if (lng == null)
                continue;
            String lng_string =
                    String.format("%-35s %-35s %-20s",
                            lng.name, lng.population, lng.percentage);
            System.out.println(lng_string);
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
