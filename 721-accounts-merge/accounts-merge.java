class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int accountListSize = accounts.size();
        DSU dsu = new DSU(accountListSize);
        
        // Maps email to their component index
        Map<String, Integer> emailGroup = new HashMap<>();
        
        for (int i = 0; i < accountListSize; i++) {
            int accountSize = accounts.get(i).size();
            
            for (int j = 1; j < accountSize; j++) {
                String email = accounts.get(i).get(j);
                String accountName = accounts.get(i).get(0);
                
                // If this is the first time seeing this email then
                // assign component group as the account index
                if (!emailGroup.containsKey(email)) {
                    emailGroup.put(email, i);
                } else {
                    // If we have seen this email before then union this
                    // group with the previous group of the email
                    dsu.union(i, emailGroup.get(email));
                }
            }
        }

        // Store emails corresponding to the component's representative
        Map<Integer, List<String>> components = new HashMap<Integer, List<String>>();
        for (String email : emailGroup.keySet()) {
            int group = emailGroup.get(email);
            int groupRep = dsu.find(group);
            
            if (!components.containsKey(groupRep)) {
                components.put(groupRep, new ArrayList<String>());
            }
            
            components.get(groupRep).add(email);
        }

        // Sort the components and add the account name
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (int group : components.keySet()) {
            List<String> component = components.get(group);
            Collections.sort(component); 
            component.add(0, accounts.get(group).get(0));
            mergedAccounts.add(component);
        }
        
        return mergedAccounts;
    }
}

class DSU{
    int[] repr;
    int[] rank;
    DSU(int n){
        repr = new int[n];
        rank = new int[n];

        for(int i=0;i<n;i++){
            repr[i] = i;
            rank[i] = 1;
        }
    }

    int find(int x){
        if(x==repr[x]) return x;
        return repr[x] = find(repr[x]);
    }

    void union(int a, int b){
        int repra = find(a);
        int reprb = find(b);
        if(repra==reprb){
            return;
        }

        if(rank[repra] >= rank[reprb]){
            repr[reprb] = repra;
            rank[repra] += rank[reprb];
        }else{
            repr[repra] = reprb;
            rank[reprb] += rank[repra];
        }
    }
}