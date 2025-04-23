//{ Driver Code Starts
import java.io.*;
import java.util.*;

class Node {
    int val;
    ArrayList<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }

    public Node(int val, ArrayList<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}

class GFG {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        Integer nextInt() { return Integer.parseInt(next()); }
    }

    static ArrayList<Node> bfs(Node src) {
        ArrayList<Node> ans = new ArrayList<>();
        HashSet<Node> visited = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        q.add(src);
        visited.add(src);

        while (!q.isEmpty()) {
            Node u = q.poll();
            ans.add(u);
            ArrayList<Node> v = u.neighbors;
            for (Node x : v) {
                if (!visited.contains(x)) {
                    visited.add(x);
                    q.add(x);
                }
            }
        }

        return ans;
    }

    static boolean compare(Node prev, Node newNode, Set<Node> prevVis,
                           Set<Node> newVis) {
        if (prev == newNode) return false;

        if (prev == null || newNode == null) {
            return prev == null && newNode == null;
        }

        if (prevVis.contains(prev) || newVis.contains(newNode)) {
            return prevVis.contains(prev) && newVis.contains(newNode);
        }

        prevVis.add(prev);
        newVis.add(newNode);

        if (prev.val != newNode.val) return false;

        List<Node> prevNeighbors = prev.neighbors;
        List<Node> newNeighbors = newNode.neighbors;

        if (prevNeighbors.size() != newNeighbors.size()) return false;

        // Sort neighbors to ensure consistency in order comparison
        prevNeighbors.sort(Comparator.comparingInt(a -> a.val));
        newNeighbors.sort(Comparator.comparingInt(a -> a.val));

        // Now check that the neighbors are consistent both ways (bidirectional check)
        for (int i = 0; i < prevNeighbors.size(); i++) {
            Node prevNeighbor = prevNeighbors.get(i);
            Node newNeighbor = newNeighbors.get(i);

            // Check if the current neighbor of prev matches the neighbor of newNode in
            // reverse
            if (!compare(prevNeighbor, newNeighbor, prevVis, newVis)) {
                return false;
            }

            // Bidirectional check for the current neighbors: a -> b and b -> a
            if (!newNeighbor.neighbors.contains(prevNode(prevNeighbor, newNode))) {
                return false;
            }
        }

        return true;
    }

    static Node prevNode(Node prevNeighbor, Node newNode) {
        for (Node neighbor : newNode.neighbors) {
            if (neighbor.val == prevNeighbor.val) {
                return newNode;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            Node v[] = new Node[n];
            for (int i = 0; i < n; i++) v[i] = new Node(i);

            Solution ob = new Solution();

            for (int i = 0; i < n; i++) {
                ArrayList<Node> li = new ArrayList<>();
                String arr[] = sc.nextLine().split(" ");
                for (String s : arr) {
                    if (!s.isEmpty()) li.add(v[Integer.parseInt(s)]);
                }
                v[i].neighbors = li;
            }

            Node ans = ob.cloneGraph(v[0]);

            Set<Node> prevVis = new HashSet<>();
            Set<Node> newVis = new HashSet<>();

            out.println(compare(v[0], ans, prevVis, newVis) ? "true" : "false");
            out.println("~");
        }
        out.flush();
    }
}
// } Driver Code Ends


// User function Template for Java

/*
    class Node{
        int val;
        ArrayList<Node> neighbors;
        public Node(){
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int val){
            this.val = val;
            neighbors = new ArrayList<>();
        }

        public Node(int val, ArrayList<Node> neighbors){
            this.val = val;
            this.neighbors = neighbors;
        }
    }
*/
class Solution {
    Node cloneGraph(Node node) {
        // code here
        if (node == null) return null;
        Map<Node, Node> m = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        m.put(node, new Node(node.val));
        q.add(node);
        while (!q.isEmpty()) {
            for (Node n : q.peek().neighbors) {
                if (!m.containsKey(n)) {
                    m.put(n, new Node(n.val));
                    q.add(n);
                }
                m.get(q.peek()).neighbors.add(m.get(n));
            }
            q.poll();
        }
        return m.get(node);
    }
}