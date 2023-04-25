package ex01;

class UserIdsGenerator {
  Integer Prev_id;

  private static final UserIdsGenerator instance = new UserIdsGenerator();

  private UserIdsGenerator() { Prev_id = 0; }

  public static UserIdsGenerator getInstance() { return instance; }

  int generateId() {
    Integer id = Prev_id + 1;
    this.Prev_id = id;
    return id;
  }
}