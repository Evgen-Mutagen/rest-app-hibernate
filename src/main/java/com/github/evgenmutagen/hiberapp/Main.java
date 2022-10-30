package com.github.evgenmutagen.hiberapp;

import com.github.evgenmutagen.hiberapp.data.BaseConnection;
import com.github.evgenmutagen.hiberapp.model.User;
import com.github.evgenmutagen.hiberapp.repository.MealRepository;
import com.github.evgenmutagen.hiberapp.repository.MealRepositoryInterface;
import com.github.evgenmutagen.hiberapp.repository.UserRepository;
import com.github.evgenmutagen.hiberapp.repository.UserRepositoryInterface;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserRepositoryInterface userRepository = new UserRepository(BaseConnection.getInstance());

        System.out.println(userRepository.findAll());

        System.out.println(userRepository.save(User.builder()
                .name("Pikachu")
                .email("pikachu@mail.ru")
                .caloriesPerDay(2003)
                .build()));

        System.out.println(userRepository.findById(100004));

        MealRepositoryInterface mealRepository = new MealRepository(BaseConnection.getInstance());

        System.out.println(mealRepository.findGeneratedMeal());
    }
}
