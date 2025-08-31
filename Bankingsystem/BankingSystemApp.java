import java.util.Scanner;
import java.util.List;
import java.util.Collection;

public class BankingSystemApp {
    private static BankingService bankingService = new BankingServiceImpl();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    addAccount();
                    break;
                case 3:
                    addBeneficiary();
                    break;
                case 4:
                    addTransaction();
                    break;
                case 5:
                    findCustomerById();
                    break;
                case 6:
                    listAccountsByCustomer();
                    break;
                case 7:
                    listTransactionsByAccount();
                    break;
                case 8:
                    listBeneficiariesByCustomer();
                    break;
                case 9:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    private static void displayMenu() {
        System.out.println("\nBanking System");
        System.out.println("1. Add Customers");
        System.out.println("2. Add Accounts");
        System.out.println("3. Add Beneficiary");
        System.out.println("4. Add Transaction");
        System.out.println("5. Find Customer by Id");
        System.out.println("6. List all Accounts of specific Customer");
        System.out.println("7. List all transactions of specific Account");
        System.out.println("8. List all beneficiaries of specific customer");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void addCustomer() {
        System.out.println("Enter Customer Details");
        System.out.print("Customer Id: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Address: ");
        String address = scanner.nextLine();
        
        System.out.print("Contact No.: ");
        String contact = scanner.nextLine();
        
        Customer customer = new Customer(customerId, name, address, contact);
        bankingService.addCustomer(customer);
        System.out.println("Customer added successfully!");
    }
    
    private static void addAccount() {
        System.out.println("Enter Account Details");
        System.out.print("Account Id: ");
        int accountId = scanner.nextInt();
        
        System.out.print("Customer Id: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Account Type Saving/ Current: ");
        String type = scanner.nextLine();
        
        System.out.print("Balance: ");
        double balance = scanner.nextDouble();
        
        Account account = new Account(accountId, customerId, type, balance);
        bankingService.addAccount(account);
        System.out.println("Account added successfully!");
    }
    
    private static void addBeneficiary() {
        System.out.println("Enter Beneficiary Details");
        System.out.print("Customer Id: ");
        int customerId = scanner.nextInt();
        
        System.out.print("Beneficiary Id: ");
        int beneficiaryId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Beneficiary Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Beneficiary Account No.: ");
        String accountNumber = scanner.nextLine();
        
        System.out.print("Beneficiary Bank details: ");
        String bankDetails = scanner.nextLine();
        
        Beneficiary beneficiary = new Beneficiary(beneficiaryId, customerId, 
                                                name, accountNumber, bankDetails);
        bankingService.addBeneficiary(beneficiary);
        System.out.println("Beneficiary added successfully!");
    }
    
    private static void addTransaction() {
        System.out.println("Enter Transaction Details");
        System.out.print("Account Id: ");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Type (Deposit/Withdrawal): ");
        String type = scanner.nextLine();
        
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        
        Transaction transaction = new Transaction(accountId, type, amount);
        bankingService.addTransaction(transaction);
        System.out.println("Transaction processed successfully!");
    }
    
    private static void findCustomerById() {
        // Display all customers first
        Collection<Customer> allCustomers = bankingService.getAllCustomers();
        for (Customer customer : allCustomers) {
            System.out.println("Customer ID: " + customer.getCustomerID() + 
                             ", Name: " + customer.getName());
        }
        
        System.out.print("Customer Id: ");
        int customerId = scanner.nextInt();
        
        Customer customer = bankingService.findCustomerById(customerId);
        if (customer != null) {
            System.out.println("Customer: " + customer.getName());
        } else {
            System.out.println("Customer not found!");
        }
    }
    
    private static void listAccountsByCustomer() {
        // Display all accounts first
        Collection<Account> allAccounts = bankingService.getAllAccounts();
        for (Account account : allAccounts) {
            System.out.println("Account ID: " + account.getAccountID() + 
                             ", Customer ID: " + account.getCustomerID() + 
                             ", Balance: " + account.getBalance());
        }
        
        System.out.print("Customer Id: ");
        int customerId = scanner.nextInt();
        
        List<Account> accounts = bankingService.getAccountsByCustomerId(customerId);
        System.out.println("Accounts for Customer ID: " + customerId);
        for (Account account : accounts) {
            System.out.println("Account ID: " + account.getAccountID() + 
                             ", Balance: " + account.getBalance());
        }
    }
    
    private static void listTransactionsByAccount() {
        System.out.print("Account Id: ");
        int accountId = scanner.nextInt();
        
        List<Transaction> transactions = bankingService.getTransactionsByAccountId(accountId);
        System.out.println("Transactions for Account ID: " + accountId);
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
    
    private static void listBeneficiariesByCustomer() {
        // Display all beneficiaries first
        Collection<Beneficiary> allBeneficiaries = bankingService.getAllBeneficiaries();
        for (Beneficiary beneficiary : allBeneficiaries) {
            System.out.println("Beneficiary ID: " + beneficiary.getBeneficiaryID() + 
                             ", Name: " + beneficiary.getName());
        }
        System.out.println("-------------------------------------");
        
        System.out.print("Customer Id: ");
        int customerId = scanner.nextInt();
        
        List<Beneficiary> beneficiaries = bankingService.getBeneficiariesByCustomerId(customerId);
        System.out.println("Beneficiaries for Customer ID: " + customerId);
        for (Beneficiary beneficiary : beneficiaries) {
            System.out.println("Beneficiary ID: " + beneficiary.getBeneficiaryID() + 
                             ", Name: " + beneficiary.getName());
        }
    }
}