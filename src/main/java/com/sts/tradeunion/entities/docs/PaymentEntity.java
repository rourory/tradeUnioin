package com.sts.tradeunion.entities.docs;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sts.tradeunion.entities.сlassification.TradeUnionClassificationEntity;
import com.sts.tradeunion.entities.PersonEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "doc_payment")
public class PaymentEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "finished")
    private Date finished;

    @OneToOne
    @JsonSerialize(typing = JsonSerialize.Typing.STATIC)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private TradeUnionClassificationEntity tradeUnion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(typing = JsonSerialize.Typing.STATIC)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity owner;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "id=" + id +
                ", created=" + created +
                ", finished=" + finished +
                ", tradeUnion=" + tradeUnion +
                ", updated=" + updated +
                '}';
    }
}
