package com.deploy.Praktikum1.services;

import com.deploy.Praktikum1.model.dto.UserDto;
import com.deploy.Praktikum1.model.dto.UserAddRequest;

import java.util.List;

public interface UserService {
    UserDto addUser(UserAddRequest request);
    List<UserDto> getAllUser();
    UserDto getUserById(String id);
    UserDto updateUser(String id, UserAddRequest request);
    void deleteUser(String id);

}
