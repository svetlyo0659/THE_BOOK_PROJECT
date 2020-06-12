package bg.codeacademy.spring.project1.config;

import bg.codeacademy.spring.project1.enums.Role;
import bg.codeacademy.spring.project1.model.User;
import bg.codeacademy.spring.project1.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

  private UserService userService;

  public UserDetailsServiceImpl(UserService userService)
  {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
  {
    List<User> users = userService.getUsers();
    if (users.isEmpty()) {
      // first start, create default admin user
      userService.createUser("admin", "123456", Role.ADMIN);
    }

    Optional<User> optionalUser = userService.getUser(userName);
    if (!optionalUser.isPresent()) {
      throw new UsernameNotFoundException("User not found.");
    }
    User user = optionalUser.get();
    return org.springframework.security.core.userdetails.User.withUsername(userName)
        .password(user.getPassword())
        .roles(user.getRole().toString())
        .build();

  }
}