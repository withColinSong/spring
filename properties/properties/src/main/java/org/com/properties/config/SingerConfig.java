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

        log.info("singerProperties.group[0]={}", singerProperties.getGroup()[0]);
        log.info("singerProperties.group[1]={}", singerProperties.getGroup()[1]);
        log.info("singerProperties.group[2]={}", singerProperties.getGroup()[2]);

    }
}
