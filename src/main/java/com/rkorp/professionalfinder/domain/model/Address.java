package com.rkorp.professionalfinder.domain.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String number;

    @NotBlank
    private String streetName;

    @NotBlank
    private String district;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

}
