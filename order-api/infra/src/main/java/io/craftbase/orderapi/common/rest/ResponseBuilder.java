package io.craftbase.orderapi.common.rest;

import java.util.List;

public class ResponseBuilder {

    public static <T> Response<DataResponse<T>> build(List<T> items) {
        return Response.<DataResponse<T>>builder().data(DataResponse.<T>builder().items(items).build()).build();
    }

    public static <T> Response<DataResponse<T>> build(List<T> items, Integer page, Integer size, Long totalSize) {
        return Response.<DataResponse<T>>builder().data(DataResponse.<T>builder().items(items).page(page).size(size).totalSize(totalSize).build()).build();
    }

    public static <T> Response<T> build(T item) {
        return Response.<T>builder().data(item).build();
    }

    public static Response build(ErrorResponse errorResponse) {
        return Response.builder().errors(errorResponse).build();
    }
}