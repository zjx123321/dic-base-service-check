package com.jdddata.dic.base.service.check.muti.source.jobhandler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jdddata.dic.base.service.check.muti.source.config.*;
import com.jdddata.dic.common.redis.JredisFactory;
import com.jdddata.dic.common.redis.RedisClientTemplate;
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
@JobHandler(value="SoccerHeadJobHandler")
@Component
public class SoccerHeadJobHandler extends IJobHandler {

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
		List<Map<String, String>> mapList = initData(businessConfig.getMutiSource());
		RedisTemplate<Object, Object> redisTemplate1 = jredisFactory.redisTemplate();
		redisTemplate1.setValueSerializer(new StringRedisSerializer());
		StringBuilder stringBuilder = new StringBuilder();
		mapList.forEach(map -> {

			// 2、压缓存  sportradar opta 500w
			redisTemplate1.opsForValue().set(dataPoolConfig.getSportradarRedisKey(), map.get(dataPoolConfig.getSportradarRedisKey()), 1, TimeUnit.HOURS);
			redisTemplate1.opsForValue().set(dataPoolConfig.getSportradarScoreRedisKey(), map.get(dataPoolConfig.getSportradarScoreRedisKey()), 1, TimeUnit.HOURS);
			redisTemplate1.opsForValue().set(dataPoolConfig.getOptaRedisKey(), map.get(dataPoolConfig.getOptaRedisKey()), 1, TimeUnit.HOURS);
			redisTemplate1.opsForValue().set(dataPoolConfig.getOptaScoreRedisKey(), map.get(dataPoolConfig.getOptaScoreRedisKey()), 1, TimeUnit.HOURS);
			redisTemplate.opsForValue().set(dataPoolConfig.getFiveRedisKey(), map.get(dataPoolConfig.getFiveRedisKey()), 1, TimeUnit.HOURS);

			// 3、请求头部
			Map<String, String> body = Maps.newHashMap();
			body.put("matchId", "14894341");
			body.put("lotteryId", "90");
			Map response = HttpClientUtils.httpPost(RequestUrls.HeadUrl, new Request(body));
			String resultSource = ((Map)response.get("data")).get("source").toString();
			if(!map.get("result").equals(resultSource)) {
				stringBuilder.append(map.get("testcase") + " error, run result is : " + resultSource + "\r\n");
			}else{
				XxlJobLogger.log(map.get("testcase") + " success, run result is : " + resultSource);
			}
		});
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
			String sportradar = s[0];
			String opta = s[1];
			String five = s[2];
			String result = s[3];

			Map<String, String> map = Maps.newHashMap();
			switch (sportradar) {
				case "1" :
					map.put(dataPoolConfig.getSportradarRedisKey(), dataPoolConfig.getSportradar1());
					map.put(dataPoolConfig.getSportradarScoreRedisKey(), dataPoolConfig.getSportradarScore1());
					break;
				case "2" :
					map.put(dataPoolConfig.getSportradarRedisKey(), dataPoolConfig.getSportradar2());
					map.put(dataPoolConfig.getSportradarScoreRedisKey(), dataPoolConfig.getSportradarScore2());
					break;
				case "3" :
					map.put(dataPoolConfig.getSportradarRedisKey(), dataPoolConfig.getSportradar3());
					map.put(dataPoolConfig.getSportradarScoreRedisKey(), dataPoolConfig.getSportradarScore3());
					break;
				case "4" :
					map.put(dataPoolConfig.getSportradarRedisKey(), dataPoolConfig.getSportradar4());
					map.put(dataPoolConfig.getSportradarScoreRedisKey(), dataPoolConfig.getSportradarScore4());
					break;
				case "5" :
					map.put(dataPoolConfig.getSportradarRedisKey(), dataPoolConfig.getSportradar5());
					map.put(dataPoolConfig.getSportradarScoreRedisKey(), dataPoolConfig.getSportradarScore5());
					break;
			}

			switch (opta) {
				case "1" :
					map.put(dataPoolConfig.getOptaRedisKey(), dataPoolConfig.getOpta1());
					map.put(dataPoolConfig.getOptaScoreRedisKey(), dataPoolConfig.getOptaScore1());
					break;
				case "2" :
					map.put(dataPoolConfig.getOptaRedisKey(), dataPoolConfig.getOpta2());
					map.put(dataPoolConfig.getOptaScoreRedisKey(), dataPoolConfig.getOptaScore2());
					break;
				case "3" :
					map.put(dataPoolConfig.getOptaRedisKey(), dataPoolConfig.getOpta3());
					map.put(dataPoolConfig.getOptaScoreRedisKey(), dataPoolConfig.getOptaScore3());
					break;
				case "4" :
					map.put(dataPoolConfig.getOptaRedisKey(), dataPoolConfig.getOpta4());
					map.put(dataPoolConfig.getOptaScoreRedisKey(), dataPoolConfig.getOptaScore4());
					break;
				case "5" :
					map.put(dataPoolConfig.getOptaRedisKey(), dataPoolConfig.getOpta5());
					map.put(dataPoolConfig.getOptaScoreRedisKey(), dataPoolConfig.getOptaScore5());
					break;
			}

			switch (five) {
				case "1" :
					map.put(dataPoolConfig.getFiveRedisKey(), dataPoolConfig.getFive1());
					break;
				case "2" :
					map.put(dataPoolConfig.getFiveRedisKey(), dataPoolConfig.getFive2());
					break;
				case "3" :
					map.put(dataPoolConfig.getFiveRedisKey(), dataPoolConfig.getFive3());
					break;
				case "4" :
					map.put(dataPoolConfig.getFiveRedisKey(), dataPoolConfig.getFive4());
					break;
				case "5" :
					map.put(dataPoolConfig.getFiveRedisKey(), dataPoolConfig.getFive5());
					break;
			}

			map.put("result", result);
			map.put("testcase", source);
			list.add(map);
		}
		return list;
	}

}
