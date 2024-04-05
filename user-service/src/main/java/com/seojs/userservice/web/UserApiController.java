package com.seojs.userservice.web;

import com.seojs.userservice.service.UserService;
import com.seojs.userservice.web.dto.UserResponseDto;
import com.seojs.userservice.web.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Long> save(@RequestBody UserSaveDto userSaveDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userSaveDto));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }
}
