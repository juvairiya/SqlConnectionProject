package com.example.user.sqlconnectionproject;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by USER on 17-4-18.
 */

public class Connection {
    @SuppressLint("NewApi")
    public java.sql.Connection connectionclass() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.9:1433;databaseName=test;","sa","123456");

        } catch (SQLException se) {
            Log.e("error here 1 : ", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("error here 2 : ", e.getMessage());
        } catch (Exception e) {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}