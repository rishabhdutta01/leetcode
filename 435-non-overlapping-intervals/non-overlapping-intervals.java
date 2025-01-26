class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Double.compare(a[0], b[0]));
        
        int res = 0;
        int lastEnd = intervals[0][1];

        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0] >= lastEnd){
                lastEnd = intervals[i][1];
            } else {
                res++;
                lastEnd = Math.min(lastEnd, intervals[i][1]);
            }
        }
        return res;
    }
}