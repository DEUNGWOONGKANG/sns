package com.java.sns.service;

import com.java.sns.exception.SnsException;
import com.java.sns.fixture.UserEntityFixture;
import com.java.sns.model.entity.UserEntity;
import com.java.sns.repository.UserEntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserEntityRepository userEntityRepository;

    @Test
    void 회원가입_정상() {
        String userName = "username";
        String password = "password";

        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());
        when(userEntityRepository.save(any())).thenReturn(Optional.of(UserEntityFixture.get(userName, password)));


        Assertions.assertDoesNotThrow(() -> userService.register(userName, password));
    }

    @Test
    void 회원가입시_중복된userName() {
        String userName = "username";
        String password = "password";

        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(UserEntityFixture.get(userName, password)));
        when(userEntityRepository.save(any())).thenReturn(Optional.of(UserEntityFixture.get(userName, password)));


        Assertions.assertThrows(SnsException.class, () -> userService.register(userName, password));
    }

    @Test
    void 로그인_정상() {
        String userName = "username";
        String password = "password";


        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(UserEntityFixture.get(userName, password)));

        Assertions.assertDoesNotThrow(() -> userService.login(userName, password));
    }

    @Test
    void 로그인시_없는userName() {
        String userName = "username";
        String password = "password";

        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());

        Assertions.assertThrows(SnsException.class, () -> userService.login(userName, password));
    }

    @Test
    void 로그인시_비밀번호틀린경우() {
        String userName = "username";
        String password = "password";
        String inputPassword = "wrongPassword";

        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(UserEntityFixture.get(userName, password)));

        Assertions.assertThrows(SnsException.class, () -> userService.login(userName, password));
    }

}
