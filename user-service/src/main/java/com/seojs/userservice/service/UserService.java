package com.seojs.userservice.service;

import com.seojs.userservice.domain.user.User;
import com.seojs.userservice.domain.user.UserRepository;
import com.seojs.userservice.web.dto.UserResponseDto;
import com.seojs.userservice.web.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveDto userSaveDto) {
        User user = new User(userSaveDto.getLoginId(),
                userSaveDto.getPassword(),
                userSaveDto.getName(),
                userSaveDto.getRole(),
                userSaveDto.getTapologyUrl(),
                userSaveDto.getSherdogUrl()
        );

        return userRepository.save(user);
    }

    @Transactional
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        return new UserResponseDto(user);
    }
}
