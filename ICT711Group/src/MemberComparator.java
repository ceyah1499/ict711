import java.util.Comparator;

public abstract class MemberComparator implements Comparator<Member>
{
	public abstract int compare(Member e1, Member e2);
}