class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals == null || intervals.length == 0) {
            return new int[][]{newInterval};
        }

        List<int[]> l = new ArrayList<>();

        int i=0;

        while(i<intervals.length) {
            if(newInterval[0] < intervals[i][0]) {
                if(newInterval[1] < intervals[i][0]) {
                    l.add(newInterval);
                    break;
                } else {
                    newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                    newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
                    i++;
                }
            } else {
                if(newInterval[0] > intervals[i][1]) {
                    l.add(intervals[i]);
                } else {
                    newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                    newInterval[1] = Math.max(newInterval[1], intervals[i][1]);   
                }
                i++;     
            }
        }
        if(i<intervals.length) {
            while(i<intervals.length) {
                l.add(intervals[i]);
                i++;
            }
        } else {
            l.add(newInterval);
        }
        return l.toArray(new int[l.size()][]);
    }
}