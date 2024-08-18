package pl.project.cryptotradingcommon.model;

public interface CommonUser {
  String getId();
  void setId(String id);
  String getUsername();
  void setUsername(String username);
  String getEmail();
  void setEmail(String email);
  String getRole();
  void setRole(String role);
}
