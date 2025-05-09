// class Solution {
//     class Node {
//         Character ch;
//         Map<Character, Node> children;
//         String word;

//         Node(Character ch) {
//             this.ch = ch;
//             children = new TreeMap<>();
//         }
//     }

//     public List<List<String>> suggestedProducts(String[] products, String searchWord) {
//         //build trie
//         Node root = new Node('/');
//         for (String p : products) {
//             Node curr = root;
//             for (int i = 0; i < p.length(); i++) {
//                 Character ch = p.charAt(i);
//                 if (!curr.children.containsKey(ch)) {
//                     curr.children.put(ch, new Node(ch));
//                 }
//                 curr = curr.children.get(ch);
//             }
//             curr.word = p;
//         }

//         List<List<String>> res = new ArrayList<>();
//         Node curr = root;
//         for (int i = 0; i < searchWord.length(); i++) {
//             char ch = searchWord.charAt(i);
//             List<String> arr = new ArrayList<>();
//             if (!curr.children.containsKey(ch)) {
//                 for(int j=i;j<searchWord.length(); j++){
//                     res.add(new ArrayList<>());
//                 }
//                 break;
//             }
//             curr = curr.children.get(ch);
//             dfs(curr, arr);
//             res.add(arr);
//         }
//         return res;
//     }

//     void dfs(Node curr, List<String> arr) {
//         if (arr.size() >= 3) { // Check size before doing anything
//             return;
//         }

//         if (curr.word != null) {
//             arr.add(curr.word);
//         }

//         for (Character ch : curr.children.keySet()) {
//             if (arr.size() >= 3)
//                 break; // Break the loop if we have 3 words
//             dfs(curr.children.get(ch), arr);
//         }
//     }
// }

// class Solution {
//     public List<List<String>> suggestedProducts(String[] products, String searchWord) {
//         Arrays.sort(products);

//         List<List<String>> res = new ArrayList<>();
//         List<String> p = Arrays.asList(products);
//         for(int i=0;i<searchWord.length();i++){
//             List<String> newp = new ArrayList<>();
//             for(int j=0;j<p.size();j++){
//                 if(i>=p.get(j).length()){
//                     continue;
//                 }
//                 if(searchWord.charAt(i) == p.get(j).charAt(i)){
//                     newp.add(p.get(j));
//                 }
//             }
//             p = newp;

//             res.add(p.subList(0, Math.min(3, p.size())));
//         }
//         return res;
//     }
// }

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        Arrays.sort(products);
        List<String> productsList = Arrays.asList(products);

        for (int i = 0; i < products.length; i++) {
            map.put(products[i], i);
        }


        StringBuilder key = new StringBuilder();
        for (char c : searchWord.toCharArray()) {
            key.append(c);
            String ceiling = map.ceilingKey(key.toString());
            String floor = map.floorKey(key.toString() + "~");
            if (ceiling == null || floor == null)
                break;
            res.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
        }

        while (res.size() < searchWord.length())
            res.add(new ArrayList<>());
        return res;
    }
}