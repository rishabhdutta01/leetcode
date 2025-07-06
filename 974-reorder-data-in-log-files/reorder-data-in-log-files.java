class Solution {
    public String[] reorderLogFiles(String[] logs) {
        List<String> letters = new ArrayList<String>();
        List<String> digits = new ArrayList<String>();

        for(String s: logs){
            String[] arr = s.split(" ");
            if(Character.isDigit(arr[1].charAt(0))){
                digits.add(s);
            } else{
                letters.add(s);
            }
        }

        Collections.sort(letters, (a,b) -> {
            String sa = a.substring(a.indexOf(" ") + 1);
            String sb = b.substring(b.indexOf(" ") + 1);
            int comp = sa.compareTo(sb);
            if(comp!=0) return comp;

            String ida = a.substring(0, a.indexOf(" "));
            String idb = b.substring(0, b.indexOf(" "));
            return ida.compareTo(idb);
        });

        String[] res = new String[logs.length];
        int i=0;
        for(String s: letters){
            res[i++] = s;
        }
        for(String s: digits){
            res[i++] = s;
        }
        return res;
    }
}