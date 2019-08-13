package data_manu;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Author extends Book{
	
	public static String split_name(String s) { // return string like A','B and applied to encap to be' { A','B } ',
		String t = s;
		t = t.replace(" ", "','");
		return t;
	}
	public static String removeParenthesis(String s) {
		String t = s;
		t = t.replace("(", "");
		t = t.replace(")", "");
		return t;
	}
	public static String encap(String[] args) {	// format like ('AAA','BBB','CCC'),\n 
		String s = "(";
		String t = "";
		for(int i = 0; i < args.length; i++) {
			t = args[i];
			if(t.contains(" ")) {
				t = split_name(t);
			}
			if(t.contains("(")) {
				t = removeParenthesis(t);
			}
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
	public static String InputTableName() {			//return a string like "insert into `AA` values	\n"
		System.out.println("please input the table name for the scrit to insert into: ");
		String s = "insert into`";
		s += new Scanner(System.in).nextLine();
		return s+"`values\n";
	}
 	public static String  ReadFile(String FileName) {	//read file whose attributes in a line.
 		File f = new File(FileName);
 		String [] t = null;
 		String script = InputTableName();
 		try {
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				t = sc.nextLine().split(", ");
				script += encap(t); 
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		script = script.substring(0,script.length()-2)+';'; //-2 because have ',' and '\n' at the end
		return script;
 	}
 	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String script = ReadFile("Author.txt");
	
		writeFile(script, "AuthorInfo.txt");
	}

}
