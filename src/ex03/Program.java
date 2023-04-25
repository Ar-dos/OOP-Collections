package ex03;
public class Program {
  public static void main(String[] args) {
    UserList list = new UsersArrayList();
    list.add(new User("John", 777));
    list.add(new User("Mike", 100));
    list.getUserByIndex(0).Print();
    list.getUserByIndex(1).Print();
    list.getUserByIndex(0).Transactions.add(new Transaction(
        list.getUserByIndex(0), list.getUserByIndex(1), true, 100));
    list.getUserByIndex(1).Transactions.add(new Transaction(
        list.getUserByIndex(1), list.getUserByIndex(0), false, -100));
    list.getUserByIndex(0).Transactions.toArray()[0].Print();
    list.getUserByIndex(1).Transactions.toArray()[0].Print();
  }
}