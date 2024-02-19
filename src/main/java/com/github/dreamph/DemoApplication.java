package com.github.dreamph;

import com.github.dreamph.config.AppProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneId;
import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {
    final AppProperties appProperties;

    private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Server DateTime : {}", new Date());
        LOG.info("Server TimeZone : {}", ZoneId.systemDefault());
        LOG.info("Swagger UI : http://localhost:{}/swagger-ui.html", appProperties.getPort());
    }
}
