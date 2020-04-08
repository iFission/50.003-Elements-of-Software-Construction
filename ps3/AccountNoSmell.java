import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class Transaction {
  private String type;
  private int amount;

  public Transaction(String newType, int newAmount) {
    this.type = newType;
    this.amount = newAmount;
  }
}

public class AccountNoSmell {
  private int balance = 0;
  private List<Transaction> ListOfAllTransactions = new ArrayList<Transaction>();
  private String lastDebitTime;
  private String lastCreditTime;

  public AccountNoSmell() {
  }

  public AccountNoSmell(int balance) {
    this.balance = balance;
  }

  public void deposit(int amount) {
    balance += amount;
  }

  public void setBalance(int amount) {
    balance = amount;
  }

  public int getBalance() {
    return balance;
  }

  public void addTransaction(String type, int amount) {
    ListOfAllTransactions.add(new Transaction(type, amount));
  }

  public String updateTransactionTime() {
    Calendar cal = Calendar.getInstance();
    return cal.get(Calendar.DATE) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR);
  }

  // this method has a long method smell
  public void DebitTransaction(int amount) {
    //reduce the amount
    deposit(-amount);

    //add to the transaction list
    addTransaction("debit", amount);

    //update the last debit date
    lastDebitTime = updateTransactionTime();
  }

  // this method has a long method smell
  public void CreditTransaction(int amount) {
    //reduce the amount
    deposit(amount);

    //add to the transaction list
    addTransaction("credit", amount);

    //update the last credit date
    lastCreditTime = updateTransactionTime();
  }

}
