package ex01;
public class Program {
  public static void main(String[] args) {
    User u = new User("John", 777);
    u.Print();
    User p = new User("Mike", 100);
    p.Print();
    Transaction t = new Transaction(u, p, true, 100);
    Transaction h = new Transaction(p, u, false, -100);
    t.Print();
    h.Print();
  }
}