class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length ==0){
            return null;
        }
        else if(intervals.length==1){
            return intervals;
        }

        Arrays.sort(intervals, (a,b) -> { if(a[0] == b[0]) return a[1] - b[1]; return a[0] - b[0];});

        List<List<Integer>> l = new ArrayList<>();
        int i=1;
        int e=intervals[0][1];
        int s=intervals[0][0];
        while(i<intervals.length){
            if(e >= intervals[i][0]){
                s=Math.min(s,intervals[i][0]);
                e=Math.max(e,intervals[i][1]);
            }
            else {
                l.add(Arrays.asList(s,e));
                s = intervals[i][0];
                e = intervals[i][1];
            }             
            i++;
        }
        l.add(Arrays.asList(s,e));
        
        //return l.stream().map(x -> x.stream().toArray(int[]::new)).toArray(int[]::new);
        return l.stream().map(p -> new int[] {p.get(0), p.get(1)}).toArray(int[][]::new);
    }
}