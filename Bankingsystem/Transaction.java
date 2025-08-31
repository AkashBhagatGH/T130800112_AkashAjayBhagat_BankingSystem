import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private static int transactionCounter = 1;
    private int transactionID;
    private int accountID;
    private String type;
    private double amount;
    private LocalDateTime timestamp;
    
    public Transaction(int accountID, String type, double amount) {
        this.transactionID = transactionCounter++;
        this.accountID = accountID;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters and Setters
    public int getTransactionID() {
        return transactionID;
    }
    
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
    
    public int getAccountID() {
        return accountID;
    }
    
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return "Transaction ID: " + transactionID + ", Type: " + type + 
               ", Amount: " + amount + ", Timestamp: " + timestamp;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transaction transaction = (Transaction) obj;
        return transactionID == transaction.transactionID;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(transactionID);
    }
}
