package data_manu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Phone extends Author{
	
	public static void main(String[] args) {
		File f = new File("Author.txt");	
		File ff = new File("Publisher.txt");//open file
		
		String script= "insert into `Phone` values \n";
		String[] t = null;

		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				t = sc.nextLine().split(", |,");
				for(int i = 0; i < t.length; i++) {
					if(t[i].contains("(")) {
						script += encapPri(t[i].substring(0, t[i].indexOf("(")));
						script += "'" + t[i].charAt(t[i].indexOf("(")+1) + "'),\n";
					}
				}				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Scanner sc = new Scanner(ff);
			while(sc.hasNextLine()) {
				t = sc.nextLine().split(", ");
				for(int i = 0; i < t.length; i++) {
					if(t[i].contains("(")) {
						script += encapPri(t[i].substring(0, t[i].indexOf("(")));
						script += "'" + t[i].charAt(t[i].indexOf("(")+1) + "'),\n";
					}
				}				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		script = script.substring(0, script.length()-2)+ ";";
		writeFile(script, "Phone.txt");
		

	}

}
