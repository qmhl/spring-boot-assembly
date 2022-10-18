package io.geekidea.springboot.assembly.demo.aspect;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.Exception.PermissionException;
import io.geekidea.springboot.assembly.demo.model.Father;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangqi1099
 * @description: 统一处理权限校验的切面
 * @date 2022/02/24 12:08
 * <p>
 * 参考链接：   https://blog.csdn.net/weixin_39927967/article/details/100144872?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-1-100144872.pc_agg_new_rank&utm_term=aop%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B3%A8%E8%A7%A3%E7%B1%BBwithin&spm=1000.2123.3001.4430
 */
@Aspect
@Component
@Slf4j
@Order(2)
public class PermissionAspect {


    // 定义切点Pointcut--处理@PermissionAuth
    @Pointcut("@annotation(io.geekidea.springboot.assembly.demo.aspect.PermissionAuth)")
    public void pointCut() {
    }


    @Before("pointCut()")
    public void exBefore(JoinPoint pjp) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if (ra instanceof ServletRequestAttributes) {
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            //参数列表
            Object[] args = pjp.getArgs();
            Signature signature = pjp.getSignature();

            // 判断是否有PermissionAuth注解并获取对应的值
            MethodSignature methodSignature = (MethodSignature) signature;
            Method targetMethod = methodSignature.getMethod();
            if (targetMethod.isAnnotationPresent(PermissionAuth.class)) {
                PermissionAuth pma = (PermissionAuth) targetMethod.getAnnotation(PermissionAuth.class);
                int catePermissionType = pma.catePermissionType();
                String moduleCode = pma.moduleCode();
                int permissionGran = pma.permissionGran();
                log.info("注解PermissionAuth 的值 is {}，{}，{}", catePermissionType, moduleCode, permissionGran);

                // 获取接口中入参
                String brandId = "";
                String shopId = "";
                List<List<Integer>> cateList = new ArrayList<>();
                String userType = "";
                // 处理请求参数
                for (Object arg : args) {
                    if (arg instanceof Father) {
                        Father fa = (Father) arg;
                        log.info("切面中fa is {}", JSON.toJSONString(fa));
                        break;
                    }
                }
//                int i = 1 / 0;
//                throw new PermissionException("切面异常");

            }
        }


    }


}