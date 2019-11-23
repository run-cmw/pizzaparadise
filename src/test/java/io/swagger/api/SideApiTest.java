package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import io.swagger.model.SideItem;
import io.swagger.repository.SideItemRepository;
import io.swagger.service.SideService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@SpringBootTest(classes = { SideApiController.class, SideService.class })
public class SideApiTest {
    @Autowired
    private SideApi sideApi;

    @MockBean
    private SideItemRepository sideItemRepository;
    private SideItem testSide;
    private SideItem testSide2;
    private SideItem testSide3;

    @Before
    public void setUp() {
        testSide = new SideItem("cheesesticks", "Cheesesticks", 7.99, "appetizer");
        testSide2 = new SideItem("chocolateChipCookie", "Chocolate chip cookie", 1.99, "dessert");
        testSide3 = new SideItem("brownie", "Brownie", 2.49, "dessert");

        when(sideItemRepository.findAll())
                .thenReturn(Stream.of(testSide, testSide2, testSide3).collect(Collectors.toList()));

        when(sideItemRepository.findById("cheesesticks")).thenReturn(Optional.of(testSide));
    }

    @Test
    public void testGetAllSides() {
        final ResponseEntity<List<SideItem>> response = sideApi.getAllSides();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().size());
    }
}
