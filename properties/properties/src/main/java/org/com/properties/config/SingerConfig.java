package org.com.properties.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties({SingerProperties.class})
@RequiredArgsConstructor
public class SingerConfig implements ApplicationRunner {
    private final SingerProperties singerProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (String s : singerProperties.getGroup()) {
            log.info(s+"={}", s);
        }

    }
}
