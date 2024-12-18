package com.xworkz.commonmodule.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_table")
//@NamedQuery(name="getNameByEmailAndPassword" ,query="select ue.name from CourseEntity ue where ue.email = :byEmail and ue.password = :byPassword")
@RequiredArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private long phone;
    @Column(name = "alter_email")
    private String alterEmail;
    @Column(name = "alter_phone")
    private long alterPhone;
    private String location;
    private String password;

}
