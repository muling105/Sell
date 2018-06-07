package com.ztes.sell.controller;

import com.ztes.sell.VO.ProductInfoVO;
import com.ztes.sell.VO.ProductVO;
import com.ztes.sell.VO.ResultVO;
import com.ztes.sell.utils.ResultVOUtil;
import com.ztes.sell.pojo.ProductCategory;
import com.ztes.sell.pojo.ProductInfo;
import com.ztes.sell.service.ProductCategoryService;
import com.ztes.sell.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list() throws Exception{
        List<ProductInfo> productInfoList = productInfoService.getUpAll();
        List<Integer> categoryTypeList = productInfoList.stream()
                                .map(e -> e.getCategoryType())
                                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

}
