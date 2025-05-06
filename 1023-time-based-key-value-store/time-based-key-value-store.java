class TimeMap {

    Map<String,TreeMap<Integer,String>> m;
    public TimeMap() {
        m = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> tm;
        if(!m.containsKey(key)){
            tm = new TreeMap<>();
            m.put(key, tm);
        } else{
            tm=m.get(key);
        }
        tm.put(timestamp,value);

    }
    
    public String get(String key, int timestamp) {
        if(!m.containsKey(key)){
            return "";
        }
        TreeMap<Integer, String> tm = m.get(key);
        Integer floorKey = tm.floorKey(timestamp);
        if(floorKey == null) return "";
        return tm.get(floorKey);
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */