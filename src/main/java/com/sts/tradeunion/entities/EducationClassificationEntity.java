package com.sts.tradeunion.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "class_education")
public class EducationClassificationEntity extends AbstractEntity {

    @Column(name="name")
    private String name;

    @Column(name = "name1")
    private String shortName;

    @Column(name = "name2")
    private String charName;

    @Column(name = "updated")
    private LocalDateTime updated;
}
