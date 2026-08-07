class Solution {
    int[] P = {2, 3, 5, 7};
    public String smallestNumber(String s, long t) {
        int[] req = new int[10]; 
        for (int p : P) while (t % p == 0) { req[p]++; t /= p; }
        if (t > 1) return "-1";
        
        int n = s.length();
        int[] d = s.chars().map(c -> c - 48).toArray();
        int[][] p = new int[n + 1][10];
        int z = s.indexOf('0');
        
        for (int i = 0; i < n; i++) {
            p[i+1] = p[i].clone();
            if (d[i] > 0) add(p[i+1], d[i]);
        }
        
        // Zero-allocation buffer array for processing the final output sequence
        char[] res = new char[n];
        for (int i = n; i >= 0; i--) {
            if (z >= 0 && i > z) continue;
            int st = (i == n) ? d[i-1] + 1 : d[i] + 1;
            if (i == n && ok(p[n], req)) return s;
            
            for (int v = st; v <= 9; v++) {
                int[] cur = p[i].clone(); 
                add(cur, v);
                if (fillSuf(res, i + 1, cur, req, n - 1 - i)) {
                    for (int j = 0; j < i; j++) res[j] = (char) (d[j] + '0');
                    res[i] = (char) (v + '0');
                    return new String(res);
                }
            }
        }
        
        // Suffix length expansion strategy when length grows
        for (int l = n + 1; ; l++) {
            char[] expRes = new char[l];
            if (fillSuf(expRes, 0, new int[10], req, l)) {
                return new String(expRes);
            }
        }
    }

    void add(int[] c, int v) {
        for (int p : P) { int tmp = v; while (tmp > 1 && tmp % p == 0) { c[p]++; tmp /= p; } }
    }

    boolean ok(int[] c, int[] t) { 
        return c[2] >= t[2] && c[3] >= t[3] && c[5] >= t[5] && c[7] >= t[7]; 
    }

    // In-place character modification completely bypasses memory creation allocations
    boolean fillSuf(char[] buffer, int start, int[] c, int[] t, int len) {
        int r2 = Math.max(0, t[2] - c[2]), r3 = Math.max(0, t[3] - c[3]);
        int c9 = r3 / 2; r3 %= 2; 
        int c8 = r2 / 3; r2 %= 3;
        int c6 = (r2 > 0 && r3 > 0) ? 1 : 0; r2 -= c6; r3 -= c6;
        int c4 = r2 / 2; r2 %= 2;
        int c5 = Math.max(0, t[5] - c[5]), c7 = Math.max(0, t[7] - c[7]);
        int tot = r2 + r3 + c4 + c5 + c6 + c7 + c8 + c9;
        
        if (tot > len) return false;
        
        int idx = start;
        int ones = len - tot;
        while (ones-- > 0) buffer[idx++] = '1';
        while (r2-- > 0) buffer[idx++] = '2';
        while (r3-- > 0) buffer[idx++] = '3';
        while (c4-- > 0) buffer[idx++] = '4';
        while (c5-- > 0) buffer[idx++] = '5';
        while (c6-- > 0) buffer[idx++] = '6';
        while (c7-- > 0) buffer[idx++] = '7';
        while (c8-- > 0) buffer[idx++] = '8';
        while (c9-- > 0) buffer[idx++] = '9';
        return true;
    }
}