package ex04;
public class Program {
  public static void main(String[] args) {
    TransactionsService service = new TransactionsService();
    service.list.add(new User("John", 777));
    service.list.add(new User("Mike", 100));
    service.list.getUserByIndex(0).Print();
    service.list.getUserByIndex(1).Print();
    service.list.getUserByIndex(0).Transactions.add(
        new Transaction(service.list.getUserByIndex(0),
                        service.list.getUserByIndex(1), true, 100));
    service.list.getUserByIndex(1).Transactions.add(
        new Transaction(service.list.getUserByIndex(1),
                        service.list.getUserByIndex(0), false, -100));
    service.list.getUserByIndex(0).Transactions.toArray()[0].Print();
    service.list.getUserByIndex(1).Transactions.toArray()[0].Print();
  }
}