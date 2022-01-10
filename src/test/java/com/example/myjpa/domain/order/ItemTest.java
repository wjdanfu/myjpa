package com.example.myjpa.domain.order;

import org.hibernate.tuple.entity.EntityTuplizerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ItemTest {

    @Autowired
    EntityManagerFactory enf;

    @Test
    void 테스트(){

        EntityManager entityManager = enf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Food food = new Food();

        food.setChef("정병우");
        food.setPrice(1000);
        food.setStockQuantity(100);
        entityManager.persist(food);

        transaction.commit();
    }
}