
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShootTheAccountPlusNoSmell {
  private int balance = 0;
  private List<Transaction> ListOfAllTransactions = new ArrayList<Transaction>();
  private String lastDebitTime;
  private String lastCreditTime;
  private String accountType;

  public ShootTheAccountPlusNoSmell() {
  }

  public ShootTheAccountPlusNoSmell(int balance) {
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
    if (!exceedPersonalLimit()) {
      //reduce the amount
      deposit(-amount);

      //add to the transaction list
      addTransaction("debit", amount);

      //update the last debit date
      lastDebitTime = updateTransactionTime();
    }
  }

  // this method has a long method smell
  public void Transfer(int amount, ShootTheAccountPlusNoSmell Benf) {

    if (!exceedPersonalLimit()) {
      this.DebitTransaction(amount);

      Benf.CreditTransaction(amount);
    }
  }

  private void CreditTransaction(int amount) {
    //reduce the amount
    deposit(amount);

    //add to the transaction list
    addTransaction("credit", amount);

    //update the last credit date
    lastCreditTime = updateTransactionTime();
  }

  public void sendWarning() {
    if (exceedPersonalLimit())
      System.out.println("Balance must be more than 500, please deposit");
  }

  private Boolean exceedPersonalLimit() {
    return balance < 500 && accountType == "personal";
  }
}
