class Solution {
    public String customSortString(String order, String s) {
        // Step 1: Count frequency of each character in s
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Step 2: Append characters from 'order' in correct order
        StringBuilder result = new StringBuilder();
        for (char c : order.toCharArray()) {
            while (freq[c - 'a']-- > 0) {
                result.append(c);
            }
        }

        // Step 3: Append remaining characters not in 'order'
        for (char c = 'a'; c <= 'z'; c++) {
            while (freq[c - 'a']-- > 0) {
                result.append(c);
            }
        }

        return result.toString();
    }
}