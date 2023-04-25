package ex04;

import java.util.UUID;

interface TransactionsList {
  void add(Transaction t);
  Transaction removeById(UUID Id);
  Transaction[] toArray();
  Transaction searchById(UUID Id);
}