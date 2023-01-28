import java.io.File; // to be able to work with files
import java.io.FileNotFoundException; // exceptions checker: when trying to access a file 
import java.io.IOException; // exceptions checker: when an input/output error occurs
import java.io.FileWriter; // to write characters into a file
import java.io.BufferedWriter; // provides buffering to make the performance faster
import java.io.PrintWriter; // prints formatted representations of objects to a text-output stream
import java.util.Scanner; // used for obtaining the input the data from outside the application



public class FileOperation // class FileOperation  
{
	// local variables
	private String name = "";
	private String birthday = "";
	private String passType = "";
	private String mobile = "";
	private Double fee = 0.0;
	

/**
 * Function name: Read from file
 * Description: The method reads the information from the file
 * 
 * @param filename (String)
 * 
 */
	
	public void readFromFile(String filename) // method that reads information from the file; takes a filename 
	{
		File file = new File(filename); // creating a new object of File class
	    try // trying to read information from the file
	    {
			Scanner scan = new Scanner(file); // read input from the file
			
			while (scan.hasNextLine()) // continue iteration until the file has the line that follows the current
			{
		        String data = scan.nextLine();
		        this.lineProcessor(data);
		    }
			
			scan.close(); // closing class Scanner
		} 
	    catch (FileNotFoundException e) // error handling when a file with the given name is not found
	    {
	    	System.out.println("An error occurred."); // showing an error to a user
			e.printStackTrace(); // showing the details of the error
		}
	}
	
	/**
	 * Function name: Write to file
	 * Description: 
	 * 1. Write the information to a file 
	 * 2. Get the @param filename and a string that will be recorded
	 * 
	 * 
	 * @param filename (String)
	 * @param content  (String)
	 * 
	 */
	
	
	public void writeToFile(String filename, String content) // method that writes information to a file; takes a filename and a string that will be recorded
	{
		// objects initialization
		File file = new File(filename);
		FileWriter fw = null;
		BufferedWriter bw = null;
        PrintWriter pw = null;
	    try // trying to create a new file and write information to it
	    {
	    	if (file.createNewFile()) {
	            System.out.println("File created: " + file.getName()); // showing a user the information that file has been created as well as whoing the name of a new file
			} else {
				System.out.println("File already exists."); // inform a user that a file already exists 
			}
	    	fw = new FileWriter(filename, true); // to write data in character form to file
	    	bw = new BufferedWriter(fw); // write text to a character-output stream, buffering characters
            pw = new PrintWriter(bw); // write data in the form of text
            
            pw.println(content); // showing the data in a console
            System.out.println("Data Successfully appended into file"); // informing a user
            pw.flush(); // to flush the content of the buffer to the output stream
		} 
	    catch (IOException e) // catches exceptions while accessing information using file
	    {
	    	System.out.println("An error occurred."); // informing a user about an error
			e.printStackTrace(); // showing the details of the error
		}
	    finally // execute code no matter if there is an exception or not 
	    {
	    	try {
                pw.close(); // close the stream and release the resources that were busy in the stream, if any
                bw.close(); // close the stream and release the resources that were busy in the stream, if any
                fw.close(); // close the stream and release the resources that were busy in the stream, if any
            } 
	    	catch (IOException e) // catches exceptions while accessing information
	    	{
	    		System.out.println("An error occurred."); // informing a user about an error
				e.printStackTrace(); // showing the details of the error
            }
        }
	}
	
	/**
	 * Function name: 
	 * Description: The method that processes using strings. The commands: "add", 
	 * "delete", "query" and "save".
	 * 
	 * @param data
	 */
	
	private void lineProcessor(String data) // method that processes strings 
	{
		String firstWord = ""; // variable
		String restOfString = ""; // variable
		if(data.indexOf(" ") != -1) // check is there is any space characters occur in a string 
		{
			firstWord = data.substring(0, data.indexOf(" ")); // extract the first word in a string and write it to a variable firstWord  
			restOfString = data.substring(data.indexOf(" ") + 1); // extract the rest of the string and write it to a variable restOfString
		}
		else 
		{
			firstWord = data; // is there aren't any space characters occur in a string write string to a variable firstWord
		}
		
		switch(firstWord) // choosing the action depending on the first word that appears in a string 
        {
	        case "name": // if there "name" is the first word
	        	this.upsertToDatabase(data); // use the method to insert the data into the database 
	        	break; // terminate switch statement 
	        case "add": // if there "add" is the first word
	        	this.upsertToDatabase(restOfString); // adding the rest of the string into the database
	        	break; // terminate switch statement
	        case "delete": // if there "delete" is the first word
	        	this.deleteFromDatabase(restOfString); //delete the rest of the string from the database 
	        	break; // terminate switch statement
	        case "query": // if there "query" is the first word
	        	this.queryFromDatabase(restOfString); // query the list of members
	        	break; // terminate switch statement
	        case "save": // if there "save" is the first word
	        	Run.saveEnabled = true; // allow to save data into a file
	        	break; // terminate switch statement
        }
	}
	
	/**
	 * Function name: Database query
	 * Description: 
	 * 1. Query the list of members by extract the first word in a string and write it to a variable firstWord
	 * 2. Extract the rest of the string and write it to a variable restOfString
	 * Note:Choose the action depending on the first word that appears in a string
	 * 
	 * @param data
	 */
	
	
	private void queryFromDatabase(String data)  // query the list of members
	{
		String firstWord = data.substring(0, data.indexOf(" ")); // extract the first word in a string and write it to a variable firstWord
		String restOfString = data.substring(data.indexOf(" ") + 1); // extract the rest of the string and write it to a variable restOfString
		
		switch(firstWord) // choosing the action depending on the first word that appears in a string
        {
	        case "age": // if there "age" is the first word
	        	if (restOfString.equals("fee")) // if there "fee" is the rest of a string
	        	{
	        		Run.membersDatabase.runQueryAgeFee(); // query the list of members by age
	        	}
	        	break; // terminate switch statement
	        case "pass": // if there "pass" is the first word
	        	Run.membersDatabase.runQueryPassType(restOfString); // query the list of members by type of a pass
	        	break; // terminate switch statement
        }
	}
	
	private void upsertToDatabase(String data) // a method to insert the data into the database 
	{
		String[] dataArray = new String[5]; // creating an array that contains name, birthday, pass, mobile and fee of the member
		dataArray = data.split("; "); // splitting the string to separate all the data about the member
		this.assigner(dataArray); // assigning data to member according to the information contained in a string
		
		Member member = new Member(name,birthday,passType,mobile,fee); //creating a new object of Member class
		int index = Run.membersDatabase.getIndexOfMember(member.getName(), member.getMobile()); // receiving the index of a member in an array depending on the name and the mobile number 
		if(index == -1) // checking if the member exists and if not running the method to add a new member into a base 
		{
			Run.membersDatabase.addNewMember(member); // adding a new member into a database
			Run.resultsContent += // inform a user about adding a new member 
					"Successfully added a new member with name " + member.getName() + 
					" , birthday " + member.getBirthday() +
					" , pass type " + member.getPassType() +
					" , mobile number " + member.getMobile() +
					" and fee of $" + member.getFee() + "\n"
			;
		} 
		else // if the member already exists running the method to update a member's data
		{
			Run.membersDatabase.updateMember(index, member); // updating a member's data
			Run.resultsContent += // inform a user about updating information about a member
					"Successfully update member with name " + member.getName() + 
					" and mobile number " + member.getMobile() +
					" with new details of: birthday " + member.getBirthday() +
					" , pass type " + member.getPassType() +
					" and fee of $" + member.getFee() + "\n"
			;
		}
	}
	
	private void deleteFromDatabase(String data) // a method to delete the existing data from the database
	{
		String[] dataArray = new String[2]; // creating a new array that contains two elements
		dataArray = data.split("; "); // splitting the string to separate all the data about the member
		
		int index = Run.membersDatabase.getIndexOfMember(dataArray[0], dataArray[1]); // receiving the index of a member in an array depending on the name and the mobile number
		if(index == -1) // checking if the member exists and if not running the method show a user information about an unsuccessful deleting
		{
			Run.resultsContent += // inform a user about an unsuccessful deleting
					"Delete unsuccessful, cannot find a member with name " + dataArray[0] + 
					" and mobile number " + dataArray[1] + "\n"
			;
		} 
		else 
		{
			Run.membersDatabase.deleteMember(index); // otherwise delete the existing member
			Run.resultsContent += // and inform a user about a successful deleting
					"Successfully deleted member with name " + dataArray[0] + 
					" and mobile number " + dataArray[1] + "\n"
			;
		}
	}
	
	private void assigner(String[] dataArray)  // assigning data to member according to the information contained in a string 
	{
		name = ""; // reset variable
        birthday = ""; // reset variable
        passType = ""; // reset variable
        mobile = ""; // reset variable
        fee = 0.0; // reset variable
		
		for(int i = 0; i < dataArray.length; i++) // search inside the array to assign parameters to variables depending on the data from an array
        {
	        String[] tempArray = dataArray[i].split(" ", 2); // splitting the array by space into 2 parts
	        
	        if(tempArray[0].equals("name")) // if the first word of the left part of an array is name
	        {
	        	name = tempArray[1]; // assign the variable name an appropriate value 
	        } 
	        else if(tempArray[0].equals("birthday")) // if the first word of the left part of an array is birthday
	        {
	        	birthday = tempArray[1]; // assign the variable birthday an appropriate value
	        } 
	        else if(tempArray[0].equals("pass")) // if the first word of the left part of an array is pass
	        {
	        	passType = tempArray[1]; // assign the variable passType an appropriate value
	        } 
	        else if(tempArray[0].equals("mobile")) // if the first word of the left part of an array is mobile 
	        {
	        	mobile = tempArray[1]; // assign the variable mobile an appropriate value
	        } 
	        else if(tempArray[0].equals("fee")) // if the first word of the left part of an array is fee
	        {
	        	String str = tempArray[1].substring(1); // return fee without "$" sign
	        	fee = Double.parseDouble(str); // assign the variable fee an appropriate value that was converted from String to Double 
	        }
        }
	}
}
