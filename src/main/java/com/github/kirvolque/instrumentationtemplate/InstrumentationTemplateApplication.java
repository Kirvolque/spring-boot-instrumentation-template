package com.github.kirvolque.instrumentationtemplate;

import com.github.kirvolque.instrumentationtemplate.entity.ItemEntity;
import com.github.kirvolque.instrumentationtemplate.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InstrumentationTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstrumentationTemplateApplication.class, args);
        System.out.println("Application has started successfully!");
    }

    @Bean
    public CommandLineRunner populateDatabase(ItemRepository repository) {
        return args -> {
            // Insert sample data into the database
            repository.save(new ItemEntity(null, "Item1"));
            repository.save(new ItemEntity(null, "Item2"));
            repository.save(new ItemEntity(null, "Item3"));

            System.out.println("Database populated with initial data.");
        };
    }
}
