package data_manu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PublisherHasPhone extends Publisher{
	public static String encap(String s) {
		String t = "'";
		t = t + s + "'";
		return t;
	}
	public static void main(String[] args) {
		File f = new File("Publisher.txt");	//open file
		String script= "insert into `PublisherHasPhone` values \n";
		String[] t = null;

		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				t = sc.nextLine().split(", ");
				
				for(int i = 2; i < t.length; i++) {
					script += encapPri(t[0]);	
					if(t[i].contains("("))
						t[i] = t[i].substring(0, t[i].indexOf("("));
					script+= encap(t[i]);
					script += "),\n";
				}
				
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		script = script.substring(0, script.length()-2)+ ";";
		writeFile(script, "PublisherHasPhone");
		
	}

}
