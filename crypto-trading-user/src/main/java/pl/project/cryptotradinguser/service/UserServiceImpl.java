package pl.project.cryptotradinguser.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.project.cryptotradingcommon.validator.UserValidator;
import pl.project.cryptotradinguser.exception.UserNotFoundException;
import pl.project.cryptotradinguser.exception.UserValidationException;
import pl.project.cryptotradinguser.model.User;
import pl.project.cryptotradinguser.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User createUser(User user) throws UserValidationException {
    if (!UserValidator.isValid(user)) {
      throw new UserValidationException("Invalid user data");
    }
    if (!isUsernameAvailable(user.getUsername())) {
      throw new UserValidationException("Username is already taken");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public User getUserById(String id) throws UserNotFoundException {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
  }

  @Override
  public User getUserByUsername(String username) throws UserNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
  }

  @Override
  public void updateUser(User user) throws UserNotFoundException, UserValidationException {
    if (!UserValidator.isValid(user)) {
      throw new UserValidationException("Invalid user data");
    }
    var existingUser = getUserById(user.getId());

    // Użycie pattern matching dla instanceof (Java 21)
    if (existingUser instanceof User oldUser) {
      oldUser.setUsername(user.getUsername());
      oldUser.setEmail(user.getEmail());
      oldUser.setRole(user.getRole());
      // Nie aktualizujemy hasła tutaj - to powinno być osobna operacja
      userRepository.save(oldUser);
    }
  }

  @Override
  public void deleteUser(String id) throws UserNotFoundException {
    if (!userRepository.existsById(id)) {
      throw new UserNotFoundException("User not found with id: " + id);
    }
    userRepository.deleteById(id);
  }

  @Override
  public boolean isUsernameAvailable(String username) {
    return !userRepository.existsByUsername(username);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // Metoda pomocnicza do zmiany hasła
  public void changePassword(String userId, String oldPassword, String newPassword)
      throws UserNotFoundException, UserValidationException {
    var user = getUserById(userId);
    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
      throw new UserValidationException("Old password is incorrect");
    }
    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
  }
}
