import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class MembersDatabase 
{
	private ArrayList<Member> membersList;
	
	public MembersDatabase() 
	{
		membersList = new ArrayList<Member>();
	}
	
	public ArrayList<Member> getAllMembers() 
	{
		ArrayList<Member> copy = new ArrayList<Member>();
		
		for (Member element: membersList) 
		{
			copy.add(element);
		}
		
		return copy;
	}
	
	public int getCount() 
	{
		return membersList.size();
	}
	
	public int getIndexOfMember(String name, String mobile) 
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
	
	public void deleteMember(int index) 
	{
		membersList.remove(index);
	}
	
	public String[] printArrayList(ArrayList<Member> arrayList) 
	{
		String[] stringArray = new String[arrayList.size()];
		
		int count = 0;
		
		for (Member element: arrayList) 
		{
			stringArray[count] =
				element.getName() + ", " + 
				element.getBirthday() + ", " + 
				element.getPassType() + ", " + 
				element.getMobile() + ", " + 
				"$" + element.getFee()
			;
			count++;
		}
		
		return stringArray;
	}
	
	private void sortArrayList(ArrayList<Member> arrayList) 
	{
		MemberNameComparator nameComparator = new MemberNameComparator();
		MemberMobileComparator mobileComparator = new MemberMobileComparator();
		MemberFeeComparator feeComparator = new MemberFeeComparator();
		
		String[] stringArray = this.printArrayList(arrayList);
		
		// Before sorting
		Run.resultsContent += "----Before sorting---\n";
		for (String element: stringArray) 
		{
			Run.resultsContent += element + "\n";
		}
		Run.resultsContent += "---------------------\n";
		
		arrayList.sort(feeComparator);
		arrayList.sort(mobileComparator);
		arrayList.sort(nameComparator);
	}
	
	public void runQueryPassType(String passType) 
	{
		ArrayList<Member> copy = new ArrayList<Member>();
		double totalFee = 0;
		
		for (Member element: membersList) 
		{
			if (element.getPassType().equals(passType)) 
			{
				copy.add(element);
				totalFee += element.getFee();
			}
		}
		
		this.sortArrayList(copy);
		
		String[] stringArray = this.printArrayList(copy);
		
		// After sorting
		Run.resultsContent += "----After sorting----\n";
		for (String element: stringArray) 
		{
			Run.resultsContent += element + "\n";
		}
		Run.resultsContent += "---------------------\n";
		
		Run.reportsContent += 
				"---query pass type---\n" + 
				"Pass Type: " + passType + "\n" +
				"Total Club Member size: " + copy.size() + "\n" +
				"Total membership fees: $" + totalFee + "\n" +
				"---------------------\n"
		;
	}
	
	public void runQueryAgeFee() 
	{
		LocalDate currentDate = LocalDate.now();
		
		double incomeA = 0; //0-8
		double incomeB = 0; //8-18
		double incomeC = 0; //18-65
		double incomeD = 0; //65++
		double incomeE = 0; //Unknown
		
		for (Member element: membersList) 
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate birthdayDate = LocalDate.parse(element.getBirthday(), formatter);
			Period intervalPeriod = Period.between(birthdayDate, currentDate);
			int age = intervalPeriod.getYears();
			
			if (age >= 0 && age < 8)
			{
				incomeA += element.getFee();
			}
			else if (age >= 8 && age < 18)
			{
				incomeB += element.getFee();
			}
			else if (age >= 18 && age < 65)
			{
				incomeC += element.getFee();
			}
			else if (age >= 65)
			{
				incomeD += element.getFee();
			}
			else 
			{
				incomeE += element.getFee();
			}
		}
		
		Run.reportsContent += 
			"----query age fee----\n" + 
			"Total Club Member size: " + this.getCount() + "\n" +
			"Age based fee income distribution\n" + 
			"[0,8]: $" + incomeA + "\n" + 
			"[8,18]: $" + incomeB + "\n" + 
			"[18,65]: $" + incomeC + "\n" + 
			"[65,-]: $" + incomeD + "\n" + 
			"Unknown: $" + incomeE + "\n" + 
			"---------------------\n"
		;
	}
}
