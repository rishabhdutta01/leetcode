class MaxStack {
    List<int[]> l;
    Set<Integer> r;
    PriorityQueue<int[]> h;
    int id;

    public MaxStack() {
        l = new ArrayList<>();
        r = new HashSet<>();
        h = new PriorityQueue<>((a,b) -> {
            if(a[1] != b[1]) return Integer.compare(b[1],a[1]);
            return Integer.compare(b[0],a[0]);
        });
        id = 0;
    }
    
    public void push(int x) {
        l.add(new int[]{id, x});
        h.add(new int[]{id, x});
        id++;
    }
    
    public int pop() {
        while(r.contains(l.getLast()[0])){
            r.remove(l.getLast()[0]);
            l.removeLast();
        }

        if(h.peek()[0] == l.getLast()[0]) h.poll();
        else r.add(l.getLast()[0]);
        return l.removeLast()[1];
    }
    
    public int top() {
        while(r.contains(l.getLast()[0])){
            r.remove(l.getLast()[0]);
            l.removeLast();
        }

        return l.getLast()[1];
    }
    
    public int peekMax() {
        while(r.contains(h.peek()[0])){
            r.remove(h.peek()[0]);
            h.poll();
        }

        return h.peek()[1];
    }
    
    public int popMax() {
        while(r.contains(h.peek()[0])){
            r.remove(h.peek()[0]);
            h.poll();
        }

        if(h.peek()[0] == l.getLast()[0]) l.removeLast();
        else r.add(h.peek()[0]);
        return h.poll()[1];
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */