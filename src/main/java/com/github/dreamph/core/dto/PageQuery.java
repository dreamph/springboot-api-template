package com.github.dreamph.core.dto;

import lombok.Data;

@Data
public class PageQuery {
    private Integer offset = 0;
    private Integer pageSize = 0;
}
