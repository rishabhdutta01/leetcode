class BoundedBlockingQueue {
    AtomicInteger curr;
    Object lock;
    int capacity;
    Queue<Integer> q;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        curr = new AtomicInteger(0);
        lock = new Object();
        q = new LinkedList<>();
    }
    
    public void enqueue(int element) throws InterruptedException {
        synchronized(lock){
            while(curr.get() == capacity) {
                lock.wait();
            }
            q.offer(element);
            curr.incrementAndGet();
            lock.notifyAll();
        }  
    }
    
    public int dequeue() throws InterruptedException {
        int e;
        synchronized(lock){
            while(curr.get() == 0) {
                lock.wait();
            }
            e = q.poll();
            curr.decrementAndGet();
            lock.notifyAll();
        }        
        return e;
    }
    
    public int size() {
        return curr.get();
    }
}