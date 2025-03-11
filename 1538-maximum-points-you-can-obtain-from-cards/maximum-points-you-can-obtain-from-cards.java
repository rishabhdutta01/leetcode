class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=cardPoints[i];
        }

        int l=k-1;        
        int r=cardPoints.length-1;

        int res = sum;

        while(l>=0) {
            sum -= cardPoints[l];
            sum += cardPoints[r];
            res = Math.max(res, sum);
            r--;
            l--;
        }
        return res;
    }
}