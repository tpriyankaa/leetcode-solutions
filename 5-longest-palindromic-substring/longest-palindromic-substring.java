class Solution {
    public String longestPalindrome(String s) {
        int[] res = {0, 0};
        for (int i = 0; i < s.length(); i++) {
            extend(s, i, i, res);
            extend(s, i, i + 1, res);
        }
        return s.substring(res[0], res[1]);
    }

    private void extend(String s, int L, int R, int[] res) {
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) { L --; R++;}
            if (R - L - 1 > res[1] - res[0]) { res[0] = L + 1; res[1] = R; }
        }
    }
