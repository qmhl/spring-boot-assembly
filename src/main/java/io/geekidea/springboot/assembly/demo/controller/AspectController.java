package io.geekidea.springboot.assembly.demo.controller;


import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.aspect.PermissionAuth;
import io.geekidea.springboot.assembly.demo.model.Father;
import io.geekidea.springboot.assembly.demo.model.Son;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aspect")
@Slf4j
public class AspectController {


    @RequestMapping("/test")
    @PermissionAuth(moduleCode = "1", permissionGran = 1, catePermissionType = 1)
    public String A(@RequestBody Son son) {
        Father father = son;
        System.out.println("son: " + JSON.toJSONString(son));
        System.out.println("father: " + JSON.toJSONString(father));
        return JSON.toJSONString(father);
    }
}
