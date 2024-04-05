package com.seojs.userservice.web.dto;

import com.seojs.userservice.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserResponseDto {
    private String name;
    private String tapologyUrl;
    private String sherdogUrl;

    public UserResponseDto(User user) {
        this.name = user.getName();
        this.tapologyUrl = user.getTapologyUrl();
        this.sherdogUrl = user.getSherdogUrl();
    }
}
