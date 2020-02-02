package io.craftbase.paymentapi.contract.base;

import io.craftbase.paymentapi.TestApplication;
import io.craftbase.paymentapi.order.OrderFacade;
import io.craftbase.paymentapi.order.OrderRepository;
import io.craftbase.paymentapi.payment.PaymentFacade;
import io.craftbase.paymentapi.payment.PaymentRepository;
import io.craftbase.paymentapi.restaurant.MenuItemRepository;
import io.craftbase.paymentapi.restaurant.RestaurantFacade;
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
    protected OrderFacade orderFacade;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private PaymentRepository paymentRepository;

    @MockBean
    private MenuItemRepository menuItemRepository;

    @MockBean
    protected PaymentFacade paymentFacade;

    @MockBean
    protected RestaurantFacade restaurantFacade;

    @BeforeEach
    public void doBeforeEach() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        setUp();
    }

    abstract void setUp();
}