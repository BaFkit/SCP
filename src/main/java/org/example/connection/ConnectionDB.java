package org.example.connection;

import java.sql.DriverManager;

public class ConnectionDB {

    private ConnectionDB connectionDB;

   public ConnectionDB(){
       try {
           Class.forName("org.sqlite.JDBC");
           connectionDB = (ConnectionDB) DriverManager.getConnection("jdbc:sqlite:products.db");
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   public ConnectionDB getConnection() {
       return connectionDB;
   }

}
