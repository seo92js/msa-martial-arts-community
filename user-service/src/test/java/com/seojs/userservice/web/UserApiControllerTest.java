package com.seojs.userservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seojs.userservice.web.dto.LoginRequestDto;
import com.seojs.userservice.web.dto.UserSaveDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserApiControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void save() throws Exception {
        String loginId = "loginId";
        String password = "password";
        String name = "name";
        String role = "ROLE_ADMIN";
        String tapologyUrl = "1";
        String sherdogUrl = "2";

        UserSaveDto userSaveDto = new UserSaveDto(loginId, password, name, role, tapologyUrl, sherdogUrl);

        String postUrl = "/user";

        mvc.perform(post(postUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userSaveDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void findById() throws Exception {
        String loginId = "loginId";
        String password = "password";
        String name = "name";
        String role = "ROLE_ADMIN";
        String tapologyUrl = "1";
        String sherdogUrl = "2";

        UserSaveDto userSaveDto = new UserSaveDto(loginId, password, name, role, tapologyUrl, sherdogUrl);

        String postUrl = "/user";

        MvcResult result = mvc.perform(post(postUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userSaveDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        Long id = extractedId(result);

        String getUrl = "/user/" + id;

        mvc.perform(MockMvcRequestBuilders.get(getUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.loginId", Matchers.is(loginId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tapologyUrl", Matchers.is(tapologyUrl)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sherdogUrl", Matchers.is(sherdogUrl)));
    }

    @Test
    void login() throws Exception {
        String loginId = "loginId";
        String password = "password";
        String name = "name";
        String role = "ROLE_ADMIN";
        String tapologyUrl = "1";
        String sherdogUrl = "2";

        UserSaveDto userSaveDto = new UserSaveDto(loginId, password, name, role, tapologyUrl, sherdogUrl);

        String postUrl = "/user";

        mvc.perform(post(postUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userSaveDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        LoginRequestDto loginRequestDto = new LoginRequestDto(loginId, password);

        String loginUrl = "/login";

        mvc.perform(post(loginUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(header().exists("Authorization"));
    }

    private Long extractedId(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        MockHttpServletResponse response = result.getResponse();
        String responseBody = response.getContentAsString();
        return objectMapper.readValue(responseBody, Long.class);
    }
}