package io.craftbase.restaurantapi.adapters.restaurant.rest;

import io.craftbase.restaurantapi.adapters.restaurant.rest.dto.MenuItemResponse;
import io.craftbase.restaurantapi.common.rest.BaseController;
import io.craftbase.restaurantapi.common.rest.Response;
import io.craftbase.restaurantapi.restaurant.RestaurantFacade;
import io.craftbase.restaurantapi.restaurant.model.MenuItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurant-menu-items")
public class RestaurantController extends BaseController {

    private final RestaurantFacade restaurantFacade;

    @GetMapping("/{menuItemId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<MenuItemResponse> create(@PathVariable Long menuItemId) {
        MenuItem menuItem = restaurantFacade.retrieveMenuItem(menuItemId);
        return respond(MenuItemResponse.fromModel(menuItem));
    }
}