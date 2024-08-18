package pl.project.cryptotradinguser.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.project.cryptotradingcommon.model.CommonUser;
import pl.project.cryptotradinguser.enums.UserRole;
import pl.project.cryptotradinguser.records.UserDetails;

@Data
@Document(collection = "users")
public class User implements CommonUser {
  @Id
  private String id;
  private String username;
  private String password;
  private String email;
  private UserRole role;
  private UserDetails userDetails;

  @Override
  public String getRole() {
    return role != null ? role.name() : null;
  }

  @Override
  public void setRole(String role) {
    this.role = role != null ? UserRole.valueOf(role) : null;
  }
}
