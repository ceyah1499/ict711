import java.util.ArrayList;

public class MembersDatabase {
	
	private ArrayList<Member> membersList;
	
	public MembersDatabase() {
		membersList = new ArrayList<Member>();
	}
	
	public int getMember(String name, String mobile) {
		// if exists return index of element;
		/* else */ return -1;
	}
	
	public void updateMember(int index, Member member) {
		// toDo updateMember
	}
	
	public void addNewMember(Member member) {
		membersList.add(member);
	}
	
	public boolean deleteMember(String name, String mobile) {
		/* check if exists */ 
		int index = this.getMember(name, mobile);
		if (index != -1) {
			membersList.remove(index);
			// if delete successful return true;
		}
		/* else */ return false;
	}
}
