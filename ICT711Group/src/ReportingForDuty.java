import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportingForDuty 
{
	public void writeToFile(String filename, String content) 
	{
		File file = new File(filename);
	    try 
	    {
	    	if (file.createNewFile()) {
	            System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File already exists.");
			}
	    	FileWriter myWriter = new FileWriter(filename);
	        myWriter.write(content);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
		} 
	    catch (IOException e) 
	    {
	    	System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}