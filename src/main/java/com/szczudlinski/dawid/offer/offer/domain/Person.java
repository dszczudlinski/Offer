package com.szczudlinski.dawid.offer.offer.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    private String sex;

    private String firstName;

    private String middleName;

    private String lastName;

    private Date birthDate;

    private String birthPlace;

    private String birthCountry;

    private String pesel;

    private String phoneNumber;

    private String identityDocumentNumber;

    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "personId")
    private Address address;
}
