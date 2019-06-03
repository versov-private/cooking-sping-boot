package com.diploma.cooking.controller;

import com.diploma.cooking.model.Role;
import com.diploma.cooking.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> findAll() {
        List<Role> roles = roleService.findAll();
        return roles.isEmpty()
                ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id) {
        return roleService.findById(id)
                .map(role -> new ResponseEntity<>(role, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> save(@RequestBody Role role) {
        return role.getId().intValue() == 0
                ? new ResponseEntity<>(roleService.saveOrUpdate(role), HttpStatus.CREATED)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/roles")
    public ResponseEntity<Role> update(@RequestBody Role role) {
        return role.getId().intValue() > 0
                ? new ResponseEntity<>(roleService.saveOrUpdate(role), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Role> delete(@PathVariable Long id) {
        return roleService.findById(id)
                .map(role -> {
                    roleService.delete(role);
                    return new ResponseEntity<>(role, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));
    }
}
