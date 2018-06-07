package com.ztes.sell.controller;

import com.ztes.sell.service.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    @RequestMapping("/query")
    public String query(@RequestParam(value = "productId", defaultValue = "123456") String productId){
        return secKillService.querySkillProductInfo(productId);
    }

    @RequestMapping("/order")
    public String order(@RequestParam(value = "productId", defaultValue = "123456") String productId){
        log.info("【秒杀开始】 productId = {}", productId);
        secKillService.orderProductDiffUser(productId);
        return secKillService.querySkillProductInfo(productId);
    }

}
