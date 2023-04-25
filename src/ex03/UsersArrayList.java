package ex03;

import java.util.Arrays;

class UsersArrayList implements UserList {

  private int size_;
  private int cap_;
  private User[] arr_;

  public int add(User u) {
    if (size_ == cap_) {
      this.Extend();
    }
    this.arr_[size_] = u;
    this.size_++;
    return u.GetId();
  }

  public User getUserById(int Id) {
    Boolean check = true;
    User res = new User(null, null);
    for (int i = 0; i < size_; i++) {
      if (arr_[i].GetId() == Id) {
        check = false;
        res = arr_[i];
      }
    }
    if (check)
      throw new UserNotFoundException("User not found.");
    return res;
  }
  public User getUserByIndex(int ind) {
    if (!(ind > -1 && ind < size_))
      throw new ArrayIndexOutOfBoundsException();
    return arr_[ind];
  }
  public int getNumberOfUsers() { return size_; }

  public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage) { super(errorMessage); }
  }

  public UsersArrayList() {
    this.size_ = 0;
    this.cap_ = 10;
    this.arr_ = new User[cap_];
  }

  private void Extend() {
    this.cap_ = this.cap_ + (this.cap_ >> 1);
    this.arr_ = Arrays.copyOf(this.arr_, this.cap_);
  }
}