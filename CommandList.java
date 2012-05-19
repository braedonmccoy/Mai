import java.io.*;

public class CommandList {
	private Command first;

	public CommandList() {
		first = null;
	}

	public boolean isEmpty(){
		return first == null;
	}
	public int size(){
		Command current = first;
		String action = null;
		int size = 0;
		while(current != null){
			size++;
			current = current.nextCommand;
		}
		current = first;
		return size;
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
	public void add(String one){
		Command com = new Command(one);
		com.nextCommand = first;
		first = com;
		return;
	}
	public void add(String one, String two, String three){
		Command com = new Command(one, two, three);
		com.nextCommand = first;
		first = com;
		return;
	}
	public void add(String one, String two, String three, String four){
		Command com = new Command(one, two, three, four);
		com.nextCommand = first;
		first = com;
		return;
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
	public void printCommand(String target){
		Command current = first;
		String name = null;
		while(current != null){
			name = current.getName();
			if(name.equals(target)){
				current.printCommand();
				return;
			}else{
				current = current.nextCommand;
			}
		}
		return;
	}
	public String export(String target){
		Command current = first;
		String name = null;
		String ex = null;
		while(current != null){
			name = current.getName();
			if(name.equals(target)){
				ex = current.export();
				current = first;
				return ex;
			}else{
				current = current.nextCommand;
			}
		}
		return "Error: Couldn't find "+target+"!";
	}
}
