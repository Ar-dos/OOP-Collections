package ex05;

interface UserList {
  int add(User u);
  User getUserById(int Id);
  User getUserByIndex(int ind);
  int getNumberOfUsers();
}