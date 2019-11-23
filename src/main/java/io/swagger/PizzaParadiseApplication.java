package io.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.repository.PizzaSizeRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"io.swagger", "io.swagger.api", "io.swagger.configuration"})
public class PizzaParadiseApplication implements CommandLineRunner {

  @Autowired
  PizzaSizeRepository pizzaSizeRepository;

  @Override
  public void run(String... arg0) {
    // This code ABSOLUTELY MUST BE FACTORED OUT
    // Perhaps PizzaSize.initialize(pizzaSizeRepository)
    if (pizzaSizeRepository.count() == 0) {
      // ... insert some sizes
    }
  }

  public static void main(String[] args) {
    SpringApplication.run(PizzaParadiseApplication.class, args);
  }
}
