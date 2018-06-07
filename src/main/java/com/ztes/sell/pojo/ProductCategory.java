package com.ztes.sell.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 类目表
 */
@Entity
@Data //提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
public class ProductCategory implements Serializable{

    private static final long serialVersionUID = 2363483861846595318L;

    @Id
    @GeneratedValue
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
    private Date createTime;

    public ProductCategory() {
        super();
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
