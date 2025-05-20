class MinStack {
    List<int[]> s;

    public MinStack() {
        s = new ArrayList<>();
    }
    
    public void push(int val) {
        if(s.size() == 0) s.add(new int[]{val, val});
        else {
            int min = Math.min(this.getMin(), val);
            s.add(new int[]{val, min});
        }
    }
    
    public void pop() {
        if(s.size() == 0) return;
        
        s.remove(s.size()-1);
    }
    
    public int top() {
        if(s.size() == 0) return Integer.MIN_VALUE;
        
        return s.get(s.size()-1)[0];
    }
    
    public int getMin() {
        if(s.size() == 0) return Integer.MIN_VALUE;
        
        return s.get(s.size()-1)[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */