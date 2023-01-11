public class Run 
{
	public static void main(String[] args) 
	{
		MembersDatabase membersDatabase = new MembersDatabase();
		ReadFromMemberFile rfmf = new ReadFromMemberFile();
		rfmf.readFromFile(membersDatabase);
		membersDatabase.runQueryAgeFee();
	}
}
