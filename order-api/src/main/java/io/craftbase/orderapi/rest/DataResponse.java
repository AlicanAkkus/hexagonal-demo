package io.craftbase.orderapi.rest;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DataResponse<T> {

    @Builder.Default
    private List<T> items = List.of();
    private Integer page;
    private Integer size;
    private Long totalSize;
}