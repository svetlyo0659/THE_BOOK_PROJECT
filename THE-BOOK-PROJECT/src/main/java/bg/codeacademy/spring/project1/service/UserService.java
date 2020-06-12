package bg.codeacademy.spring.project1.service;


import bg.codeacademy.spring.project1.enums.Role;
import bg.codeacademy.spring.project1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService
{

  Optional<User> getUser(String userName);

  void createUser(String userName, String password, Role role);

  boolean changePassword(String userName, String oldPassword, String newPassword);

  boolean deleteUser(String userName);

  List<User> getUsers();

  Optional<User> getUser(Integer id);
}
