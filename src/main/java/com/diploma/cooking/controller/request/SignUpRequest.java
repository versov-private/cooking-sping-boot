package com.diploma.cooking.controller.request;

import com.diploma.cooking.model.Role;
import com.diploma.cooking.model.User;
import com.diploma.cooking.model.addition.RoleName;
import com.diploma.cooking.model.addition.UserSex;
import com.diploma.cooking.service.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<String> roles;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private UserSex sex;

    public SignUpRequest() {
    }

    public User toEntity(PasswordEncoder encoder, RoleService roleService) {
        Set<Role> roles = new HashSet<>();
        getRoles().forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "user":
                    Role userRole = roleService.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);

                    break;
            }
        });

        User user = new User();
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setUsername(getUsername());
        user.setEmail(getEmail());
        user.setSex(getSex());
        user.setRoles(roles);
        user.setPassword(encoder.encode(getPassword()));
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSex getSex() {
        return sex;
    }

    public void setSex(UserSex sex) {
        this.sex = sex;
    }
}
