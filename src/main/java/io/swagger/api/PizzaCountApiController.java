package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2019-09-26T03:54:46.062Z[GMT]")
@RestController
public class PizzaCountApiController {

  private final int SMALL_PIZZA_CAL = 1601;
  private final int MED_PIZZA_CAL = 2236;
  private final int LARGE_PIZZA_CAL = 3384;

  private final int ADULT_CAL = 850;
  private final int KID_CAL = 700;


  @GetMapping("/pizzaCount")
  @ApiOperation(
      value = "suggests the number of pizzas required to feed a given number of people",
      tags = {
          "pizza count",
      })
  public Integer getNumOfPizzaByInput(Integer adult, Integer kid) {
    if (adult == 0 && kid == 0) {
      return 0;
    }
    int totalCal = adult * ADULT_CAL + kid * KID_CAL;
    return totalPizzaNeed(totalCal);

  }

  private Integer totalPizzaNeed(int totalCal) {
    Integer calResults = totalCal;
    Integer smallCount = 0;
    Integer mediumCount = 0;
    Integer largeCount = 0;
    List<Integer> pizzaCount = new ArrayList<Integer>();

    while (calResults > 0) {
      if (calResults <= SMALL_PIZZA_CAL) {
        smallCount = +1;
      } else if (calResults <= MED_PIZZA_CAL) {
        mediumCount = +1;
      } else if (calResults >= LARGE_PIZZA_CAL) {
        largeCount = +1;
        calResults += totalPizzaNeed(calResults - LARGE_PIZZA_CAL);
      }
    }
    pizzaCount.add(smallCount);
    pizzaCount.add(mediumCount);
    pizzaCount.add(largeCount);

    return pizzaCount;
  }
}
