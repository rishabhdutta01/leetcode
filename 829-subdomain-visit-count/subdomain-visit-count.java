class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for(String s: cpdomains){
            String[] sarr = s.split(" ");
            int cnt = Integer.parseInt(sarr[0]);

            map.put(sarr[1], map.getOrDefault(sarr[1], 0) + cnt);

            int idx = sarr[1].indexOf(".");
            String d = sarr[1].substring(idx + 1);
            while(idx != -1){
                map.put(d, map.getOrDefault(d, 0) + cnt);
                idx = d.indexOf(".");
                d = d.substring(idx + 1);
            }
        }

        List<String> res = new ArrayList<>();

        for(Map.Entry<String, Integer> e: map.entrySet()){
            res.add(e.getValue() + " " + e.getKey());
        }
        return res;
    }
}