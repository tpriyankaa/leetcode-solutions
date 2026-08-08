class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length(), j = m - 1, suf[] = new int[m+1], res[] = new int[m];
        Arrays.fill(suf, -1);
        for (int i = n - 1; i >= 0 && j >= 0; i--) if (word1.charAt(i) == word2.charAt(j)) suf[j--] = i;

        j = 0; boolean changed = false;
        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j) || (!changed && (j == m - 1 || suf[j + 1] > i))) {
                changed |= word1.charAt(i) != word2.charAt(j);
                res[j++] = i;
            }
        }
        return j == m ? res : new int[0];
    }
}