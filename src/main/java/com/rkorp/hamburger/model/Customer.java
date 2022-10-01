package com.rkorp.hamburger.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "customer")
@Entity
public class Customer {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String age;

    @Column
    @NotBlank
    private String email;

    @Column
    @NotBlank
    private String phone;
}
