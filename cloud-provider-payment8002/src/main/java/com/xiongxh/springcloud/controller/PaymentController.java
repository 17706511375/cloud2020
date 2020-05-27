package com.xiongxh.springcloud.controller;


import com.xiongxh.springcloud.entity.CommonResult;
import com.xiongxh.springcloud.entity.Payment;
import com.xiongxh.springcloud.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xxh
 * @since 2020-03-30
 */
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    IPaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/save")
    public CommonResult<String> save(@RequestBody Payment payment){
        boolean save = paymentService.save(payment);
        log.info("******插入结果：" + save);
        if (save){
            return new CommonResult<>(200,"插入数据成功,端口号："+serverPort,null);
        }else {
            return new CommonResult<>(444,"插入数据失败,端口号："+serverPort,null);
        }

    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getById(id);
        if (payment != null){
            return new CommonResult<>(200, "查询成功,端口号："+serverPort, payment);
        }else {
            return new CommonResult<>(444,"没有对应的记录，查询ID：" + id,null);
        }
    }

    @GetMapping("/lb")
    public String lb(){
        return serverPort;
    }

}

