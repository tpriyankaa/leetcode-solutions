/**
 * @param {string} s
 * @return {string}
 */
var smallestPalindrome = function(s) {
    const counts = {};
    for (const char of s) {
        counts[char] = (counts[char] || 0) + 1;
    }
    const sortedChars = Object.keys(counts).sort();
     
     let leftHalf = "";
     let middle = "";

     for (const char of sortedChars) {
        const freq = counts[char];

        if (freq % 2 === 1){
            middle = char;
        }
        leftHalf += char.repeat(Math.floor(freq / 2));
     }
    
    const rightHalf = leftHalf.split("").reverse().join("");

    return leftHalf + middle + rightHalf;
};