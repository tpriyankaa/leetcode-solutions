#include <string.h>
#include <stdlib.h>

// Comparison function for qsort (descending order)
int compare(const void *a, const void *b) {
    return (*(int*)b - *(int*)a);
}

int minimumPushes(char* word) {
    int freq[26] = {0};
    int totalPushes = 0;

    // Step 1: Count frequencies of each character
    for (int i = 0; word[i] != '\0'; i++) {
        freq[word[i] - 'a']++;
    }

    // Step 2: Sort frequencies in descending order
    qsort(freq, 26, sizeof(int), compare);

    // Step 3: Calculate minimum pushes using greedy allocation across 8 keys
    for (int i = 0; i < 26 && freq[i] > 0; i++) {
        // i / 8 determines the key position (0 = 1st press, 1 = 2nd press, etc.)
        int pressesNeeded = (i / 8) + 1;
        totalPushes += freq[i] * pressesNeeded;
    }

    return totalPushes;

}