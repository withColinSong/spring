package com.security.jwt.config;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.security.jwt.entity.QUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueryDslConfigTest {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    public void Test() {
        QUser qUser = QUser.user;
        jpaQueryFactory.selectFrom(qUser).fetch();

    }
}