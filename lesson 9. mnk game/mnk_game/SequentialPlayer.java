package mnk_game;

public class SequentialPlayer implements Player{

    @Override
    public Move makeMove(Turn turn) {
        for (int r = 0; r < turn.getM(); r++) {
            for (int c = 0; c < turn.getN(); c++) {
                if (turn.getField()[r][c].getValue().equals(CellStates.E)) {
                    return new Move(r, c, turn.getValue());
                }
            }
        }
        throw new AssertionError("No valid moves");
    }

    @Override
    public void notificationErrorMove(Move move, String message) {

    }
}
