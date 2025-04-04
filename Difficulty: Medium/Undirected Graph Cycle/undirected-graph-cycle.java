//{ Driver Code Starts
// Initial Template for Java
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
            boolean ans = obj.isCycle(V, edges);
            System.out.println(ans ? "true" : "false");
            System.out.println("~");
        }
        sc.close();
    }
}

// } Driver Code Ends


class Solution {
    private boolean helper(int start, List<Integer>[] adj, int[] vis) {
        vis[start] = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{-1, start});
        
        while (!q.isEmpty()) {
            int[] front = q.poll();
            int parent = front[0];
            int node = front[1];
            
            for (int it : adj[node]) {
                if (vis[it] == 0) {
                    vis[it] = 1;
                    q.add(new int[]{node, it});
                } else if (it != parent) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isCycle(int V, int[][] edges) {
        List<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        
        int[] vis = new int[V];
        
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                if (helper(i, adj, vis)) {
                    return true;
                }
            }
        }
        return false;
    }
}