package com.hieplp.url.common.payload.request;

import lombok.Data;

@Data
public class QueryRequest {
    private int from;
    private int limit;

    // Order
    private String order;
    private String by;

    // Filter
    private String filterBy;
    private String filterValue;

    // Search
    private String searchBy;
    private String searchValue;
}
