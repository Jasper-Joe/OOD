import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank("Bank of Drausin");
        User user = bank.addUser("John", "Doe", "1234");
        Account newAccount = new Account("Checking", user, bank);
        user.addAccount(newAccount);
        bank.addAccount(newAccount);
        User curUser;
        while (true) {
            curUser = ATM.mainMenuPromp(bank, sc);
            ATM.printUserMenu(curUser, sc);
        }
    }

    public static User mainMenuPromp(Bank bank, Scanner sc) {
        String userId;
        String pin;
        User authUser;
        do {
            System.out.println("Enter user ID: ");
            userId = sc.nextLine();
            System.out.println("Enter pin: ");
            pin = sc.nextLine();
            authUser = bank.userLogin(userId, pin);
            if (authUser == null) {
                System.out.println("Incorrect. Please try again.");
            }
        } while(authUser == null);
        return authUser;
    }

    public static void printUserMenu(User user, Scanner sc) {
        user.printAccountsSummary();

        int choice;
        do {
            System.out.println(" 1) Show account transaction history");
            System.out.println(" 2) Withdrawl");
            System.out.println(" 3) Deposit");
            System.out.println(" 4) Transfer");
            System.out.println(" 5) Quit");
            System.out.println(" Enter choice: ");
            choice = sc.nextInt();

            if(choice < 1 || choice > 5) {
                System.out.println(" Invalid choice");
            }
        } while (choice < 1 || choice > 5);

        switch (choice) {
            case 1:
                ATM.showTransHistory(user, sc);
                break;
            case 2:
                ATM.withdrawFunds(user, sc);
                break;
            case 3:
                ATM.depositFunds(user, sc);
                break;
            case 4:
                ATM.transferFunds(user, sc);
                break;

            case 5:
                sc.nextLine();
                break;

        }
        if (choice != 5) {
            ATM.printUserMenu(user, sc);

        }
    }

    public static void showTransHistory(User user, Scanner sc) {
        int theAcct;
        do {
            System.out.printf("Enter the number (1-%d) of the account whose transactions you want to see: ",
                    user.numAccounts());
            theAcct = sc.nextInt() - 1;
            if(theAcct < 0 || theAcct >= user.numAccounts()) {
                System.out.println("Invalid account");;
            }
        } while (theAcct < 0 || theAcct >= user.numAccounts());
        user.printAcctTransHistory(theAcct);
    }

    public static void transferFunds(User user, Scanner sc) {
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        do {
            System.out.printf("Enter the number (1-%d of the account to transfer from: ", user.numAccounts());
            fromAcct = sc.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= user.numAccounts()) {
                System.out.println("Invalid");
            }
        } while (fromAcct < 0 || fromAcct >= user.numAccounts());

        acctBal = user.getAcctBalance(fromAcct);

        do {
            System.out.printf("Enter the number (1-%d of the account to transfer to: ");
            toAcct = sc.nextInt() - 1;
            if (toAcct < 0 || toAcct >= user.numAccounts()) {
                System.out.println("Invalid");
            }
        } while (toAcct < 0 || toAcct >= user.numAccounts());

        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $",
                    acctBal);
            amount = sc.nextDouble();
            if(amount < 0) {
                System.out.println("Amount must be greater than zero");
            } else if(amount > acctBal) {
                System.out.println("Amount must not be greater than balance");
            }
        } while (amount < 0 || amount > acctBal);

        user.addAcctTransaction(fromAcct, -1 * amount, String.format("Transfer to account %s", user.getAcctUUID(toAcct)));
        user.addAcctTransaction(fromAcct, amount, String.format("Transfer to account %s", user.getAcctUUID(fromAcct)));

    }

    public static void withdrawFunds(User user, Scanner sc) {
        int fromAcct;
        double amount;
        double acctBal;
        String memo;

        do {
            System.out.printf("Enter the number (1-%d of the account to transfer from: ", user.numAccounts());
            fromAcct = sc.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= user.numAccounts()) {
                System.out.println("Invalid");
            }
        } while (fromAcct < 0 || fromAcct >= user.numAccounts());

        acctBal = user.getAcctBalance(fromAcct);

        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $",
                    acctBal);
            amount = sc.nextDouble();
            if(amount < 0) {
                System.out.println("Amount must be greater than zero");
            } else if(amount > acctBal) {
                System.out.println("Amount must not be greater than balance");
            }
        } while (amount < 0 || amount > acctBal);

        sc.nextLine();
        System.out.println("Enter a memo: ");
        memo = sc.nextLine();
        user.addAcctTransaction(fromAcct, -1 * amount, memo);


    }

    public static void depositFunds(User user, Scanner sc) {
        int toAcct;
        double amount;
        double acctBal;
        String memo;

        do {
            System.out.printf("Enter the number (1-%d of the account to transfer from: ", user.numAccounts());
            toAcct = sc.nextInt() - 1;
            if (toAcct < 0 || toAcct >= user.numAccounts()) {
                System.out.println("Invalid");
            }
        } while (toAcct < 0 || toAcct >= user.numAccounts());

        acctBal = user.getAcctBalance(toAcct);

        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $",
                    acctBal);
            amount = sc.nextDouble();
            if(amount < 0) {
                System.out.println("Amount must be greater than zero");
            }
        } while (amount < 0);

        sc.nextLine();
        System.out.println("Enter a memo: ");
        memo = sc.nextLine();
        user.addAcctTransaction(toAcct, amount, memo);

    }




















}
