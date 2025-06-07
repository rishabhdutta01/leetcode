class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums.length == 1) {
            if(k==1){
                return nums;
            }
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int max = -1;

        for(int i=0; i<nums.length; i++){
            if(map.get(nums[i]) == null) {
                map.put(nums[i], 0);
            }
            int n = map.get(nums[i]) + 1;
            max = Math.max(max, n);
            map.put(nums[i], n);
        }

        if(map.size() == k) {
            int[] arr = new int[k];
            int i=0;
            for(Integer key: map.keySet()){           
                arr[i] = key;
                i++;
            }
            return arr;
        }

        List<List<Integer>> list = new ArrayList<>(max+1);
        for (int i = 0; i <= max; i++) {
            list.add(new ArrayList<>());
        }

        for(Integer key: map.keySet()){
            int val = map.get(key);
            List<Integer> l = list.get(val);
            l.add(key);
            list.set(val, l);
        }

        int[] arr = new int[k];
        int i = 0;

        while(i<k) {
            List<Integer> l = list.get(max);
            if(l != null){
                for (int j = 0; j < l.size(); j++) {
                    if(i == k) {
                        break;
                    }
                    arr[i]=l.get(j);
                    i++;
                }
            }
            max--;
        }

        return arr;
    }
}