package com.sts.tradeunion.entities.docs;


import com.sts.tradeunion.entities.сlassification.TradeUnionClassification;
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
@Table(name = "doc_payment")
public class PaymentEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "created")
    private Date created;

    @Column(name = "finished")
    private Date finished;

    @OneToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private TradeUnionClassification tradeUnion;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity owner;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

}
