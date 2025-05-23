class TimeMap {
    HashMap<String, ArrayList<Pair<Integer, String>>> keyTimeMap;
    
    public TimeMap() {
        keyTimeMap = new HashMap();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!keyTimeMap.containsKey(key)) {
            keyTimeMap.put(key, new ArrayList());
        }
        
        // Store '(timestamp, value)' pair in 'key' bucket.
        keyTimeMap.get(key).add(new Pair(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        // If the 'key' does not exist in map we will return empty string.
        if (!keyTimeMap.containsKey(key)) {
            return "";
        }
        
        if (timestamp < keyTimeMap.get(key).get(0).getKey()) {
            return "";
        }

        // Comparator<Pair<Integer, String>> c = new Comparator<>() {
        //     public int compare(Pair<Integer, String> a, Pair<Integer, String> b)
        //     {
        //         return a.getKey() - b.getKey();
        //     }
        // };

        int idx = Collections.binarySearch(keyTimeMap.get(key), new Pair<>(timestamp,null),(a,b) -> a.getKey() - b.getKey());
        if(idx>=0){
            return keyTimeMap.get(key).get(idx).getValue();
        }
        idx = idx*-1 -2;
        return keyTimeMap.get(key).get(idx).getValue();
        
        // // Using binary search on the list of pairs.
        // int left = 0;
        // int right = keyTimeMap.get(key).size();
        
        // while (left < right) {
        //     int mid = (left + right) / 2;
        //     if (keyTimeMap.get(key).get(mid).getKey() <= timestamp) {
        //         left = mid + 1;
        //     } else {
        //         right = mid;
        //     }
        // }

        // // If iterator points to first element it means, no time <= timestamp exists.
        // if (right == 0) {
        //     return "";
        // }
                
        // return keyTimeMap.get(key).get(right - 1).getValue();
    }
}
// class TimeMap {

//     Map<String,TreeMap<Integer,String>> m;
//     public TimeMap() {
//         m = new HashMap<>();
//     }
    
//     public void set(String key, String value, int timestamp) {
//         TreeMap<Integer, String> tm;
//         if(!m.containsKey(key)){
//             tm = new TreeMap<>();
//             m.put(key, tm);
//         } else{
//             tm=m.get(key);
//         }
//         tm.put(timestamp,value);

//     }
    
//     public String get(String key, int timestamp) {
//         if(!m.containsKey(key)){
//             return "";
//         }
//         TreeMap<Integer, String> tm = m.get(key);
//         Integer floorKey = tm.floorKey(timestamp);
//         if(floorKey == null) return "";
//         return tm.get(floorKey);
//     }
// }

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */