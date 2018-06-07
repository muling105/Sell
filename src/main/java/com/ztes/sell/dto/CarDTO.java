package com.ztes.sell.dto;

import lombok.Data;

/**
 * 购物车传输
 */
@Data
public class CarDTO {

    //商品ID
    private String productId;
    //选购数量
    private Integer productQuan;

    public CarDTO(String productId, Integer productQuan) {
        this.productId = productId;
        this.productQuan = productQuan;
    }
}
