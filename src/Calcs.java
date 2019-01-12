public class Calcs implements Runnable {
    private final Board finalBoard;
    private int row;
    private QueensProblem parent;
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
            Board return_bs = new Board(this.finalBoard);
            parent.count.incrementAndGet();
            return return_bs;
        }
        Board returnState = null;
        for (int i = 0; i < this.finalBoard.size(); i++) {
            if (this.check_single(this.finalBoard, i, this.row)) {
                this.finalBoard.insert(this.row, i, true);
                this.row++;
                returnState = check();
                this.row--;
                this.finalBoard.insert(this.row, i, false);
            } else returnState = null;
        }
        return returnState;
    }

    private boolean check_single(Board bs, int colIndex, int rowIndex) {
        for (int i = rowIndex, j = colIndex; i > -1; i--, j++) {
            if (j < this.finalBoard.size()) if (bs.isOccupied(i, j)) return false;
        }

        for (int i = rowIndex, j = colIndex; i > -1; i--, j--) {
            if (j > -1) if (bs.isOccupied(i, j)) return false;
        }

        for (int i = 0; i < rowIndex; i++) {
            if (bs.isOccupied(i, colIndex)) return false;
        }

        return true;
    }
}