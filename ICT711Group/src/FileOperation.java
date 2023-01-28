import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
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
	    	System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public void writeToFile(String filename, String content) 
	{
		File file = new File(filename);
		FileWriter fw = null;
		BufferedWriter bw = null;
        PrintWriter pw = null;
	    try 
	    {
	    	if (file.createNewFile()) {
	            System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.");
			}
	    	fw = new FileWriter(filename, true);
	    	bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            
            pw.println(content);
            System.out.println("Data Successfully appended into file");
            pw.flush();
		} 
	    catch (IOException e) 
	    {
	    	System.out.println("An error occurred.");
			e.printStackTrace();
		}
	    finally 
	    {
	    	try {
                pw.close();
                bw.close();
                fw.close();
            } 
	    	catch (IOException e) 
	    	{
	    		System.out.println("An error occurred.");
				e.printStackTrace();
            }
        }
	}
	
	private void lineProcessor(String data) 
	{
		String firstWord = "";
		String restOfString = "";
		if(data.indexOf(" ") != -1) 
		{
			firstWord = data.substring(0, data.indexOf(" "));
			restOfString = data.substring(data.indexOf(" ") + 1);
		}
		else 
		{
			firstWord = data;
		}
		
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
	        	Run.saveEnabled = true;
	        	break;
        }
	}
	
	private void queryFromDatabase(String data) 
	{
		String firstWord = data.substring(0, data.indexOf(" "));
		String restOfString = data.substring(data.indexOf(" ") + 1);
		
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
			Run.resultsContent +=  
					"Successfully added a new member with name " + member.getName() + 
					" , birthday " + member.getBirthday() +
					" , pass type " + member.getPassType() +
					" , mobile number " + member.getMobile() +
					" and fee of $" + member.getFee() + "\n"
			;
		} 
		else 
		{
			Run.membersDatabase.updateMember(index, member);
			Run.resultsContent += 
					"Successfully update member with name " + member.getName() + 
					" and mobile number " + member.getMobile() +
					" with new details of: birthday " + member.getBirthday() +
					" , pass type " + member.getPassType() +
					" and fee of $" + member.getFee() + "\n"
			;
		}
	}
	
	private void deleteFromDatabase(String data) 
	{
		String[] dataArray = new String[2];
		dataArray = data.split("; ");
		
		int index = Run.membersDatabase.getIndexOfMember(dataArray[0], dataArray[1]);
		if(index == -1) 
		{
			Run.resultsContent += 
					"Delete unsuccessful, cannot find a member with name " + dataArray[0] + 
					" and mobile number " + dataArray[1] + "\n"
			;
		} 
		else 
		{
			Run.membersDatabase.deleteMember(index);
			Run.resultsContent += 
					"Successfully deleted member with name " + dataArray[0] + 
					" and mobile number " + dataArray[1] + "\n"
			;
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
