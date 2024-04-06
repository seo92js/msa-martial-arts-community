package com.seojs.userservice.service;

import com.seojs.userservice.web.dto.UserResponseDto;
import com.seojs.userservice.web.dto.UserSaveDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void save_findById() {
        String loginId = "loginId";
        String password = "password";
        String name = "name";
        String role = "ROLE_ADMIN";
        String tapologyUrl = "1";
        String sherdogUrl = "2";

        UserSaveDto userSaveDto = new UserSaveDto(loginId, password, name, role, tapologyUrl, sherdogUrl);

        Long savedId = userService.save(userSaveDto);

        UserResponseDto userResponseDto = userService.findById(savedId);

        assertThat(userResponseDto).isNotNull();
        assertThat(userResponseDto.getName()).isEqualTo(name);
        assertThat(userResponseDto.getLoginId()).isEqualTo(loginId);
        assertThat(userResponseDto.getSherdogUrl()).isEqualTo(sherdogUrl);
        assertThat(userResponseDto.getTapologyUrl()).isEqualTo(tapologyUrl);
    }

    @Test
    void findByLoginId() {
        String loginId = "loginId";
        String password = "password";
        String name = "name";
        String role = "ROLE_ADMIN";
        String tapologyUrl = "1";
        String sherdogUrl = "2";

        UserSaveDto userSaveDto = new UserSaveDto(loginId, password, name, role, tapologyUrl, sherdogUrl);

        Long savedId = userService.save(userSaveDto);

        UserResponseDto userResponseDto = userService.findByLoginId(loginId);

        assertThat(userResponseDto).isNotNull();
        assertThat(userResponseDto.getName()).isEqualTo(name);
        assertThat(userResponseDto.getLoginId()).isEqualTo(loginId);
        assertThat(userResponseDto.getSherdogUrl()).isEqualTo(sherdogUrl);
        assertThat(userResponseDto.getTapologyUrl()).isEqualTo(tapologyUrl);
    }
}