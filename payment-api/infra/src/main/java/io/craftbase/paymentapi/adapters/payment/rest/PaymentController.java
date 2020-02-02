package io.craftbase.paymentapi.adapters.payment.rest;

import io.craftbase.paymentapi.adapters.payment.rest.dto.PaymentCreateRequest;
import io.craftbase.paymentapi.adapters.payment.rest.dto.PaymentResponse;
import io.craftbase.paymentapi.common.rest.BaseController;
import io.craftbase.paymentapi.common.rest.Response;
import io.craftbase.paymentapi.payment.PaymentFacade;
import io.craftbase.paymentapi.payment.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController extends BaseController {

    private final PaymentFacade paymentFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<PaymentResponse> create(@RequestBody @Valid PaymentCreateRequest paymentCreateRequest) {
        Payment payment = paymentFacade.pay(paymentCreateRequest.toModel());
        return respond(PaymentResponse.fromModel(payment));
    }
}