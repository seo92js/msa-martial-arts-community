package com.seojs.userservice.mapper;

import com.seojs.userservice.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void save(User user);

    Optional<User> findById(Long id);
}
