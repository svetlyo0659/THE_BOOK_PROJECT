package bg.codeacademy.spring.project1.model;

import bg.codeacademy.spring.project1.enums.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User extends IdEntity
{
  @Column(name = "username", unique = true, nullable = false)
  @NotBlank
  private String  username;
  @Column(name = "password", nullable = false)
  private String  password;
  @Column(name = "role", nullable = false)
  private Role    role;
  private boolean isEnabled; //isEnabled will be true by default, as only owner can create users

  /**
   * We need a default constructor, otherwise we get
   * org.springframework.orm.jpa.JpaSystemException: No default constructor for entity:  : bg.codeacademy.spring.project1.model.User
   */
  public User()
  {
  }

  public User(@NotBlank String username, String password, Role role, boolean isEnabled)
  {
    this.username = username;
    this.password = password;
    this.role = role;
    this.isEnabled = isEnabled;
  }

  //user created by Admin can have a directly approved state
  public User(boolean isEnabled)
  {
    this.isEnabled = isEnabled;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public Role getRole()
  {
    return role;
  }

  public void setRole(Role role)
  {
    this.role = role;
  }

  public void setEnabled(boolean isEnabled)
  {
    this.isEnabled = isEnabled;
  }

  public boolean isEnabled()
  {
    return isEnabled;
  }
}
