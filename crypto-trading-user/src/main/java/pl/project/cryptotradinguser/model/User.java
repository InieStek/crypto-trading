package pl.project.cryptotradinguser.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.project.cryptotradinguser.enums.UserRole;

@Data
@Document(collection = "users")
public class User {
  @Id
  private String id;
  private String username;
  private String password;
  private String email;
  private UserRole role;

}
