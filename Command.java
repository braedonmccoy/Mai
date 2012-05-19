public class Command {
	public String whole;
	public String[] parts;
	public String name;
	public String act;
	public String arg;
	public String type;
	public Command nextCommand;

	public void printCommand(){
		System.out.println("["+name+", "+act+", "+arg+" = "+type+"]");
	}
	public Command(String one){
		int i = 0;
		whole = one;
		parts = whole.split("#");
		type = parts[1];
		if(type.equals("Com")){
			name = parts[0];
			act = parts[2];
			arg = "No Arg";
		}else if (type.equals("ComA")){
			name = parts[0];
			act = parts[2];
			arg = null;
		}else if (type.equals("Arg")){
			name = parts[0];
			act = null;
			arg = parts[2];
		}

	}
	public Command(String one, String two, String three){
		type = two;
		if(type.equals("Com")){
			name = one;
			act = three;
			arg = "No Arg";
		}else if (type.equals("Arg")){
			name = one;
			act = null;
			arg = three;
		}else{
			return;
		}
	}
	public Command(String one, String two, String three, String four) {
		name = one;
		type = two;
		act = three;
		arg = four;
	}
	public String getName(){
		return name;
	}
	public String getAction(){
		return (act.concat(arg));
	}
	public String getType(){
		return type;
	}
}