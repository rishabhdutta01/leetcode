class MedianFinder {
    List<Integer> l;

    public MedianFinder() {
        l = new ArrayList();
    }
    
    public void addNum(int num) {
        if(l.size() == 0) {
            l.add(num);
            return;
        }

        int i=0;
        while(i<l.size() && num>l.get(i)) {
            i++;
        }

        if(i==l.size()){
            l.add(num);
        }
        else {
            l.add(i, num);
        }
    }
    
    public double findMedian() {
        if(l.size() == 0)
            return 0;
        else {
            int n = l.size();
            if(n % 2 == 0 ) {
                double res = (l.get(n/2 -1)) + (l.get(n/2));
                return res/2;

            } else {
                return l.get(n/2);
            }
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */