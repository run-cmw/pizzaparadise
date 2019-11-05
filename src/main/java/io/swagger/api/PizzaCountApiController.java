package io.swagger.api;

import io.swagger.annotations.ApiOperation;
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
  public String getNumOfPizzaByInput(int adult, int kid) {
    if(adult == 0 && kid == 0) {return ""; }
    int totalCal = adult * ADULT_CAL + kid * KID_CAL;
    return totalPizzaNeed(totalCal);

  }

  private String totalPizzaNeed(int totalCal) {
    String result = "";
    if(totalCal < SMALL_PIZZA_CAL) {
      result += "1 Small Pizza(11\")";
    } else if(totalCal <MED_PIZZA_CAL) {
      result +=  "1 Medium Pizza(13\")";
    } else if(totalCal < LARGE_PIZZA_CAL) {
      result += "1 Large Pizza(15\")";
    } else {
      result += "1 Large Pizza(15\")";
      result += " and ";
      result += totalPizzaNeed(totalCal - LARGE_PIZZA_CAL);
    }
    return result;
  }

}
