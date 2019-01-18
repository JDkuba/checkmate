/**
 * Class that represents Chessboard
 **/
public class Board {
    private boolean[][] board;

    Board(Board b) {
        this.board = new boolean[b.board.length][b.board.length];
        for (int i = 0; i < b.board.length; i++) {
            if (b.board[i].length >= 0)
                System.arraycopy(b.board[i], 0, this.board[i], 0, b.board[i].length);
        }
    }

    Board(int n) {
        this.board = new boolean[n][n];
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = false;
            }
        }
    }

    void replaceFirstRow(boolean[] row) {
        boolean[] temp = new boolean[row.length];
        System.arraycopy(row, 0, temp, 0, row.length);
        this.board[0] = temp;
    }

    void insert(int x, int y, boolean b) {
        this.board[x][y] = b;
    }

    boolean isOccupied(int x, int y) {
        return this.board[x][y];
    }

    int size() {
        return this.board.length;
    }

    public String toString() {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j]) r.append(1);
                else r.append(0);
                r.append(" ");
            }
            if (i != (this.board.length - 1)) r.append("\n");
        }
        return r.toString();
    }
}