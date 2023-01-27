import java.time.LocalDateTime;

public class Run 
{
	static MembersDatabase membersDatabase = new MembersDatabase();
	
	public static void main(String[] args) 
	{
		LocalDateTime currentDateTime = LocalDateTime.now();
		FileOperation fileOp = new FileOperation();
		fileOp.writeToFile("src/results.txt", "Result for execution at " + currentDateTime + "\n");
		fileOp.writeToFile("src/reports.txt", "Report for execution at " + currentDateTime + "\n");
		fileOp.readFromFile("src/members.txt");
		fileOp.readFromFile("src/instructions.txt");
	}
}
