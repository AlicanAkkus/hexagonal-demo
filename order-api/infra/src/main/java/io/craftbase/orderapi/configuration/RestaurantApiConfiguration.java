package io.craftbase.orderapi.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "restaurant-api")
public class RestaurantApiConfiguration {

    private String baseUrl;
}