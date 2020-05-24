
package com.itmayiedu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/member")
public class MemberController {

	@RequestMapping("/memberIndex")
	public Object memberIndex() throws InterruptedException {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("code", 200);
		hashMap.put("msg", "会员服务");
		Thread.sleep(1500);
		return hashMap;
	}

}
