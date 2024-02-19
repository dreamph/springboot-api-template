package com.github.dreamph.api;

import com.github.dreamph.config.AppProperties;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AppApi {
    private static final Logger LOG = LoggerFactory.getLogger(AppApi.class);

    private final AppProperties appProperties;

    @Operation(summary = "Index")
    @GetMapping("/")
    public String index() throws Exception {
        return "Online";
    }

    @Operation(summary = "Health")
    @GetMapping("/health")
    public String health() throws Exception {
        return "Healthy";
    }

}
