package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import data_manu.Book;

public class BookSouth extends Book{
	
	public static String encap(String[] t) {
		String s = "(";

		for( int i = 0; i < t.length; i++) {
			if(i >= 1 && i < 4) {
				//System.out.println(t[i]);
				continue;
			}
			if(t[i].contains("/")) {
				try {
					Date date = new SimpleDateFormat("dd/MM/yyyy").parse(t[i]);
					t[i] = new SimpleDateFormat("yyyy-MM-dd").format(date);
			//		System.out.println(t);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			s+= "'"+t[i]+"',";
		}
		s = s.substring(0, s.length()-1);
		return s +"),\n";
	}
	
	public static void main(String[] args) {
		File f = new File("Book.txt");
		//File f = new File("SouthParkLibBooks.txt");
		String script= "insert ignore into `Book` values";
		//String script= "insert into `StoredOn` values";
		String[] t = null;
		String PriKey = "";
		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				t = sc.nextLine().split(", ");


				 
				 
				if(t[0].contains("  "))	//enter the part for complex attributes
				{	
					continue;
					// it is the same way as members will use
				}
				else {					//enter the part for next entity 
					script += encap(t);
						
					script = script.substring(0,script.length()-2);
					script+= ",\n";
					//need revise for member because a priKey should reserve for all attributes follows
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		script = script.substring(0,script.length()-2)+';'; //-2 because have ',' and '\n' at the end
		//writeFile(script, "SouthLibBookInfo.txt");
		//writeFile(script, "MainLibBookStoredOnInfo.txt");
		writeFile(script, "MainLibBookInfo.txt");
	}
}
