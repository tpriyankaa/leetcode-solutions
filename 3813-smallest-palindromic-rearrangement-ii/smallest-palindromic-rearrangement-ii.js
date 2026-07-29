/**
 * @param {string} s
 * @param {number} k
 * @return {string}
 */
var smallestPalindrome = function(s, k) {
    
    const MAX_K = 1e6 + 7; // Cap computations safely since k is within constraints
    const counts = new Array(26).fill(0);
    for (const char of s) {
        counts[char.charCodeAt(0) - 97]++;
    }

    // 1. Verify if a palindrome layout is possible
    let oddCount = 0;
    let midChar = "";
    const halfCounts = new Array(26).fill(0);
    
    for (let i = 0; i < 26; i++) {
        if (counts[i] % 2 !== 0) {
            oddCount++;
            midChar = String.fromCharCode(i + 97);
        }
        halfCounts[i] = Math.floor(counts[i] / 2);
    }
    
    if (oddCount > 1) return "";

    // Helper: Compute combinations nCr with saturation protection
    const nCr = (n, r) => {
        if (r < 0 || r > n) return 0;
        if (r === 0 || r === n) return 1;
        if (r > n - r) r = n - r;
        let res = 1;
        for (let i = 1; i <= r; i++) {
            res = Math.floor((res * (n - i + 1)) / i);
            if (res > MAX_K) return MAX_K;
        }
        return res;
    };

    // Helper: Count distinct multinomial arrangements of remaining characters
    const countArrangements = (arr) => {
        let total = arr.reduce((sum, val) => sum + val, 0);
        let ways = 1;
        for (const freq of arr) {
            if (freq > 0) {
                ways *= nCr(total, freq);
                if (ways > MAX_K) return MAX_K;
                total -= freq;
            }
        }
        return ways;
    };

    // 2. Fast check if total permutations match k threshold requirements
    const totalPermutations = countArrangements(halfCounts);
    if (k > totalPermutations) return "";

    // 3. Greedily assemble the left side of the target palindrome string
    let leftHalf = "";
    const totalLen = halfCounts.reduce((sum, val) => sum + val, 0);

    for (let p = 0; p < totalLen; p++) {
        for (let i = 0; i < 26; i++) {
            if (halfCounts[i] === 0) continue;

            // Choose character i temporarily
            halfCounts[i]--;
            const arrangements = countArrangements(halfCounts);

            if (arrangements >= k) {
                leftHalf += String.fromCharCode(i + 97);
                break; // Found the correct letter for this position
            } else {
                k -= arrangements; // Skip the permutations belonging to character i
                halfCounts[i]++;   // Backtrack
            }
        }
    }

    // 4. Construct and return full mirrored string representation
    const rightHalf = leftHalf.split("").reverse().join("");
    return leftHalf + midChar + rightHalf;
};
