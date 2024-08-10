package pl.project.cryptotradinguser.service;

import pl.project.cryptotradinguser.model.User;

public interface UserService {
  User createUser(User user);
  User getUserById(String id);
  User getUserByUsername(String username);
  void updateUser(User user);
  void deleteUser(String id);

}
