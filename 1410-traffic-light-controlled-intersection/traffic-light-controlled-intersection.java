class TrafficLight {
    private final Object lock = new Object();
    private volatile boolean road1Green = true; // Road A starts green
    
    public void carArrived(
            int carId,
            int roadId,
            int direction,
            Runnable turnGreen,
            Runnable crossCar
    ) {

            synchronized (lock) {
                // If car is from the road that doesn't currently have green light
                if ((roadId == 1 && !road1Green) || (roadId == 2 && road1Green)) {
                    // Switch the light to this road
                    road1Green = !road1Green;
                    turnGreen.run();
                }
                
                // Car crosses the intersection
                crossCar.run();
            }
        
    }
}