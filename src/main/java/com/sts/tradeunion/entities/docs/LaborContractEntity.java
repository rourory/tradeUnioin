package com.sts.tradeunion.entities.docs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sts.tradeunion.entities.PersonEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "doc_job")
public class LaborContractEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "place")
    private String place;

    @Column(name = "post")
    private String post;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "finished")
    private Date finished;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(typing = JsonSerialize.Typing.STATIC)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity owner;

    @Override
    public String toString() {
        return "LaborContractEntity{" +
                "id=" + id +
                ", place='" + place + '\'' +
                ", post='" + post + '\'' +
                ", created=" + created +
                ", finished=" + finished +
                ", updated=" + updated +
                '}';
    }
}
