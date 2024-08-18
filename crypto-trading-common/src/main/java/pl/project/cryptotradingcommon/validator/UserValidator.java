package pl.project.cryptotradingcommon.validator;

import pl.project.cryptotradingcommon.model.CommonUser;

public class UserValidator {
  public static boolean isValid(CommonUser user) {
    return user != null &&
        isValidUsername(user.getUsername()) &&
        isValidEmail(user.getEmail());
  }

  public static boolean isValidUsername(String username) {
    return username != null && username.length() >= 3 && username.length() <= 50;
  }

  public static boolean isValidEmail(String email) {
    return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
  }

}
