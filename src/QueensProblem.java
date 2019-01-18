import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * QueensProblem class is responsible for spawning threads and counting result in AtomicInteger Object
 *
 **/
class QueensProblem {
    private ExecutorService executors;
    private int size;
    final AtomicInteger count = new AtomicInteger();
    int mode;

    /**
     * Depending of noThreads, constructor takes appropriate Executor Pool
     * If mode equals 1 then every queens arrangement is written
     **/
    QueensProblem(int size, int noThreads, int mode) {
        this.mode = mode;
        if (noThreads == 0) {
            this.executors = Executors.newCachedThreadPool();
        } else if (noThreads == 1) {
            this.executors = Executors.newSingleThreadExecutor();
        } else {
            this.executors = Executors.newFixedThreadPool(noThreads);
        }
        this.size = size;
    }


    int start() {
        for (int i = 0; i < this.size; i++) {
            boolean[] temp = new boolean[this.size];
            temp[i] = true;
            Board bs = new Board(this.size);
            bs.replaceFirstRow(temp);
            Calcs bc = new Calcs(bs, 1, this);
            this.executors.execute(bc);
        }

        this.executors.shutdown();
        while (!this.executors.isTerminated()) {
        }

        return count.get();
    }
}