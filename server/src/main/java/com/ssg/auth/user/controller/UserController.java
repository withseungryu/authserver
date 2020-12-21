package com.ssg.auth.user.controller;


import com.ssg.auth.jwt.JwtUtil;
import com.ssg.auth.user.dto.UserJWT;
import com.ssg.auth.user.repository.UserRepository;
import com.ssg.auth.user.domain.User;
import com.ssg.auth.user.dto.UserRequestDTO;
import com.ssg.auth.user.dto.UserResponseDTO;
import com.ssg.auth.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping(value="/login/signUp")
    public String signUp(@RequestBody UserRequestDTO userRequestDTO){

        try {
            userService.signUp(userRequestDTO);
            return "success";
        }catch(Exception e){
            return "fail";
        }
    }


    @GetMapping(value="/admin")
    public List<UserResponseDTO> adminPage(){
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();
        for(int i=0; i< users.size(); ++i) {
            UserResponseDTO userResponseDTO = new UserResponseDTO(users.get(i));
            userResponseDTOs.add(userResponseDTO);

        }
        return userResponseDTOs;
    }

    @GetMapping(value="/admin/{id}")
    public UserResponseDTO detailPage(@PathVariable Long id){
        User user = userRepository.findById(id)
                .orElseThrow();

        UserResponseDTO userResponseDTO = new UserResponseDTO(user);
        return userResponseDTO;
    }

    @GetMapping(value="/login")
    public ModelAndView loginPage(ModelAndView model){
        model.setViewName("redirect:http://localhost:3000/login");
        return model;
    }


    @PostMapping(value="/login/authenticate")
    public String loginCheck(@RequestBody UserRequestDTO userRequestDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequestDTO.getEmail(),
                        userRequestDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(authentication);
//        String refresh = jwtUtil.generateRefreshToken(authentication);
        return token;
    }

//    @GetMapping(value="/refresh")
//    public String refreshTokne(HttpServletRequest)




}
