package com.mystic.TodoAppMVC.DTO;


import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDTO {
    private final String firstName;
    private final String lastName;
    private String email;
    private String password;
}
