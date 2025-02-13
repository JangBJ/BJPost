package com.bjpost.service;

import com.bjpost.dto.request.UserCreate;
import com.bjpost.entity.User;
import com.bjpost.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthRepository authRepository;

    public void createUser(UserCreate request) {
        User user = User.builder()
                .name(request.getName())
                .nickName(request.getNickName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }


}
