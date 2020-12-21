package com.ssg.auth.user.service;

import com.ssg.auth.user.domain.User;
import com.ssg.auth.user.dto.UserRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void signUp(UserRequestDTO userRequestDTO);

    User login(String email, String password);

    User verifyJwt(String email, String password);
}
