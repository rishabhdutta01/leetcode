class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }
    
    private void backtrack(String s, int start, List<String> segments, List<String> result) {
        // Base case: if we have 4 segments and used all characters
        if (segments.size() == 4) {
            if (start == s.length()) {
                result.add(String.join(".", segments));
            }
            return;
        }
        
        // Try segments of length 1, 2, and 3
        for (int len = 1; len <= 3; len++) {
            // Check bounds
            if (start + len > s.length()) break;
            
            String segment = s.substring(start, start + len);
            
            // Check if segment is valid
            if (isValid(segment)) {
                segments.add(segment);
                backtrack(s, start + len, segments, result);
                segments.remove(segments.size() - 1); // backtrack
            }
        }
    }
    
    private boolean isValid(String segment) {
        // Check for leading zeros (except single "0")
        if (segment.length() > 1 && segment.startsWith("0")) {
            return false;
        }
        
        // Check if number is in valid range
        int num = Integer.parseInt(segment);
        return num >= 0 && num <= 255;
    }
}