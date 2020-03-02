package io.craftbase.orderapi.contract;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.craftbase.orderapi.contract.base.BaseOrderContractTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.RestAssured.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;

public class OrderContractTest extends BaseOrderContractTest {

	@Test
	public void validate_create_order() throws Exception {
		// given:
			RequestSpecification request = given()
					.header("Content-Type", "application/json")
					.body("{\"note\":\"l\u00FCtfen s\u0131cak gelsin\",\"address\":\"g\u00F6ztepe nidakule\",\"price\":10,\"orderItems\":[{\"id\":1,\"name\":\"hamburger\",\"price\":10}]}");

		// when:
			Response response = given().spec(request)
					.post("/api/v1/orders");

		// then:
			assertThat(response.statusCode()).isEqualTo(201);
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['data']").field("['createdDate']").isEqualTo("2020-02-01T10:21:47");
			assertThatJson(parsedJson).field("['data']").field("['price']").isEqualTo(10);
			assertThatJson(parsedJson).field("['data']").field("['id']").isEqualTo(1);
	}

}
