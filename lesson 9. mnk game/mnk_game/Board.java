package mnk_game;

public interface Board {
    GameResult makeMove(Move move);

    Turn getTurn();

    boolean isValid(Move move);
}
