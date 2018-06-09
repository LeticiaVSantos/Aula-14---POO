
package br.com.fatecpg.parking.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class DataBaseConnector {
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = "jdbc:derby://localhost:1527/parking";
    private static final String USER = "parking";
    private static final String PASS = "parking";
    
    public static void executeCommand
     (String SQL, Object[] parameters) throws Exception{
         Class.forName(DRIVER);
         Connection con = DriverManager.getConnection(URL, USER, PASS);
         PreparedStatement stmt = con.prepareStatement(SQL);
         for(int i=0; i<parameters.length; i++){
             stmt.setObject(i+1, parameters[i]);
         }
         stmt.execute();
         stmt.close();
         con.close();
     }
      

    public static ArrayList<Object[]> executeQuery
     (String SQL, Object[] parameters) throws Exception{
         Class.forName(DRIVER);
         Connection con = DriverManager.getConnection(URL, USER, PASS);
         PreparedStatement stmt = con.prepareStatement(SQL);
         for(int i=0; i<parameters.length; i++){
             stmt.setObject(i+1, parameters[i]);
         }
         ResultSet rs = stmt.executeQuery();
         ArrayList<Object[]> list = new ArrayList<>();
         while(rs.next()){
             Object[] row = new Object [rs.getMetaData().getColumnCount()];
             for(int i=1; i<rs.getMetaData().getColumnCount(); i++){
                 row[i-1] = rs.getObject(i);
             }
             list.add(row);
         }
         rs.close(); stmt.close(); con.close();
         return list;
         
     }       
    
}
