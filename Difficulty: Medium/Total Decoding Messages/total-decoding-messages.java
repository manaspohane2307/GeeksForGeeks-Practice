//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String digits = br.readLine().trim();
            Solution ob = new Solution();
            int ans = ob.countWays(digits);
            out.println(ans);

            out.println("~");
        }
        out.close();
    }
}

// } Driver Code Ends


// User function Template for Java
class Solution {
    public int countWays(String digits) {
        // code here
        if(digits==null || digits.length()==0 || digits.charAt(0)=='0'){
            return 0;
        }
        
        int n = digits.length();
        
        int start=1,end=1;
        for(int i=1;i<n;i++){
            int current=0;
            int singledigit = digits.charAt(i)-'0';
            int twodigit = Integer.parseInt(digits.substring(i-1,i+1));
            if(singledigit>0){
                current += start;
            }
            if(twodigit>=10 && twodigit<=26){
                current += end;
            }
            
            end = start;
            start=current;
        }
        return start;
    }
}