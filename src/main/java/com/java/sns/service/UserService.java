package com.java.sns.service;

import com.java.sns.exception.SnsException;
import com.java.sns.model.User;
import com.java.sns.model.entity.UserEntity;
import com.java.sns.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    public User register(String userName, String password) {
        // 회원가입하려는 userName 체크 없으면 exception 처리
        userEntityRepository.findByUserName(userName).ifPresent(it -> {
            throw new SnsException();
        });

        // 회원가입 진행
        userEntityRepository.save(new UserEntity());


        return new User();
    }

    public String login(String userName, String password) {
        // 회원가입 여부 체크
        UserEntity userEntity = userEntityRepository.findByUserName(userName).orElseThrow(() -> new SnsException());
        // 비밀번호 체크
        if(!userEntity.getPassword().equals(password)) {
            throw new SnsException();
        }
        // 토큰 생성

        return "";
    }
}
