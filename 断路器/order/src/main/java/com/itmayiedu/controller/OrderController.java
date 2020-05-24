
package com.itmayiedu.controller;

import com.itmayeidu.hystrix.OrderHystrixCommand3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.itmayeidu.hystrix.OrderHystrixCommand;
import com.itmayeidu.hystrix.OrderHystrixCommand2;
import com.itmayiedu.service.MemberService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/orderIndex")
    public Object orderIndex() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        JSONObject member = memberService.getMember();
        System.out.println("当前线程名称:" + Thread.currentThread().getName() + ",订单服务调用会员服务:member:" + member);
        long endTime = System.currentTimeMillis();
        long end = (endTime - startTime) ;
        System.out.println("结束时间" + end);
        return member;
    }

    /**
     *  线程池隔离，然后降级
     * @return  信息
     * @throws InterruptedException 终止异常
     */
    @RequestMapping("/orderIndexHystrix")
    public Object orderIndexHystrix() throws InterruptedException {
        return new OrderHystrixCommand(memberService).execute();
    }

    /**
     *  信号量隔离，然后降级
     * @return  信息
     * @throws InterruptedException 终止异常
     */
    @RequestMapping("/orderIndexHystrix2")
    public Object orderIndexHystrix2() throws InterruptedException {
        return new OrderHystrixCommand2(memberService).execute();
    }

    /**
     *  线程池隔离，触发熔断，熔断之后走降级
     * @return  信息
     * @throws InterruptedException 终止异常
     */
    @RequestMapping("/orderIndexHystrix3")
    public Object orderIndexHystrix3() throws InterruptedException {
        return new OrderHystrixCommand3(memberService).execute();
    }

    @RequestMapping("/findOrderIndex")
    public Object findIndex() {
        System.out.println("当前线程:" + Thread.currentThread().getName() + ",findOrderIndex");
        return "findOrderIndex";
    }
}
