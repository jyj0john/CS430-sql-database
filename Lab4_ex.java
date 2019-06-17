import java.io.File;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Lab4_ex {
	public static void test(ResultSet rs, Statement stmt) {
		try{
	        rs = stmt.executeQuery("SELECT * FROM Author");
	        while (rs.next()) {
	          System.out.println (rs.getString("AuthorID"));
	      }
	      }catch(Exception e){
	        System.out.print(e);
	        System.out.println(
	                  "No Author table to query");
	      }//end catch
	}
	
	public static String command(String[] a, boolean f) {
		String s = null;
		for(int i = 0; i < a.length; i++) {
			if(f == true) {	//update
				s = " set CheckinDate = '" + encap(a[3]) + "' where MemberID = " + a[0] + " and ISBN = '" + a[1] + "';";
				
			}
			else
				s = "'" + a[0] + "','" + a[1] + "','" + encap(a[2]) + "'," + "NULL" + ");"; 
		}
		return s;
	}
	
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
	
	public static void execute(Connection con) {
	try {
		Statement stmt = con.createStatement();
		ResultSet rs;	// reserve for checkdata
		
		String[] a = new String[4];
		Lab4_xml reader = new Lab4_xml();
		reader.readXML("Libdata.xml");
		File f = new File("ParsedData.txt");
		PrintWriter p = new PrintWriter("record.txt");
		Scanner sc = new Scanner(f);
		while(sc.hasNextLine()) {
			a = sc.nextLine().split(" ");
			
		/* 	check data before insert or update, use CheckData(rs, stmt, flag, array) in either update or insert  */
			
			if(a[2].equals("N/A")) {					//checkout is null, checkin, update
				String s = "update `BorrowedBy`";	// ignore may be replaced
				a[2] = "NULL";
				s += command(a, true);
				//checkdata
				if(CheckData(stmt, true,a) !=0) {
				stmt.executeUpdate(s);
				//System.out.println(s);
				stmt.executeUpdate(ManuCopies(a,3));
				System.out.println("updated");
				p.write("updated\n");
				}
				else {
					System.out.println("data truncated, error query display as follows");
					p.write("data truncated, error query display as follows");
					System.out.println(s);
					p.write(s + "\n");
				}
			}
			if(a[3].equals("N/A")) {					// checkin is null, checkout, new record
				String s = "insert ignore into `BorrowedBy` values(";
				a[3] = "null";
				s += command(a,false);
				//checkdata
				if(CheckData(stmt, false, a) != 0) {
				//System.out.println(s);
				stmt.executeUpdate(s);
				//String copyModify = "update ignore `StoredOn` set TotalCopies = TotalCopies-1 where ISBN =" + a[1] + "and LibName = ";
				//stmt.executeUpdate(copyModify);
				stmt.executeUpdate(ManuCopies(a,CheckData(stmt,false,a)));	//ManuCopies in Libraries, by default main
				//System.out.println(s);
				System.out.println("new record inserted");
				p.write("new record inserted\n");
				}
				else {
					System.out.println(" data truncated, error query display as follows");
					p.write(" data truncated, error query display as follows\n");
					System.out.println(s);
					p.write(s + "\n");
				}
			}
		}
		p.close();
    }
		catch( Exception e) {
			e.printStackTrace();
		
		}
	}
	
	public static String ManuCopies(String[] a,int lib) {	//actually nothing changed in storedon
		
		 if(lib == 1) {
			 return "update `StoredOn` set TotalCopies = TotalCopies - 1 where LibName = 'Main' and ISBN = '" + a[1]+ "'";
		 }
		 else if(lib == 2) {
			 //System.out.println("Marked");
			 return "update  `StoredOn` set TotalCopies = TotalCopies - 1 where LibName = 'South Park' and ISBN = '" + a[1]+ "'";
		 }
		 else if(lib == 3) {
			 //System.out.println("CheckIn");
			 return "update  `StoredOn` set TotalCopies = TotalCopies + 1 where LibName = 'Main' and ISBN = '" + a[1] + "'";

		 }
			 
		 else {
			System.out.println("Wrong!!!");
			return null;
		}
	}
	public static int CheckData(Statement stmt, boolean f, String[]a) {	//1->main, 2->south, 0->none
		Scanner sc = new Scanner(System.in);	//break point

		if( f == false) {	// insert check if book exists
		try{
			ResultSet rs;
	        rs = stmt.executeQuery("SELECT * FROM StoredOn order by LibName");
	        while (rs.next()) {
	        	if(a[1].equals(rs.getString("ISBN"))&& rs.getInt("TotalCopies") != 0) {	// found matched
	        		//System.out.println(rs.getString("ISBN") + " "+ rs.getString("LibName") + rs.getInt("TotalCopies"));
	        		if(rs.getString("LibName").equals("Main"))
	        			return 1;	
	        		else if(rs.getString("LibName").equals("South Park"))
	        			return 2;
	        	}
	        	//int point = sc.nextInt();
	        }
	      }catch(Exception e){
	        System.out.print(e);
	      }//end catch
		}
		else {	// update check if check in matches previous records
			//System.out.println("Break Point");
			//int aa = sc.nextInt();
			try{
				ResultSet rs1;
		        rs1 = stmt.executeQuery("SELECT * FROM BorrowedBy");
		        while (rs1.next()) {
		        	
		        	if(a[0].equals(rs1.getString("MemberID"))&&a[1].equals(rs1.getString("ISBN"))){
		        		System.out.println(a[0] + " " + a[1]);
		        		return 1;	//default return to main library
		        	}
		        }
		      }catch(Exception e){
		        System.out.print(e);
		      }//end catch
		}
		
		return 0;	//not found a record matched
	}
	
	public static void main(String args[]){
    Connection con = null;
    	try {
      Statement stmt;
      ResultSet rs;
  
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

      // Display URL and connection information
      System.out.println("URL: " + url);
      System.out.println("Connection: " + con);

      // Get a Statement object
      stmt = con.createStatement();
    
      try {
      	execute(con);
      	//test(rs, stmt);
      }
      catch(Exception e) {
      	try {
      		if(con == null)
      			con.close();
  		} catch (SQLException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}
      }

    	}catch( Exception e ) {
      e.printStackTrace();
    }//end catch
    
  }//end main

}//end class Lab4A_ex
