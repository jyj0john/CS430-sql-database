package data_manu;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Member extends Author{
	
	public static String encap(String[] args) {
		String s = "(";
		String t = "";
		for(int i = 0; i < args.length; i++) {
			t = args[i];
			if(t.contains("  "))
				t = t.substring(2);
			if(t.contains(" "))
				t = split_name(t);
			if(t.contains("/")) {
				try {
					Date date = new SimpleDateFormat("MM/dd/yyyy").parse(t);
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
	
	public static void main(String[] args) {
		File f = new File("Members.txt");
		String script= "insert into `Member` values";
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
		writeFile(script,"MemberInfo.txt");

	}

}
