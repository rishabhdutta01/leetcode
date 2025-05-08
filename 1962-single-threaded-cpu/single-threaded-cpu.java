class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[] res = new int[n];
        
        // Step 1: Create array with [enqueueTime, processingTime, originalIndex]
        // This helps us keep track of original indices after sorting
        int[][] indexedTasks = new int[n][3];
        for(int i = 0; i < n; i++) {
            indexedTasks[i][0] = tasks[i][0];    // enqueue time
            indexedTasks[i][1] = tasks[i][1];    // processing time
            indexedTasks[i][2] = i;              // original index
        }
        
        // Step 2: Sort tasks by enqueue time to process them in order
        // If tasks come later, we can't process them first
        Arrays.sort(indexedTasks, (a, b) -> a[0] - b[0]);
        
        // Step 3: Create PriorityQueue to store available tasks
        // Sort by: 1) processing time (shorter first)
        //         2) If processing times are equal, sort by index (smaller first)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> 
            a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]);
        
        int idx = 0;            // pointer to current task in indexedTasks
        int resultIdx = 0;      // pointer to current position in result array
        long currentTime = 0;   // current time (long to avoid overflow)
        
        // Continue while we have either:
        // - Tasks still to be added to queue (idx < n)
        // - Tasks in the queue to be processed (!pq.isEmpty())
        while (idx < n || !pq.isEmpty()) {
            // If queue is empty and we can't process next task yet,
            // fast forward time to when next task is available
            if (pq.isEmpty() && currentTime < indexedTasks[idx][0]) {
                currentTime = indexedTasks[idx][0];
            }
            
            // Add all tasks that have arrived by currentTime to the queue
            while (idx < n && indexedTasks[idx][0] <= currentTime) {
                pq.offer(indexedTasks[idx]);
                idx++;
            }
            
            // Process the task with shortest processing time
            int[] task = pq.poll();
            res[resultIdx++] = task[2];    // add original index to result
            currentTime += task[1];         // increment time by processing time
        }
        
        return res;
    }
}
