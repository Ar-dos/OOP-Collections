package ex03;
import java.util.UUID;

class TransactionsLinkedList implements TransactionsList {

  UserList list = new UsersArrayList();

  public void add(Transaction t) {
    if (this.first == null) {
      this.first = new Node(null, t, null);
      this.last = this.first;
      size++;
    } else {
      this.last.next = new Node(this.last, t, null);
      this.last = this.last.next;
      size++;
    }
  }
  public Transaction removeById(UUID Id) {
    Node curr = this.first;
    Transaction target = null;
    while (curr != null) {
      if (curr.item.Identifier.equals(Id)) {
        target = curr.item;
        break;
      }
      curr = curr.next;
    }
    if (target == null)
      throw new TransactionNotFoundException("Transaction not found.");
    if (curr != null) {
      curr.next.prev = curr.prev;
      curr.prev.next = curr.next;
      curr.item = null;
      curr.next = null;
      curr.prev = null;
      size--;
    }
    return target;
  }

  public Transaction searchById(UUID Id) {
    Node curr = this.first;
    Transaction target = null;
    while (curr != null) {
      if (curr.item.Identifier.equals(Id)) {
        target = curr.item;
        break;
      }
      curr = curr.next;
    }
    return target;
  }

  public Transaction[] toArray() {
    Transaction[] res = null;
    if (size != 0) {
      res = new Transaction[size];
      Node curr = this.first;
      for (int i = 0; i < size; i++) {
        res[i] = curr.item;
        curr = curr.next;
      }
    }
    return res;
  }

  public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String errorMessage) {
      super(errorMessage);
    }
  }

  transient int size = 0;
  transient Node first;
  transient Node last;

  public TransactionsLinkedList() {
    size = 0;
    first = null;
    last = null;
  }

  private static class Node {
    Transaction item;
    Node next;
    Node prev;

    Node(Node prev, Transaction element, Node next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }
}