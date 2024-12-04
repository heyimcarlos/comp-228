import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AccountTest {

    public static void main(String[] args) {
        // account
        Account account = new Account(100000);

        // transactions
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(account, "deposit", 15000));
        transactions.add(new Transaction(account, "withdraw", 25000));
        transactions.add(new Transaction(account, "withdraw", 35000));
        transactions.add(new Transaction(account, "deposit", 10000));

        ExecutorService executor = Executors.newCachedThreadPool();
        try {
            for (Transaction transaction : transactions) {
                executor.execute(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                    if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                        System.err.println("Executor did not terminate correctly");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        System.out.printf("Final Balance: %.2f\n", account.getBalance());
    }

}
