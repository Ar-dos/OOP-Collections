package ex05;

import java.util.Scanner;
import java.util.UUID;

class Menu {
  TransactionsService service;
  Boolean dev = false;

  public Menu(boolean b) {
    dev = b;
    service = new TransactionsService();
  }

  public void Print() {
    System.out.println("1. Add a user");
    System.out.println("2. View user balances");
    System.out.println("3. Perform a transfer");
    System.out.println("4. View all transactions for a specific user");
    if (dev) {
      System.out.println("5. DEV – remove a transfer by ID");
      System.out.println("6. DEV – check transfer validity");
      System.out.println("7. Finish execution");
    } else {
      System.out.println("5. Finish execution");
    }
  }

  public void Input() {
    Scanner in = new Scanner(System.in);
    int inp = in.nextInt();
    in.nextLine();
    if (inp == 1) {
      System.out.println("Enter a user name and a balance");
      String input = in.nextLine();
      this.addUser(input);
    } else if (inp == 2) {
      System.out.println("Enter a user ID");
      int input = in.nextInt();
      in.nextLine();
      this.viewUserBalance(input);
    } else if (inp == 3) {
      System.out.println(
          "Enter a sender ID, a recipient ID, and a transfer amount");
      String input = in.nextLine();
      this.performTransfer(input);
    } else if (inp == 4) {
      System.out.println("Enter a user ID");
      int input = in.nextInt();
      in.nextLine();
      this.viewUserTrans(input);
    } else if (inp == 5 && dev) {
      System.out.println("Enter a user ID and a transfer ID");
      String input = in.nextLine();
      this.removeTrans(input);
    } else if (inp == 5 && !dev) {
      in.close();
      System.exit(0);
    } else if (inp == 6 && dev) {
      this.checkTrans();
    } else if (inp == 7 && dev) {
      in.close();
      System.exit(0);
    }
  }

  private void addUser(String input) {
    String[] split = input.split("\\s+");
    System.out.printf(
        "User with id = %d is added\n",
        service.add(new User(split[0], Integer.parseInt(split[1]))));
    System.out.println(
        "---------------------------------------------------------");
  }

  private void viewUserBalance(int input) {

    System.out.printf("%s - %d\n", service.list.getUserById(input).Name,
                      service.getBalance(input));
    System.out.println(
        "---------------------------------------------------------");
  }

  private void performTransfer(String input) {
    String[] split = input.split("\\s+");
    service.addTrans(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
                     Integer.parseInt(split[2]));
    System.out.println("The transfer is completed");
    System.out.println(
        "---------------------------------------------------------");
  }

  private void viewUserTrans(int input) {
    Transaction[] temp = service.list.getUserById(input).Transactions.toArray();
    for (int i = 0; i < temp.length; i++) {
      User rec = service.list.getUserById(temp[i].Recipient.GetId());
      System.out.printf("To %s(id = %d) %d with id = %s\n", rec.Name,
                        rec.GetId(), temp[i].Amount,
                        temp[i].Identifier.toString());
    }
    System.out.println(
        "---------------------------------------------------------");
  }

  private void removeTrans(String input) {

    String[] split = input.split("\\s+");
    Transaction res = service.list.getUserById(Integer.parseInt(split[0]))
                          .Transactions.removeById(UUID.fromString(split[1]));
    User rec = service.list.getUserById(res.Recipient.GetId());
    System.out.printf("Transfer To %s(id = %d) %d removed\n", rec.Name,
                      rec.GetId(), Math.abs(res.Amount));
    System.out.println(
        "---------------------------------------------------------");
  }

  private void checkTrans() {
    System.out.println("Check results:");
    Transaction[] temp = service.checkValidity();
    for (int i = 0; i < temp.length; i++) {
      System.out.printf(
          "%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %d\n",
          temp[i].Sender.Name, temp[i].Sender.GetId(),
          temp[i].Identifier.toString(), temp[i].Recipient.Name,
          temp[i].Recipient.GetId(), temp[i].Amount);
    }
  }
}