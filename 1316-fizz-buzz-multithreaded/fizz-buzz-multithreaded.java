class FizzBuzz {
    private int n;
    private int current = 1;
    private final Object lock = new Object();
    
    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (current <= n && (current % 3 != 0 || current % 5 == 0)) {
                    lock.wait();
                }
                if (current > n) break;
                printFizz.run();
                current++;
                lock.notifyAll();
            }
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (current <= n && (current % 5 != 0 || current % 3 == 0)) {
                    lock.wait();
                }
                if (current > n) break;
                printBuzz.run();
                current++;
                lock.notifyAll();
            }
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (current <= n && current % 15 != 0) {
                    lock.wait();
                }
                if (current > n) break;
                printFizzBuzz.run();
                current++;
                lock.notifyAll();
            }
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (current <= n && (current % 3 == 0 || current % 5 == 0)) {
                    lock.wait();
                }
                if (current > n) break;
                printNumber.accept(current);
                current++;
                lock.notifyAll();
            }
        }
    }
}