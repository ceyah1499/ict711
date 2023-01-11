import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromMemberFile 
{
	static MembersDatabase membersDatabase = new MembersDatabase();

	public static void main(String[] args) 
	{
		File file = new File("src/members.txt");
	    try 
	    {
			Scanner scan = new Scanner(file);
			String[] dataArray = new String[2];
			
			while (scan.hasNextLine()) 
			{
		        String data = scan.nextLine();
		        dataArray = data.split("; ");
		        String name = "";
		        String birthday = "";
		        String passType = "";
		        String mobile = "";
		        Double fee = 0.0;
		        
		        for (int i = 0; i < dataArray.length; i++) 
		        {
			        String[] myArray3 = dataArray[i].split(" ", 2);
			        if (myArray3[0].equals("name")) 
			        {
			        	name = myArray3[1];
			        } 
			        else if (myArray3[0].equals("birthday")) 
			        {
			        	birthday = myArray3[1];
			        } 
			        else if (myArray3[0].equals("pass")) 
			        {
			        	passType = myArray3[1];
			        } 
			        else if (myArray3[0].equals("mobile")) 
			        {
			        	mobile = myArray3[1];
			        } 
			        else if (myArray3[0].equals("fee")) 
			        {
			        	String str = myArray3[1].substring(1);
			        	fee = Double.parseDouble(str);
			        }
		        }
		        Member member = new Member(name,birthday,passType,mobile,fee);
		        membersDatabase.addNewMember(member);
		    }
			scan.close();
			
		} 
	    catch (FileNotFoundException e) 
	    {
			e.printStackTrace();
		}
	    
	    // Uncomment to check if method works
	    // membersDatabase.printArrayList(membersDatabase.getAllMembers());
	}
}
