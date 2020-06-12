package bg.codeacademy.spring.project1.dto;

import bg.codeacademy.spring.project1.enums.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistration extends UserDTO
{
  @NotBlank
  @Size(min = 3, max = 50)
  private String password;
  @NotNull
  private Role   role;

  public UserRegistration()
  {
    // default constructor
  }

  public UserRegistration(String username, String password, Role role)
  {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public String getPassword()
  {
    return password;
  }

  public UserRegistration setPassword(String password)
  {
    this.password = password;
    return this;
  }

  public Role getRole()
  {
    return role;
  }

  public UserRegistration setRole(Role role)
  {
    this.role = role;
    return this;
  }
}
