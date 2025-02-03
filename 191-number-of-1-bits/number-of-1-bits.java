class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        for(int i=0;i<32;i++)
        {
            int bit = n & 1;
            res+=bit;
            n=n>>1;
        }

        return res;
    }
}