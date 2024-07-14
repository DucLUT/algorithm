import edu.princeton.cs.algs4.Date;
public class Transactionn implements Comparable<Transactionn>{
    private double amount;
    private Date date;
    private String name;

    public Transactionn(String name, Date date, double amount){
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public String getName(){
        return this.name;
    }
    public String getDate(){
        return this.date.toString();
    }
    public double getAmount(){
        return this.amount;
    }
    @Override
    public String toString(){
        return String.format("Transaction[customer=%s, date=%s, amount=%.2f]", name, date.toString(), amount);
    }
    public int compareTo(Transactionn that){
        if (amount > that.amount){
            return 1;
         }
        if (amount < that.amount){
            return -1;
         }
        return 0;
    }
    @Override
    public boolean equals(Object obj) {
        // Check for self-comparison
        if (this == obj) return true;
    
        // Check for null and ensure the object is of the same class
        if (obj == null || getClass() != obj.getClass()) return false;
    
        // Cast the object to Transactionn
        Transactionn that = (Transactionn) obj;
    
        // Check each field for equality
        return Double.compare(this.amount, that.amount) == 0
                && this.name.equals(that.name)
                && this.date.equals(that.date);
    }

    public static void main(String[] args) {
        // Create sample Transactionn objects
        Transactionn transaction1 = new Transactionn("Alice", new Date(1, 1, 2020), 100.0);
        Transactionn transaction2 = new Transactionn("Alice", new Date(1, 1, 2020), 100.0);
        Transactionn transaction3 = new Transactionn("Bob", new Date(2, 2, 2020), 200.0);
    
        // Test equals method
        System.out.println("Transaction1 equals Transaction2: " + transaction1.equals(transaction2)); // Expected: true
        System.out.println("Transaction1 equals Transaction3: " + transaction1.equals(transaction3)); // Expected: false
    
        // Test compareTo method
        System.out.println("Transaction1 compared to Transaction2: " + transaction1.compareTo(transaction2)); // Expected: 0
        System.out.println("Transaction1 compared to Transaction3: " + transaction1.compareTo(transaction3)); // Expected: -1 or 1 depending on the comparison logic
    }

}