package io.craftbase.orderapi.common.rest;

import java.util.List;

public class BaseController {

    public <T> Response<DataResponse<T>> respond(List<T> items) {
        return ResponseBuilder.build(items);
    }

    public <T> Response<DataResponse<T>> respond(List<T> items, int page, int size, Long totalSize) {
        return ResponseBuilder.build(items, page, size, totalSize);
    }

    protected <T> Response<T> respond(T item) {
        return ResponseBuilder.build(item);
    }

    protected Response respond(ErrorResponse errorResponse) {
        return ResponseBuilder.build(errorResponse);
    }
}