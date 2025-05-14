class Logger {
    HashMap<String, Integer> lastTime;

    public Logger() {
        lastTime = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (timestamp - lastTime.getOrDefault(message, -100) < 10)
            return false;
        lastTime.put(message, timestamp);
        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */