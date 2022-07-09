package com.sts.tradeunion.entities.docs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sts.tradeunion.entities.сlassification.TradeUnionClassification;
import com.sts.tradeunion.entities.PersonEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "doc_member")
public class MembershipCardEntity{

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(typing = JsonSerialize.Typing.STATIC)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private TradeUnionClassification tradeUnion;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Override
    public String toString() {
        return "MembershipCardEntity{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", created=" + created +
                ", completed=" + completed +
                ", finished=" + finished +
                ", tradeUnion=" + tradeUnion +
                ", updated=" + updated +
                '}';
    }
}
