package model.classes;

public class BranchAndBoundSolver extends NQueensSolver {

    private boolean[] cols;
    private boolean[] diag1;
    private boolean[] diag2;

    public BranchAndBoundSolver(int N) {
        super(N);
        cols = new boolean[N];
        diag1 = new boolean[2 * N];
        diag2 = new boolean[2 * N];
    }

    @Override
    public boolean solve(int row) {
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
            cols[col] = diag1[d1] = diag2[d2] = true;

            if (solve(row + 1)) return true;

            cols[col] = diag1[d1] = diag2[d2] = false;
            queens[row] = 0;
            backtracks++;
        }
        return false;
    }
}