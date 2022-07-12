package com.sts.tradeunion.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sts.tradeunion.entities.docs.LaborContractEntity;
import com.sts.tradeunion.entities.docs.MembershipCardEntity;
import com.sts.tradeunion.entities.docs.PaymentEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")
public class PersonEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ln")
    private String lastName;

    @Column(name = "fn")
    private String firstName;

    @Column (name = "mn")
    private String middleName;

    @Column (name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "live_place")
    private String livePlace;

    @Column(name = "reg_place")
    private String regPlace;

    @Column(name = "marital_id")
    private int maritalState;

    @Column(name = "citizenship")
    private String citizenship;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "comment")
    private String comment;

    @Column(name = "updated")
    private LocalDateTime updated;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<MembershipCardEntity> membershipCards;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<PaymentEntity> payments;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<LaborContractEntity> contracts;

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", livePlace='" + livePlace + '\'' +
                ", regPlace='" + regPlace + '\'' +
                ", maritalState=" + maritalState +
                ", citizenship='" + citizenship + '\'' +
                ", nationality='" + nationality + '\'' +
                ", comment='" + comment + '\'' +
                ", updated=" + updated +
                '}';
    }
}
