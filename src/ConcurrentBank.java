import java.util.ArrayList;
import java.util.List;

public class ConcurrentBank {
    private List<BankAccount> accounts;

    public ConcurrentBank() {
        accounts = new ArrayList<>();
    }

    public synchronized BankAccount createAccount(int balance) {
        BankAccount account = new BankAccount(balance);
        accounts.add(account);
        return account;
    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, int amount){
        try {
            if (!accounts.contains(fromAccount) || !accounts.contains(toAccount)) {
                throw new InvalidAccountException("Счёта не найдены в системе.");
            } else if (fromAccount.getBalance() < amount) {
                throw new InsufficientFundsException("На счёте недостаточно средств.");
            } else {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized int getTotalBalance() {
        int totalBalance = 0;
        try {
            for (int i = 0; i < accounts.size(); i++) {
                totalBalance += accounts.get(i).getBalance();
            }
        } catch (Exception e) {
            totalBalance = 0;
        }
        return totalBalance;
    }
}