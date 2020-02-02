package io.craftbase.orderapi.adapters.restaurant;

import io.craftbase.orderapi.common.rest.Response;
import io.craftbase.orderapi.configuration.RestaurantApiConfiguration;
import io.craftbase.orderapi.order.RestaurantPort;
import io.craftbase.orderapi.order.model.MenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@Service
@RequiredArgsConstructor
public class RestaurantAdapter implements RestaurantPort {

    private final RestTemplate restTemplate;
    private final RestaurantApiConfiguration restaurantApiConfiguration;
    private final ParameterizedTypeReference<Response<MenuItem>> responseParameterizedTypeReference = new ParameterizedTypeReference<>() {
    };

    @Override
    public MenuItem retrieveMenuItem(Long id) {
        Response<MenuItem> response = restTemplate.exchange(restaurantApiConfiguration.getBaseUrl() + "/api/v1/restaurant-menu-items/" + id, GET, null, responseParameterizedTypeReference).getBody();
        return response.getData();
    }
}