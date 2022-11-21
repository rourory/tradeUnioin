package com.sts.tradeunion.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "class_marital_state")
public class MaritalStateEntity extends AbstractEntity{
    @Column(name="name1")
    private String name;

    @Column(name = "name2")
    private String shortName;

    @Column(name = "name3")
    private String charName;

    @Column(name = "updated")
    private LocalDateTime updated;
}
