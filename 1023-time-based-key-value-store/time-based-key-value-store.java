class TimeMap {
    Map<String, List<Pair<Integer, String>>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(new Pair<>(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key)){
            return "";
        }
        List<Pair<Integer, String>> arr = map.get(key);
        int ans = -1;
        int l = 0;
        int r = arr.size()-1;
        while(l<=r){
            int mid = l +(r-l)/2;
            if(arr.get(mid).getKey() == timestamp){
                ans = mid;
                break;
            } else if(arr.get(mid).getKey() < timestamp){
                ans = mid;
                l = mid+1;
            } else{
                r=mid-1;
            }
        }
        if (ans == -1) return "";
        else return arr.get(ans).getValue();
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */