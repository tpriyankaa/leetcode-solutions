class Solution {
    public boolean predictTheWinner(int[] nums) {
     int n = nums.length;
     int[] dp = new int[n];

     for (int i = 0; i<n; i++){
        dp[i] = nums[i];
     }
     for (int len = 1; len <n; len++){
        for (int i = 0; i < n - len; i++){
            int j = i + len;

            dp[i] = Math.max(nums[i] - dp[i + 1], nums[j] - dp[i]);
        }
     }
    return dp[0] >= 0;
    }
}