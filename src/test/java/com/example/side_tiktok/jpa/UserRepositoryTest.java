package com.example.side_tiktok.jpa;

import com.example.side_tiktok.modules.User.entity.User;
import com.example.side_tiktok.modules.User.enums.RoleType;
import com.example.side_tiktok.modules.User.enums.UserStatus;
import com.example.side_tiktok.modules.User.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init() {
        setUp(
                "user1",
                "password1",
                "user1@gmail.com",
                RoleType.USER,
                UserStatus.ACTIVE
        );
    }

    @Test
    public void selectAll() {
        var users = userRepository.findAll();
        assertNotEquals(users.size(), 0);

        assertEquals(users.get(0).getUsername(), "user1");
        assertEquals(users.get(0).getPassword(), "password1");
        assertEquals(users.get(0).getEmail(), "user1@gmail.com");
        assertEquals(users.get(0).getRole(), RoleType.USER);
        assertEquals(users.get(0).getStatus(), UserStatus.ACTIVE);
    }

    @Test
    @Transactional
    public void selectAndUpdate() {
        //given
        Long id = 1L;
        var optionalUser = userRepository.findById(1L);

        if (optionalUser.isPresent()) {
            var result = optionalUser.get();

            assertEquals(result.getUsername(), "user1");
            assertEquals(result.getPassword(), "password1");
            assertEquals(result.getEmail(), "user1@gmail.com");
            assertEquals(result.getRole(), RoleType.USER);
            assertEquals(result.getStatus(), UserStatus.ACTIVE);

            var updatePassword = "password2";
            result.setPassword(updatePassword);
            entityManager.merge(result);

            assertEquals(result.getUsername(), "user1");
            assertEquals(result.getPassword(), "password2");
            assertEquals(result.getEmail(), "user1@gmail.com");
            assertEquals(result.getRole(), RoleType.USER);
            assertEquals(result.getStatus(), UserStatus.ACTIVE);
        } else {
            assertNotNull(optionalUser.get());
        }
    }

    @Test
    @Transactional
    public void insertAndDelete() {
        User user = setUp(
                "user2",
                "password2",
                "user2@gmail.com",
                RoleType.USER,
                UserStatus.ACTIVE
        );

        var optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isPresent()) {
            var result = optionalUser.get();

            assertEquals(result.getUsername(), "user2");
            assertEquals(result.getPassword(), "password2");
            assertEquals(result.getEmail(), "user2@gmail.com");
            assertEquals(result.getRole(), RoleType.USER);
            assertEquals(result.getStatus(), UserStatus.ACTIVE);

            entityManager.remove(result);

            Optional<User> deleteUser = userRepository.findById(result.getId());
            if (deleteUser.isPresent()) {
                assertNull(deleteUser.get());
            }
        }
    }

    private User setUp(String username, String password, String email, RoleType role, UserStatus status) {
        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .status(status)
                .build();
        return this.entityManager.persist(user);
    }
}
