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
@Table(name = "doc_member")
public class MembershipCardEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "num")
    private int cardNumber;

    @Column(name = "created")
    private Date created;

    @Column(name = "completed")
    private Date completed;

    @Column(name = "finished")
    private Date finished;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity owner;

    @OneToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private TradeUnionClassification tradeUnion;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
}
