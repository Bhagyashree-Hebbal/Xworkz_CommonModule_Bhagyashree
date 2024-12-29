package com.xworkz.commonmodule.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class UserDTO {
    private int id;

    @Size(min=3,max=10,message="name length must be in range of 3-10")
    //@Min(3)
    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @Digits(integer = 10, fraction = 0)
    @NotNull
    private long phone;

    @Email
    @NotNull
    private String alterEmail;

    @Digits(integer = 10, fraction = 0)
    @NotNull
    private long alterPhone;
    private String location;
}
