package model.classes;

public abstract class NQueensSolver {

    protected int N;
    protected int[] queens;

    protected long nodesExplored;
    protected long backtracks;

    public NQueensSolver(int N) {
        this.N = N;
        this.queens = new int[N];
    }

    public abstract boolean solve(int row);

    public void run(String name) {
        nodesExplored = 0;
        backtracks = 0;

        System.out.println("\nEstratégia: " + name);

        long start = System.nanoTime();
        solve(0);
        long end = System.nanoTime();

        if (isSolved()) {
            printSolution();
            printBoard();
            System.out.printf("Tempo: %.4f ms\n", (end - start) / 1_000_000.0);
            System.out.println("Nós explorados: " + nodesExplored);
            System.out.println("Backtracks: " + backtracks);
        } else {
            System.out.println("Sem solução para N = " + N);
        }
    }

    protected boolean isSolved() {
        return queens[N - 1] != 0;
    }

    protected void printSolution() {
        System.out.print("Solução: ");
        for (int i = 0; i < N; i++)
            System.out.print(queens[i] + " ");
        System.out.println();
    }

    protected void printBoard() {
        System.out.println("Tabuleiro:");
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                if (queens[i] == j)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}