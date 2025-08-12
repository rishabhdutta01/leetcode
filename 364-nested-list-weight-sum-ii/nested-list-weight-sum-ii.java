/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        List<Pair<NestedInteger, Integer>> l = new ArrayList<>();
        Queue<Pair<List<NestedInteger>, Integer>> q = new LinkedList<>();
        int max = 0;

        q.add(new Pair<>(nestedList, 0));       
        
        while(!q.isEmpty()){
            Pair<List<NestedInteger>, Integer> p = q.poll();
            List<NestedInteger> k = p.getKey();
            int v = p.getValue();

            for(int i=0;i<k.size();i++){
                if(k.get(i).isInteger()){
                    l.add(new Pair<>(k.get(i), v+1));
                    max = Math.max(max, v+1);
                } else{
                    q.add(new Pair<>(k.get(i).getList(), v+1));
                }
            }
        }

        int res = 0;
        for(Pair<NestedInteger, Integer> p: l){
            res += p.getKey().getInteger()*(max - p.getValue() + 1);
        }
        return res;
    }
}