import java.time.LocalDateTime;

public class Run 
{
	static MembersDatabase membersDatabase = new MembersDatabase();
	static boolean saveEnabled = false;
	static String resultsContent = "";
	static String reportsContent = "";
	
	public static void main(String[] args) 
	{
		FileOperation fileOp = new FileOperation();
		fileOp.readFromFile("src/members.txt");
		fileOp.readFromFile("src/instructions.txt");
		if(Run.saveEnabled) 
		{
			LocalDateTime currentDateTime = LocalDateTime.now();
			fileOp.writeToFile("src/results.txt", "Result for execution at " + currentDateTime + "\n");
			fileOp.writeToFile("src/results.txt", resultsContent);
			fileOp.writeToFile("src/results.txt", "");
			fileOp.writeToFile("src/reports.txt", "Report for execution at " + currentDateTime + "\n");
			fileOp.writeToFile("src/reports.txt", reportsContent);
		}
	}
}
