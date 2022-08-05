package com.sts.tradeunion.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "class_org")
public class TradeUnionClassificationEntity extends AbstractEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "updated")
    private LocalDateTime updated;
}
