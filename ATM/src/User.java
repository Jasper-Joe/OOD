import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {
    private String firstName;

    private String lastName;

    private String uuid;

    private ArrayList<Account> accounts;

    private byte pinHash[];

    public User(String firstName, String lastName, String pin, Bank bank) {
        this.firstName = firstName;
        this.lastName = lastName;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        this.uuid = bank.getNewUserUUID();
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getUUID() {
        return this.uuid;
    }

    public boolean validatePin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void printAccountsSummary() {
        System.out.println(this.firstName);
        for (int i = 0; i < this.accounts.size(); i++) {
            System.out.println(this.accounts.get(i).getSummaryLine());
        }

    }

    public int numAccounts() {
        return this.accounts.size();
    }

    public void printAcctTransHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }

    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }

    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
























}
