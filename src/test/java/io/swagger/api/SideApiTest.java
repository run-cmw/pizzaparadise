package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.swagger.model.SideItem;
import io.swagger.repository.SideItemRepository;
import io.swagger.service.SideService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SideApiTest {
  @Autowired
  private SideService sideService;

  @MockBean
  private SideItemRepository sideItemRepository;
  private SideItem testSide;
  private SideItem testSide2;
  private SideItem testSide3;

  @Before
  public void setUp() throws Exception {
    testSide = new SideItem("3dae8e058980e20b64e28179", "Cheesesticks", 7.99);
    testSide2 = new SideItem("3dae8e058980e20b64e28177", "Chocolate chip cookie", 1.99);
    testSide3 = new SideItem("1dae8e058980e20b64e28177", "Brownie", 2.49);
  }

  @Test
  public void getAllSidesTest() {
    when(sideItemRepository.findAll()).thenReturn(Stream.of(
        new SideItem("3dae8e058980e20b64e28179", "Cheesesticks", 7.99),
        new SideItem("3dae8e058980e20b64e28177", "Chocolate chip cookie", 1.99),
        new SideItem("1dae8e058980e20b64e28177", "Brownie", 2.49)
    ).collect(Collectors.toList()));

    // Test size of list of sides
    assertEquals(3, sideService.getAllSides().size());
  }

  @Test
  public void getSideByIdTest() {
    final String TEST_SIDE_ID = "3dae8e058980e20b64e28179";

    assertTrue((sideService.getSideById(TEST_SIDE_ID)).equals(testSide));
    assertEquals(sideService.getSideById(TEST_SIDE_ID).toString(), testSide.toString());
  }

  @Test
  public void getSideByIncorrectIdTest() {
    final String NONEXISTENT_ID = "1989";

    assertEquals(sideService.getSideById(NONEXISTENT_ID), null);
  }

  @Test
  public void equalityTest() {
    assertTrue(testSide.equals(testSide));
    assertFalse(testSide.equals(testSide2));
    assertFalse(testSide.equals(null));
  }

  @Test
  public void statusCodeTest() {
    int statusCode = given().get("https://pizza-paradise.herokuapp.com/side").statusCode();
    int statusCode2 = given().get("https://pizza-paradise.herokuapp.com/sides").statusCode();

    assertEquals(statusCode, 200);
    assertEquals(statusCode2, 404);
  }
}