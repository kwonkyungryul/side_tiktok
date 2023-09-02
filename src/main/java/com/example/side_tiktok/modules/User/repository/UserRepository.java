package com.example.side_tiktok.modules.User.repository;

import com.example.side_tiktok.modules.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
