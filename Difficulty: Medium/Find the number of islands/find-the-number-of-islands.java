//{ Driver Code Starts
import java.util.*;

class GFG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tc = scanner.nextInt();
        while (tc-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            char[][] grid = new char[n][m];

            // Read the grid input
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = scanner.next().charAt(0);
                }
            }
            Solution obj = new Solution();
            int ans = obj.countIslands(grid);
            System.out.println(ans);
            System.out.println("~");
        }
        scanner.close();
    }
}

// } Driver Code Ends


class Solution {
    public boolean isSafe(char[][] grid, int r, int c) {
        int row = grid.length;
        int col = grid[0].length;
        return (r >= 0) && (r < row) && (c >= 0) && (c < col) && grid[r][c] == 'L';
    }

    public void dfs(char[][] grid, int r, int c) {
        int[] rNbr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] cNbr = {-1, 0, 1, -1, 1, -1, 0, 1};
        grid[r][c] = 'W';
        for (int k = 0; k < 8; ++k) {
            int newR = r + rNbr[k];
            int newC = c + cNbr[k];
            if (isSafe(grid, newR, newC)) {
                dfs(grid, newR, newC);
            }
        }
    }
    public int countIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                if (grid[r][c] == 'L') {
                    dfs(grid, r, c);
                    ++count;
                }
            }
        }
        return count;
    }
}