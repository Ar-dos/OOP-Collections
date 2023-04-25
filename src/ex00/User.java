package ex00;

class User {
  private Integer Identifier;
  String Name;
  Integer Balance;

  public User(String name, Integer bal, Integer id) {
    this.Name = name;
    this.Balance = bal;
    this.Identifier = id;
  }

  public void Print() {
    System.out.printf("%d, %s, %d\n", this.Identifier, this.Name, this.Balance);
  }

  public int GetId() { return this.Identifier; }

  public void addTransaction(Transaction t) {
    this.Balance = this.Balance + t.GetAmount();
  }
}