package org.koreait.commons.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class JSONResult<T> {

    private boolean success;
    private T data;
    private String errorMessage;
}
