package expression;

public class Variable implements Details {
	private final String name;
	
	public Variable(String name){
		this.name = name;
	}
	
	@Override
	public int evaluate(int value){
		return value;
	}

	@Override
	public String toString(){
		return name;
	}

	@Override
	public boolean equals(Details val) {
		return false;
	}
}
