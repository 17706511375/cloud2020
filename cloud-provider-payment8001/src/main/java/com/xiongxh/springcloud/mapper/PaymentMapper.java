package com.xiongxh.springcloud.mapper;

import com.xiongxh.springcloud.entity.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxh
 * @since 2020-03-30
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {

}
