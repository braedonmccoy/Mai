/**
 * @(#)Mai2.java
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
    	print("Initializing MAI. Please wait...");
		parse(CL, "check.txt");
		//display(CL);
    }
    public static void print(String text){
    	System.out.println(text);
    	return;
    }
    public static void parse(CommandList list, String filename){
		if(list.addFile(filename) == true){
			print("Woohoo!");
		}else{
			print("Aw, shit.");
		}
		return;
    }
    public static void display(CommandList list){
    	list.printList();
    	return;
    }

}
