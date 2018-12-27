package com.jdddata.dic.base.service.check.muti.source.jobhandler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jdddata.dic.base.service.check.muti.source.config.*;
import com.jdddata.dic.common.redis.JredisFactory;
import com.jdddata.dic.common.util.MyJsonUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 任务Handler示例（Bean模式）
 *
 * 开发步骤：
 * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 * @author xuxueli 2015-12-19 19:43:36
 */
@JobHandler(value="BasketHeadJobHandler")
@Component
public class BasketHeadJobHandler extends IJobHandler {

	@Resource
	JredisFactory jredisFactory;

	@Resource
	RedisTemplate redisTemplate;

	@Resource
	BusinessConfig businessConfig;

	@Resource
	DataPoolConfig dataPoolConfig;

	@Override
	public ReturnT<String> execute(String param) throws Exception {
		XxlJobLogger.log("muti source start ...");
		// 1、初始化数据  sportradar opta 500w
		List<Map<String, String>> mapList = initData(businessConfig.getBasketMutiSource());
		RedisTemplate<Object, Object> redisTemplate1 = jredisFactory.redisTemplate();
		redisTemplate1.setValueSerializer(new StringRedisSerializer());
		StringBuilder stringBuilder = new StringBuilder();
		for(Map<String, String> map : mapList) {
			// 2、压缓存  sportradar opta 500w
			redisTemplate1.opsForValue().set(dataPoolConfig.getDtRedisKey(), map.get(dataPoolConfig.getDtRedisKey()), 1, TimeUnit.HOURS);
			redisTemplate1.opsForValue().set(dataPoolConfig.getDtScoreRedisKey(), map.get(dataPoolConfig.getDtScoreRedisKey()), 1, TimeUnit.HOURS);
			redisTemplate.opsForValue().set(dataPoolConfig.getBsFiveRedisKey(), map.get(dataPoolConfig.getBsFiveRedisKey()), 1, TimeUnit.HOURS);

			// 3、请求头部
			Map<String, String> body = Maps.newHashMap();
			body.put("matchId", "15329996");
			HttpClientUtils.httpPost(RequestUrls.BaseBasketHeadUrl, new Request(body));
			Map response = HttpClientUtils.httpPost(RequestUrls.BasketHeadUrl, new Request(body));
			String resultSource = ((Map)response.get("data")).get("sourceCode").toString();
			if(!map.get("result").equals(resultSource)) {
				stringBuilder.append(map.get("testcase") + " error, run result is : " + resultSource + "\r\n" + MyJsonUtils.bean2Json((Map)response.get("data")));
			}else{
				XxlJobLogger.log(map.get("testcase") + " success, run result is : " + resultSource);
			}
		}
		XxlJobLogger.log("muti source end ...");
		if(StringUtils.isNotEmpty(stringBuilder.toString())) {
			XxlJobLogger.log("RESULT : " + stringBuilder.toString());
			return FAIL;
		}
		return SUCCESS;
	}

	private List<Map<String, String>> initData(String mutiSource) {
		List<Map<String, String>> list = Lists.newArrayList();
		String[] sources = mutiSource.split(";");
		for(String source : sources) {
			String[] s = source.split(",");
			String dt = s[0];
			String five = s[1];
			String result = s[2];

			Map<String, String> map = Maps.newHashMap();
			switch (dt) {
				case "1" :
					map.put(dataPoolConfig.getDtRedisKey(), dataPoolConfig.getDt1());
					map.put(dataPoolConfig.getDtScoreRedisKey(), dataPoolConfig.getDtScore1());
					break;
				case "2" :
					map.put(dataPoolConfig.getDtRedisKey(), dataPoolConfig.getDt2());
					map.put(dataPoolConfig.getDtScoreRedisKey(), dataPoolConfig.getDtScore2());
					break;
				case "3" :
					map.put(dataPoolConfig.getDtRedisKey(), dataPoolConfig.getDt3());
					map.put(dataPoolConfig.getDtScoreRedisKey(), dataPoolConfig.getDtScore3());
					break;
				case "4" :
					map.put(dataPoolConfig.getDtRedisKey(), dataPoolConfig.getDt4());
					map.put(dataPoolConfig.getDtScoreRedisKey(), dataPoolConfig.getDtScore4());
					break;
				case "5" :
					map.put(dataPoolConfig.getDtRedisKey(), dataPoolConfig.getDt5());
					map.put(dataPoolConfig.getDtScoreRedisKey(), dataPoolConfig.getDtScore5());
					break;
				case "6" :
					map.put(dataPoolConfig.getDtRedisKey(), dataPoolConfig.getDt6());
					map.put(dataPoolConfig.getDtScoreRedisKey(), dataPoolConfig.getDtScore6());
					break;
				case "7" :
					map.put(dataPoolConfig.getDtRedisKey(), dataPoolConfig.getDt7());
					map.put(dataPoolConfig.getDtScoreRedisKey(), dataPoolConfig.getDtScore7());
					break;
				case "8" :
					map.put(dataPoolConfig.getDtRedisKey(), dataPoolConfig.getDt8());
					map.put(dataPoolConfig.getDtScoreRedisKey(), dataPoolConfig.getDtScore8());
					break;
				case "9" :
					map.put(dataPoolConfig.getDtRedisKey(), dataPoolConfig.getDt9());
					map.put(dataPoolConfig.getDtScoreRedisKey(), dataPoolConfig.getDtScore9());
					break;
				case "10" :
					map.put(dataPoolConfig.getDtRedisKey(), dataPoolConfig.getDt10());
					map.put(dataPoolConfig.getDtScoreRedisKey(), dataPoolConfig.getDtScore10());
					break;
			}

			switch (five) {
				case "1" :
					map.put(dataPoolConfig.getBsFiveRedisKey(), dataPoolConfig.getBsFive1());
					break;
				case "2" :
					map.put(dataPoolConfig.getBsFiveRedisKey(), dataPoolConfig.getBsFive2());
					break;
				case "3" :
					map.put(dataPoolConfig.getBsFiveRedisKey(), dataPoolConfig.getBsFive3());
					break;
				case "4" :
					map.put(dataPoolConfig.getBsFiveRedisKey(), dataPoolConfig.getBsFive4());
					break;
				case "5" :
					map.put(dataPoolConfig.getBsFiveRedisKey(), dataPoolConfig.getBsFive5());
					break;
				case "6" :
					map.put(dataPoolConfig.getBsFiveRedisKey(), dataPoolConfig.getBsFive6());
					break;
				case "7" :
					map.put(dataPoolConfig.getBsFiveRedisKey(), dataPoolConfig.getBsFive7());
					break;
				case "8" :
					map.put(dataPoolConfig.getBsFiveRedisKey(), dataPoolConfig.getBsFive8());
					break;
				case "9" :
					map.put(dataPoolConfig.getBsFiveRedisKey(), dataPoolConfig.getBsFive9());
					break;
				case "10" :
					map.put(dataPoolConfig.getBsFiveRedisKey(), dataPoolConfig.getBsFive10());
					break;
			}

			map.put("result", result);
			map.put("testcase", source);
			list.add(map);
		}
		return list;
	}

}
