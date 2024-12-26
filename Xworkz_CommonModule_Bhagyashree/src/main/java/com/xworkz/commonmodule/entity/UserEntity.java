package com.xworkz.commonmodule.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_table")
@RequiredArgsConstructor
@NamedQuery(name = "getNameByEmailAndPassword" ,query="select ue.name from UserEntity ue where ue.email = :byEmail and ue.password = :byPassword")
@NamedQuery(name = "countByName" , query ="select count(*) from UserEntity ue where ue.name = :SetName")
@NamedQuery(name = "countByEmail", query = "select count(*) from UserEntity ue where ue.email = :SetEmail")
@NamedQuery(name = "countByPhone", query = "select count(*) from UserEntity ue where ue.phone = :SetPhone")
@NamedQuery(name = "countByAlterEmail", query = "select count(*) from UserEntity ue where ue.alterEmail = :SetAlterEmail")
@NamedQuery(name = "countByAlterPhone", query = "select count(*) from UserEntity ue where ue.alterPhone = :SetAlterPhone")
@NamedQuery(name = "getAll", query = "select ue from UserEntity ue where ue.email = :setEmail and ue.password = :setPassword")

@NamedQuery(name = "updatePasswordByName",query = "update UserEntity ue set ue.password = :setNewPassword , ue.count = :setCount where ue.name = :nameBy")
@NamedQuery(name = "getAllByEmail", query="select ue from UserEntity ue  where ue.email = :byEmail")

@NamedQuery(name = "updateCount",query="update UserEntity ue set ue.count = :setCount where ue.email = :byEmail")
@NamedQuery(name = "resetCount",query="update UserEntity ue set ue.count = :setCount where ue.email = :byEmail")
@NamedQuery(name = "getByNamePassword" , query = "select ue from UserEntity ue where ue.name = :setName and ue.password = :setPassword")
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
    private int count;
}
