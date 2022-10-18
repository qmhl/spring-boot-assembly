package io.geekidea.springboot.assembly.demo.service;

import io.geekidea.springboot.assembly.demo.Exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 项目管理service
 */
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Override
    public Boolean deleteProject(Integer id, String erp) {

        if (id == null) {
            throw new BusinessException("项目id不存在！");
        }
        if (!erp.equals("张三")) {
            throw new BusinessException("您无权删除此项目信息！");
        }

        return true;
    }

}