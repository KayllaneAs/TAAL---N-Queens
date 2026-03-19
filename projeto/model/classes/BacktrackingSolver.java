package model.classes;

public class BacktrackingSolver extends NQueensSolver {

    public BacktrackingSolver(int N) {
        super(N);
    }

    @Override
    public boolean solve(int row) {
        if (row == N) return true;

        for (int col = 0; col < N; col++) {
            nodesExplored++;

            if (isSafe(row, col)) {
                queens[row] = col + 1;

                if (solve(row + 1)) return true;

                queens[row] = 0;
                backtracks++;
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            int qCol = queens[i] - 1;
            if (qCol == col || Math.abs(qCol - col) == Math.abs(i - row))
                return false;
        }
        return true;
    }
}