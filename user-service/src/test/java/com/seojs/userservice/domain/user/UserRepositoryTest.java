package com.seojs.userservice.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void save_findById() {
        String loginId = "loginId";
        String password = "password";
        String name = "name";
        String role = "ROLE_ADMIN";
        String tapologyUrl = "1";
        String sherdogUrl = "2";

        User user = new User(loginId, password, name, role, tapologyUrl, sherdogUrl);

        Long savedId = userRepository.save(user);

        User findUser = userRepository.findById(savedId).get();

        assertThat(findUser.getLoginId()).isEqualTo(loginId);
        assertThat(findUser.getPassword()).isEqualTo(password);
        assertThat(findUser.getName()).isEqualTo(name);
        assertThat(findUser.getRole()).isEqualTo(role);
        assertThat(findUser.getTapologyUrl()).isEqualTo(tapologyUrl);
        assertThat(findUser.getSherdogUrl()).isEqualTo(sherdogUrl);
    }
}