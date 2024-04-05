package com.seojs.userservice.domain.user;

import lombok.*;

@Getter
@NoArgsConstructor
public class User {
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String role;
    private String tapologyUrl;
    private String sherdogUrl;

    public User(String loginId, String password, String name, String role, String tapologyUrl, String sherdogUrl) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.tapologyUrl = tapologyUrl;
        this.sherdogUrl = sherdogUrl;
    }
}
