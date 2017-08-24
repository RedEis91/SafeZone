package com.userdate.model;

import com.userdate.controller.Resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Grand Circus Student on 8/23/2017.
 */
public class DAO {

    public static ArrayList<Resources> getResourceList() {
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

            String readResourcesCommand = "select id, OrganizationName, zipcode, Website, PhoneNumber, Address, Hours, Description from resources";
            Statement readResources = mysqlConnection.createStatement();// creates the statement

            ResultSet results = readResources.executeQuery(readResourcesCommand);// executes the statement
            // array list of customers
            ArrayList<Resources> resourceList = new ArrayList<Resources>();

            // map from the ResultSet to the ArrayList
            while(results.next())
            {
                Resources temp = new Resources(results.getInt(1),
                        results.getString(2), results.getString(3), results.getString(4),
                        results.getLong(5), results.getString(6), results.getString(7),
                        results.getString(8));
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

