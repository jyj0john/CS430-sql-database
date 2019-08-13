import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lab5 {
	private static boolean runFlag = true;
	public static String encap(String t) {
		String s = t;
		if(s.equals("NULL"))
			return "NULL";
			if(s.contains("/")) {
				try {
					Date date = new SimpleDateFormat("dd/MM/yyyy").parse(t);
					s = new SimpleDateFormat("yyyy-MM-dd").format(date);
			//		System.out.println(t);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return s;
	}
	// book not find or book all checked out print as GUI example, no new dialog or jframe needed. 
	public static void main(String[] args) {
		 Connection con = null;
	    	try {
	      // Register the JDBC driver for MySQL.
	      Class.forName("com.mysql.cj.jdbc.Driver");

	      // Define URL of database server for
	      // database named 'jyj0john' on the faure.
	      String url =
	            "jdbc:mysql://faure/jyj0john?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

	      // Get a connection to the database for a
	      // jyj0john named 'jyj0john' with the password
	      // 832089371.
	      con = DriverManager.getConnection(
	                        url,"jyj0john", "832089371");
	      con.createStatement();
	      // Display URL and connection information
	      System.out.println("URL: " + url);
	      System.out.println("Connection: " + con);
	     Login test =  new Login(con); 
	      test.setVisible(true);
	    }
	    catch( Exception e ) {
	      e.printStackTrace();
	    	}//end catch
	    
	  }//end main

	
	public static boolean getRunFlag() {
		return runFlag;
	}

	public static void setRunFlag(boolean runFlag) {
		Lab5.runFlag = runFlag;
	}
	
}
