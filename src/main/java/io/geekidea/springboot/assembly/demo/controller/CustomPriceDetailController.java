package io.geekidea.springboot.assembly.demo.controller;


import io.geekidea.springboot.assembly.demo.dao.CustomPriceDetailMapper;
import io.geekidea.springboot.assembly.demo.model.CustomPriceDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom/")
@Slf4j
public class CustomPriceDetailController {

    @Autowired
    private CustomPriceDetailMapper customPriceDetailMapper;

    @RequestMapping("/test")
    public CustomPriceDetail A(Integer id) {
        CustomPriceDetail customPriceDetail = customPriceDetailMapper.selectByProjectId(id);
        return customPriceDetail;
    }


}
