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
	public static List<String> commands = new ArrayList<String>(); // Commands in txt
	public static List<String> filtered = new ArrayList<String>(); // Common items from above two
	public static List<String> full = new ArrayList<String>(); // Full list of entries in text
	public static List<String> actions = new ArrayList<String>(); // Actions in txt.
	public static List<String> todo = new ArrayList<String>(); // Actions in txt.
	public static int numCommands = 0;

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String text = "";
		ArrayList arr = new ArrayList();
		ArrayList action = new ArrayList();
		String finalCommand;
		String result;

		result = getCommands("../command_list.txt");
		if(result != ""){
			print(result);
		}
		result = getActions("../action_list.txt");
		if(result != ""){
			print(result);
		}
		print("MAI: Please enter what you want me to do: ");
		text = scan.nextLine();
		print("MAI: Working...");

		//print("ArrayList: Commands");
		//printAL(commands);
		input = split(text); // input is an ArrayList that contains the input.
		//print("ArrayList: ");
		//printAL(input);
		filtered = check(); // action is the commands found in the input.
		//print("ArrayList: Filtered");
		//printAL(actions);
		finalCommand = process(); // get associated actions from txt and runs them.
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
		if(common.size() == 0){
			return(null);
		}else{
			return(common);
		}
	}

	public static String process(){ // Runs the associated action tied to the command entered by the user.
		int i;
		String act;
		String tmp;
		String str;

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

		}else{
			todo.add(actions.get(commands.indexOf((String)filtered.get(0))));
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
				}catch(IOException e){
					print("ERROR WITH RUNTIME");
				}
		return(" ");
	}
}