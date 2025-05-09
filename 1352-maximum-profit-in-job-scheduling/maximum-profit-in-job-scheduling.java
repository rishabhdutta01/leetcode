class Solution {
    class Job {
        int st;
        int et;
        int profit;
        Job(int st, int et, int profit) {
            this.st = st;
            this.et = et;
            this.profit = profit;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, (a,b) -> a.st - b.st);

        Map<String, Integer> memo = new HashMap<>();
        return fnc(jobs, 0, -1, memo);
    }

    int fnc(Job[] jobs, int idx, int lastidx, Map<String, Integer> memo) {
        if(idx == jobs.length) {
            return 0;
        }

        String key = String.valueOf(idx);
        if(memo.containsKey(key)) {
            return memo.get(key);
        }

        int nextIdx = findNextIdx(jobs, idx);
        int pick = jobs[idx].profit + fnc(jobs, nextIdx, idx, memo);
        int notpick = fnc(jobs, idx+1, lastidx, memo);

        int result = Math.max(pick, notpick);
        memo.put(key, result);
        return result;
    }

    int findNextIdx(Job[] jobs, int idx) {
        int target = jobs[idx].et;
        int left = idx + 1;  // Start from next index
        int right = jobs.length;  // Use length as right boundary
        
        // Find first job that starts at or after current job's end time
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(jobs[mid].st < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
