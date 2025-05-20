class UndergroundSystem {
    Map<Integer, Pair<String, Integer>> checkin;
    Map<Pair<String, String>, Pair<Integer, Integer>> journey;
    public UndergroundSystem() {
        checkin = new HashMap<>();
        journey = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkin.put(id, new Pair<>(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        String startStation = checkin.get(id).getKey();
        Integer startTime = checkin.get(id).getValue();

        Pair<String, String> journey_pair  = new Pair<>(startStation,stationName); 
        if(!journey.containsKey(journey_pair)){
            journey.put(journey_pair, new Pair<>(t - startTime, 1));
        } else{
            Pair<Integer, Integer> stat  = journey.get(journey_pair); 
            journey.put(journey_pair, new Pair<>(stat.getKey() + t - startTime, stat.getValue() + 1));
        }        
    }
    
    public double getAverageTime(String startStation, String endStation) {
        Pair<Integer, Integer> stat  = journey.get(new Pair<>(startStation,endStation));
        return (double)stat.getKey()/stat.getValue();
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */

//  class UndergroundSystem {
//     private Map<String, Pair<Double, Double>> journeyData = new HashMap<>();
//     private Map<Integer, Pair<String, Integer>> checkInData = new HashMap<>();
    
//     public UndergroundSystem() {
//     }
    
//     public void checkIn(int id, String stationName, int t) {
//         checkInData.put(id, new Pair<>(stationName, t));
//     }
    
//     public void checkOut(int id, String stationName, int t) {
//         // Look up the check in station and check in time for this id.
//         // You could combine this "unpacking" into the other lines of code
//         // to have less lines of code overall, but we've chosen to be verbose
//         // here to make it easy for all learners to follow.
//         Pair<String, Integer> checkInDataForId = checkInData.get(id);
//         String startStation = checkInDataForId.getKey();
//         Integer checkInTime = checkInDataForId.getValue();
        
//         // Lookup the current travel time data for this route.
//         String routeKey = stationsKey(startStation, stationName);
//         Pair<Double, Double> routeStats  = journeyData.getOrDefault(routeKey, new Pair<>(0.0, 0.0));
//         Double totalTripTime = routeStats.getKey();
//         Double totalTrips = routeStats.getValue();
        
//         // Update the travel time data with this trip.
//         double tripTime = t - checkInTime;
//         journeyData.put(routeKey, new Pair<>(totalTripTime + tripTime, totalTrips + 1));
        
//         // Remove check in data for this id.
//         // Note that this is optional, we'll talk about it in the space complexity analysis.
//         checkInData.remove(id);
//     }
    
//     public double getAverageTime(String startStation, String endStation) {
//         // Lookup how many times this journey has been made, and the total time.
//         String routeKey = stationsKey(startStation, endStation);
//         Double totalTime = journeyData.get(routeKey).getKey();
//         Double totalTrips = journeyData.get(routeKey).getValue();
//         // The average is simply the total divided by the number of trips.
//         return totalTime / totalTrips;
//     }
    
//     private String stationsKey(String startStation, String endStation) {
//         return startStation + "->" + endStation;
//     }
// }