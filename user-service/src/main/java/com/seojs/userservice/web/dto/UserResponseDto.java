package com.seojs.userservice.web.dto;

import com.seojs.userservice.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String loginId;
    private String name;
    private String tapologyUrl;
    private String sherdogUrl;

    public UserResponseDto(User user) {
        this.loginId = user.getLoginId();
        this.name = user.getName();
        this.tapologyUrl = user.getTapologyUrl();
        this.sherdogUrl = user.getSherdogUrl();
    }
}
