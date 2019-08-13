package data_manu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BorrowedBy extends Book{

	public static void main(String[] args) {
		File f = new File("Members.txt");
		String script= "insert into `BorrowedBy` values \n";
		String[] t = new String[4];
		String PriKey = "";
		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				t = sc.nextLine().split(", |,");


				 
				if(t[0].contains("  "))	//enter the part for complex attributes
				{	
					script += encapPri(PriKey);
					script += encapAtt(t);
					if(t.length == 2)
					{
						script = script.substring(0,script.length()-3) + ",null),\n";
					}
				
				}
				else {					//enter the part for next entity 
					PriKey = t[0];
						
					
					//need revise for member because a priKey should reserve for all attributes follows
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		script = script.substring(0,script.length()-2)+';'; //-2 because have ',' and '\n' at the end
		writeFile(script,"BorrowedBy.txt");
		
		
	}

	private static String encapdate(String[] t) {
		// TODO Auto-generated method stub
		return null;
	}

}
