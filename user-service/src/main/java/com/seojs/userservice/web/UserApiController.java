package com.seojs.userservice.web;

import com.seojs.userservice.service.UserService;
import com.seojs.userservice.web.dto.UserResponseDto;
import com.seojs.userservice.web.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final Environment env;

    @GetMapping("/user/health_check")
    public String status() {
        return String.format("It's working in user-service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" +  env.getProperty("local.server.port")
                + ", token secret=" +  env.getProperty("token.secret")
                + ", token expiration time=" +  env.getProperty("token.expiration_time")
                + ", token prefix=" +  env.getProperty("token.prefix"));
    }

    @PostMapping("/user")
    public ResponseEntity<Long> save(@RequestBody UserSaveDto userSaveDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userSaveDto));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }
}
