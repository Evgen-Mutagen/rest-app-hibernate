package com.github.evgenmutagen.hiberapp.repository;

import com.github.evgenmutagen.hiberapp.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryInterface {

    User save(User user) throws SQLException;

    Optional<User> findById(Integer id) throws SQLException;

    List<User> findAll() throws SQLException;

    void deleteById(Integer id) throws SQLException;

    void deleteAll() throws SQLException;
}
