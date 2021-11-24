package mnk_game;

public class Cell {
    private CellStates value;

    public Cell(CellStates state) {
        this.value = state;
    }

    public CellStates getValue(){
        return value;
    }

    public void setValue(CellStates state){
        this.value = state;
    }

}
