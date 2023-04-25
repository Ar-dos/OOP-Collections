package ex05;

import java.util.UUID;

class Transaction {
  UUID Identifier;
  User Recipient;
  User Sender;
  Boolean Transfer;
  Integer Amount;

  public Transaction(User Rec, User Sen, Boolean Trans, Integer Am) {
    this.Recipient = Rec;
    this.Sender = Sen;
    this.Transfer = Trans;
    this.SetAmount(Am);
    this.Identifier = UUID.randomUUID();
  }

  public Transaction(User Rec, User Sen, Boolean Trans, Integer Am, UUID Id) {
    this.Recipient = Rec;
    this.Sender = Sen;
    this.Transfer = Trans;
    this.SetAmount(Am);
    this.Identifier = Id;
  }

  public int GetAmount() { return this.Amount; }

  public void SetAmount(int Am) {
    if (this.Transfer == true) {
      this.Amount = ((Am > 0) ? Am : 0);
    } else if (Transfer == false) {
      if (this.Sender.Balance > 0 && Am < 0)
        this.Amount = Am;
      else
        this.Amount = 0;
    }
  }

  public UUID getID() { return Identifier; }

  public void Print() {
    if (this.Transfer)
      System.out.printf("%s -> %s, %d, %s, %s\n", this.Sender.Name,
                        this.Recipient.Name, this.Amount, "OUTCOME",
                        this.Identifier.toString());
    else
      System.out.printf("%s -> %s, %d, %s, %s\n", this.Sender.Name,
                        this.Recipient.Name, this.Amount, "INCOME",
                        this.Identifier.toString());
  }
}