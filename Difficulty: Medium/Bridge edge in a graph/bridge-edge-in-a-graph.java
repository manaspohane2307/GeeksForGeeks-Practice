//{ Driver Code Starts
import java.util.*;


// } Driver Code Ends

class Solution {
    public boolean isBridge(int V, int[][] edges, int c, int d) {
        List<List<Integer>> adj = new ArrayList<>();
        
        // Create adjacency list
        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        // Count components before removing the edge
        int initialComponents = countComponents(V, adj);
        
        // Remove the edge (c, d)
        adj.get(c).remove(Integer.valueOf(d));
        adj.get(d).remove(Integer.valueOf(c));
        
        // Count components after removing the edge
        int newComponents = countComponents(V, adj);
        
        // If components increase, it is a bridge
        return newComponents > initialComponents;
    }
    
    private int countComponents(int V, List<List<Integer>> adj){
        boolean[] visited = new boolean[V];
        int components = 0;
        
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                components++;
                dfs(i, visited, adj);
            }
        }
        return components;
    }
    
    private void dfs(int node, boolean[] visited, List<List<Integer>> adj){
        visited[node] = true;
        for(int neighbor : adj.get(node)){
            if(!visited[neighbor]){
                dfs(neighbor, visited, adj);
            }
        }
    }
}


//{ Driver Code Starts.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = Integer.parseInt(sc.nextLine().trim());
        while (t-- > 0) {
            // V and E on separate lines
            int V = Integer.parseInt(sc.nextLine().trim());
            int E = Integer.parseInt(sc.nextLine().trim());

            // Using a 2D array to store edges.
            int[][] edges = new int[E][2];
            for (int i = 0; i < E; i++) {
                // Use split("\\s+") to handle one or more whitespace characters
                String[] parts = sc.nextLine().trim().split("\\s+");
                edges[i][0] = Integer.parseInt(parts[0]);
                edges[i][1] = Integer.parseInt(parts[1]);
            }

            // c and d on separate lines
            int c = Integer.parseInt(sc.nextLine().trim());
            int d = Integer.parseInt(sc.nextLine().trim());

            Solution obj = new Solution();
            boolean result = obj.isBridge(V, edges, c, d);
            System.out.println(result ? "true" : "false");
            System.out.println("~");
        }

        sc.close();
    }
}
// } Driver Code Ends