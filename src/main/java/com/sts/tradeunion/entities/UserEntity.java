package com.sts.tradeunion.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "role")
    private String role;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private Date created;


}
