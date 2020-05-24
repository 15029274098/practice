
package com.itmayeidu.hystrix;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.service.MemberService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;


/**
 * @author Administrator
 */
public class OrderHystrixCommand extends HystrixCommand<JSONObject> {
	@Autowired
	private MemberService memberService;

	/**
	 * @param memberService
	 */
	public OrderHystrixCommand(MemberService memberService) {
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
				.withExecutionTimeoutEnabled(false);
		return HystrixCommand.Setter.withGroupKey(groupKey).andCommandKey(commandKey).andThreadPoolKey(threadPoolKey)
				.andThreadPoolPropertiesDefaults(threadPoolProperties).andCommandPropertiesDefaults(commandProperties);

	}

	@Override
	protected JSONObject getFallback() {
		// 如果Hystrix发生熔断，当前服务不可用,直接执行Fallback方法
		System.out.println("系统错误！");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 500);
		jsonObject.put("msg", "系统错误！");
		return jsonObject;
	}
}
