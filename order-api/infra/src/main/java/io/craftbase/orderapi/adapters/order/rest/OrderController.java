package io.craftbase.orderapi.adapters.order.rest;

import io.craftbase.orderapi.common.rest.BaseController;
import io.craftbase.orderapi.common.rest.Response;
import io.craftbase.orderapi.order.OrderFacade;
import io.craftbase.orderapi.order.model.Order;
import io.craftbase.orderapi.adapters.order.rest.dto.OrderCreateRequest;
import io.craftbase.orderapi.adapters.order.rest.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController extends BaseController {

    private final OrderFacade orderFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<OrderResponse> create(@RequestBody @Valid OrderCreateRequest orderCreateRequest) {
        Order order = orderFacade.create(orderCreateRequest.toModel());
        return respond(OrderResponse.fromModel(order));
    }
}