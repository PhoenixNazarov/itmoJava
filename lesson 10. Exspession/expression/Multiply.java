package expression;

public class Multiply extends Operation{
	public Multiply(Details first, Details second){
		super(first, second);
	}
	
	@Override
	public int evaluate(int value){
		return first.evaluate(value) * second.evaluate(value);
	}

	@Override
	public String toString(){
		return "(" + first.toString() + " * " + second.toString() + ")";
	}
}
