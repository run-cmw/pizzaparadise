//package io.swagger.configuration;
//
//import io.swagger.repository.ToppingItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//@EnableMongoRepositories(basePackages={"io.swagger.repository"})
//@Configuration
//public class MongoDBConfigurationTopping implements CommandLineRunner {
//
//  @Autowired
//  private ToppingItemRepository toppingItemRepository;
//
//  @Override
//  public void run(String... strings) throws Exception {
//    System.out.println(toppingItemRepository.findAll());
//  }
//
//}