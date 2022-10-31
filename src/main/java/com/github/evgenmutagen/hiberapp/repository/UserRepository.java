package com.github.evgenmutagen.hiberapp.repository;

import com.github.evgenmutagen.hiberapp.model.User;
import com.github.evgenmutagen.hiberapp.proxy.UserProxy;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository implements UserRepositoryInterface {
    private final SessionFactory sessionFactory;

    @Override
    public User save(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        return proxyUser(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return Optional.of(proxyUser(user));
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("select u FROM User u", User.class).list();
            session.getTransaction().commit();
            List<User> list = new ArrayList<>();
            for (User u : users) {
                User user = proxyUser(u);
                list.add(user);
            }
            return list;
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User u").executeUpdate();
            session.getTransaction().commit();
        }
    }

    private User proxyUser(User user) {
        User proxy = new UserProxy().contextInitialized();
        proxy.setId(user.getId());
        proxy.setName(user.getName());
        proxy.setEmail(user.getEmail());
        proxy.setCaloriesPerDay(user.getCaloriesPerDay());
        proxy.setRole(user.getRole());
        return proxy;
    }
}
