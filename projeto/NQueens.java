import java.util.Scanner;

public class NQueens {

    private static int N;
    private static int[] queens;

    private static long nodesExplored;
    private static long backtracks;

    private static boolean[] cols;
    private static boolean[] diag1;
    private static boolean[] diag2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\nDigite N (0 para sair): ");
            if (!sc.hasNextInt()) break;
            N = sc.nextInt();

            if (N <= 0) break;

            runExperiment("Backtracking", () -> {
                queens = new int[N];
                solveBacktracking(0);
            });

            System.out.println("\n=====================================\n");

            runExperiment("Branch and Bound", () -> {
                queens = new int[N];
                cols = new boolean[N];
                diag1 = new boolean[2 * N];
                diag2 = new boolean[2 * N];
                solveBranchAndBound(0);
            });

            System.out.print("\nDeseja testar outro valor de N? (s/n): ");
            String resposta = sc.next();
            if (!resposta.equalsIgnoreCase("s")) break;
        }
        sc.close();
    }

    private static void runExperiment(String name, Runnable solver) {
        nodesExplored = 0;
        backtracks = 0;

        System.out.println("Estratégia: " + name);

        long start = System.nanoTime();
        solver.run();
        long end = System.nanoTime();

        if (isSolved()) {
            System.out.print("Solução: ");
            printSolution();
            
            printBoard();

            System.out.printf("Tempo: %.4f ms\n", (end - start) / 1_000_000.0);
            System.out.println("Nós explorados: " + nodesExplored);
            System.out.println("Backtracks: " + backtracks);
        } else {
            System.out.println("Sem solução encontrada para N = " + N);
        }
    }

    private static boolean isSolved() {
        if (queens == null || queens.length == 0) return false;
        // Verifica se a última rainha foi colocada
        return queens[N-1] != 0;
    }

    private static boolean solveBacktracking(int row) {
        if (row == N) return true;
        for (int col = 0; col < N; col++) {
            nodesExplored++;
            if (isSafe(row, col)) {
                queens[row] = col + 1;
                if (solveBacktracking(row + 1)) return true;
                
                // Backtrack
                queens[row] = 0;
                backtracks++;
            }
        }
        return false;
    }

    private static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            int qCol = queens[i] - 1;
            if (qCol == col || Math.abs(qCol - col) == Math.abs(i - row))
                return false;
        }
        return true;
    }

    private static boolean solveBranchAndBound(int row) {
        if (row == N) return true;
        for (int col = 0; col < N; col++) {
            nodesExplored++;
            int d1 = row + col;
            int d2 = row - col + (N - 1);

            if (cols[col] || diag1[d1] || diag2[d2]) {
                backtracks++;
                continue;
            }

            queens[row] = col + 1;
            cols[col] = true; diag1[d1] = true; diag2[d2] = true;

            if (solveBranchAndBound(row + 1)) return true;

            // Backtrack
            cols[col] = false; diag1[d1] = false; diag2[d2] = false;
            queens[row] = 0;
            backtracks++;
        }
        return false;
    }

    private static void printSolution() {
        for (int i = 0; i < N; i++) System.out.print(queens[i] + " ");
        System.out.println();
    }

    private static void printBoard() {
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