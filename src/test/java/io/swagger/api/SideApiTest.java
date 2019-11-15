package io.swagger.api;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import io.swagger.model.SideItem;
import io.swagger.repository.SideItemRepository;
import io.swagger.service.SideService;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:/application-test.properties")
public class SideApiTest {
  @Autowired
  private SideService sideService;

  @MockBean
  private SideItemRepository sideItemRepository;
  private SideItem testSide;
  private SideItem testSide2;
  private SideItem testSide3;

  @Before
  public void setUp() {
    testSide = new SideItem("cheeseSticks", "Cheesesticks", 7.99, "appetizer");
    testSide2 = new SideItem("chocolateChipCookie", "Chocolate chip cookie", 1.99, "dessert");
    testSide3 = new SideItem("brownie", "Brownie", 2.49, "dessert");

    when(sideItemRepository.findAll()).thenReturn(Stream.of(
        testSide,
        testSide2,
        testSide3
    ).collect(Collectors.toList()));
  }

  @Test
  public void getAllSidesTest() {
    // Test size of list of sides
    assertEquals(3, sideService.getAllSides().size());
  }

  @Test
  public void getSideByIdTest() {
    final String TEST_SIDE_ID = "cheeseSticks";

    assertEquals(sideService.getSideById(TEST_SIDE_ID), testSide);
    assertEquals(sideService.getSideById(TEST_SIDE_ID).toString(), testSide.toString());
  }

  @Test
  public void getSideByIncorrectIdTest() {
    final String NONEXISTENT_ID = "1989";

    assertNull(sideService.getSideById(NONEXISTENT_ID));
  }

  @Test
  public void equalityTest() {
    assertEquals(testSide, testSide);
    assertNotEquals(testSide, testSide2);
    assertNotEquals(testSide, null);
  }

  @Test
  public void statusCodeTest() {
    int statusCodeOk = given().get("https://fierce-hamlet-19207.herokuapp.com/side").statusCode();
    int statusCodeNotFound = given().get("https://fierce-hamlet-19207.herokuapp.com/sides").statusCode();

    assertEquals(statusCodeOk, 200);
    assertEquals(statusCodeNotFound, 404);
  }
}