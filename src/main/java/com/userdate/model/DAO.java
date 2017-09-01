package com.userdate.model;

import com.userdate.controller.Resource;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class DAO {

    public static ArrayList<Resource> getResourceList() {
        //connection info was pulled out into DBCredentials
        //so that file can be hidden
        try {
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");
            // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            // create the db connection object
            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(
                    DBCredentials.DB_ADDRESS,
                    DBCredentials.USERNAME,
                    DBCredentials.PASSWORD);
            // create the db statement
            //this statements selects the ID, Org name, zip, website, phone number, address and description for all of our
            //resources in our resources table in our db (row by row(
            String readResourcesCommand = "select ID, Organization, Zip, Website, Phone, Address, Description from resources";
            Statement readResources = mysqlConnection.createStatement();// creates the statement
            ResultSet results = readResources.executeQuery(readResourcesCommand);// executes the statement
            // creates an empty array list called resourceList that references our Resource class
            ArrayList<Resource> resourceList = new ArrayList<Resource>();
            // map from the ResultSet to the ArrayList
            //this while loop says that for each row that was grabbed from the db (or 'while there is a row that we grabbed from our db)
            //create an object of our Resource class called 'temp" that constructs an object (sets that object's values)
            // based on that row's (specific resource's)
            // ID, Org name, zip, website, phone number, address and description
            //AND then adds each object to our Arraylist called resourceList
            while(results.next())
            {
                Resource temp = new Resource(results.getInt(1),
                        results.getString(2), results.getString(3), results.getString(4),
                        results.getLong(5), results.getString(6),
                        results.getString(7));
                resourceList.add(temp);

            }
            //debugging
            for (int i = 0; i > resourceList.size(); i++) {
                System.out.println(resourceList.get(i));
            }
            //our resourceList is returned to our Home Controller in our formhandler and viewresourceList methods
            return resourceList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null; //null result indicates an issue
        }
    }

    public static ArrayList<Resource> getFoodResourceList(){
        try {
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");
            // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            // create the db connection object
            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(
                    DBCredentials.DB_ADDRESS,
                    DBCredentials.USERNAME,
                    DBCredentials.PASSWORD);
            //db statement
            //String readResourcesCommand is a SQL query to select all rows, determined by user's need (which category of
            //resources they would like) such as Food, Shelter, Healthcare, Education, etc. on the resource request form
            //give me all rows from resources table in safezone database WHERE Food applies
            PreparedStatement readResourcesCommand = mysqlConnection.prepareStatement("SELECT Organization, Latitude, Longitude FROM resources WHERE Food = 1");
            ResultSet results = readResourcesCommand.executeQuery();
            // creates an empty array list called userResourceList that refers to our Resource class
            ArrayList<Resource> userResourceList = new ArrayList<Resource>();
            // map from the ResultSet to the ArrayList
            //this while loop says that for each row that was grabbed from the db (or 'while there is a row that we grabbed from our db)
            //create an object of our Resource class called 'temp" that constructs an object (sets that object's values)
            // based on that row's (specific resource's)
            // Org name, latitude and longitude
            //AND then adds each object to our Arraylist called userResourceList
            while(results.next())
            {
                Resource temp = new Resource(results.getString("Organization"), results.getFloat("Latitude"),results.getFloat("Longitude"));
                userResourceList.add(temp);
            }
            //debugging
            for (int i = 0; i > userResourceList.size(); i++) {
                System.out.println(userResourceList.get(i));
            }
            //our userResourceList is returned to our Home Controller in our viewresourceList method
            return userResourceList;
            }
        catch(Exception ex)
            {
            ex.printStackTrace();
            return null; //null result indicates an issue
            }
    }

    public static ArrayList<Resource> getShelterResourceList(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(DBCredentials.DB_ADDRESS, DBCredentials.USERNAME, DBCredentials.PASSWORD);
            PreparedStatement readResourcesCommand = mysqlConnection.prepareStatement("SELECT Organization, Latitude, Longitude FROM resources WHERE Shelter = 1");
            ResultSet results = readResourcesCommand.executeQuery();
            ArrayList<Resource> userResourceList = new ArrayList<Resource>();
            while(results.next())
            {
                Resource temp = new Resource(results.getString("Organization"), results.getFloat("Latitude"),results.getFloat("Longitude"));
                userResourceList.add(temp);
            }
            for (int i = 0; i > userResourceList.size(); i++) {
                System.out.println(userResourceList.get(i));
            }
            return userResourceList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Resource> getClothingResourceList(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(DBCredentials.DB_ADDRESS, DBCredentials.USERNAME, DBCredentials.PASSWORD);
            PreparedStatement readResourcesCommand = mysqlConnection.prepareStatement("SELECT Organization, Latitude, Longitude FROM resources WHERE Clothing = 1");
            ResultSet results = readResourcesCommand.executeQuery();
            ArrayList<Resource> userResourceList = new ArrayList<Resource>();
            while(results.next())
            {
                Resource temp = new Resource(results.getString("Organization"), results.getFloat("Latitude"),results.getFloat("Longitude"));
                userResourceList.add(temp);
            }
            for (int i = 0; i > userResourceList.size(); i++) {
                System.out.println(userResourceList.get(i));
            }
            return userResourceList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Resource> getCounselingResourceList(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(DBCredentials.DB_ADDRESS, DBCredentials.USERNAME, DBCredentials.PASSWORD);
            PreparedStatement readResourcesCommand = mysqlConnection.prepareStatement("SELECT Organization, Latitude, Longitude FROM resources WHERE Counseling = 1");
            ResultSet results = readResourcesCommand.executeQuery();
            ArrayList<Resource> userResourceList = new ArrayList<Resource>();
            while(results.next())
            {
                Resource temp = new Resource(results.getString("Organization"), results.getFloat("Latitude"),results.getFloat("Longitude"));
                userResourceList.add(temp);
            }
            for (int i = 0; i > userResourceList.size(); i++) {
                System.out.println(userResourceList.get(i));
            }
            return userResourceList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Resource> getHealthcareResourceList(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(DBCredentials.DB_ADDRESS, DBCredentials.USERNAME, DBCredentials.PASSWORD);
            PreparedStatement readResourcesCommand = mysqlConnection.prepareStatement("SELECT Organization, Latitude, Longitude FROM resources WHERE Healthcare = 1");
            ResultSet results = readResourcesCommand.executeQuery();
            ArrayList<Resource> userResourceList = new ArrayList<Resource>();
            while(results.next())
            {
                Resource temp = new Resource(results.getString("Organization"), results.getFloat("Latitude"),results.getFloat("Longitude"));
                userResourceList.add(temp);
            }
            for (int i = 0; i > userResourceList.size(); i++) {
                System.out.println(userResourceList.get(i));
            }
            return userResourceList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Resource> getEducationResourceList(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(DBCredentials.DB_ADDRESS, DBCredentials.USERNAME, DBCredentials.PASSWORD);
            PreparedStatement readResourcesCommand = mysqlConnection.prepareStatement("SELECT " +
                    "Organization, Latitude, Longitude FROM resources WHERE Education = 1");
            ResultSet results = readResourcesCommand.executeQuery();
            ArrayList<Resource> userResourceList = new ArrayList<Resource>();
            while(results.next())
            {
                Resource temp = new Resource(results.getString("Organization"), results.getFloat("Latitude"),results.getFloat("Longitude"));
                userResourceList.add(temp);
            }
            for (int i = 0; i > userResourceList.size(); i++) {
                System.out.println(userResourceList.get(i));
            }
            return userResourceList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Resource> getJobResourceList(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(DBCredentials.DB_ADDRESS, DBCredentials.USERNAME, DBCredentials.PASSWORD);
            PreparedStatement readResourcesCommand = mysqlConnection.prepareStatement("SELECT " +
                    "Organization, Latitude, Longitude FROM resources WHERE Job = 1");
            ResultSet results = readResourcesCommand.executeQuery();
            ArrayList<Resource> userResourceList = new ArrayList<Resource>();
            while(results.next())
            {
                Resource temp = new Resource(results.getString("Organization"), results.getFloat("Latitude"),results.getFloat("Longitude"));
                userResourceList.add(temp);
            }
            for (int i = 0; i > userResourceList.size(); i++) {
                System.out.println(userResourceList.get(i));
            }
            return userResourceList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean addUser(String firstName, String lastName, long phoneNum, String email) {
        //info from our first form on the register.jsp page is passed through to this method from our Home Controller in our
        //formhandler method

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(
                    DBCredentials.DB_ADDRESS,
                    DBCredentials.USERNAME,
                    DBCredentials.PASSWORD);

            //this creates a SQL command to insert all of the info we received in this method (via the @RequestParams)
            //into their respective fields, in the users table in our db
            String addUserCommand = "INSERT INTO users " +
                    "(FirstName, LastName, PhoneNumber, Email) " +
                    "VALUES ('" +
                    firstName + "', '" +
                    lastName + "', '" +
                    phoneNum + "', '" +
//                    gender + "', '" +
//                    birthDay + "', '" +
                    email + "')";

            //debugging technique to make sure the SQL query was executed
            System.out.println("SQL Query " + addUserCommand);

            Statement st = mysqlConnection.createStatement();// creates the statement

            int result = st.executeUpdate(addUserCommand);// executes the statement
            // array list of customers

            //if (result == 1)
            //this addUser method returns true within our formhandler method when a successful new entry into our user table
            //was made, from information that was passed through to this method
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false; //null result indicates an issue
        }

    }
}

