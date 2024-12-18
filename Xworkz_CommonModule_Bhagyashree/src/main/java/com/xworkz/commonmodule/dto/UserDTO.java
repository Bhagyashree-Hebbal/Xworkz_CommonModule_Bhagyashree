package com.xworkz.commonmodule.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private long phone;
    private String alterEmail;
    private long alterPhone;
    private String location;
}
