//{ Driver Code Starts
import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine().trim()); // Read number of test cases

        while (t-- > 0) {
            String line = read.readLine().trim(); // Read the array input
            String[] numsStr = line.split(" ");   // Split the input string by spaces
            int[] nums =
                new int[numsStr.length]; // Convert string array to integer array
            for (int i = 0; i < numsStr.length; i++) {
                nums[i] = Integer.parseInt(numsStr[i]);
            }

            int k = Integer.parseInt(read.readLine().trim()); // Read target sum

            Solution ob = new Solution();
            int ans = ob.longestSubarray(nums, k); // Call the function and store result
            System.out.println(ans);
            System.out.println("~");
        }
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    public int longestSubarray(int[] arr, int k) {
        // code here
        int n = arr.length;
        int max_len=0;
        HashMap<Long,Integer> prefixSum = new HashMap<>();
        long sum=0;
        //store prefix sum
        for(int i=0;i<n;i++){
            sum += arr[i];
            if(sum == k){
                max_len = Math.max(max_len, i+1);
            }

            long rem = sum - k;
            if(prefixSum.containsKey(rem)){
                int len = i - prefixSum.get(rem);
                max_len = Math.max(max_len,len);
            }

            if(!prefixSum.containsKey(sum)){ //handles 0s and negatives
                prefixSum.put(sum,i);
            }
        }
        return max_len;
    }
}
