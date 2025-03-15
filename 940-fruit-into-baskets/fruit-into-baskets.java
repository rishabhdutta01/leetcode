class Solution {
    public int totalFruit(int[] fruits) {
        if(fruits.length <=2){
            return fruits.length;
        }

        Map<Integer, Integer> m = new HashMap<>();
        int l=0;
        int r=0;
        int res=0;

        while(r<fruits.length){
            if(!m.containsKey(fruits[r])){
                m.put(fruits[r],0);
            }
            m.put(fruits[r], m.get(fruits[r])+1);

            if(m.size() > 2){
                m.put(fruits[l], m.get(fruits[l])-1);
                if(m.get(fruits[l]) == 0){
                    m.remove(fruits[l]);
                }
                l++;
            }

            if(m.size() <= 2){
                res = Math.max(res, r-l+1);
            }
            r++;
        }
        return res;
    }
}