package com.deploy.Praktikum1.repository;

import com.deploy.Praktikum1.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends  JpaRepository<User, String> {
}
