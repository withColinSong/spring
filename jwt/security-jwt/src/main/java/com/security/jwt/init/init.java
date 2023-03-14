package com.security.jwt.init;

import com.security.jwt.entity.User;
import com.security.jwt.enums.RoleType;
import com.security.jwt.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class init {

    private UserRepository userRepository;

    public init(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public void running() {
        Set<RoleType> roleType = new HashSet<>();
        roleType.add(RoleType.USER);

        User user = User.builder()
                .name("song")
                .email("yj.song")
                .password("1234")
                .roleType(RoleType.USER)
                .build();

        userRepository.save(user);
    }
}
