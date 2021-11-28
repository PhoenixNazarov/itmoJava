package expression;

public class Const implements Details {
	private final int value;
	
	public Const(int value){
		this.value = value;
	}

	@Override
	public int evaluate(int val){
		return value;
	}

	@Override
	public String toString(){
		return String.valueOf(value);
	}

	@Override
	public boolean equals(Details val) {
		return false;
	}
}