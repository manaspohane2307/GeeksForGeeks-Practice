//{ Driver Code Starts
import java.io.*;
import java.util.*;

class Geeks {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int g = 0; g < t; g++) {
            String[] str = (br.readLine()).trim().split(" ");
            int arr[] = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            System.out.println(new Solution().matrixMultiplication(arr));
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Solution {
    static int helper(int start, int end, int[]arr, int [][] dp){
        if(end-start==1) return 0;
        if(dp[start][end] != -1) return dp[start][end];
        
        int ans = Integer.MAX_VALUE;
        for(int k=start+1;k<end;k++){
            int left = helper(start,k,arr,dp);
            int right = helper(k,end,arr,dp);
            ans = Math.min(ans,left+right+arr[start]*arr[k]*arr[end]);
        }
        return dp[start][end] = ans;
    }
    static int matrixMultiplication(int arr[]) {
        // code here
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int[] row : dp) Arrays.fill(row,-1);
        return helper(0,n-1,arr,dp);
    }
}