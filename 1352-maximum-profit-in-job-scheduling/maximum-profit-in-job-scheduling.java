// class Solution {
//     class Job {
//         int st;
//         int et;
//         int profit;

//         Job(int st, int et, int profit) {
//             this.st = st;
//             this.et = et;
//             this.profit = profit;
//         }
//     }

//     public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
//         Job[] jobs = new Job[startTime.length];
//         for (int i = 0; i < startTime.length; i++) {
//             jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
//         }
//         Arrays.sort(jobs, (a, b) -> a.st - b.st);

//         Map<String, Integer> memo = new HashMap<>();
//         return fnc(jobs, 0, memo);
//     }

//     int fnc(Job[] jobs, int idx, Map<String, Integer> memo) {
//         if (idx == jobs.length) {
//             return 0;
//         }

//         String key = String.valueOf(idx);
//         if (memo.containsKey(key)) {
//             return memo.get(key);
//         }

//         int nextIdx = findNextIdx(jobs, idx);
//         int pick = jobs[idx].profit + fnc(jobs, nextIdx, memo);
//         int notpick = fnc(jobs, idx + 1, memo);

//         int result = Math.max(pick, notpick);
//         memo.put(key, result);
//         return result;
//     }

//     int findNextIdx(Job[] jobs, int idx) {
//         int target = jobs[idx].et;
//         int left = idx + 1;
//         int right = jobs.length;

//         while (left < right) {
//             int mid = left + (right - left) / 2;
//             if (jobs[mid].st < target) {
//                 left = mid + 1;
//             } else {
//                 right = mid;
//             }
//         }
//         return left;
//     }
// }


// Method 4 : Top-Down Dynamic Programming + Binary Search + Using Jobs Class type
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, new Comparator<Job>(){
            @Override
            public int compare(Job a, Job b) {
                return a.end - b.end;
            }
        });
        int[] dp = new int[profit.length];//包含[0, i]工作能赚的最多的钱
        dp[0] = jobs[0].profit;
        for (int i = 1; i < profit.length; i++) {
            int start = jobs[i].start;
            int left = 0, right = i - 1, res = -1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (jobs[mid].end <= start) {
                    res = mid;
                    left = mid + 1;
                } else
                    right = mid - 1;
            }
            int take = jobs[i].profit;
            if (res != -1)
                take += dp[res];
            dp[i] = Math.max(take, dp[i - 1]);
        }
        return dp[dp.length - 1];
        
    }
   
    private static class Job  {
        int start;
        int end;
        int profit;
        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}