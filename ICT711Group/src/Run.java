public class Run 
{
	static MembersDatabase membersDatabase = new MembersDatabase();
	
	public static void main(String[] args) 
	{
		FileOperation fileOp = new FileOperation();
		fileOp.readFromFile("src/members.txt");
		fileOp.readFromFile("src/instructions.txt");
		membersDatabase.printArrayList(membersDatabase.getAllMembers());
		
		// Uncomment to check if method works
	    
	}
}
