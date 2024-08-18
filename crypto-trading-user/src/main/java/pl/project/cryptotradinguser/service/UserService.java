package pl.project.cryptotradinguser.service;

import java.util.List;
import pl.project.cryptotradinguser.exception.UserNotFoundException;
import pl.project.cryptotradinguser.exception.UserValidationException;
import pl.project.cryptotradinguser.model.User;

public interface UserService {
  User createUser(User user) throws UserValidationException;
  User getUserById(String id) throws UserNotFoundException;
  User getUserByUsername(String username) throws UserNotFoundException;
  void updateUser(User user) throws UserNotFoundException, UserValidationException;
  void deleteUser(String id) throws UserNotFoundException;
  boolean isUsernameAvailable(String username);
  List<User> getAllUsers();

}
