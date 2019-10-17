/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalmobil;
import java.sql.*;
import java.sql.DriverManager;
/**
 *
 * @author LATIFA
 */
public class koneksi {
//   public koneksi(){
//    
//    }
//    public Connection getConnection() throws SQLException{
//    Connection con;
//        try {
//            String server = "jdbc:mysql://localhost/si_kereta";
//            String driver = "com.mysql.jdbc.Driver";
//            Class.forName(driver);
//            con = DriverManager.getConnection(server,"root","");
//            return con;
//        } catch (SQLException se) {
//            System.out.println(se.toString());
//            System.out.println("konek");
//            return null;  
//        }catch (Exception ex) {
//            System.out.println(ex.toString());
//            return null;  
//        }
//    }
      public koneksi(){
      
      }
      public Connection getConnection() throws SQLException, ClassNotFoundException{
          Connection con;
          try {
              String server = "jdbc:mysql://localhost/rentalmobil";
              String driver = "com.mysql.jdbc.Driver";
              Class.forName(driver);
              con = DriverManager.getConnection(server,"root","");
              return con;
              
          } catch (SQLException se) {
              System.out.println(se.toString());
              System.out.println("konek");
              return null;
          }catch(Exception ex){
              System.out.println(ex.toString());
              return null;
          }
          
      }
}
