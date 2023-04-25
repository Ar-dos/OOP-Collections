package ex05;
import java.util.ArrayList;
import java.util.UUID;

class TransactionsService {
  UserList list = new UsersArrayList();

  public int add(User u) { return list.add(u); }

  public int getBalance(int userId) { return list.getUserById(userId).Balance; }

  public void addTrans(int SenderId, int RecipientId, int am) {
    if (list.getUserById(SenderId).Balance < am)
      throw new IllegalTransactionException("Illegal Transaction.");
    UUID id = UUID.randomUUID();
    list.getUserById(SenderId).addTransaction(
        new Transaction(list.getUserById(RecipientId),
                        list.getUserById(SenderId), false, am * (-1), id));

    list.getUserById(RecipientId)
        .addTransaction(new Transaction(list.getUserById(SenderId),
                                        list.getUserById(RecipientId), true, am,
                                        id));
  }

  public Transaction[] getTransactionsAsArray(int UserId) {
    return list.getUserById(UserId).Transactions.toArray();
  }

  public Transaction removeTransactionById(UUID transactionId, int userId) {
    return list.getUserById(userId).Transactions.removeById(transactionId);
  }

  public Transaction[] checkValidity() {
    ArrayList<Transaction> temp = new ArrayList<Transaction>();
    for (int i = 0; i < list.getNumberOfUsers(); i++) {
      for (int j = 0; j < list.getUserByIndex(i).Transactions.toArray().length;
           j++) {
        UUID tranId =
            list.getUserByIndex(i).Transactions.toArray()[j].Identifier;
        int recId =
            list.getUserByIndex(i).Transactions.toArray()[j].Recipient.GetId();
        if (list.getUserById(recId).Transactions.searchById(tranId) == null)
          temp.add(list.getUserByIndex(i).Transactions.toArray()[j]);
      }
    }
    Transaction[] res = new Transaction[temp.size()];
    for (int i = 0; i < temp.size(); i++)
      res[i] = temp.get(i);
    return res;
  }
  public class IllegalTransactionException extends RuntimeException {
    public IllegalTransactionException(String errorMessage) {
      super(errorMessage);
    }
  }
}