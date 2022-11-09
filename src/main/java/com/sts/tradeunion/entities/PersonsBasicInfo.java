package com.sts.tradeunion.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")
//@RequiredArgsConstructor
@AllArgsConstructor
public class PersonsBasicInfo  extends AbstractEntity{
    @Column(name = "ln")
    private String lastName;

    @Column(name = "fn")
    private String firstName;

    @Column (name = "mn")
    private String middleName;

    @Column (name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
}
