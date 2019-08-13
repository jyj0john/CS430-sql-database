package data_manu;
import java.io.*;
import java.util.*;
public class ToTable {
public ArrayList<String> SetTableAttributes() {
	ArrayList<String>s = null;
	System.out.println("set table attribute name");
	s.add(new Scanner(System.in).next());
	return s;
}
public File openFile(String filename) {
	File f = new File("filename");
	return f;
}
public String setFilename() {
	System.out.println("set new file name");
	return new Scanner(System.in).next();
}
public static void writeFile(String s) {
	try {
		PrintWriter p = new PrintWriter(".txt");
		p.print(s);
		p.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public ArrayList<String> manuFile(File f, ArrayList<String>attributes) {
	ArrayList<String> s = null;
	String[] t = null;
	String rows = "";
	String PriKey = "";
	try {
		Scanner sc = new Scanner(f);
		while(sc.hasNextLine()) {
			t = sc.nextLine().split(", ");
			
			
			 
			if(t[0].contains("  "))	//enter the part for complex attribute
				rows += PriKey;
				
			else {
				s.add(rows);//enter the part for next entity 
				PriKey = t[0];
				//need little revise for member because a priKey should reserve for all attributes follows
			}
		
		}
	}
		catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return s;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
