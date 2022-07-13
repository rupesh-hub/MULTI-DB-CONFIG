package com.rupesh.app.role.convertor;

import com.rupesh.app.role.entities.Role;
import com.rupesh.app.role.models.RoleDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RoleConvertor {

    public static Role toEntity(final RoleDTO roleDTO) {
        return Role
                .builder()
                .name(roleDTO.getName())
                .build();
    }

    public static RoleDTO toDto(final Role role) {
        return RoleDTO
                .builder()
                .name(role.getName())
                .build();
    }

    public static List<RoleDTO> toList(final List<Role> roleList) {
        return roleList
                .stream()
                .map(role ->
                        toDto(role))
                .collect(Collectors.toList());
    }

}
