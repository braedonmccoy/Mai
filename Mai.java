/**
 * @(#)Mai.java
 *
 * Mai, the voice controlled Artificial Intelligence.
 *
 * @Braedon McCoy
 * @version 2.00 2012/5/7
 */
import java.util.*;
import java.io.*;

public class Mai {

    public static void main(String[] args) {
    	CommandList CL = new CommandList();
    	String input = null;
    	String uname = "User";
    	boolean test = false;
    	String output = null;

    	print("Initializing MAI. Please wait...");
		parse(CL, "check.txt"); //Load CL with commands
		//display(CL); //Print out each entry in CL
		while(true){
			printl(uname+": ");
			input = scan();
			//print(input);
			input.toLowerCase();
			if(input.equals("exit")){
				print("Thank you. Goodbye...");
				break;
			}else{
				CommandList keywords = new CommandList();
				String name = null;
				if((check(input, CL, keywords)) && (!(keywords.isEmpty()))){
					//keywords.printList();
					execute(keywords);
				}else{
					print("Error!");
				}
			}
		}
    }
    public static void print(String text){ //Prints with endline
    	System.out.println(text);
    	return;
    }
    public static void printl(String text){ //Prints without endline
    	System.out.print(text);
    	return;
    }
    public static String scan(){ //scans String and returns
    	Scanner in = new Scanner(System.in);
    	String line = in.nextLine();
    	return line;
    }
    public static void parse(CommandList list, String filename){ //Load CL with commands
		if(list.addFile(filename) == true){
			print("File loaded.");
		}else{
			print("Error, File could not load!");
		}
		return;
    }
    public static void display(CommandList list){ //Print out each entry in CL
    	list.printList();
    	return;
    }
    public static boolean check(String target, CommandList list, CommandList matches){
    	String[] words = target.split(" ");
    	String result = null;
    	String exported = null;

    	for(int i = 0; i < words.length; i++){
    	//	print(words[i]);
    		result = list.getName(words[i]);
    		if(result.equals(words[i])){
    			//print("Found match: "+result);
    			exported = list.export(result);
    			matches.add(exported);
    		//	matches.printCommand(result);
    		}
    	}
    	if(words.length >= 1){
    		return true;
    	}else{
    		return false;
    	}
    }
    public static boolean execute(CommandList list){

    }
}
