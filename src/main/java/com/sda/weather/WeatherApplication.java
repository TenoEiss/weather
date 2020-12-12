package com.sda.weather;

import com.sda.weather.security.User;
import com.sda.weather.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@RequiredArgsConstructor
@SpringBootApplication
public class WeatherApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args)
    {
        SpringApplication.run(WeatherApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        User mainUser = new User();
        mainUser.setUsername("teno");
        mainUser.setPassword(passwordEncoder.encode("1234"));
        mainUser.setAuthorities(Collections.singletonList(()->"ROLE_USER"));
        userRepository.save(mainUser);

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("root"));
        adminUser.setAuthorities(Collections.singletonList(()->"ROLE_ADMIN"));
        userRepository.save(adminUser);
    }
}
