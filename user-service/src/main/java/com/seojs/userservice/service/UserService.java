package com.seojs.userservice.service;

import com.seojs.userservice.domain.user.User;
import com.seojs.userservice.domain.user.UserRepository;
import com.seojs.userservice.web.dto.UserResponseDto;
import com.seojs.userservice.web.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(UserSaveDto userSaveDto) {
        User user = new User(userSaveDto.getLoginId(),
                passwordEncoder.encode(userSaveDto.getPassword()),
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

    @Transactional
    public UserResponseDto findByLoginId(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElseThrow();

        return new UserResponseDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username).orElseThrow();

        if (user == null)
            throw new UsernameNotFoundException(username);

        return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(),
                true, true, true, true,
                new ArrayList<>());
    }
}
