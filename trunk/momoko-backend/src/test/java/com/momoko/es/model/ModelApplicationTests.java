/**
 * ModelApplicationTests.java 14-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ModelApplicationTests {

    @Test
    public void contextLoads() {
    }

    // @Test
    // public void connectJDBCToAWSEC2() {

    // System.out.println("----MySQL JDBC Connection Testing -------");
    //
    // try {
    // Class.forName("com.mysql.jdbc.Driver");
    // } catch (ClassNotFoundException e) {
    // System.out.println("Where is your MySQL JDBC Driver?");
    // e.printStackTrace();
    // return;
    // }
    //
    // System.out.println("MySQL JDBC Driver Registered!");
    // Connection connection = null;
    //
    // try {
    // connection = DriverManager.
    // getConnection("jdbc:mysql://ec2-52-37-81-48.us-west-2.compute.amazonaws.com:3306/momokobd",
    // "momokobdUser", "momokoBDpass");
    // } catch (SQLException e) {
    // System.out.println("Connection Failed!:\n" + e.getMessage());
    // }
    //
    // if (connection != null) {
    // System.out.println("SUCCESS!!!! You made it, take control your database now!");
    // } else {
    // System.out.println("FAILURE! Failed to make connection!");
    // }

    // }
}
