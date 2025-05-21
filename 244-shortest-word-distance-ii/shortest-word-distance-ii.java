class WordDistance {

    HashMap<String, ArrayList<Integer>> locations;

    public WordDistance(String[] words) {
        this.locations = new HashMap<String, ArrayList<Integer>>();

        // Prepare a mapping from a word to all it's locations (indices).
        for (int i = 0; i < words.length; i++) {
            ArrayList<Integer> loc = this.locations.getOrDefault(words[i], new ArrayList<Integer>());
            loc.add(i);
            this.locations.put(words[i], loc);
        }
    }

    public int shortest(String word1, String word2) {
        ArrayList<Integer> loc1, loc2;

        // Location lists for both the words
        // the indices will be in SORTED order by default
        loc1 = this.locations.get(word1);
        loc2 = this.locations.get(word2);

        int l1 = 0, l2 = 0, minDiff = Integer.MAX_VALUE;
        while (l1 < loc1.size() && l2 < loc2.size()) {
            minDiff = Math.min(minDiff, Math.abs(loc1.get(l1) - loc2.get(l2)));
            if (loc1.get(l1) < loc2.get(l2)) {
                l1++;
            } else {
                l2++;
            }
        }

        return minDiff;
    }
}
// class WordDistance {
//     Map<String,List<Integer>> map;
//     String[] wordsDict;
//     public WordDistance(String[] wordsDict) {
//         this.wordsDict = wordsDict;
//         map = new HashMap<>();
//         for(int i=0;i<wordsDict.length;i++){
//             if(!map.containsKey(wordsDict[i])){
//                 map.put(wordsDict[i], new ArrayList<>());
//             }
//             map.get(wordsDict[i]).add(i);
//         }
//     }
    
//     public int shortest(String word1, String word2) {
//         List<Integer> s;
//         List<Integer> l;
//         if(map.get(word1).size() >= map.get(word2).size()){
//             l=map.get(word1);
//             s=map.get(word2);
//         } else{
//             l=map.get(word2);
//             s=map.get(word1);
//         }
        
//         int dist = Integer.MAX_VALUE;
//         for(int i: s){
//             int idx = Collections.binarySearch(l,i);

//             int lower = idx*-1 -2;
//             int lowerdiff =  lower<0 ? Integer.MAX_VALUE : Math.abs(i-l.get(lower));

//             int upper = idx*-1 -1;
//             int upperdiff = upper>=l.size() ? Integer.MAX_VALUE : Math.abs(i-l.get(upper));
//             dist = Math.min(dist, Math.min(lowerdiff, upperdiff));
//         }
//         return dist;
//     }
// }

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */