class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if(arr.length == k) {
            List<Integer> res =  new ArrayList<>();
            for (int val : arr) {
                res.add(val);
            }
            return res;
        }

        PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());
        for(int val: arr){
            if(k>0) {
                q.offer(new Pair<>(val, Math.abs(val - x)));
                k--;
            }
            else{
                if(q.peek().getValue() > Math.abs(val - x)){
                    q.poll();
                    q.offer(new Pair<>(val, Math.abs(val - x)));
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        while(!q.isEmpty()){
            res.add(q.poll().getKey());
        }
        
        Collections.sort(res);
        return res;
    }
}