class LFUCache {

    // key: original key, value: frequency and original value.
    private Map<Integer, Pair<Integer, Integer>> cache;
    // key: frequency, value: All keys that have the same frequency.
    private Map<Integer, LinkedHashSet<Integer>> frequencies;
    private int minf;
    private int capacity;

    private void insert(int key, int frequency, int value) {
        cache.put(key, new Pair<>(frequency, value));
        frequencies.putIfAbsent(frequency, new LinkedHashSet<>());
        frequencies.get(frequency).add(key);
    }

    public LFUCache(int capacity) {
        cache = new HashMap<>();
        frequencies = new HashMap<>();
        minf = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        Pair<Integer, Integer> frequencyAndValue = cache.get(key);
        if (frequencyAndValue == null) {
            return -1;
        }
        final int frequency = frequencyAndValue.getKey();
        final Set<Integer> keys = frequencies.get(frequency);
        keys.remove(key);
        if (keys.isEmpty()) {
            frequencies.remove(frequency);

            if (minf == frequency) {
                ++minf;
            }
        }
        final int value = frequencyAndValue.getValue();
        insert(key, frequency + 1, value);
        return value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        Pair<Integer, Integer> frequencyAndValue = cache.get(key);
        if (frequencyAndValue != null) {
            cache.put(key, new Pair<>(frequencyAndValue.getKey(), value));
            get(key);
            return;
        }
        if (capacity == cache.size()) {
            final Set<Integer> keys = frequencies.get(minf);
            final int keyToDelete = keys.iterator().next();
            cache.remove(keyToDelete);
            keys.remove(keyToDelete);
            if (keys.isEmpty()) {
                frequencies.remove(minf);
            }
        }
        minf = 1;
        insert(key, 1, value);
    }
}
// class LFUCache {
//     public LFUCache(int capacity) {
//         this.capacity = capacity;
//     }

//     private int capacity;
//     private int minFreq = 0;
//     private Map<Integer, Integer> keyToVal = new HashMap<>();
//     private Map<Integer, Integer> keyToFreq = new HashMap<>();
//     private Map<Integer, LinkedHashSet<Integer>> freqToLRUKeys = new HashMap<>();

//     private void putFreq(int key, int freq) {
//         keyToFreq.put(key, freq);
//         freqToLRUKeys.putIfAbsent(freq, new LinkedHashSet<>());
//         freqToLRUKeys.get(freq).add(key);
//     }

//     public int get(int key) {
//         if (!keyToVal.containsKey(key))
//             return -1;

//         final int freq = keyToFreq.get(key);
//         freqToLRUKeys.get(freq).remove(key);
//         if (freq == minFreq && freqToLRUKeys.get(freq).isEmpty()) {
//             freqToLRUKeys.remove(freq);
//             ++minFreq;
//         }

//         // Increase key's freq by 1
//         // Add this key to next freq's list
//         putFreq(key, freq + 1);
//         return keyToVal.get(key);
//     }

//     public void put(int key, int value) {
//         if (capacity == 0)
//             return;
//         if (keyToVal.containsKey(key)) {
//             keyToVal.put(key, value);
//             get(key); // Update key's count
//             return;
//         }

//         if (keyToVal.size() == capacity) {
//             // Evict LRU key from the minFreq list
//             final int keyToEvict = freqToLRUKeys.get(minFreq).iterator().next();
//             freqToLRUKeys.get(minFreq).remove(keyToEvict);
//             keyToVal.remove(keyToEvict);
//         }

//         minFreq = 1;
//         putFreq(key, minFreq); // Add new key and freq
//         keyToVal.put(key, value); // Add new key and value
//     }
// }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */