package com.Task01_CashCardFullStack.CashCardBackend.Dao;

import com.Task01_CashCardFullStack.CashCardBackend.Entities.CashCard;
import com.Task01_CashCardFullStack.CashCardBackend.Entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserDaoImpl implements UserDao{

    private EntityManager entityManager;
    @Autowired
    public UserDaoImpl(EntityManager entityManager1){
        this.entityManager = entityManager1;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("FROM User where username = :theUsername", User.class);
        query.setParameter("theUsername", username);
        List<User> users = query.getResultList();
        return users.getFirst();
    }
}
