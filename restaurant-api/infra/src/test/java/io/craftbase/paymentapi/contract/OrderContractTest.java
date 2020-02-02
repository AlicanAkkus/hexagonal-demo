package io.craftbase.paymentapi.contract;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.craftbase.paymentapi.contract.base.BaseOrderContractTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

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
					.body("{\"note\":\"mukemmel burgerdan istiyorum\",\"address\":\"goztepe nidakule\",\"price\":30.00,\"orderItems\":[{\"id\":1,\"name\":\"mukemmel burger - 1\",\"price\":15.00},{\"id\":2,\"name\":\"mukemmel burger - 2\",\"price\":15.00}]}");

		// when:
			Response response = given().spec(request)
					.post("/api/v1/orders");

		// then:
			assertThat(response.statusCode()).isEqualTo(201);
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['data']").field("['createdDate']").isEqualTo("2020-02-01T10:21:47");
			assertThatJson(parsedJson).field("['data']").field("['price']").isEqualTo(30.00);
			assertThatJson(parsedJson).field("['data']").field("['id']").isEqualTo(1);
	}

}
