class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : invocations) g[e[0]].add(e[1]);

        boolean[] s = new boolean[n];
        Queue<Integer> q = new LinkedList<>(List.of(k)); s[k] = true;
        while (!q.isEmpty()) for (int v : g[q.poll()]) if (!s[v]) {s[v] = true; q.add(v); }

        for (int[] e : invocations) if (!s[e[0]] && s[e[1]]) s = new boolean[n]; 

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) if (!s[i]) ans.add(i);
        return ans;
    }
}