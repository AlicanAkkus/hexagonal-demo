package payment

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "create payment"
    description "should create payment"

    request {
        url "/api/v1/payments"
        headers {
            header(contentType(), applicationJson())
        }
        method POST()
        body(
                """
                {
                    "cardId": 1,
                    "price": 10.00,
                    "referenceCode": "ref"
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
                        "cardId": 1,
                        "price": 10.00,
                        "referenceCode": "ref"
                    }
                }
                """
        )
    }
}