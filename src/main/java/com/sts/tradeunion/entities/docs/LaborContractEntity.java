package com.sts.tradeunion.entities.docs;

import com.sts.tradeunion.entities.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doc_job")
public class LaborContractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "place")
    private String place;

    @Column(name = "post")
    private String post;

    @Column(name = "created")
    private Date created;

    @Column(name = "finished")
    private Date finished;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updated;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity owner;

}
