package com.szczudlinski.dawid.offer.offer.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    private String country;

    private String postalCode;

    private String postOffice;

    private String city;

    private String street;

    private String buildingNumber;

    private String flatNumber;
}
