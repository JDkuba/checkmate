/**
 * Main class for performance tests.
 * 'threads' is a variable for number of threads, and 'queens' is variable for number of queens
 * threads = 0 means, that application uses every available thread.
 *
 * */


public class Main {
    public static void main(String[] args) {
        int result;
        QueensProblem q;
        for (int queens = 2; queens < 15; ++queens) {
            for (int threads = 0; threads < 4; ++threads) {
                q = new QueensProblem(queens, threads, 0);

                long start = System.currentTimeMillis();
                result = q.start();
                long end = System.currentTimeMillis();

                if (threads == 0) System.out.println("Number of solutions for " + queens + " queens: " + result);
                System.out.println((end - start) + "ms with " + (threads == 0 ? "max number of": threads) + " threads");
            }
            System.out.println();
        }

        System.out.println("Solutions for 7 queens:");
        q = new QueensProblem(7, 0, 1);
        q.start();
    }
}