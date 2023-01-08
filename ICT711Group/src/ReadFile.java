import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
  public static void main(String[] args) {
    try {
   
      File myObj = new File("src/instructions.txt");
      Scanner myReader = new Scanner(myObj);
      MembersDatabase myDatabase = new MembersDatabase();
      String[] myArray = new String[2];
      String[] myArray2 = new String[5];
//      String[] myArray3 = new String[];
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        myArray = data.split(" ", 2);
/*        for (int i = 0; i < myArray.length; i++) {
        System.out.println(myArray[i]);
        } */
        myArray2 = myArray[1].split("; ");
        Member object = new Member("","","","",0);
        for (int i = 0; i < myArray2.length; i++) {
        String[] myArray3 = myArray2[i].split(" ", 2);
        if (myArray3[0].equals("name")) {
        	object.setName(myArray3[1]);
        } else if (myArray3[0].equals("birthday")) {
        	object.setBirthday(myArray3[1]);
        } else if (myArray3[0].equals("pass")) {
        	object.setPassType(myArray3[1]);
        } else if (myArray3[0].equals("mobile")) {
        	object.setMobile(myArray3[1]);
        } else if (myArray3[0].equals("fee")) {
        	String str = myArray3[1].substring(1);
//        	System.out.println(str);
        	object.setFee(Double.parseDouble(str));
        } 
//        System.out.println(myArray3[0]+"a");
//        System.out.println(myArray3[1]);

        }
		System.out.println(object.getName());
		System.out.println(object.getBirthday());
		System.out.println(object.getPassType());
		System.out.println(object.getMobile());
		System.out.println(object.getFee());
		int index = -1;
		index = myDatabase.getMember(object.getName(), object.getMobile());

		if(index == -1) {
			myDatabase.addNewMember(object);
		} else {
			myDatabase.updateMember(index, object);
		}
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}