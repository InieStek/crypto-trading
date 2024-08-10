package pl.project.cryptotradinguser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.project.cryptotradinguser.model.User;
import pl.project.cryptotradinguser.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User getUserById(String id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Override
  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Override
  public void updateUser(User user) {
    userRepository.save(user);
  }

  @Override
  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }
}
