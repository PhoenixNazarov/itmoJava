package expression;

public abstract class Operation implements Details {
	protected final Details first;
	protected final Details second;

	protected Operation(Details first, Details second){
		this.first = first;
		this.second = second;
	}

	@Override
	public int evaluate(int value){
		return 0;
	}

	@Override
	public boolean equals(Details val){
		return (this.toString().equals(val.toString()));  
	}
}