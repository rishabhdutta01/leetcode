public class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];
        // counting papers for each citation number
        for (int c: citations)
            papers[Math.min(n, c)]++;
        // finding the h-index
        int k = n;
        int s = papers[n]; // Initialize s
        while (k > s) {  // Condition
            k--; // Decrement k
            s += papers[k]; // Increment s based on papers[k]
        }
        return k;
    }
}