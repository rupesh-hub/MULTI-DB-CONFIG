package com.rupesh.app.role.services;

import com.rupesh.app.role.models.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO create(final RoleDTO roleDTO);
    List<RoleDTO> getAll();

}
