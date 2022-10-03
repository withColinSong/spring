
# 1. WebMvcConfigurer 사용하기
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:63342")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*"); 
            }
        };
    }
}
```
## 1.1. addMapping
- CORS를 적용할 URL패턴을 정의
```java
.addMapping("/api/v1/*");
```

## 1.2 allowedOrigins
- Origin 설정
```java
.allowedOrigins("http://localhost:8080", "http://localhost:8081");
```
- 참고 : port 없을 시 cors 해결되지 않는다. 
 > `http://localhost`

### 1.2.1.
```java
.allowedOriginPatterns("*localhost:63342")
```

## 1.3. allowedMethods
- http method
```java
.allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS")  
```

## 1.4. allowedHeaders
- http header
```java
.allowedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");

```

# 참고문헌
- https://reflectoring.io/spring-cors/