
public class InstructionFile {

		public static void main(String[] args) {
			/*
			 * This task will create a method that read contents of instructionFile.txt
			 * YunaTask1: Separate it line by line 
			 */
	         String str = "name James Bloggs; birthday 20/12/1978; pass Gold; mobile 04111111; fee $60";
	        String parts []	= str.split (";"); 
	        
	        for (int i =0 ;  i <parts.length; i++) {
	        	System.out.println( parts [i] );
	        	
	        }
	        /*
	         * YunaTask2: Decide which method to be executed using parameter based on the first word of each line.
	         */
	        
		}
}

	
