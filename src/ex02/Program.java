package ex02;
public class Program {
  public static void main(String[] args) {
    UserList list = new UsersArrayList();
    list.add(new User("John", 777));
    list.add(new User("Mike", 100));
    list.getUserByIndex(0).Print();
    list.getUserByIndex(1).Print();
    Transaction t = new Transaction(list.getUserByIndex(0),
                                    list.getUserByIndex(1), true, 100);
    Transaction h = new Transaction(list.getUserByIndex(1),
                                    list.getUserByIndex(0), false, -100);
    t.Print();
    h.Print();
  }
}