class FooBar {
    private int n;
    boolean calledFoo = false;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {      
            while(calledFoo){
                Thread.onSpinWait();
            }      
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();
            calledFoo = true;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            while(!calledFoo){
                Thread.onSpinWait();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
        	printBar.run();
            calledFoo = false;
        }
    }
}