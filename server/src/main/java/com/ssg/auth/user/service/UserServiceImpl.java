package com.ssg.auth.user.service;

import com.ssg.auth.user.domain.User;
import com.ssg.auth.user.repository.UserRepository;
import com.ssg.auth.user.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void signUp(UserRequestDTO userRequestDTO){
        User checkUser = userRepository.findByEmail(userRequestDTO.getEmail());
//        System.out.println(checkUser.getEmail());
//        if(checkUser != null){
//            try{
//                System.out.println("되나");
//                Exception e = new Exception("고의");
//                throw e;
//            }catch(Exception e){
//                System.out.println(e);
//                return;
//            }
//        }
        User user = new User(userRequestDTO.getEmail(), passwordEncoder.encode(userRequestDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User login(String email, String password){
        System.out.println(email);
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User verifyJwt(String email, String password){
        User user = new User();
        try {
            user = userRepository.findByEmailAndPassword(email, password);

        }catch(Exception e){
            System.out.println(e);
        }
        return user;
    }




}
