package com.rkorp.hamburger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank
    private String buildingNumber;

    @Column
    @NotBlank
    private String cep;

    @Column
    @NotBlank
    private String streetName;

    @Column
    @NotBlank
    private String districtName;

    @Column
    @NotBlank
    private String country;

    @Column
    @NotBlank
    private String state;

    @Column
    @NotBlank
    private String town;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;
}
