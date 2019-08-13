package data_manu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Book {
	public static void writeFile(String s, String Filename) {
		try {
			PrintWriter p = new PrintWriter(Filename);
			p.print(s);
			p.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	public static void writeFile2(String s) {
		try {
			PrintWriter p = new PrintWriter("NewBook2.txt");
			p.print(s);
			p.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public static String encap(String[] args) {	// format like ('AAA','BBB','CCC'),\n 
		String s = "(";
		String t = "";
		for(int i = 0; i < args.length; i++) {
			t = args[i];
			if(t.contains("  "))
				t = t.substring(2);
			if(t.contains("/")) {
				try {
					Date date = new SimpleDateFormat("dd/MM/yyyy").parse(t);
					t = new SimpleDateFormat("yyyy-MM-dd").format(date);
			//		System.out.println(t);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			s += ("'" + t + "'"+ ",");				
		}
		s = s.substring(0, s.length()-1);
		s+="),\n";
		return s;
	}
	
	
	
	public static String encapPri(String s) {	// format like ('AAA',
		String t = "('";
		t += s+"',";
		return t;
	}
	/*	this two are for complex and multi-valued attributes that not in a line	*/
	public static String encapAtt(String[] args) {	//format like 'AAA','BBB','CCC'),\n 
		String s = "";
		String t = "";
		for(int i = 0; i < args.length; i++) {
			t = args[i];
			if(t.contains("  "))
				t = t.substring(2);
			if(t.contains("/")) {
				try {
					Date date = new SimpleDateFormat("dd/MM/yyyy").parse(t);
					t = new SimpleDateFormat("yyyy-MM-dd").format(date);
					System.out.println(t);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			
			s += ("'" + t + "'"+ ",");				
		}
		s = s.substring(0, s.length()-1);
		s+="),\n";
		return s;
	}
	public static void main(String[]args) {
		File f = new File("Book.txt");
		String script= "insert into `Book` values";
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
						
					
					//need revise for member because a priKey should reserve for all attributes follows
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		script = script.substring(0,script.length()-2)+';'; //-2 because have ',' and '\n' at the end
		writeFile(script, "BookInfo.txt");
		
		
	}
	private static String encapKey(String string) {		//not used yet.
		String s = "'";
		return s + string + "'),\n";
	}
		
}

