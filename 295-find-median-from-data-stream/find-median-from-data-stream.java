class MedianFinder {
    PriorityQueue<Integer> q1;
    PriorityQueue<Integer> q2;

    public MedianFinder() {
        q1 = new PriorityQueue<>((a,b) -> b - a);
        q2 = new PriorityQueue<>((a,b) -> a - b);
    }
    
    public void addNum(int num) {
        int n1 = q1.size();
        int n2 = q2.size();

        if(n2 == 0){
            if(n1 == 0) q1.add(num);
            else{
                if(num < q1.peek()){
                    q2.add(q1.poll());
                    q1.add(num);
                } else q2.add(num);
            }
            return;
        }

        if(n1 == n2){
            if(num > q2.peek()){
                q1.add(q2.poll());
                q2.add(num);
            } else q1.add(num);
        } else{
            if(num > q1.peek()){
                q2.add(num);
            } else {
                q2.add(q1.poll());
                q1.add(num);
            }
        }
    }
    
    public double findMedian() {
        int n1 = q1.size();
        int n2 = q2.size();
        if(n1 == n2){
            return (q1.peek() + q2.peek())/2.0;
        } else return q1.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */