package io.craftbase.orderapi.contract.base;

import io.craftbase.orderapi.TestApplication;
import io.craftbase.orderapi.manager.OrderManager;
import io.craftbase.orderapi.repository.MenuItemJpaRepository;
import io.craftbase.orderapi.repository.OrderJpaRepository;
import io.craftbase.orderapi.repository.PaymentJpaRepository;
import io.craftbase.orderapi.service.PaymentService;
import io.craftbase.orderapi.service.RestaurantService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Tag("contractTest")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = TestApplication.class)
@TestPropertySource(properties = "spring.profiles.active=contractTest")
public abstract class AbstractContractTest {

    @LocalServerPort
    private Integer port;

    @MockBean
    protected OrderManager orderManager;

    @MockBean
    private OrderJpaRepository orderJpaRepository;

    @MockBean
    private PaymentJpaRepository paymentJpaRepository;

    @MockBean
    private MenuItemJpaRepository menuItemJpaRepository;

    @MockBean
    protected PaymentService paymentService;

    @MockBean
    protected RestaurantService restaurantService;

    @BeforeEach
    public void doBeforeEach() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        setUp();
    }

    abstract void setUp();
}