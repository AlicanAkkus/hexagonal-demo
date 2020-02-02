package order

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "create order"
    description "should create order"

    request {
        url "/api/v1/orders"
        headers {
            header(contentType(), applicationJson())
        }
        method POST()
        body(
                """
                {
                    "note": "mukemmel burgerdan istiyorum",
                    "address": "goztepe nidakule",
                    "price": 30.00,
                    "orderItems": [
                        {
                            "id": 1,
                            "name": "mukemmel burger - 1",
                            "price": 15.00
                        },
                        {
                            "id": 2,
                            "name": "mukemmel burger - 2",
                            "price": 15.00
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
                        "price": 30.00,
                        "createdDate": "2020-02-01T10:21:47"
                    }
                }
                """
        )
    }
}