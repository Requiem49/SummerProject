package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    private int id;

    @Column(name = "name")
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private int age;

    @Column
    private String nation;

    @Column
    private String passwd;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
}
