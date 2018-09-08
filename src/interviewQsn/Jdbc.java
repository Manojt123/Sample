package interviewQsn;

import java.sql.Connection;
import java.sql.DriverManager;

public final class Jdbc {

	 private Connection conn;
	 public void Connect(){
	  conn = null;
	  try{
	   String userName = "root";
	   String password = "root";
	   String url = "jdbc:mysql://localhost/3307";
	   Class.forName ("com.mysql.jdbc.Driver").newInstance ();
	   conn = DriverManager.getConnection(url, userName, password);
	  }
	  catch(Exception e){
	   System.out.println("Exception found");
	  }
	 }
	 public Connection getConnection(){
	  return conn;
	 }
	 public void closeConnection(){
	  try{
	   conn.close ();
	  }catch (Exception e) {
	   System.out.println ("Connection close error");
	  }
	 }
	}
