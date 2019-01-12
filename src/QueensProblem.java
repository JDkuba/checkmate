import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class QueensProblem {
    private ExecutorService executors;
    private int size;
    final AtomicInteger count = new AtomicInteger();


    QueensProblem(int size, int noThreads) {
        if (noThreads == 0) {
            this.executors = Executors.newCachedThreadPool();
        } else if (noThreads == 1) {
            this.executors = Executors.newSingleThreadExecutor();
        } else {
            this.executors = Executors.newFixedThreadPool(noThreads);
        }
        this.size = size;
    }


    int start(int mode) {
        for (int i = 0; i < this.size; i++) {
            int[] temp = new int[this.size];
            temp[i] = 1;
            Board bs = new Board(this.size);
            bs.swapRow(temp);
            Calcs bc = new Calcs(bs, 1, this, mode);
            this.executors.execute(bc);
        }

        this.executors.shutdown();
        while (!this.executors.isTerminated()) {      //todo
        }

        return count.get();
    }
}