import java.io.*;

public class CommandList {
	private Command first;

	public CommandList() {
		first = null;
	}
	public boolean isEmpty(){
		return first == null;
	}
	public boolean addFile(String file){
		String line = null;
		try{
			BufferedReader br =  new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null){
				Command com = new Command(line);
				com.nextCommand = first;
				first = com;
			}
			br.close();
		}catch(IOException e){
			return false;
		}
		return true;
	}
	public void add(String one, String two, String three){
		Command com = new Command(one, two, three);
		com.nextCommand = first;
		first = com;
	}
	public void add(String one, String two, String three, String four){
		Command com = new Command(one, two, three, four);
		com.nextCommand = first;
		first = com;
	}
	public void printList() {
		Command current = first;
            System.out.print("CommandList: \n");
            while(current != null) {
                    current.printCommand();
                    current = current.nextCommand;
            }
            System.out.println("");
	}
	public String getAction(String target){
		Command current = first;
		String action = null;
		while(current != null){
			action = current.getName();
			if(action.equals(target)){
				return current.getAction();
			}else{
				current = current.nextCommand;
			}
		}
		return "Error: Couldn't find "+target+"!";
	}
}
