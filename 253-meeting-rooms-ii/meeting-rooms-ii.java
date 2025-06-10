class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int res = 0;
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        for(int i=0;i<intervals.length;i++){
            if(q.isEmpty()) {
                q.add(intervals[i]);
                res = Math.max(res, q.size());
                continue;
            }

            if(q.peek()[1] <= intervals[i][0]){
                q.poll();
            }
            q.add(intervals[i]);
            res = Math.max(res, q.size());
        }
        return res;
    }
}