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

    @Column(name = "role")
    private String role;

    @Column(name = "reliability_demonstration")
    private boolean reliabilityDemonstration;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    private Date created;

    @Column(name = "secret_key")
    private String secretKey;

}
