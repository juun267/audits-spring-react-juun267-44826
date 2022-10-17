package com.wovenreviews.java.config;

import com.wovenreviews.java.model.Audit;
import com.wovenreviews.java.model.Project;
import com.wovenreviews.java.model.User;
import com.wovenreviews.java.repo.AuditRepository;
import com.wovenreviews.java.repo.ProjectRepository;
import com.wovenreviews.java.repo.UserRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Component
@ConditionalOnProperty(value = "ENV", havingValue = "test")
public class InitDBWithData {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    private PasswordEncoder encoder;

    private Faker faker;
    private Random random;

    @PostConstruct
    public void init() {
        this.random = new Random();
        this.faker = Faker.instance();

        List<String> events = Arrays.asList(
                "authentication:logout",
                "authentication:new",
                "invoice:create",
                "invoice:send",
                "settings:change");

        List<String> statuses = Arrays.asList("started", "in progress", "finished", "error");

        User user1 = new User("test@example.com",
                encoder.encode("password"),
                "User",
                "Test",
                Instant.now());

        User user2 = new User("test2@example.com",
                encoder.encode("password"),
                "User",
                "Test 2",
                Instant.now());

        List<User> users = Arrays.asList(user1, user2);

        userRepository.saveAll(users);

        List<Project> projects = Stream.generate(() -> random.nextInt(users.size() - 1))
                .limit(10)
                .map(users::get)
                .map(user -> new Project(user, faker.commerce().productName(), faker.lorem().paragraph()))
                .toList();

        projectRepository.saveAll(projects);

        List<Audit> audits = Stream.generate(() -> random.nextInt(users.size() - 1))
                .limit(100)
                .map(users::get)
                .map(user -> {
                    String event = events.get(random.nextInt(events.size() - 1));
                    String status = statuses.get(random.nextInt(statuses.size() - 1));
                    return new Audit(user.getId(), event, status, faker.lorem().paragraph());
                })
                .toList();

        auditRepository.saveAll(audits);
    }

}
