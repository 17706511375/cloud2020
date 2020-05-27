package com.xiongxh.springcloud.service.impl;

import com.xiongxh.springcloud.entity.Payment;
import com.xiongxh.springcloud.mapper.PaymentMapper;
import com.xiongxh.springcloud.service.IPaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxh
 * @since 2020-03-30
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {

}
