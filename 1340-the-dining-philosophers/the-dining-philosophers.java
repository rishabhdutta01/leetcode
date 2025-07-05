class DiningPhilosophers {
    Semaphore[] arr;
    Object lock;

    public DiningPhilosophers() {
        lock = new Object();
        arr = new Semaphore[5];
        arr[0] = new Semaphore(1);
        arr[1] = new Semaphore(1);
        arr[2] = new Semaphore(1);
        arr[3] = new Semaphore(1);
        arr[4] = new Semaphore(1);
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
            Runnable pickLeftFork,
            Runnable pickRightFork,
            Runnable eat,
            Runnable putLeftFork,
            Runnable putRightFork) throws InterruptedException {
        synchronized (lock) {
            while (!(arr[philosopher].tryAcquire(1L, TimeUnit.SECONDS)
                    && arr[(philosopher + 1) % 5].tryAcquire(1L, TimeUnit.SECONDS))) {
                arr[philosopher].release();
                arr[(philosopher + 1) % 5].release();
                lock.wait();
            }
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
            arr[philosopher].release();
            arr[(philosopher + 1) % 5].release();
            lock.notifyAll();
        }
    }
}