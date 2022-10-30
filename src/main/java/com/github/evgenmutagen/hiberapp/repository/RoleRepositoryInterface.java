package com.github.evgenmutagen.hiberapp.repository;

import com.github.evgenmutagen.hiberapp.model.Role;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RoleRepositoryInterface {
    List<Role> findAll() throws SQLException;

    Optional<Role> findById(Integer id) throws SQLException;
}
