package com.sda.weather;

import com.sda.weather.security.User;
import com.sda.weather.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Slf4j
@EnableSwagger2
@RequiredArgsConstructor
@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class WeatherApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args)
    {
        SpringApplication.run(WeatherApplication.class, args);
    }

    @Scheduled(cron = "* */10 5-7 * * *")
    public void generateReport(){
        log.info("At this point we have "+ userRepository.count() + " users");
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
