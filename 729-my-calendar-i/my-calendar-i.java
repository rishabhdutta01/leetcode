class MyCalendar {
    TreeMap<Integer, Integer> map;
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int startTime, int endTime) {
        if(map.size() == 0) {
            map.put(startTime, endTime);
            return true;
        }
        if(map.containsKey(startTime)) return false;

        Integer floor = map.floorKey(startTime);
        Integer ceil = map.ceilingKey(startTime);

        if(floor != null){
            if(map.get(floor) > startTime) return false;
        }

        if(ceil != null){
            if(endTime > ceil) return false;
        }
        
        map.put(startTime, endTime);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(startTime,endTime);
 */