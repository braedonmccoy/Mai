/* Mai
 *Version 1.0
 *3/14/2012
 *Braedon McCoy
*/
import java.io.*;
import java.util.*;
public class main{
	public static ArrayList commands = new ArrayList();
	public static int numCommands = 0;

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String text = "";
		ArrayList arr = new ArrayList();
		ArrayList action = new ArrayList();

		print("Please enter text to speak: ");
		text = scan.nextLine();
		print("You said: " + text);

		arr = split(text);
		/*
		int i = 0;
		for (i = 0; i < finalArr.length; i++){
			print(finalArr[i]);
		}
		*/
		//action = check(arr);
		check(arr);
	}

	public static void print(String text){
		System.out.println(text);
		return;
	}

	public static ArrayList split(String orig){
		ArrayList finalArr = new ArrayList();
		String[] arr = orig.split(" ");
		int i = 0;
		for(i = 0; i < arr.length; i++){
			finalArr.add(arr[i]);
		}
		return finalArr;
	}
	public String getCommands(String location){
		String line = "";
		try{
			BufferedReader reader = new BufferedReader(
			new FileReader(location), 8 * 1024);
			while((line = reader.readLine()) != null){
				commands.add(line);
				numCommands++;
			}
			return("");
		} catch(IOException e){
			return("ERROR: " + e.getMessage());
		}
	}
	public String remCommand(String command){
		commands.remove(command);
		numCommands--;
		return("");
	}
	public static void check(ArrayList command){
		ArrayList common = new ArrayList();
		common = commands.retainAll(commands, command);

		return;
	}
}