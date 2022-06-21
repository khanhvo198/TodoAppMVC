package com.mystic.TodoAppMVC.DTO;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDTO {

    private  String firstName;
    private  String lastName;
    private String email;
    private String password;
}
