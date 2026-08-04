class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];
        int max = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            start = Math.max(start, last[s.charAt(i)]);
            max = Math.max(max, i - start + 1);
            last[s.charAt(i)] = i + 1;
        }
        return max;
  }
}