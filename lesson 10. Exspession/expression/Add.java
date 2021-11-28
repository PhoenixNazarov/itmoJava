package expression;

public class Add extends Operation{
	public Add(Details first, Details second){
		super(first, second);
	}
	
	@Override
	public int evaluate(int value){
		return first.evaluate(value) + second.evaluate(value);
	}

	@Override
	public String toString(){
		return "(" + first.toString() + " + " + second.toString() + ")";
	}

	@Override
	public boolean equals(Details val){
		return first.equals(second);
	}
}
