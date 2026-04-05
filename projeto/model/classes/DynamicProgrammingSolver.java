package model.classes;

import java.util.HashMap;
import java.util.Map;

public class DynamicProgrammingSolver extends NQueensSolver {
    private final Map<String, Boolean> memo;

    public DynamicProgrammingSolver(int N) {
        super(N);
        this.memo = new HashMap<>();
    }

    @Override
    public boolean solve(int row) {
        return solveDP(0, 0L, 0L, 0L);
    }

    private boolean solveDP(int row, long cols, long diag1, long diag2) {
        if (row == N) {
            return true;
        }

        String stateKey = buildStateKey(row, cols, diag1, diag2);

        if (memo.containsKey(stateKey)) {
            return memo.get(stateKey);
        }

        for (int col = 0; col < N; col++) {
            nodesExplored++;

            long colMask = 1L << col;
            long diag1Mask = 1L << (row - col + N - 1); 
            long diag2Mask = 1L << (row + col);      

            boolean occupied =
                    (cols & colMask) != 0 ||
                    (diag1 & diag1Mask) != 0 ||
                    (diag2 & diag2Mask) != 0;

            if (!occupied) {
                queens[row] = col + 1; 

                if (solveDP(
                        row + 1,
                        cols | colMask,
                        diag1 | diag1Mask,
                        diag2 | diag2Mask)) {
                    memo.put(stateKey, true);
                    return true;
                }

                queens[row] = 0;
                backtracks++;
            }
        }

        memo.put(stateKey, false);
        return false;
    }

    private String buildStateKey(int row, long cols, long diag1, long diag2) {
        return row + "|" + cols + "|" + diag1 + "|" + diag2;
    }
}