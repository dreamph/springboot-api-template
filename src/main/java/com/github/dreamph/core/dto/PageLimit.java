package com.github.dreamph.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageLimit {
    public static final PageLimit DEFAULT = new PageLimit(1, 200);
    public static final PageLimit ONE = new PageLimit(1, 1);

    private Integer pageNumber = 1;
    private Integer pageSize = 200;
}
