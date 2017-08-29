package com.userdate.model;

import com.userdate.controller.Resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
            String readResourcesCommand = "select ID, Organization, Zip, Website, Phone, Address, Description from resources";
            Statement readResources = mysqlConnection.createStatement();// creates the statement

            ResultSet results = readResources.executeQuery(readResourcesCommand);// executes the statement
            // array list of customers
            ArrayList<Resource> resourceList = new ArrayList<Resource>();

            // map from the ResultSet to the ArrayList
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

            return resourceList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null; //null result indicates an issue
        }
    }

    public static ArrayList<Resource> getUserResourceList(){
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

            //String to append to Database query when user is in need of a FOOD based resource
            String userSelection = "Food";
            String isFood = userSelection + " = 1";


            //db statement
            //String readResourcesCommand is a SQL query to select all rows, determined by user's need
            String readResourcesCommand = "select Organization, Latitude, Longitude from resources where " + isFood;
            Statement readResources = mysqlConnection.createStatement();// creates the statement

            ResultSet results = readResources.executeQuery(readResourcesCommand);// executes the statement
            // array list of customers
            ArrayList<Resource> userResourceList = new ArrayList<Resource>();

            // map from the ResultSet to the ArrayList
            while(results.next())
            {
                Resource temp = new Resource(results.getString("Organization"), results.getFloat("Latitude"),results.getFloat("Longitude"));
                userResourceList.add(temp);

            }

            //debugging
            for (int i = 0; i > userResourceList.size(); i++) {
                System.out.println(userResourceList.get(i));
            }

            return userResourceList;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null; //null result indicates an issue
        }


    }

    public static boolean addUser(
            String firstName,
            String lastName,
            long phoneNum,
            String gender,
            String birthDay,
            String email
    ) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(
                    DBCredentials.DB_ADDRESS,
                    DBCredentials.USERNAME,
                    DBCredentials.PASSWORD);

            String addUserCommand = "INSERT INTO Users " +
                    "(FirstName, LastName, PhoneNumber, GenderIdentify, DOB, Email) " +
                    "VALUES ('" +
                    firstName + "', '" +
                    lastName + "', '" +
                    phoneNum + "', '" +
                    gender + "', '" +
                    birthDay + "', '" +
                    email + "')";
            System.out.println("SQL Query " + addUserCommand);

            Statement st = mysqlConnection.createStatement();// creates the statement

            int result = st.executeUpdate(addUserCommand);// executes the statement
            // array list of customers

            //if (result == 1)
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false; //null result indicates an issue
        }

    }
}

