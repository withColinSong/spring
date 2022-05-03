package cos.jwt.controller;

import cos.jwt.model.User;
import cos.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("home")
    public String home() {
        return "<h1>home</h1>";
    }

    @PostMapping("/token")
    public String token() {
        return "<h1>token</h1>";
    }

    @PostMapping("join")
    public String join(@RequestBody User user) {
        System.out.println("hello");
        System.out.println("user.getUsername()" + user.getUsername());

        user.setUsername(user.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");

        userRepository.save(user);
        return "join 성공";
    }

    // user,manager, admin
    @GetMapping("/api/v1/user")
    public String user() {
        return "user";
    }

    // manager, admin
    @GetMapping("/api/v1/manager")
    public String manager() {
        return "manager";
    }

    // admin
    @GetMapping("/api/v1/admin")
    public String admin() {
        return "admin";
    }
}
