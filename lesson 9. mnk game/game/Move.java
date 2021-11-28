package game;

public class Move {
    private final int row;
    private final int col;
    private final CellStates value;

    public Move(int row, int col, CellStates value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellStates getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("Move(%s, %d, %d)", value, row + 1, col + 1);
    }
}
