package org.com.properties.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties("singer")
@ConstructorBinding
public class SingerProperties {

    private final String[] group;
    private final String[] album;

    public SingerProperties(String[] group,
                            String[] album) {
        this.group = group;
        this.album = album;
    }
}
