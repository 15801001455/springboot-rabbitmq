package com.sxw.springbootproducer;

import com.sxw.entity.Order;
import com.sxw.springbootproducer.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootProducerApplicationTests {
    @Autowired private OrderService orderService;
    @Test
    public void testSend() throws Exception {
        Order order = new Order();
        for(int i=203001;i<210000;i++){
            order.setId(i);
            order.setName("第三次测试订单" + i);
            order.setMessageId(System.currentTimeMillis()+"$"+UUID.randomUUID().toString());
            orderService.createOrder(order);
        }
        // update jyc Thread.sleep(5000)这句的意思是防止测试方法生产数据的时候rabbitmq服务器还没有回调呢，结果测试程序就终止了，导致总有数据显示生产没有mq返回ack响应信息，让
        // 程序休眠等待mq回调ack信息就好啦
        Thread.sleep(5000);
    }

}
