package com.hieplp.url.common.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QueryResponse<T> {
    private List<T> list;
    private Integer total;
}
