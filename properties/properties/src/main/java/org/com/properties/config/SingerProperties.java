package org.com.properties.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@Getter
@ConfigurationProperties("singer")
@ConstructorBinding
@RequiredArgsConstructor
public class SingerProperties {

    private final List<String> group;
    private final List<String> album;

}
