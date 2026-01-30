package com.smartservice.smart_service_platform.admin;

import com.smartservice.smart_service_platform.user.model.Role;
import com.smartservice.smart_service_platform.user.model.User;
import com.smartservice.smart_service_platform.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminBootstrap implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        String adminEmail = "admin@smartservice.com";

        if (userRepository.findByEmail(adminEmail).isPresent()) {
            return;
        }

        User admin = new User();
        admin.setEmail(adminEmail);
        admin.setName("Super Admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ADMIN);

        userRepository.save(admin);

        System.out.println("✅ ADMIN CREATED: admin@smartservice.com / admin123");
    }
}
