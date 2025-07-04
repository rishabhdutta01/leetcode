class Foo {
    boolean calledFirst = false;
    boolean calledSecond = false;
    boolean calledThird = false;
    ReentrantLock lock;

    public Foo() {
        lock = new ReentrantLock();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        calledFirst = true;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (!calledFirst) {
            try {
                if (lock.tryLock() || lock.tryLock(1, TimeUnit.SECONDS)){
                    try {
                        
                    } finally {
                        lock.unlock();
                    }
                }
            }finally {
            }
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        calledSecond = true;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (!calledSecond) {
            try {
                if (lock.tryLock() || lock.tryLock(1, TimeUnit.SECONDS)) {
                    try {

                    } finally {
                        lock.unlock();
                    }
                }
            } finally {
            }
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        calledThird = true;
    }
}