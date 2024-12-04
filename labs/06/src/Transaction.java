public class Transaction implements Runnable {
    private Account account;
    private String type;
    private int amount;

    public Transaction(Account account, String type, int amount) {
        this.account = account;
        this.type = type;
        this.amount = amount;
    }

    public void run() {
        if (type.equals("deposit")) {
            account.deposit(amount);
        } else if (type.equals("withdraw")) {
            account.withdraw(amount);
        }
    }

}
