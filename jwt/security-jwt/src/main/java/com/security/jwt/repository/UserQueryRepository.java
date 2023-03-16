package com.security.jwt.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.security.jwt.entity.QUser;
import com.security.jwt.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserQueryRepository {

    private JPAQueryFactory jpaQueryFactory;

    public UserQueryRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public User fndByUser(String email, String password) {
            return Optional.ofNullable(
                    jpaQueryFactory
                            .selectFrom(QUser.user)
                            .where(QUser.user.email.eq(email))
                            .where(QUser.user.password.eq(password))
                            .fetchOne()
            ).orElseThrow(() -> new IllegalArgumentException("no such data"));
    }
}
