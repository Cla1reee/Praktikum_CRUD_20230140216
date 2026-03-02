package com.deploy.Praktikum1.services;

import com.deploy.Praktikum1.model.dto.UserDto;
import com.deploy.Praktikum1.model.dto.UserAddRequest;

import java.util.List;

public interface UserService {
    UserDto AddUser(UserAddRequest Request);
    List<UserDto> getAllUser();
    UserDto getUserById(String id);
     UserDto updateUser(String id, UserAddRequest Request);
     void deleteUser(String id);

}
