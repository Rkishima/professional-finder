package com.rkorp.hamburger.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@Table(name = "customer")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String cpf;

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
