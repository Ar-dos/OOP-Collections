package ex04;

class User {
  private Integer Identifier;
  String Name;
  Integer Balance;
  public TransactionsList Transactions = new TransactionsLinkedList();

  public User(String name, Integer bal) {
    this.Name = name;
    this.Balance = bal;
    if (name == null && bal == null)
      this.Identifier = null;
    else
      this.Identifier = UserIdsGenerator.getInstance().generateId();
  }

  public void Print() {
    System.out.printf("%d, %s, %d\n", this.Identifier, this.Name, this.Balance);
  }

  public int GetId() { return this.Identifier; }

  public void addTransaction(Transaction t) {
    this.Balance = this.Balance + t.GetAmount();
    Transactions.add(t);
  }
}