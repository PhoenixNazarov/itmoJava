package game;

public class Turn {
    final private Cell[][] copyField;
    final private CellStates value;
    final private String stringField;

    public Turn(Cell[][] copyField, CellStates value, String stringField) {
        this.copyField = copyField;
        this.value = value;
        this.stringField = stringField;
    }

    public Cell[][] getField() {
        return copyField;
    }

    public String getStringField() {
        return stringField;
    }

    public int getM() {
        return copyField.length;
    }

    public int getN() {
        return copyField[0].length;
    }

    public CellStates getValue() {
        return value;
    }
}
