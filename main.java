/* Mai
 *Version 1.0
 *3/14/2012
 *Braedon McCoy
*/
import java.io.*;
import java.util.*;
import java.lang.*;
public class main{
	public static List<String> input = new ArrayList<String>(); // User input
	public static List<String> commands = new ArrayList<String>(); // Commands in txt.
	public static List<String> actions = new ArrayList<String>(); // Actions in txt.
	public static List<String> dialogue = new ArrayList<String>(); // Dialogue in txt
	public static List<String> filtered = new ArrayList<String>(); // Common items from commands and actions
	public static List<String> full = new ArrayList<String>(); // Full list of entries in text
	public static List<String> todo = new ArrayList<String>(); // Final commands to execute.
	public static List<String> response = new ArrayList<String>(); // Responses to dialogue.
	public static int numCommands = 0;

	public static void main(String[] args){
		String result;
		boolean status;

		print("Hello and welcome. I am MAI. How can I help you?\n");

		//status =
		result = getCommands("../command_list.txt");
		if(result != ""){
			print(result);
		}
		result = getActions("../action_list.txt");
		if(result != ""){
			print(result);
		}
		result = getDialogue("../dialogue_list.txt");
		if(result != ""){
			print(result);
		}

		do{
			status = run();
		}while(status == true);
	}

	public static void print(String text){
		System.out.println(text);
		return;
	}

	public static void printAL(List<String> al){
		int i = 0;
		for(i = 0; i < al.size(); i++){
			System.out.println(al.get(i));
		}
		return;
	}

	public static String formatInput(String input){
		char letter;
		int ascii;
		String formatted = "";

		input = input.toLowerCase();
		for(int i = 0; i< input.length(); i++){
			letter = input.charAt(i);
			ascii = letter;
			if(((ascii >= 97) && (ascii <= 122)) || (ascii == 32)){
				formatted += letter;
			}
		}
		return(formatted);
	}

	public static List split(String orig){
		List<String> finalArr = new ArrayList<String>();
		String[] arr = orig.split(" ");
		int i = 0;
		for(i = 0; i < arr.length; i++){
			finalArr.add(arr[i]);
		}
		return(finalArr);
	}

	public static String seperateCom(String full){ // Giving me trouble, not returning anything useful. Need a better way.
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
				FileReader r = new FileReader(location);
				if (!(r.ready())){

				}
					BufferedReader reader = new BufferedReader(r);
				if(!reader.ready()){
					print("ERROR: Cannot create Reader");
					return("ERROR");
				}else{
					//print("Reader is ready. Getting Commands.");
				}
			while((line = reader.readLine()) != null){
				//print("Full: " + line);
				full.add(line);
				//print("Com: " + line);
				if(line != ""){
					commands.add(line);
					numCommands++;
				}else{
					return("ERROR: Command not found!");
				}
			}
			return("");
		} catch(IOException e){
			return("ERROR JavaIOException e:" + e.getMessage());
		}
	}

	public static String getActions(String location){
		String line = "";
		try{
				FileReader r = new FileReader(location);
				if (!(r.ready())){

				}
					BufferedReader reader = new BufferedReader(r);
				if(!reader.ready()){
					print("ERROR: Cannot create Reader");
					return("ERROR");
				}else{
					//print("Reader is ready. Getting Actions.");
				}
			while((line = reader.readLine()) != null){
				if(line != ""){
					actions.add(line);
				}else{
					return("ERROR: Action not found!");
				}
			}
			return("");
		} catch(IOException e){
			return("ERROR JavaIOException e:" + e.getMessage());
		}
	}

	public static String getDialogue(String location){
		String line = "";
		try{
				FileReader r = new FileReader(location);
				if (!(r.ready())){

				}
					BufferedReader reader = new BufferedReader(r);
				if(!reader.ready()){
					print("ERROR: Cannot create Reader");
					return("ERROR");
				}else{
					//print("Reader is ready. Getting Actions.");
					while((line = reader.readLine()) != null){
						if(line != ""){
							dialogue.add(line);
						}else{
							return("ERROR: Action not found!");
						}
					}
				}
			return("");
		} catch(IOException e){
			return("ERROR JavaIOException e:" + e.getMessage());
		}
	}

	public static boolean run(){
		Scanner scan = new Scanner(System.in);
		String text = "";

		System.out.print("User: ");
		text = scan.nextLine();
		if(text.contains("exit")){
			print("Goodbye...");
			return(false);
		}
		text = formatInput(text);
		//print(text);

		input = split(text); // input is an ArrayList that contains the input.
		filtered = check(); // action is the commands found in the input.
		if(filtered.size() != 0){
			process(); // get associated actions from txt and runs them.
		}else{
			response = checkDia();
			if(response.size()  == 0){
				print("MAI: I'm not sure what you are trying to say...");
			}else{
				process();
			}
		}
		return(true);
	}

	public static ArrayList check(){ // Need to add backup check to check dialogue.
		ArrayList common = new ArrayList(commands);
		int numCommon = 0;
		common.retainAll(input);
		return(common);
	}

	public static ArrayList checkDia(){ // Backup check to check for dialogue.
		ArrayList common = new ArrayList(dialogue);
		int numCommon = 0;
		common.retainAll(input);
		return(common);
	}

	public static void process(){ // Runs the associated action tied to the command entered by the user.
		int i;
		String act;
		String tmp;
		String str;
		int index;

		if(filtered.size() > 1){
			todo.add(actions.get(commands.indexOf((String)filtered.get(0)))); // gets the action associated with the command
			//print(todo.get(0));
			for(i = 0; i < filtered.size(); i++){
				if(!(todo.contains(actions.get(commands.indexOf((String)filtered.get(i)))))){
					todo.add(actions.get(commands.indexOf((String)filtered.get(i))));
					//print("" + i);
				}
				//print(todo.get(i));
			}

		}else if(filtered.size() == 1){
			todo.add(actions.get(commands.indexOf((String)filtered.get(0))));
		}else{
			if(dialogue.size() != 0){
				index = dialogue.indexOf(response.get(0));
				index++;
				print("MAI: " + dialogue.get(index));
				return;
			}else{
				print("MAI: I'm not sure what you are trying to say...");
				return;
			}

		}
			try{

				for(i = 1; i < todo.size(); i++){
					if(todo.get(i) != null){
						str = todo.get(0); // get the base command
						tmp = todo.get(i); // get the argument
						str += tmp; // add the argument to the end of the first command
						todo.set(0, str); // set the first item to the full command
						//print(todo.get(0));
					}
				}
				Runtime rt = Runtime.getRuntime();
				Process pr = rt.exec("cmd /c " + todo.get(0));
				//pr.destroy();
			}catch(IOException e){
				print("Sir, there was a problem: " + e.getMessage());
			}
		print("MAI: Yes sir.");
		return;
	}
}