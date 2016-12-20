package ximagination80;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ximagination80.repos.config.AppConfig;
import ximagination80.repos.config.AppConfigRepository;

import javax.annotation.PreDestroy;

@EnableJpaRepositories
@SpringBootApplication
public class Application {

    @Autowired
    AppConfigRepository appConfigRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Application has been started. Please open url:");
            System.out.println("http://localhost:8080/");

            appConfigRepository.save(new AppConfig("count", "5", "Initial count of cities"));
        };
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Bye bye!");
    }

}
