/* Mai
 *Version 1.0
 *3/14/2012
 *Braedon McCoy
*/
import java.io.*;
import java.util.*;
public class main{
<<<<<<< HEAD
	public static List<String> input = new ArrayList<String>(); // User input
	public static List<String> commands = new ArrayList<String>(); // Commands in txt
	public static List<String> filtered = new ArrayList<String>(); // Common items from above two
	public static List<String> full = new ArrayList<String>(); // Full list of entries in text
=======
	public static ArrayList input = new ArrayList(); // User input
	public static ArrayList commands = new ArrayList(); // Commands in txt
	public static ArrayList filtered = new ArrayList(); // Common items from above two
	public static ArrayList full = new ArrayList(); // Full list of entries in text
>>>>>>> fc7f993c72477f4ee121e83dbf66d61ee9d2e9ad
	public static int numCommands = 0;

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String text = "";
		ArrayList arr = new ArrayList();
		ArrayList action = new ArrayList();
		String finalCommand;
<<<<<<< HEAD
		String result;

		result = getCommands("../command_list.txt");
		if(result != ""){
			print(result);
		}
		print("Please enter text to speak: ");
		text = scan.nextLine();

		//print("ArrayList: ");
		//printAL(commands);
		input = split(text.toLower()); // input is an ArrayList that contains the input.
		print("ArrayList: ");
		printAL(input);
		filtered = check(); // action is the commands found in the input.
		//print("ArrayList: ");
		//printAL(filtered);
=======


		getCommands("command_list.txt");
		print("Please enter text to speak: ");
		text = scan.nextLine();
		print("You said: " + text);

		input = split(text); // input is an ArrayList that contains the input.
		filtered = check(); // action is the commands found in the input.
>>>>>>> fc7f993c72477f4ee121e83dbf66d61ee9d2e9ad
		//finalCommand = process(); // get associated actions from txt and runs them.
	}

	public static void print(String text){
		System.out.println(text);
		return;
	}

<<<<<<< HEAD
	public static void printAL(List<String> al){
		int i = 0;
		for(i = 0; i < al.size(); i++){
			System.out.println(al.get(i));
		}
		return;
	}

	public static List split(String orig){
		List<String> finalArr = new ArrayList<String>();
=======
	public static ArrayList split(String orig){
		ArrayList finalArr = new ArrayList();
>>>>>>> fc7f993c72477f4ee121e83dbf66d61ee9d2e9ad
		String[] arr = orig.split(" ");
		int i = 0;
		for(i = 0; i < arr.length; i++){
			finalArr.add(arr[i]);
		}
<<<<<<< HEAD
		return(finalArr);
	}

	public static String seperateCom(String full){ // Giving me trouble, not returning anything useful. Need a better way.
=======
		return finalArr;
	}

	public static String seperateCom(String full){
>>>>>>> fc7f993c72477f4ee121e83dbf66d61ee9d2e9ad
		int pos = 0;
		String fin;

		pos = full.indexOf('|');
		if(pos > 0){
			pos--;
			fin = full.substring(0,pos);
			return(fin);
		}else{
			return("");
		}

	}

	public static String seperateAct(String full){
		int pos = 0;
		int end = full.length();
		String fin;

		pos = full.indexOf('|');
		if(pos > 0){
			pos++;
			fin = full.substring(pos,end);
			return(fin);
		}else{
			return("");
		}

	}

	public static String getCommands(String location){
		String line = "";
		try{
			BufferedReader reader = new BufferedReader(
			new FileReader(location), 8 * 1024);
<<<<<<< HEAD
			if(!reader.ready()){
				print("ERROR: Cannot create Reader");
				return("ERROR");
			}
			while((line = reader.readLine()) != null){
				//print("Full: " + line);
				full.add(line);
				//print("Com: " + line);

=======
			while((line = reader.readLine()) != null){
				full.add(line);
				line = seperateCom(line);
>>>>>>> fc7f993c72477f4ee121e83dbf66d61ee9d2e9ad
				if(line != ""){
					commands.add(line);
					numCommands++;
				}else{
					return("ERROR: Command not found!");
				}
			}
			return("");
		} catch(IOException e){
			return("ERROR: " + e.getMessage());
		}
	}
<<<<<<< HEAD

=======
>>>>>>> fc7f993c72477f4ee121e83dbf66d61ee9d2e9ad
	public static String remCommand(String command){
		commands.remove(command);
		numCommands--;
		return("");
	}
<<<<<<< HEAD

	public static ArrayList check(){
		ArrayList common = new ArrayList(commands);
		int numCommon = 0;
		common.retainAll(input);
		/*
		for(Iterator<String> i = input.iterator(); i.hasNext();){	//Iterates through the ArrayList
			String item = i.next();
			print("Searched For: " + item);
			if(commands.contains(item)){	//Checks for commonalities and adds them to Common
				common.add(item);
				print("Found: " + item);
				numCommon++;
			}

		}
		*/
		return(common);
	}

=======
	public static ArrayList check(){
		ArrayList common = new ArrayList();
		int numCommon = 0;
		for(Iterator<String> i = input.iterator(); i.hasNext();){	//Iterates through the ArrayList
			String item = i.next();
			if(commands.contains(item)){	//Checks for commonalities and adds them to Common
				common.add(item);
				print(item);
				numCommon++;
			}
			//print("Searched For: " + item);
		}
		return common;
	}
>>>>>>> fc7f993c72477f4ee121e83dbf66d61ee9d2e9ad
	public static String process(){ // Cannot run programs. Need to debug and fix.
		int index;
		String orig;
		String act;
		int sec;
		String second;
		String[] array;

		if(filtered.size() > 1){
			print("Multiple Responses. This will be coded soon"); // Figure out what your going to do about this soon.
		}else{
		/*	act = (String)full.get(0);


			sec = act.indexOf('~') + 1;
			if(sec != 1){
				second = act.substring(sec, act.length());

				print(act);
				print(second);

				try {
		   			String ls_str;

		    		Process ls_proc = Runtime.getRuntime().exec(act);
		    		Process ls_proc2 = Runtime.getRuntime().exec(second);
				    // get its output (your input) stream

				    DataInputStream ls_in = new DataInputStream(
			                                          ls_proc.getInputStream());

				    try {
					while ((ls_str = ls_in.readLine()) != null) {
					    System.out.println(ls_str);
					}
				    } catch (IOException e) {
					System.exit(0);
				    }
				} catch (IOException e1) {
				    System.err.println(e1);
				    System.exit(1);
				}
			}else{
				print(act);
				second = null;
				try {
		   			String ls_str;

		    		Process ls_proc = Runtime.getRuntime().exec(act);

				    // get its output (your input) stream

				    DataInputStream ls_in = new DataInputStream(
			                                          ls_proc.getInputStream());

				    try {
					while ((ls_str = ls_in.readLine()) != null) {
					    System.out.println(ls_str);
					}
				    } catch (IOException e) {
					System.exit(0);
				    }
				} catch (IOException e1) {
				    System.err.println(e1);
				    System.exit(1);
				}
			}*/
		}
		return(" ");
	}
}