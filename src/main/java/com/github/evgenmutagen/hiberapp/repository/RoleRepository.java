package com.github.evgenmutagen.hiberapp.repository;

import com.github.evgenmutagen.hiberapp.model.Role;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RoleRepository implements RoleRepositoryInterface {

    private final SessionFactory sessionFactory;

    @Override
    public List<Role> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Role> roles = session.createQuery("FROM Role r", Role.class).list();
            transaction.commit();
            return roles;
        }
    }

    @Override
    public Optional<Role> findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Role role = session.get(Role.class, id);
            transaction.commit();
            return Optional.ofNullable(role);
        }
    }
}
