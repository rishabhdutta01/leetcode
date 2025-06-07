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
 *     // The result is undefined if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.size() == 0) return 0;

        int d=1;
        Queue<Pair<List<NestedInteger>, Integer>> q = new LinkedList<>();

        int res=0;
        for(int i=0;i<nestedList.size();i++){
            if(nestedList.get(i).isInteger()){
                res+=nestedList.get(i).getInteger()*d;
            } else{
                q.add(new Pair<>(nestedList.get(i).getList(), d+1));
            }
            
        }

        while(!q.isEmpty()){
            Pair<List<NestedInteger>, Integer> p = q.poll();
            List<NestedInteger> ni = p.getKey();
            d = p.getValue();

            for(int i=0;i<ni.size();i++){
                if(ni.get(i).isInteger()){
                    res+=ni.get(i).getInteger()*d;
                } else{
                    q.add(new Pair<>(ni.get(i).getList(), d+1));
                }
            }
        }
        return res;
    }
}