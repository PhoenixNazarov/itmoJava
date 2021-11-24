package mnk_game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m;
        int n;
        int k;
        System.out.println("Hello, it is m,n,k game."+System.lineSeparator()+"Please write m,n,k to start the game");
        while (true) {
//            try {
                m = in.nextInt();
                n = in.nextInt();
                k = in.nextInt();
                if (m > 0 && n > 0 && k > 0){
                    break;
                } else {
                    System.out.println("m, n, k must be greater than zero. Otherwise, the game doesn't make sense");
                }
//            } catch (java.util.InputMismatchException e) {
//                in.nextLine();
//            }
        }

        final int result = new TwoPlayerGame(
                new MNKBoard(m, n, k),
                new HumanPlayer(new Scanner(System.in)),
//                new HumanPlayer(new Scanner(System.in))
//                new RandomPlayer()
                new SequentialPlayer()
        ).play(true);
        switch (result) {
            case 1 -> System.out.println("First player won");
            case 2 -> System.out.println("Second player won");
            case 0 -> System.out.println("Draw");
            default -> throw new AssertionError("Unknown result " + result);
        }
    }
}
