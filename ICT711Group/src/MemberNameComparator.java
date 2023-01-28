// class MemberNameComparator is inherited from MemberComparator class
public class MemberNameComparator extends MemberComparator
{
	@Override // child class method is over-writing its base class method
	// method "compare" compares members by name
	public int compare(Member e1, Member e2)
	{
		return e1.getName().compareTo(e2.getName());
	}
}