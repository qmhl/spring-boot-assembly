package io.geekidea.springboot.assembly.demo.controller;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.entity.AbstractExecutor;
import io.geekidea.springboot.assembly.demo.entity.Order;
import io.geekidea.springboot.assembly.demo.entity.PayResult;
import io.geekidea.springboot.assembly.demo.service.AbstractExecutorFactory;
import io.geekidea.springboot.assembly.demo.service.IPayment;
import io.geekidea.springboot.assembly.demo.service.IReceivement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付  参考链接：https://blog.csdn.net/qq_37949192/article/details/116993087
 *
 * @author Mr.Qu
 * @since 2021/5/18 15:30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/pay/")
@Slf4j
public class PayController {

    private final ApplicationContext applicationContext;

    /**
     * 支付API
     *
     * @param amount  金额
     * @param payType 支付类型
     * @return PayResult
     */
    @GetMapping()
    public PayResult pay(int amount, String payType) {
        Order order = new Order().setAmount(amount).setType(payType);

        //  根据【支付类型】获取对应的策略 bean
        IPayment payment = applicationContext.getBean(payType, IPayment.class);
        IReceivement receivement = applicationContext.getBean(payType, IReceivement.class);
        log.info("pay :>>>{}", receivement.getClass().getName());
        log.info("pay :>>>{}", receivement.getClass().getSimpleName());
        log.info("pay :>>>{}", receivement.getClass().getTypeName());

        PayResult pay = payment.pay(order);
        PayResult receive = receivement.receive(order);
        log.info("pay :>>>{}", JSON.toJSONString(pay));
        log.info("receive :>>>{}", JSON.toJSONString(receive));
        //  开始调用策略对应的支付业务逻辑
        return pay;
    }


    @Autowired
    private AbstractExecutorFactory executorFactory;

    @GetMapping("/abs")
    public PayResult testGetExecutor() {
        String name = "Executor1";
        AbstractExecutor executor = executorFactory.getExecutor(name);
        String s = executor.printContext();
        log.info("testGetExecutor s>>> {}", s);
        return PayResult.success(s);

    }
}

