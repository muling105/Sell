package com.ztes.sell.controller;

import com.ztes.sell.dto.OrderMasterDTO;
import com.ztes.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value="pageNum", defaultValue = "1")Integer pageNum,
                             @RequestParam(value="pageSize", defaultValue = "10")Integer pageSize,
                             Map<String, Object> map) throws Exception{
        PageRequest pageRequest = new PageRequest(pageNum-1, pageSize);
        Page<OrderMasterDTO> orderMasterDTOPage = orderService.findList(pageRequest);
        map.put("orderMasterDTOPage", orderMasterDTOPage);
        return new ModelAndView("order/list", map);
    }

}
