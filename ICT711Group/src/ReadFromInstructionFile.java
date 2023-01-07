import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromInstructionFile {
	
	Member member = new Member("","","","",0);

	public void main(String[] args) {
		File file = new File("src/instructions.txt");
	    try {
			Scanner scan = new Scanner(file);
			String[] dataArray = new String[2];
			MembersDatabase membersDatabase = new MembersDatabase();
			while (scan.hasNextLine()) {
		        String data = scan.nextLine();
		        dataArray = data.split(" ", 2);

		        switch(dataArray[0]) {
			        case "add":
			        	membersDatabase.addNewMember(converterForAdd(dataArray[1]));
			        	break;
			        case "delete":
			        	// membersDatabase.deleteMember(converterForDelete(dataArray[1]));
			        	break;
			        case "query":
			        	// membersDatabase.queryMember(converterForQuery(dataArray[1]));
			        	break;
			        case "save":
			        	// to do save
			        	break;
			        default:
			        	// to be determined
			        	break;
		        }
		    }
			scan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// might be necessary
	public Member converterForAdd(String data) {
		String[] myArray2 = data.split("; ");
        for (int i = 0; i < myArray2.length; i++) {
	        String[] myArray3 = myArray2[i].split(" ", 2);
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
        return member;
	}
}
