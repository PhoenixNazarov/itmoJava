package mnk_game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    public Move makeMove(Turn turn) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(turn.getStringField());
        System.out.println("Enter you move for " + turn.getValue());
        while (true) {
            try {
                int x = in.nextInt() - 1;
                int y = in.nextInt() - 1;
                return new Move(x, y, turn.getValue());
            } catch (java.util.InputMismatchException  e) {
                in.nextLine();
                System.out.println("Write the correct move");
            }
        }
    }

    public void notificationErrorMove(Move move, String message) {
        System.out.println(message);
    }
}
