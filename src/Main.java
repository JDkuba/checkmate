public class Main {
    public static void main(String[] args) {
        int noQueens = 12;
        int noThreads = 0;
        int result;

        QueensProblem q = new QueensProblem(noQueens, noThreads);

        long start = System.currentTimeMillis();
        result = q.start(0);
        long end = System.currentTimeMillis();

        System.out.println("Number of solutions: " + result);
        System.out.println((end - start) + "ms");
    }
}