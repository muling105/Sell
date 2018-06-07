package com.ztes.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ztes.sell.dto.OrderMasterDTO;
import com.ztes.sell.enums.ResultEnum;
import com.ztes.sell.exception.SellException;
import com.ztes.sell.form.OrderForm;
import com.ztes.sell.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderFormToOrderMasterDTO {

    public static OrderMasterDTO convert(OrderForm orderForm){
            OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
            orderMasterDTO.setBuyerName(orderForm.getName());
            orderMasterDTO.setBuyerPhone(orderForm.getPhone());
            orderMasterDTO.setBuyerAddress(orderForm.getAddress());
            orderMasterDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> list = new ArrayList<>();
        Gson gson = new Gson();
        //使用gson将字符串转为json
        try {
            list = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("[对象转换错误] result = {}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderMasterDTO.setOrderDetailList(list);
        return orderMasterDTO;
    }

}
