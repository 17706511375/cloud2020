package com.xiongxh.springcloud.controller;

import com.xiongxh.springcloud.entity.CommonResult;
import com.xiongxh.springcloud.entity.Payment;
import com.xiongxh.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sun.security.jca.GetInstance;

import java.net.URI;
import java.util.List;

/**
 * @author Logic
 */
@RestController
public class OrderController {
    private final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/save")
    public CommonResult<Payment> save(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/save",payment,CommonResult.class);
    }



    @GetMapping("/consumer/payment/get/{id}")
    public  CommonResult getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get1/{id}")
    public ResponseEntity<CommonResult> getPayment1(@PathVariable("id") Long id){
        return restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);
    }


    @GetMapping("/consumer/payment/lb")
    public  String lb(){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (serviceInstances == null || serviceInstances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(serviceInstances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb/",String.class);
    }

}
