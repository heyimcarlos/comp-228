public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
        System.out.printf("Initial Balance: %.2f\n", centToDollar(balance));
    }

    public synchronized void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Deposited: %.2f, New Balance: %.2f\n", centToDollar(amount), centToDollar(balance));
        }
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount && amount > 0) {
            balance -= amount;
            System.out.printf("Withdrew: %.2f, New Balance: %.2f\n", centToDollar(amount), centToDollar(balance));
        }
        if (amount > balance) {
            System.out.println("Insufficient funds");
        }

    }

    public double centToDollar(int amount) {
        return amount / 100.0;
    }

    public double getBalance() {
        return centToDollar(balance);
    }
}
