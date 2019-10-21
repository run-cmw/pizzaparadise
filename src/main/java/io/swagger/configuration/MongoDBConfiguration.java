package io.swagger.configuration;

import io.swagger.repository.SpecialItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages={"io.swagger.repository"})
@Configuration
public class MongoDBConfiguration implements CommandLineRunner {

  @Autowired
  private SpecialItemRepository specialItemRepository;

  @Override
  public void run(String... strings) throws Exception {
    System.out.println(specialItemRepository.findAll());
  }
}
