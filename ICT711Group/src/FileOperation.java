import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileOperation 
{
	private String name = "";
	private String birthday = "";
	private String passType = "";
	private String mobile = "";
	private Double fee = 0.0;
	
	public void readFromFile(String filename) 
	{
		File file = new File(filename);
	    try 
	    {
			Scanner scan = new Scanner(file);
			
			while (scan.hasNextLine()) 
			{
		        String data = scan.nextLine();
		        this.lineProcessor(data);
		    }
			
			scan.close();
		} 
	    catch (FileNotFoundException e) 
	    {
			e.printStackTrace();
		}
	}
	
	private void lineProcessor(String data) 
	{
		String firstWord = data.substring(0, data.indexOf(" "));
		String restOfString = data.substring(data.indexOf(" "));
		
		switch(firstWord) 
        {
	        case "name":
	        	this.upsertToDatabase(data);
	        	break;
	        case "add":
	        	this.upsertToDatabase(restOfString);
	        	break;
	        case "delete":
	        	this.deleteFromDatabase(restOfString);
	        	break;
	        case "query":
	        	this.queryFromDatabase(restOfString);
	        	break;
	        case "save":
	        	this.save();
	        	break;
        }
	}
	
	private void queryFromDatabase(String data) 
	{
		String firstWord = data.substring(0, data.indexOf(" "));
		String restOfString = data.substring(data.indexOf(" "));
		
		switch(firstWord) 
        {
	        case "age":
	        	if (restOfString.equals("fee")) 
	        	{
	        		Run.membersDatabase.runQueryAgeFee();
	        	}
	        	break;
	        case "pass":
	        	Run.membersDatabase.runQueryPassType(restOfString);
	        	break;
        }
	}
	
	private void save() 
	{
		// TO DO
	}
	
	private void upsertToDatabase(String data) 
	{
		String[] dataArray = new String[5];
		dataArray = data.split("; ");
		this.assigner(dataArray);
		
		Member member = new Member(name,birthday,passType,mobile,fee);
		int index = Run.membersDatabase.getIndexOfMember(member.getName(), member.getMobile());

		if(index == -1) 
		{
			Run.membersDatabase.addNewMember(member);
		} 
		else 
		{
			Run.membersDatabase.updateMember(index, member);
		}
	}
	
	private void deleteFromDatabase(String data) 
	{
		String[] dataArray = new String[2];
		dataArray = data.split("; ");
		this.assigner(dataArray);
		
        boolean isSuccessful = Run.membersDatabase.deleteMember(name, mobile);
        ReportingForDuty rfd = new ReportingForDuty();
        if (isSuccessful) 
        {
        	rfd.writeToFile("src/results.txt", "Delete successful");
        }
        else 
        {
        	rfd.writeToFile("src/results.txt", "Delete unsuccessful because the name " + name + " with phone number " + mobile + " cannot be found on the records.");
        }
	}
	
	private void assigner(String[] dataArray) 
	{
		name = "";
        birthday = "";
        passType = "";
        mobile = "";
        fee = 0.0;
		
		for(int i = 0; i < dataArray.length; i++) 
        {
	        String[] tempArray = dataArray[i].split(" ", 2);
	        if(tempArray[0].equals("name")) 
	        {
	        	name = tempArray[1];
	        } 
	        else if(tempArray[0].equals("birthday")) 
	        {
	        	birthday = tempArray[1];
	        } 
	        else if(tempArray[0].equals("pass")) 
	        {
	        	passType = tempArray[1];
	        } 
	        else if(tempArray[0].equals("mobile")) 
	        {
	        	mobile = tempArray[1];
	        } 
	        else if(tempArray[0].equals("fee")) 
	        {
	        	String str = tempArray[1].substring(1);
	        	fee = Double.parseDouble(str);
	        }
        }
	}
}
