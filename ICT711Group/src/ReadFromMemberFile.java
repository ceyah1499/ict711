import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromMemberFile {
	
	static Member member = new Member("","","","",0);

	public static void main(String[] args) {
		File file = new File("src/members.txt");
	    try {
			Scanner scan = new Scanner(file);
			String[] dataArray = new String[2];
			MembersDatabase membersDatabase = new MembersDatabase();
			while (scan.hasNextLine()) {
		        String data = scan.nextLine();
		        dataArray = data.split("; ");
		        for (int i = 0; i < dataArray.length; i++) {
			        String[] myArray3 = dataArray[i].split(" ", 2);
			        if (myArray3[0].equals("name")) {
			        	member.setName(myArray3[1]);
			        } else if (myArray3[0].equals("birthday")) {
			        	member.setBirthday(myArray3[1]);
			        } else if (myArray3[0].equals("pass")) {
			        	member.setPassType(myArray3[1]);
			        } else if (myArray3[0].equals("mobile")) {
			        	member.setMobile(myArray3[1]);
			        } else if (myArray3[0].equals("fee")) {
			        	String str = myArray3[1].substring(1);
			        	member.setFee(Double.parseDouble(str));
			        }
		        }
		        
		        membersDatabase.addNewMember(member);
		    }
			scan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
