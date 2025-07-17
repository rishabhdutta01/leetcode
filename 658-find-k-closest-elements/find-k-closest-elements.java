class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if(arr.length == k) {
            List<Integer> res =  new ArrayList<>();
            for (int val : arr) {
                res.add(val);
            }
            return res;
        }

        int n = arr.length;
        int l = 0;
        int r = n-1;
        int ptr = -1;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(arr[mid] <= x) {
                l = mid+1;
                ptr = mid;
            } else{
                r = mid-1;
            }
        }

        if(ptr == -1) ptr=0;

        l = ptr;
        r = ptr + 1;

        while(r-l-1 < k){
            if(l < 0) r++;
            else if(r >= n) l--;
            else{
                if(Math.abs(arr[l] - x) <= Math.abs(arr[r] - x)) l--;
                else r++;
            }
        }

        List<Integer> res =  new ArrayList<>();
        for(int i=l+1;i<r;i++){
            res.add(arr[i]);
        }
        return res;
    }
}