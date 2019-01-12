public class Calcs implements Runnable {
    private final Board finalBoard;
    private QueensProblem parent;
    private int row;
    private int mode;

    Calcs(Board board, int rowNumber, QueensProblem parent, int mode) {
        this.finalBoard = board;
        this.row = rowNumber;
        this.parent = parent;
        this.mode = mode;
    }

    @Override
    public void run() {
        check();
    }

    private Board check() {
        if (this.row == this.finalBoard.size()) {
            if (mode == 1) {
                System.out.println(this.finalBoard);
                System.out.println();
            }
            parent.count.incrementAndGet();
            return new Board(this.finalBoard);
        }
        Board b = null;
        for (int i = 0; i < this.finalBoard.size(); ++i) {
            if (this.checkSingle(this.finalBoard, i, this.row)) {
                this.finalBoard.insert(this.row, i, true);
                this.row++;
                b = check();
                this.row--;
                this.finalBoard.insert(this.row, i, false);
            } else b = null;
        }
        return b;
    }

    private boolean checkSingle(Board b, int col, int row) {
        for (int i = row, j = col; i > -1; --i, ++j) {
            if (j < this.finalBoard.size()) if (b.isOccupied(i, j)) return false;
        }

        for (int i = row, j = col; i > -1; --i, --j) {
            if (j > -1) if (b.isOccupied(i, j)) return false;
        }

        for (int i = 0; i < row; ++i) {
            if (b.isOccupied(i, col)) return false;
        }

        return true;
    }
}