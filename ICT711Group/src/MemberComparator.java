import java.util.Comparator; // for sorting objects
// class MemberComparator implements an interface Comparator
public abstract class MemberComparator implements Comparator<Member>
{
	// method for sorting
	public abstract int compare(Member e1, Member e2);
}