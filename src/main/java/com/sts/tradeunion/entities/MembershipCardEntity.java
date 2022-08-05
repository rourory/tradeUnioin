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
@Table(name = "doc_member")
public class MembershipCardEntity extends AbstractEntity {

    @Column(name = "num")
    private int cardNumber;

    @Column(name = "created")
    private Date created;

    @Temporal(TemporalType.DATE)
    @Column(name = "completed")
    private Date completed;

    @Temporal(TemporalType.DATE)
    @Column(name = "finished")
    private Date finished;

    @ManyToOne
    @JsonSerialize(typing = JsonSerialize.Typing.STATIC)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity owner;

    @Column(name = "org_id")
    private int tradeUnionId;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Override
    public String toString() {
        return "MembershipCardEntity{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", created=" + created +
                ", completed=" + completed +
                ", finished=" + finished +
                ", tradeUnion=" + tradeUnionId +
                ", updated=" + updated +
                '}';
    }
}
