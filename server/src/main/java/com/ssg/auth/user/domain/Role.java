package com.ssg.auth.user.domain;

import lombok.Data;



import javax.persistence.*;

@Data
@Entity(name ="role")
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String description;
}
