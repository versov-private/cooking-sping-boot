package com.diploma.cooking.controller;


import com.diploma.cooking.controller.request.LoginRequest;
import com.diploma.cooking.controller.request.SignUpRequest;
import com.diploma.cooking.controller.response.JwtResponse;
import com.diploma.cooking.controller.response.MessageResponse;
import com.diploma.cooking.model.Role;
import com.diploma.cooking.model.User;
import com.diploma.cooking.model.addition.RoleName;
import com.diploma.cooking.security.jwt.JwtProvider;
import com.diploma.cooking.service.RoleService;
import com.diploma.cooking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    private final JwtProvider jwtProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, RoleService roleService, PasswordEncoder encoder, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new MessageResponse("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new MessageResponse("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = signUpRequest.toEntity(encoder, roleService);
        userService.saveOrUpdate(user);

        return new ResponseEntity<>(new MessageResponse("User registered successfully!"), HttpStatus.OK);
    }
}
