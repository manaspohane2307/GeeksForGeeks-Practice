//{ Driver Code Starts
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            int[][] edges = new int[E][2];
            for (int i = 0; i < E; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
            }

            Solution obj = new Solution();
            boolean ans = obj.isCyclic(V, edges);
            System.out.println(ans ? "true" : "false");
        }
        sc.close();
    }
}
// } Driver Code Ends


class Solution {
    private boolean dfs(int node, int[] vis, int[] pathVis, List<List<Integer>> adj) {
        vis[node] = 1;
        pathVis[node] = 1;

        for (int neighbor : adj.get(node)) {
            if (vis[neighbor] == 0) {
                if (dfs(neighbor, vis, pathVis, adj)) {
                    return true;
                }
            } else if (pathVis[neighbor] == 1) {
                return true;
            }
        }

        pathVis[node] = 0;
        return false;
    }

    public boolean isCyclic(int V, int[][] edges) {
        // Create adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        int[] vis = new int[V];
        int[] pathVis = new int[V];

        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                if (dfs(i, vis, pathVis, adj)) {
                    return true;
                }
            }
        }

        return false;
    }
}