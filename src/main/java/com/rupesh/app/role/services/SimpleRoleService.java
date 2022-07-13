package com.rupesh.app.role.services;

import com.rupesh.app.role.convertor.RoleConvertor;
import com.rupesh.app.role.entities.Role;
import com.rupesh.app.role.models.RoleDTO;
import com.rupesh.app.role.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleRoleService implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public SimpleRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO create(final RoleDTO roleDTO) {
        final Role role = RoleConvertor.toEntity(roleDTO);

        return Optional.ofNullable(roleRepository.save(role))
                .map(RoleConvertor::toDto)
                .orElse(null);
    }

    @Override
    public List<RoleDTO> getAll() {
        return Optional.ofNullable(roleRepository.findAll())
                .map(RoleConvertor::toList)
                .orElse(null);
    }
}
