
/**
 * Function Name: Member Mobile Comparitor
 * 
 * Description: 
 * The class MemberMobileComparator is inherited from MemberComparator class
 * The method "compare" compares members by their mobile number
 *
 */


// class MemberMobileComparator is inherited from MemberComparator class
public class MemberMobileComparator extends MemberComparator
{
	@Override // child class method is over-writing its base class method
	// method "compare" compares members by their mobile number
	public int compare(Member e1, Member e2)
	{
		return e1.getMobile().compareTo(e2.getMobile());
	}
}