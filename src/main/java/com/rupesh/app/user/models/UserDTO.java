package com.rupesh.app.user.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
