class H2O {
    Semaphore o;
    Semaphore h;
    AtomicInteger x;

    public H2O() {
        x = new AtomicInteger(0);
        h = new Semaphore(2);
        o = new Semaphore(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        x.incrementAndGet();
        if(x.get() == 2) o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
		releaseOxygen.run();
        x.set(0);
        h.release(2);
    }
}