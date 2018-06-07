package com.ztes.sell.converter;

import com.ztes.sell.dto.OrderMasterDTO;
import com.ztes.sell.pojo.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMasterToOrderDTOConverter {

    public static OrderMasterDTO covert(OrderMaster orderMaster){
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster, orderMasterDTO);
        return orderMasterDTO;
    }

    public static List<OrderMasterDTO> covertList(List<OrderMaster> orderMasterList){
        List<OrderMasterDTO> orderMasterDTOList = orderMasterList.stream()
                .map(e -> covert(e)).collect(Collectors.toList());
        return orderMasterDTOList;
    }

}
