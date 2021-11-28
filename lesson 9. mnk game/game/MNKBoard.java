package game;

import java.util.Map;

public class MNKBoard implements Board {
    private static final Map<CellStates, String> CELL_TO_STRING = Map.of(
            CellStates.E, ".",
            CellStates.X, "X",
            CellStates.O, "0",
            CellStates.T, "-",
            CellStates.P, "|"
    );

    private final Cell[][] field;
    private CellStates turn;

    private final int M;
    private final int N;
    private final int K;
    private int moveRem;

    public MNKBoard(int m, int n, int k) {
        M = m;
        N = n;
        K = k;
        moveRem = m*n;
        field = new Cell[M][N];
        for (int im = 0; im < M; im++) {
            for (int in = 0; in < N; in++) {
                field[im][in] = new Cell(CellStates.E);
            }
        }
        turn = CellStates.X;
    }

    @Override
    public void skipTurn(){
        switch (turn) {
            case X -> turn = CellStates.O;
            case O -> turn = CellStates.T;
            case P -> turn = CellStates.X;
            case T -> turn = CellStates.P;
        }
    }

    @Override
    public Turn getTurn() {
        return new Turn(field.clone(), turn, toString());
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.ERROR;
        }

        field[move.getRow()][move.getCol()].setValue(move.getValue());
        moveRem--;
        if (checkWin(move)) {
            return GameResult.WIN;
        }
        if (checkDraw()) {
            return GameResult.DRAW;
        }
        skipTurn();
        return GameResult.UNKNOWN;
    }

    private boolean checkDraw() {
        return moveRem == 0;
    }

    private int walkOnField(int x, int y, Move move) {
        // 1 ++, -1 --, 0 stay
        int count = 0;
        int xi = move.getCol();
        int yi = move.getRow();
        while (true) {
            if (x == 1) {
                xi++;
                if (xi >= N) {
                    return count;
                }
            } else if (x == -1) {
                xi--;
                if (xi < 0) {
                    return count;
                }
            }
            if (y == 1) {
                yi++;
                if (yi >= M) {
                    return count;
                }
            } else if (y == -1) {
                yi--;
                if (yi < 0) {
                    return count;
                }
            }
//            System.out.println(x+ " " + y + " " + xi + " " +yi);
            if (field[yi][xi].getValue().equals(move.getValue())){
                count++;
            } else {
                return count;
            }
        }

    }

    private boolean checkWin(Move move) {
        int diagPos = 1 + walkOnField(-1, -1, move) + walkOnField(1, 1, move);
        int diagNeg = 1 + walkOnField(1, -1, move) + walkOnField(-1, 1, move);
        int onlyY = 1 + walkOnField(0, 1, move) + walkOnField(0, -1, move);
        int onlyX = 1 + walkOnField(1, 0, move) + walkOnField(-1, 0, move);
        return diagPos >= K || diagNeg >= K || onlyY >= K || onlyX >= K;
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < M
                && 0 <= move.getCol() && move.getCol() < N
                && field[move.getRow()][move.getCol()].getValue() == CellStates.E
                && turn == move.getValue();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        int maxLengthColumn = String.valueOf(N).length();
        int maxLengthRow = String.valueOf(M).length();
        for (int curPosition = maxLengthColumn - 1; curPosition >= 0; curPosition--) {
            sb.append(" ".repeat(maxLengthRow));
            for (int ii = 1; ii <= N; ii++) {
                if (String.valueOf(ii).length() <= curPosition) {
                    sb.append(" ");
                } else {
                    String reverse = new StringBuilder(String.valueOf(ii)).reverse().toString();
                    sb.append(reverse.charAt(curPosition));
                }
            }
            sb.append(System.lineSeparator());
        }
        for (int r = 0; r < M; r++) {
            sb.append(" ".repeat(maxLengthRow - String.valueOf(r + 1).length()));
            sb.append(r + 1);
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell.getValue()));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
