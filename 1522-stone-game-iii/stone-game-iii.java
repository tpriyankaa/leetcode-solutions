class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length, dp[] = new int[4];
        for (int i = n - 1; i >= 0; i--) {
            int max = Integer.MIN_VALUE, take = 0;
            for (int k = 1; k <= 3 && i + k <= n; k++){
                take += stoneValue[i + k - 1];
                max = Math.max(max, take - dp[(i + k) % 4]);
        }
        dp[i % 4] = max;
        }
        return dp[0] > 0 ? "Alice" : dp[0] < 0 ? "Bob" : "Tie";
    }
}