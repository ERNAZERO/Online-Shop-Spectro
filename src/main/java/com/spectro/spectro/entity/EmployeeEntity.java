package com.spectro.spectro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="employee")
@Getter
@Setter
public class EmployeeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")

    private String name;

    @Column(name="email")
    private String email;

    @Column(name="numberphone")
    private String numberphone;

    @Column(name = "password")
    private String password;

    @Column(name="company")
    private String company;

    @Column(name="role")
    private String role;
}
