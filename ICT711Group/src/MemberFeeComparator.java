// class MemberFeeComparator is inherited from MemberComparator class
public class MemberFeeComparator extends MemberComparator
{
	@Override // child class method is over-writing its base class method
// method "compare" compares members by fee
	public int compare(Member e1, Member e2)
	{
		if(e1.getFee() == e2.getFee()) 
			return 0;
		else if(e1.getFee()>e2.getFee()) 
			return 1;
		else 
			return -1;
	}
}