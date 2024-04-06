public class BankAccount {
    private int uniqueNumber;
    private int balance;

    public BankAccount(int balance) {
        uniqueNumber = (int) (Math.random() * 1000);
        this.balance = balance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) throws InsufficientFundsException {
        if (balance < amount) {
            throw new InsufficientFundsException("Недостаточно средств на счёте.");
        } else {
            balance -= amount;
        }
    }

    public synchronized int getBalance() {
        return balance;
    }
}
