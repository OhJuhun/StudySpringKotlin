package com.example.demo;

import com.example.demo.controller.restcontroller.RestUserController;
import com.example.demo.documentation.UserDocumentation;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Transactional
public class UserTest extends AbstractControllerTest{

    @Autowired
    private UserService userService;


    @Override
    protected Object service() {
        return userService;
    }

    @Test
    public void getAll() throws Exception{
        this.mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserDocumentation.getUsers());
    }

    @Test
    public void getByNickName() throws Exception{
        this.mockMvc.perform(get("/user/nickname/is"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("MyName")))
                .andDo(print())
                .andDo(UserDocumentation.getByNickname());
    }

    @Test
    public void getByName() throws Exception{
        this.mockMvc.perform(get("/user/name/Jonny Johansson"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jonny")))
                .andDo(print())
                .andDo(UserDocumentation.getByName());
    }
    @Test
    public void insertUser() throws Exception{
        this.mockMvc.perform(post("/user")
                .contentType("application/json;charset=UTF-8")
                .content("{\"nickname\":\"testnickname\",\"name\":\"testname\",\"password\":\"testpw\",\"email\":\"testmail@naver.com\"}"))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(UserDocumentation.insertUser());
    }

    @Test
    public void deleteUser() throws Exception{
        this.mockMvc.perform(delete("/user/3"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserDocumentation.deleteUser());
    }

    @Test
    public void modifyUser() throws Exception{
        this.mockMvc.perform(put("/user")
                .contentType("application/json;charset=UTF-8")
                .content("{\"id\":\"1\",\"nickname\":\"myname3\",\"name\":\"mytestname3\",\"password\":\"mytestp3w\",\"email\":\"mytest3ma@naver.com\"}"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(UserDocumentation.modifyUser());
    }
}