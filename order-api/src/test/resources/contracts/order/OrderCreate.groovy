package order

import org.springframework.cloud.contract.spec.Contract

import static org.springframework.cloud.contract.spec.internal.HttpMethods.HttpMethod.POST

Contract.make {

    name "create order"
    description "should create order"

    request {
        url("/api/v1/orders")
        method(POST)
        headers {
            header(contentType(), applicationJson())
        }

        body(
                """
                    {
                        "note": "lütfen sıcak gelsin",
                        "address": "göztepe nidakule",
                        "price": 10,
                        "orderItems": [
                            {
                                "id": 1,
                                "name": "hamburger",
                                "price": 10
                            }
                        ]
                    }
                """
        )
    }

    response {
        status CREATED()
        body(
                """
                {
                    "data": {
                        "id": 1,
                        "price": 10,
                        "createdDate": "2020-02-01T10:21:47"
                    }
                }
                """
        )
    }
}