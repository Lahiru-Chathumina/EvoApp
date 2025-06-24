package edu.icet.service.system.impl;

import edu.icet.dto.customer.User;
import edu.icet.entity.customer.UserEntity;
import edu.icet.repository.customer.UserRepository;
import edu.icet.service.system.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    List<User> userList = new ArrayList<>();

    @Override
    public Boolean saveUser(User user) {
        if (user == null) {
            return false;
        }
        UserEntity savedUserEntity = userRepository.save(modelMapper.map(user, UserEntity.class));
        return userRepository.existsById(savedUserEntity.getUserId());
    }

    @Override
    public Boolean updateUser(Long userId, User user) {
        if (user == null) {
            return false;
        }
        if (!user.getUserId().equals(userId)) {
            return false;
        }
        UserEntity updatedUserEntity = userRepository.save(modelMapper.map(user, UserEntity.class));
        return userRepository.existsById(updatedUserEntity.getUserId());
    }

    @Override
    public Boolean deleteUser(Long userId) {
        if (userId == null) {
            return false;
        }
        userRepository.deleteById(userId);
        return !userRepository.existsById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<User> users = new ArrayList<>();
        userEntities.forEach(userEntity -> {
            users.add(modelMapper.map(userEntity, User.class));
        });
        return users;
    }

    @Override
    public boolean isUserExist(Long userId) {
        if (userId == null) {
            return false;
        }
        return userRepository.existsById(userId);
    }

    @Override
    public boolean isUserExist(User user) {
        if (user == null) {
            return false;
        }
        return userRepository.existsById(user.getUserId());
    }
}
