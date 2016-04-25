package com.controller;

import com.Application;
import com.entity.User;
import com.service.ServiceManager;
import com.service.forJSON.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by user on 25.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    UserController userController;

    @Mock
    ServiceManager serviceManager;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
}

//
//    @Test
//    public void register_withInvalidData() throws Exception {
//        mockMvc.perform(
//
//                post("/registration")
//                        .param("name", "aasddd")
//                        .param("surname", "asdasddd")
//                        .param("patronymic", "asdasddd")
//                        .param("login", "asdfsfds")
//                        .param("password", "das")
//
//        )
//                .andExpect(model().attribute("user", hasProperty("password", equalTo("das"))))
//                .andExpect(view().name("registration"));
//
//        verify(serviceManager.getUserService(), never()).save(any(User.class));
//    }

    @Test
    public void register_withValidData() throws Exception {
        mockMvc.perform(
                post("/registration")
                        .param("name", "aasddd")
                        .param("surname", "asdasddd")
                        .param("patronymic", "asdasddd")
                        .param("login", "asdfsfds")
                        .param("password", "dasddd")
        )
                .andExpect(view().name("welcome"))
                .andExpect(flash().attribute("success", "User aasddd has been registered successfully"));

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(serviceManager.getUserService()).save(userArgumentCaptor.capture());

        User registrationInfo = userArgumentCaptor.getValue();
        assertThat(registrationInfo.getName(), equalTo("aasddd"));
        assertThat(registrationInfo.getSurname(), equalTo("asdasddd"));
        assertThat(registrationInfo.getPassword(), equalTo("dasddd"));
    }


}
