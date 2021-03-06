import java.util.ArrayList;

public class Account {
    private String name;

    private double balance;

    private String uuid;

    private User holder;

    private ArrayList<Transaction> transactions;

    public Account(String name, User holder, Bank bank) {
        this.name = name;
        this.holder = holder;
        this.uuid = bank.getNewAccountUUID();
        this.transactions = new ArrayList<>();
    }

    public String getUUID() {
        return this.uuid;
    }
}
