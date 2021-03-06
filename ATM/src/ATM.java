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
    }




















}
