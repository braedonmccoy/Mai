public class Command {
	private String name;
	private String act;
	private String arg;
	private String type;

	private String whole;
	private String[] parts;
	public Command nextCommand;

	public Command(String one){
		whole = one;
		parts = whole.split("#");
		type = parts[2];
		if(type.equals("Com")){
			name = parts[1];
			act = parts[3];
			arg = "No Arg";
		}else if (type.equals("ComA")){
			name = parts[1];
			act = parts[3];
			arg = null;
		}else if (type.equals("Arg")){
			name = parts[1];
			act = null;
			arg = parts[3];
		}

	}
	public Command(String one, String two, String three){
		type = two;
		if(type.equals("Com")){
			name = one;
			act = three;
			arg = "No Arg";
		}else if (type.equals("ComA")){
			name = one;
			act = three;
			arg = null;
		}else if (type.equals("Arg")){
			name = one;
			act = null;
			arg = three;
		}else{
			return;
		}
		return;
	}
	public Command(String one, String two, String three, String four) {
		name = one;
		type = two;
		act = three;
		arg = four;
		return;
	}

	public void printCommand(){
		System.out.println("["+name+", "+act+", "+arg+" = "+type+"]");
	}
	public String export(){
		if(arg == null){
			whole = name+"#"+type+"#"+act;
		}else{
			whole = name+"#"+type+"#"+act+"#"+arg;
		}
		return whole;
	}
}