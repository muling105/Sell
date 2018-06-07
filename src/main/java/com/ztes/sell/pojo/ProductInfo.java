package com.ztes.sell.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表
 */
@Entity
@Data
public class ProductInfo implements Serializable{

    private static final long serialVersionUID = 3772171937699073048L;

    @Id
    //@GeneratedValue
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus;

    private Integer categoryType;

    private Date createTime;

    public ProductInfo() {
        super();
    }
}
