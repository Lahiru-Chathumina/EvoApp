package edu.icet.service.system;

import edu.icet.dto.customer.User;

import java.util.List;


public interface UserService {
     Boolean saveUser(User user);
     Boolean updateUser(Long userId,User user);
     Boolean deleteUser(Long userId);
     List<User> getAllUsers();
     boolean isUserExist(Long userId);
     boolean isUserExist(User user);
}
