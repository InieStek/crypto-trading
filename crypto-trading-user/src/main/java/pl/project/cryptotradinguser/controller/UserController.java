package pl.project.cryptotradinguser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.project.cryptotradinguser.model.User;
import pl.project.cryptotradinguser.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.createUser(user));
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable String id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody User user) {
    user.setId(id);
    userService.updateUser(user);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }
}
