package com.rupesh.app.user.resources;

import com.rupesh.app.user.models.UserDTO;
import com.rupesh.app.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserResources {

    private final UserService userService;

    @Autowired
    public UserResources(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> create(@RequestBody final UserDTO userDTO) {
        return new ResponseEntity(userService.create(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable("email") final String email) {
        return new ResponseEntity(userService.getByEmail(email), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "0") final int page,
                                    @RequestParam(name = "size", defaultValue = "10") final int size) {
        return new ResponseEntity(userService.getAll(page, size), HttpStatus.OK);
    }


}
