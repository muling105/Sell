package com.ztes.sell.service.impl;

import com.ztes.sell.dao.ProductInfoDao;
import com.ztes.sell.dto.CarDTO;
import com.ztes.sell.enums.ProductStatusEnum;
import com.ztes.sell.enums.ResultEnum;
import com.ztes.sell.exception.SellException;
import com.ztes.sell.pojo.ProductInfo;
import com.ztes.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo getOne(String productId) throws Exception{
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> getUpAll() throws Exception{
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> getAll(Pageable pageable) throws Exception{
        return productInfoDao.findAll(pageable);
    }

    @Override
    @Transactional
    public ProductInfo save(ProductInfo bean) throws Exception{
        return productInfoDao.save(bean);
    }

    @Override
    @Transactional
    public void increaseStock(List<CarDTO> carDTOList) throws Exception {
        for(CarDTO carDTO : carDTOList){
            ProductInfo productInfo = productInfoDao.findOne(carDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = productInfo.getProductStock() + carDTO.getProductQuan();
            productInfo.setProductStock(stock);
            productInfoDao.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CarDTO> carDTOList) throws Exception {
        for(CarDTO carDTO : carDTOList){
            ProductInfo productInfo = productInfoDao.findOne(carDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock = productInfo.getProductStock() - carDTO.getProductQuan();
            if(stock < 0){
                throw new SellException(ResultEnum.PRODUCT_IS_FEW);
            }
            productInfo.setProductStock(stock);
            productInfoDao.save(productInfo);
        }
    }

    @Override
    @Transactional
    public ProductInfo onSale(String productId) throws Exception {
        ProductInfo productInfo = getOne(productId);
        if(productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus() == ProductStatusEnum.UP.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return productInfoDao.save(productInfo);
    }

    @Override
    @Transactional
    public ProductInfo offSale(String productId) throws Exception {
        ProductInfo productInfo = getOne(productId);
        if(productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus() == ProductStatusEnum.DOWN.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return productInfoDao.save(productInfo);
    }
}
