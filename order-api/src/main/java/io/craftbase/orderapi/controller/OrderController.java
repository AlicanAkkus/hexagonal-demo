package io.craftbase.orderapi.controller;

import io.craftbase.orderapi.manager.OrderManager;
import io.craftbase.orderapi.mapper.OrderVoToResponseMapper;
import io.craftbase.orderapi.model.request.OrderCreateRequest;
import io.craftbase.orderapi.model.request.OrderResponse;
import io.craftbase.orderapi.rest.BaseController;
import io.craftbase.orderapi.rest.Response;
import io.craftbase.orderapi.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController extends BaseController {

    private final OrderManager orderManager;
    private final OrderVoToResponseMapper orderVoToResponseMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<OrderResponse> create(@RequestBody @Valid OrderCreateRequest orderCreateRequest) {
        OrderVo orderVo = orderManager.create(orderCreateRequest);
        OrderResponse orderResponse = orderVoToResponseMapper.map(orderVo);
        return respond(orderResponse);
    }
}