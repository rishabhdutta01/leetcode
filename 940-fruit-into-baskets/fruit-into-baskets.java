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
            if(m.containsKey(fruits[r])){
                m.put(fruits[r], r);
                r++;
            } else{
                if(m.size()<2){
                    m.put(fruits[r], r);
                } else{
                    int minf = Integer.MAX_VALUE;
                    int minl = Integer.MAX_VALUE;
                    for(Map.Entry<Integer, Integer> entry : m.entrySet()){
                        if(entry.getValue()< minl){
                            minf = entry.getKey();
                            minl = entry.getValue();
                        }
                    }
                    m.remove(minf);
                    l = minl +1;

                    m.put(fruits[r], r);
                }
                r++;
            }

            res=Math.max(res, r-l);
        }
        return res;
    }
}