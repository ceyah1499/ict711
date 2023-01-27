public class MemberNameComparator extends MemberComparator
{
	@Override
	public int compare(Member e1, Member e2)
	{
		return e1.getName().compareTo(e2.getName());
	}
}