package io.craftbase.orderapi.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class MenuVo {

    private Long id;
    private String name;
    private String description;
    private List<MenuItemVo> menuItemVos;
}