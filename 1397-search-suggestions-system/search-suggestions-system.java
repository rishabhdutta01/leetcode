class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        
        List<List<String>> res = new ArrayList<>();
        List<String> p = Arrays.asList(products);
        for(int i=0;i<searchWord.length();i++){
            List<String> newp = new ArrayList<>();
            for(int j=0;j<p.size();j++){
                if(i>=p.get(j).length()){
                    continue;
                }
                if(searchWord.charAt(i) == p.get(j).charAt(i)){
                    newp.add(p.get(j));
                }
            }
            p = newp;

            res.add(p.subList(0, Math.min(3, p.size())));
        }
        return res;
    }
}