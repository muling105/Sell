package com.ztes.sell.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 卖家
 */
@Entity
@Data
public class SellerInfo implements Serializable{

    private static final long serialVersionUID = -7746275667609495005L;

    @Id
    private String sellerId;
    private String username;
    private String password;
    private String openid;
    private Date createTime;

}
