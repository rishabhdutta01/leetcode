class Foo {
    private boolean firstDone, secondDone;

    public void first(Runnable printFirst) {
        printFirst.run();
        firstDone = true;
    }

    public void second(Runnable printSecond) {
        while (!firstDone) Thread.onSpinWait();
        printSecond.run();
        secondDone = true;
    }

    public void third(Runnable printThird) {
        while (!secondDone) Thread.onSpinWait();
        printThird.run();
    }
}