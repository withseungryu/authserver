package com.ssg.auth.user.dto;


import com.ssg.auth.user.domain.User;
import lombok.Data;

@Data
public class UserResponseDTO {
    Long id;
    String email;
    String password;


    public UserResponseDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password =user.getPassword();
    }

}
