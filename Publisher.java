package data_manu;

public class Publisher extends Author{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String script = ReadFile("Publisher.txt");
		writeFile(script,"PublisherInfo.txt");
	}

}
