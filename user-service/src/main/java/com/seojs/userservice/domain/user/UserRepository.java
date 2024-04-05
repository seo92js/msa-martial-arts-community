package com.seojs.userservice.domain.user;

import com.seojs.userservice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserMapper userMapper;

    public Long save(User user) {
        userMapper.save(user);
        return user.getId();
    }

    public Optional<User> findById(Long id) {
        return userMapper.findById(id);
    }
}
