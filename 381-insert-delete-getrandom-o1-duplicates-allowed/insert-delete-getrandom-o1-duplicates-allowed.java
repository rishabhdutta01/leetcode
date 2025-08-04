class RandomizedCollection {
    Map<Integer, Set<Integer>> map;
    List<Integer> list;
    Random r;

    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        r = new Random();
    }
    
    public boolean insert(int val) {
        if(!map.containsKey(val)){
            map.put(val, new LinkedHashSet<>());
        }

        map.get(val).add(list.size());
        list.add(val);
        return map.get(val).size() == 1;
    }
    
    public boolean remove(int val) {
        if(!map.containsKey(val) || map.get(val).size() == 0) return false;

        int currIdx = map.get(val).iterator().next();
        map.get(val).remove(currIdx);

        int lastVal = list.getLast();
        list.set(currIdx, lastVal);
        map.get(lastVal).add(currIdx);
        map.get(lastVal).remove(list.size()-1);
        list.removeLast();
        return true;
    }
    
    public int getRandom() {
        return list.get(r.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */