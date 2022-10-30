package com.github.evgenmutagen.hiberapp.repository;

import com.github.evgenmutagen.hiberapp.model.Meal;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MealRepositoryInterface {

    List<Meal> findAll() throws SQLException;

    Optional<Meal> findById(Integer id) throws SQLException;

    List<Meal> findAllMealsByUser(Integer idUser) throws SQLException;

    List<Meal> findGeneratedMeal() throws SQLException;
}
