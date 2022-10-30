package com.github.evgenmutagen.hiberapp.repository;

import com.github.evgenmutagen.hiberapp.model.Meal;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class MealRepository implements MealRepositoryInterface {
    private final SessionFactory sessionFactory;

    @Override
    public List<Meal> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Meal> meals = session.createQuery("select m from Meal m", Meal.class).list();
            transaction.commit();
            return meals;
        }
    }

    @Override
    public Optional<Meal> findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Optional<Meal> meal = Optional.ofNullable(session.get(Meal.class, id));
            transaction.commit();
            return meal;
        }
    }

    @Override
    public List<Meal> findAllMealsByUser(Integer userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Meal> meals = session.createQuery("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.date DESC", Meal.class)
                    .setParameter("userId", userId)
                    .getResultList();
            transaction.commit();
            return meals;
        }
    }

    @Override
    public List<Meal> findGeneratedMeal() {
        createGeneratedMeal();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Meal> meals = session.createQuery("SELECT m from Meal m where m.calories >= 2000", Meal.class).list();
            transaction.commit();
            return meals;
        }
    }

    private void createGeneratedMeal() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            for (int i = 1; i <= 20_000_000; i++) {
                Meal meal = new Meal();
                meal.setCalories((int) (Math.random() * 10000));
                meal.setDescription("some meals");
                meal.setDate(LocalDate.ofEpochDay(2022 - 01 - 31));
                session.save(meal);
            }
            transaction.commit();
        }
    }
}
