package ex05;
public class Program {
  public static void main(String[] args) {
    Boolean dev = false;
    if (args.length != 0) {
      if (args[0].equals("--profile=dev"))
        dev = true;
    }
    Menu m = new Menu(dev);
    while (true) {
      m.Print();
      try {
        m.Input();
      } catch (Exception e) {
      }
    }
  }
}