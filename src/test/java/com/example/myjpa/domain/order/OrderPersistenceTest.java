package com.example.myjpa.domain.order;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.myjpa.domain.order.OrderStatus.OPENED;

@Slf4j
@SpringBootTest
public class OrderPersistenceTest {

    @Autowired
    EntityManagerFactory emf;

    @Test
    void member_insert() {
        Member member = new Member();
        member.setName("kanghonggu");
        member.setAddress("서울시 동작구(만) 움직이면 쏜다.");
        member.setAge(33);
        member.setNickName("guppy.kang");
        member.setDescription("백앤드 개발자에요.");

        ArrayList order = new ArrayList<>();

        Order order1 = new Order();
        order1.setOrderDatetime(LocalDateTime.now());
        order1.setOrderStatus(OPENED);
        order1.setUuid(UUID.randomUUID().toString());
        order1.setMemo("옷좀 보내주세요");
        order1.setMember(member);

        Order order2 = new Order();
        order2.setOrderDatetime(LocalDateTime.now());
        order2.setOrderStatus(OPENED);
        order2.setUuid(UUID.randomUUID().toString());
        order2.setMemo("옷좀 보내주세요2");
        order2.setMember(member);

        order.add(order1);
        order.add(order2);

        member.setOrders(order);
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(member);
        entityManager.persist(order1);
        entityManager.persist(order2);

        transaction.commit();


//        log.info("{}",order.getMember().getAddress());
//        log.info("{}",order.getMember().getOrders().size());
    }

    @Test
    void 잘못된_설계() {
        Member member = new Member();
        member.setName("kanghonggu");
        member.setAddress("서울시 동작구(만) 움직이면 쏜다.");
        member.setAge(33);
        member.setNickName("guppy.kang");
        member.setDescription("백앤드 개발자에요.");

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(member);
        Member memberEntity = entityManager.find(Member.class, 1L);

        Order order = new Order();
        order.setUuid(UUID.randomUUID().toString());
        order.setOrderDatetime(LocalDateTime.now());
        order.setOrderStatus(OPENED);
        order.setMemo("부재시 전화주세요.");
        //order.setMemberId(memberEntity.getId()); // 외래키를 직접 지정

        entityManager.persist(order);
        transaction.commit();

        Order orderEntity = entityManager.find(Order.class, order.getUuid());
        // FK 를 이용해 회원 다시 조회
        //Member orderMemberEntity = entityManager.find(Member.class, orderEntity.getMemberId());
        // orderEntity.getMember() // 객체중심 설계라면 이렇게 해야하지 않을까?
        //log.info("nick : {}", orderMemberEntity.getNickName());
    }
}
