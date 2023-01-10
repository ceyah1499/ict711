import java.util.ArrayList;

public class MembersDatabase 
{
	private ArrayList<Member> membersList;
	
	public MembersDatabase() 
	{
		membersList = new ArrayList<Member>();
	}
	
	public int getMember(String name, String mobile) 
	{
		for (int i = 0; i < membersList.size(); i++) 
		{
			if (membersList.get(i).getName().equals(name) && membersList.get(i).getMobile().equals(mobile)) 
			{
				return i;
			}
		}
		
		return -1;
	}
	
	public void addNewMember(Member member) 
	{
		membersList.add(member);
	}
	
	public void updateMember(int index, Member member) 
	{
		membersList.get(index).setName(member.getName());
		membersList.get(index).setBirthday(member.getBirthday());
		membersList.get(index).setPassType(member.getPassType());
		membersList.get(index).setMobile(member.getMobile());
		membersList.get(index).setFee(member.getFee());
	}
	
	public boolean deleteMember(String name, String mobile) 
	{
		int index = this.getMember(name, mobile);
		
		if (index != -1) 
		{
			membersList.remove(index);
			return true;
		}
		
		return false;
	}
}
