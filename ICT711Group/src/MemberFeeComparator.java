public class MemberFeeComparator extends MemberComparator
{
	@Override
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