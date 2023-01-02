import java.util.ArrayList;

public class MembersDatabase {
	
	private ArrayList<Member> membersList;
	
	public MembersDatabase() {
		membersList = new ArrayList<Member>();
	}
	public int getMember(String name, String mobile) {
		return -1;
	}
	public void updateMember(int index, Member member) {
		// toDo updateMember
	}
	public void addNewMember(Member member) {
		membersList.add(member);
	}
}
