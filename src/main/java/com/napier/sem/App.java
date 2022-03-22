package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // This String is used to return all rows of the query
        String returnAll = "";
        // This String is used to return N number of rows of the query
        String numberOfRows = "LIMIT 3";
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
         * So the first variable in the brackets will be countriesWorld, countriesContinent or countries Region
         * The second variable in the brackets will be either numberOfRows or returnAll
         */
        // We create an ArrayList that consists of classes Country and we call the method getAllCountries to fill this ArrayList
        ArrayList<Country> countries = a.getAllCountries(countriesRegion, returnAll);

        // We call the method printCountries which creates and prints the output for the Arraylist countries
        // a.printCountries(countries);

        String citiesWorld = "WHERE country.code = city.countrycode ";
        String citiesContinent = "WHERE country.code = city.countrycode AND country.continent = 'Europe' ";
        String citiesRegion = "WHERE country.code = city.countrycode AND country.region = 'Eastern Europe' ";

        ArrayList<City> cities = a.getAllCities(citiesRegion);

        a.printCities(cities);

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
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }


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
     * Prints a list of employees.
     * @param countries The list of countries to print.
     */
    public void printCountries(ArrayList<Country> countries)
    {
        // Print header
        System.out.println(String.format("%-5s %-45s %-15s %-30s %-10s %-5s", "Code", "Name", "Continent", "Region", "Population", "Capital"));
        // Loop over all employees in the list
        for (Country ctr : countries)
        {
            String ctr_string =
                    String.format("%-5s %-45s %-15s %-30s %-10s %-5s",
                            ctr.code, ctr.name, ctr.continent, ctr.region, ctr.population, ctr.capital);
            System.out.println(ctr_string);
        }
    }

    public ArrayList<City> getAllCities(String queryPart)
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
                            + "ORDER BY city.population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                // We create a new instance of class Country each time this while loop runs, and we fill it with the output from the query
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

    public void printCities(ArrayList<City> cities)
    {
        // Print header
        System.out.println(String.format("%-30s %-20s %-20s %-10s", "Name", "Country", "District", "Population"));
        // Loop over all employees in the list
        for (City cty : cities)
        {
            String ctr_string =
                    String.format("%-30s %-35s %-20s %-10s",
                            cty.name, cty.country_name, cty.district, cty.population);
            System.out.println(ctr_string);
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
