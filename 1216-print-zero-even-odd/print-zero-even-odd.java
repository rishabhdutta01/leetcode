class ZeroEvenOdd {
    private int n;
    AtomicInteger x;
    volatile boolean calledZero = false;

    Semaphore zero;
    Semaphore odd;
    Semaphore even;

    
    public ZeroEvenOdd(int n) {
        this.n = n;
        x = new AtomicInteger(1);

        zero = new Semaphore(1);
        odd = new Semaphore(0);
        even = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while(x.get() <= n) {
            zero.acquire();

            if(x.get() > n) {
                even.release();
                odd.release();
                break;
            }

            printNumber.accept((int) 0);
            if(x.get() % 2 == 0) even.release();
            else odd.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while(x.get() <= n) {
            even.acquire();

            if(x.get() > n) break;

            printNumber.accept((int) x.get());
            x.incrementAndGet();
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while(x.get() <= n) {
            odd.acquire();

            if(x.get() > n) break;

            printNumber.accept((int) x.get());
            x.incrementAndGet();
            zero.release();
        }
    }
}