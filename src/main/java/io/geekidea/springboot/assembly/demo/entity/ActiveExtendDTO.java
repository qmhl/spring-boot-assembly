package io.geekidea.springboot.assembly.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActiveExtendDTO {

    private Long commodityMinNum;

    private Long commodityMaxNum;

}