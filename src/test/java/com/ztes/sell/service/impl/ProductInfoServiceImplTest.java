package com.ztes.sell.service.impl;

import com.ztes.sell.enums.ProductStatusEnum;
import com.ztes.sell.pojo.ProductInfo;
import com.ztes.sell.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    @Transactional
    public void getOne() throws Exception{
        ProductInfo bean = productInfoService.getOne("1001");
        Assert.assertEquals("1001", bean.getProductId());
    }

    @Test
    @Transactional
    public void getUpAll() throws Exception{
        List<ProductInfo> list = productInfoService.getUpAll();
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    @Transactional
    public void getAll() throws Exception{
        PageRequest pageRequest = new PageRequest(0, 5);
        Page<ProductInfo> page = productInfoService.getAll(pageRequest);
        Assert.assertNotEquals(0, page.getContent().size());
    }

    @Test
    public void save() throws Exception{
        ProductInfo bean = new ProductInfo();
        bean.setProductId("1002");
        bean.setProductName("蛋花汤");
        bean.setProductPrice(new BigDecimal(1.5));
        bean.setProductDescription("营养");
        bean.setProductIcon("http://*********2.jpg");
        bean.setProductStock(30);
        bean.setProductStatus(ProductStatusEnum.DOWN.getCode());
        bean.setCategoryType(2);
        productInfoService.save(bean);
    }

    @Test
    public void onSaleTest() throws Exception{
        ProductInfo productInfo = productInfoService.onSale("1001");
        Assert.assertEquals(ProductStatusEnum.UP.getCode(), productInfo.getProductStatus());
    }

    @Test
    public void offSaleTest() throws Exception{
        ProductInfo productInfo = productInfoService.offSale("1001");
        Assert.assertEquals(ProductStatusEnum.DOWN.getCode(), productInfo.getProductStatus());
    }

}