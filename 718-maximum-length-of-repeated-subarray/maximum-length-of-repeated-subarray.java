class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int max = 0;
        for(int i =0;i<n1;i++){
            
            for(int j=0;j<n2;j++){
                int cnt = 0;
                if(nums1[i] == nums2[j]){
                    cnt += 1;
                    int idxi = i+1;
                    int idxj = j+1;
                    while(idxi != n1 && idxj != n2) {
                        if(nums1[idxi] == nums2[idxj]) {
                            idxi++;
                            idxj++;
                            cnt++;
                        } else{break;}
                    }
                    max = Math.max(max, cnt);
                    if(idxi == n1) return max;
                }
            }
        }
        return max;
    }
}