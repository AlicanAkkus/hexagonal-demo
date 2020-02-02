package io.craftbase.orderapi.adapters.payment;

import io.craftbase.orderapi.adapters.payment.dto.PaymentCreateRequest;
import io.craftbase.orderapi.common.rest.Response;
import io.craftbase.orderapi.configuration.PaymentApiConfiguration;
import io.craftbase.orderapi.order.PaymentPort;
import io.craftbase.orderapi.order.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.springframework.http.HttpMethod.POST;

@Service
@RequiredArgsConstructor
public class PaymentAdapter implements PaymentPort {

    private final RestTemplate restTemplate;
    private final PaymentApiConfiguration paymentApiConfiguration;
    private final ParameterizedTypeReference<Response<Payment>> responseParameterizedTypeReference = new ParameterizedTypeReference<>() {

    };

    @Override
    public Payment pay(Long cardId, BigDecimal price, String referenceCode) {
        PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
                .cardId(cardId)
                .price(price)
                .referenceCode(referenceCode)
                .build();

        Response<Payment> response = restTemplate.exchange(paymentApiConfiguration.getBaseUrl() + "/api/v1/payments", POST, new HttpEntity<>(paymentCreateRequest), responseParameterizedTypeReference).getBody();

        return response.getData();
    }
}