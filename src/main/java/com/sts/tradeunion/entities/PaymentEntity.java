package com.sts.tradeunion.entities;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "doc_payment")
public class PaymentEntity extends AbstractEntity {

    @Column(name = "created")
    private Date created;
    @Column(name = "finished")
    @Temporal(TemporalType.DATE)
    private Date finished;

    @Column(name = "org_id")
    private int tradeUnionId;

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
                ", tradeUnion=" + tradeUnionId +
                ", updated=" + updated +
                '}';
    }
}
