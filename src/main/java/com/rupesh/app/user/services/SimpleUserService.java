package com.rupesh.app.user.services;

import com.rupesh.app.user.convertor.UserConvertor;
import com.rupesh.app.user.entities.User;
import com.rupesh.app.user.models.UserDTO;
import com.rupesh.app.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SimpleUserService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public SimpleUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDTO create(UserDTO userDTO) {
        final User user = UserConvertor.toEntity(userDTO);

        return Optional.ofNullable(userRepository.save(user))
                .map(UserConvertor::toDto)
                .orElse(null);
    }

    @Override
    public UserDTO getByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserConvertor::toDto)
                .orElseThrow(() -> new RuntimeException(String.format("no user found with email '%s'", email)));
    }

    @Override
    public Page<User> getAll(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }
}
