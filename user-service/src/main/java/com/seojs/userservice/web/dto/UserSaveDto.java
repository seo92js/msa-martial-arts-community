package com.seojs.userservice.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveDto {
    private String loginId;
    private String password;
    private String name;
    private String role;
    private String tapologyUrl;
    private String sherdogUrl;

    public UserSaveDto(String loginId, String password, String name, String role, String tapologyUrl, String sherdogUrl) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.tapologyUrl = tapologyUrl;
        this.sherdogUrl = sherdogUrl;
    }
}
