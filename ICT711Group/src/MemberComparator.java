import java.util.Comparator; // for sorting objects

/**
 * Function name: Member Comparator
 * 
 * Description: The class MemberComparator implements an interface Comparator and sort objects.
 * 
 */

public abstract class MemberComparator implements Comparator<Member>
{
	// method for sorting
	public abstract int compare(Member e1, Member e2);
}