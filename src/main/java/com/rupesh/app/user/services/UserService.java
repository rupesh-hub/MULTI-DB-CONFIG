package com.rupesh.app.user.services;

import com.rupesh.app.user.entities.User;
import com.rupesh.app.user.models.UserDTO;
import org.springframework.data.domain.Page;

public interface UserService {

    UserDTO create(final UserDTO userDTO);
    UserDTO getByEmail(final String email);
    Page<User> getAll(final int page, final int size);

}
