package mnk_game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    public Move makeMove(Turn turn) {
        int x = random.nextInt(turn.getN());
        int y = random.nextInt(turn.getM());
        return new Move(x, y, turn.getValue());
    }

    public void notificationErrorMove(Move move, String message){
//        errorMove.add(new Integer[] {move})
    }
}
