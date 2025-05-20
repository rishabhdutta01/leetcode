class TwoSum {

    HashMap<Integer, Integer> map;
    int MAX = Integer.MIN_VALUE;
    int MIN = Integer.MAX_VALUE;

    public TwoSum() {
        this.map = new HashMap<>();
    }

    public void add(int number) {
        if (number > MAX) {
            MAX = number;
        } else if (number < MIN) {
            MIN = number;
        }

        this.map.put(number, map.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        if (value > MAX * 2 || value < MIN * 2) {
            return false;
        }

        for (int key : map.keySet()) {
            int complement = value - key;
            if (complement == key) {
                if (map.get(key) > 1) {
                    return map.get(key) > 1;
                }
            } else {
                if (map.containsKey(complement)) {
                    return true;
                }
            }
        }
        return false;
    }
}