package com.deploy.Praktikum1.services.impl;

import com.deploy.Praktikum1.mapper.UserMapper;
import com.deploy.Praktikum1.model.dto.UserAddRequest;
import com.deploy.Praktikum1.model.dto.UserDto;
import com.deploy.Praktikum1.model.entity.User;
import com.deploy.Praktikum1.repository.UserRepository;
import com.deploy.Praktikum1.services.UserService;
import com.deploy.Praktikum1.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;

    @Override
    @Transactional
    public UserDto addUser(UserAddRequest request) {
        // 1. Validasi input
        validationUtil.validate(request);

        // 2. Mapping Request ke Entity & Set UUID
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .age(request.getAge())
                .build();

        // 3. Simpan ke database
        userRepository.save(user);

        // 4. Return sebagai DTO
        return UserMapper.MAPPER.toUserDtoData(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(UserMapper.MAPPER::toUserDtoData)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.MAPPER.toUserDtoData(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(String id, UserAddRequest request) {
        validationUtil.validate(request);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(request.getName());
        existingUser.setAge(request.getAge());

        userRepository.save(existingUser);

        return UserMapper.MAPPER.toUserDtoData(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
