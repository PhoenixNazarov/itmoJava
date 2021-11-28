package game;

public class MorePlayerGame {
    private final Board board;
    private final Player[] players;

    public MorePlayerGame(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public int play(boolean log) {
        while (true) {
            for (int i = 0; i < 4; i++){
                if (players[i] == null) {
                    board.skipTurn();
                } else {
                    final int result = makeMove(players[i], i+1, log);
                    if (result != -1)  {
                        return result;
                    }
                }
            }
        }
    }

    private int makeMove(Player player, int number, boolean log) {
        Move move;
        while (true) {
            if (!player.isPlay()){
                board.skipTurn();
                return -1;
            }
            try {
                move = player.makeMove(board.getTurn());
            }
            catch (Exception e) {
                player.notificationErrorMove(null, "Error");
                continue;
            }
            if (board.isValid(move)) {
                break;
            } else {
                player.notificationErrorMove(move, "You can't pawn like this");
            }
        }
        final GameResult result = board.makeMove(move);
        if (log) {
            System.out.println();
            System.out.println("Player: " + number);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }
        return switch (result) {
            case WIN -> number;
            case LOOSE -> 3 - number;
            case DRAW -> 0;
            case UNKNOWN -> -1;
            default -> throw new AssertionError("Unknown makeMove result " + result);
        };
    }
}
