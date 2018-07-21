package com.szczudlinski.dawid.offer.offer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class MakeOfferCommand {

    private BigDecimal simulationNumber;

    private Date startDate;

    private BigDecimal duration;

    private String frequency;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String phoneNumber;

    private String email;

    private List<InsuranceDTO> insuranceList;
}
