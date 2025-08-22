class Solution {

    public int maxEvents(int[][] events) {
        int n = events.length;
        int maxDay = 0;
        for (int[] event : events) {
            maxDay = Math.max(maxDay, event[1]);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(events, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);   
        int ans = 0;
        int d = events[0][0];
        int j=0;

        while(d<=maxDay) {
            //add all events equal to curr day 
            while (j < n && events[j][0] <= d) {
                pq.offer(events[j][1]);
                j++;
            }
            //remove all events whose end day is before curr day
            while (!pq.isEmpty() && pq.peek() < d) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                pq.poll();
                ans++;
            }
            d++;
        }

        return ans;
    }
}