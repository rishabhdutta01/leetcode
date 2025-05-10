class Solution {
    Integer[][] memo;
    
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        memo = new Integer[n][n];
        // If minimum deletions needed ≤ k, return true
        return minDeletions(s, 0, n-1) <= k;
    }
    
    private int minDeletions(String s, int l, int r) {
        // Base case
        if (l >= r) return 0;
        
        // Check memo
        if (memo[l][r] != null) {
            return memo[l][r];
        }
        
        // If characters match, no deletion needed
        if (s.charAt(l) == s.charAt(r)) {
            memo[l][r] = minDeletions(s, l + 1, r - 1);
        } else {
            // Take minimum of deleting either left or right character
            memo[l][r] = 1 + Math.min(minDeletions(s, l + 1, r), 
                                     minDeletions(s, l, r - 1));
        }
        
        return memo[l][r];
    }
}