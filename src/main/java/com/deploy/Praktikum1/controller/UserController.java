package com.deploy.Praktikum1.controller;

import com.deploy.Praktikum1.model.dto.UserAddRequest;
import com.deploy.Praktikum1.model.dto.UserDto;
import com.deploy.Praktikum1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserAddRequest request) {
        UserDto result = userService.addUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "status", "success",
                "data", result
        ));
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> getAllUser() {
        List<UserDto> result = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success",
                "data", result
        ));
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable String id) {
        UserDto result = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success",
                "data", result
        ));
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable String id, @RequestBody UserAddRequest request) {
        UserDto result = userService.updateUser(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success",
                "data", result
        ));
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success",
                "message", "User deleted successfully"
        ));
    }
}
