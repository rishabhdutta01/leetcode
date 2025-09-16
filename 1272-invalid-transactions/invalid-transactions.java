class Solution {
    public class Transaction {
        String name;
        int time;
        String city;
        String trans;
        
        public Transaction(String name, int time, String city, String trans) {
            this.name = name;
            this.time = time;
            this.city = city;
            this.trans = trans;
        }
    }
    
    public List<String> invalidTransactions(String[] transactions) {
        //We use set to avoid duplicates since we can reject the same transaction for multiple reasons
        Set<String> out = new HashSet<>();
        
        //We can have duplicate transactions in the intput.  We want to output multiple in that case.
        Map<String, Integer> sameTransactionCount = new HashMap<>();
        
        //We will keep the track of transactions per person.
        Map<String, List<Transaction>> perPerson = new HashMap<>();
        
        for(String trans : transactions) {
            //Update count
            sameTransactionCount.put(trans, sameTransactionCount.getOrDefault(trans, 0) + 1);

            //Extract data from transactions
            String[] split = trans.split(",");
            
            String name = split[0];
            int time = Integer.valueOf(split[1]);
            int amount = Integer.valueOf(split[2]);
            String city = split[3];
            
            if(amount > 1000) {
                //reject transaction because amount is too large
                out.add(trans);
            }
            
            List<Transaction> otherTransactions = perPerson.get(name);
            
            if(otherTransactions == null) {
                //first transaction for this person
                otherTransactions = new ArrayList<>();
                otherTransactions.add(new Transaction(name, time, city, trans));
                perPerson.put(name, otherTransactions);
            } else {
                for(Transaction transa : otherTransactions) {
                    if(!transa.city.equals(city) && Math.abs(transa.time - time) <= 60) {
                        //reject both transaction because they are in different cities within 60 minutes of each other
                        out.add(transa.trans);
                        out.add(trans);
                    }
                }
                
                otherTransactions.add(new Transaction(name, time, city, trans));
            }
        }
        
        List<String> output = new ArrayList<>();
        for(String trans : out) {
            for(int i = 0; i < sameTransactionCount.get(trans); i++) {
                output.add(trans);
            }
        }

        return output;
    }
}