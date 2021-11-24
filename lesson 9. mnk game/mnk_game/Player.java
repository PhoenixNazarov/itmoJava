package mnk_game;

public interface Player {
    Move makeMove(Turn turn);

    void notificationErrorMove(Move move, String message);
}
