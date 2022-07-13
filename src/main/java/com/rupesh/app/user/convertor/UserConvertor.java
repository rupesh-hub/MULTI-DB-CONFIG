package com.rupesh.app.user.convertor;

import com.rupesh.app.user.entities.User;
import com.rupesh.app.user.models.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserConvertor {

    public static User toEntity(final UserDTO userDTO) {
        return User
                .builder()
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phone(userDTO.getPhone())
                .build();
    }


    public static UserDTO toDto(final User user) {

        return UserDTO
                .builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .build();

    }

    public static List<UserDTO> toList(final List<User> userList) {
        return userList
                .stream()
                .map(user -> toDto(user))
                .collect(Collectors.toList());
    }

}
