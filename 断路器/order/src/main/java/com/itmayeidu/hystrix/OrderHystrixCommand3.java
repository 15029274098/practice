
package com.itmayeidu.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.service.MemberService;
import com.netflix.hystrix.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Administrator
 */
public class OrderHystrixCommand3 extends HystrixCommand<JSONObject> {
    @Autowired
    private MemberService memberService;

    /**
     * @param memberService
     */
    public OrderHystrixCommand3(MemberService memberService) {
        super(setter());
        this.memberService = memberService;
    }

    protected JSONObject run() throws Exception {
        JSONObject member = memberService.getMember();
        System.out.println("当前线程名称:" + Thread.currentThread().getName() + ",订单服务调用会员服务:member:" + member);
        return member;
    }

    private static Setter setter() {

        // 服务分组
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("members");
        // 服务标识
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("member");
        // 线程池名称
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("member-pool");
        // #####################################################
        // 线程池配置 线程池里面线程大小为10,线程存活时间15秒 队列等待的阈值为100,超过100执行拒绝策略
        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter().withCoreSize(10)
                .withKeepAliveTimeMinutes(15).withQueueSizeRejectionThreshold(100);
        // ########################################################
        // 命令属性配置Hystrix 开启超时
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
                // 采用线程池方式实现服务隔离
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                // 禁止
                .withExecutionTimeoutEnabled(false)
                .withCircuitBreakerEnabled(true)//是否启用熔断器，默认是TURE。
                .withCircuitBreakerForceOpen(false)//熔断器强制关闭，始终保持关闭状态。默认值FLASE
                .withCircuitBreakerForceClosed(false)//熔断器强制关闭，始终保持关闭状态。默认值FLASE。
                .withCircuitBreakerErrorThresholdPercentage(5)//(1)错误百分比超过5%，设定错误百分比，默认值50%，例如一段时间（10s）内有100个请求，其中有55个超时或者异常返回了，那么这段时间内的错误百分比是55%，大于了默认值50%，这种情况下触发熔断器-打开。
                .withCircuitBreakerRequestVolumeThreshold(50)//(2)默认值20.意思是至少有20个请求才进行errorThresholdPercentage错误百分比计算。比如一段时间（10s）内有19个请求全部失败了。错误百分比是100%，但熔断器不会打开，因为requestVolumeThreshold的值是20. 这个参数非常重要，熔断器是否打开首先要满足这个条件
                .withCircuitBreakerSleepWindowInMilliseconds(5000);//半开试探休眠时间，默认值5000ms。当熔断器开启一段时间之后比如5000ms，会尝试放过去一部分流量进行试探，确定依赖服务是否恢复。
        return Setter.withGroupKey(groupKey).andCommandKey(commandKey).andThreadPoolKey(threadPoolKey)
                .andThreadPoolPropertiesDefaults(threadPoolProperties).andCommandPropertiesDefaults(commandProperties);

    }

    @Override
    protected JSONObject getFallback() {
        // 如果Hystrix发生熔断，当前服务不可用,直接执行Fallback方法
        JSONObject jsonObject = new JSONObject();
        // 判断是否熔断
        boolean flag = HystrixCircuitBreaker.Factory.getInstance(HystrixCommandKey.Factory.asKey("member")).isOpen();
        if (flag) {
            jsonObject.put("code", 500);
            jsonObject.put("msg", "线程隔离，触发熔断，走降级，系统错误！");
            System.out.println("--------------触发断路器-------------------");
        } else {
            jsonObject.put("code", 500);
            jsonObject.put("msg", "线程隔离，触发降级，系统错误！");
            System.out.println("--------------线程隔离，触发降级-------------------");
        }


        return jsonObject;
    }
}
