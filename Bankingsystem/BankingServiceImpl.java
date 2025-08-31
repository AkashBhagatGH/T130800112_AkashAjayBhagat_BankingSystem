import java.util.*;

public class BankingServiceImpl implements BankingService {
    private Map<Integer, Customer> customers = new HashMap<>();
    private Map<Integer, Account> accounts = new HashMap<>();
    private Map<Integer, Transaction> transactions = new HashMap<>();
    private Map<Integer, Beneficiary> beneficiaries = new HashMap<>();
    
    @Override
    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerID(), customer);
    }
    
    @Override
    public void addAccount(Account account) {
        accounts.put(account.getAccountID(), account);
    }
    
    @Override
    public void addTransaction(Transaction transaction) {
        transactions.put(transaction.getTransactionID(), transaction);
        
        // Update account balance based on transaction type
        Account account = accounts.get(transaction.getAccountID());
        if (account != null) {
            if ("Deposit".equalsIgnoreCase(transaction.getType())) {
                account.setBalance(account.getBalance() + transaction.getAmount());
            } else if ("Withdrawal".equalsIgnoreCase(transaction.getType())) {
                if (account.getBalance() >= transaction.getAmount()) {
                    account.setBalance(account.getBalance() - transaction.getAmount());
                } else {
                    System.out.println("Insufficient balance for withdrawal!");
                }
            }
        }
    }
    
    @Override
    public void addBeneficiary(Beneficiary beneficiary) {
        beneficiaries.put(beneficiary.getBeneficiaryID(), beneficiary);
    }
    
    @Override
    public Customer findCustomerById(int id) {
        return customers.get(id);
    }
    
    @Override
    public Account findAccountById(int id) {
        return accounts.get(id);
    }
    
    @Override
    public Transaction findTransactionById(int id) {
        return transactions.get(id);
    }
    
    @Override
    public Beneficiary findBeneficiaryById(int id) {
        return beneficiaries.get(id);
    }
    
    @Override
    public List<Account> getAccountsByCustomerId(int customerId) {
        List<Account> result = new ArrayList<>();
        for (Account account : accounts.values()) {
            if (account.getCustomerID() == customerId) {
                result.add(account);
            }
        }
        return result;
    }
    
    @Override
    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.getAccountID() == accountId) {
                result.add(transaction);
            }
        }
        return result;
    }
    
    @Override
    public List<Beneficiary> getBeneficiariesByCustomerId(int customerId) {
        List<Beneficiary> result = new ArrayList<>();
        for (Beneficiary beneficiary : beneficiaries.values()) {
            if (beneficiary.getCustomerID() == customerId) {
                result.add(beneficiary);
            }
        }
        return result;
    }
    
    @Override
    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }
    
    @Override
    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
    
    @Override
    public Collection<Transaction> getAllTransactions() {
        return transactions.values();
    }
    
    @Override
    public Collection<Beneficiary> getAllBeneficiaries() {
        return beneficiaries.values();
    }
}