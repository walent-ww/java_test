package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by user on 21.09.17.
 */
public class TestDBConnection {
    @Test
    public void testDbConnection(){
        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3307/addressbook?" +
                            "user=root&password=");


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
