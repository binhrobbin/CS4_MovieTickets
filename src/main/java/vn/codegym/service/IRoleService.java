package vn.codegym.service;

import vn.codegym.entity.Role;

import java.util.Optional;

public interface IRoleService {
    void save(Role role);
    Optional<Role> findById(Long id);
}
