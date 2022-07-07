package com.sts.tradeunion.entities;

import com.sts.tradeunion.entities.docs.LaborContractEntity;
import com.sts.tradeunion.entities.docs.MembershipCardEntity;
import com.sts.tradeunion.entities.docs.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fn")
    private String lastName;

    @Column(name = "fn")
    private String firstName;

    @Column (name = "mn")
    private String middleName;

    @Column (name = "birth")
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updated;

    @OneToMany(mappedBy = "owner")
    private List<MembershipCardEntity> membershipCards;

    @OneToMany(mappedBy = "owner")
    private List<PaymentEntity> payments;

    @OneToMany(mappedBy = "owner")
    private List<LaborContractEntity> contracts;


}
