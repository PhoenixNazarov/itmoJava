package game;

import java.util.Random;

public class LoseRandomPlayer implements Player {
    private final Random random = new Random();
    private boolean isPlay = true;

    @Override
    public Move makeMove(Turn turn) {
        int x = random.nextInt(turn.getN());
        int y = random.nextInt(turn.getM());
        return new Move(x, y, turn.getValue());
    }

    @Override
    public void notificationErrorMove(Move move, String message){
        isPlay = false;
    }

    @Override
    public boolean isPlay(){
        return isPlay;
    }
}
