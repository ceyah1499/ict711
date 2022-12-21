import java.util.ArrayList;

public class MembersDatabase {
	
	private ArrayList<Member> membersList;
	
	public MembersDatabase() {
		membersList = new ArrayList<Member>();
	}

	public void addNewMember(Member member) {
		membersList.add(member);
	}
}
