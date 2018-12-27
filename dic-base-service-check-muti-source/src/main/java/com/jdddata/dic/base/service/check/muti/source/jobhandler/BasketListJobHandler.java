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
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


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
@JobHandler(value="BasketListJobHandler")
@Component
public class BasketListJobHandler extends IJobHandler {

	@Resource
	BusinessConfig businessConfig;

	@Resource
	DataPoolConfig dataPoolConfig;

	@Override
	public ReturnT<String> execute(String param) throws Exception {
		XxlJobLogger.log("headlist start ...");
		// 1、从参数中获取 比赛ID及期次ID
		String issue = param.split(",")[0];
		String matchId = param.split(",")[1];

		// 2、请求头部
		Map<String, String> headBody = Maps.newHashMap();
		headBody.put("matchId", matchId);
//		headBody.put("lotteryId", "90");
		Map headResponse = HttpClientUtils.httpPost(RequestUrls.BasketHeadUrl, new Request(headBody));
		Map headMap = (Map) headResponse.get("data");
		headMap.get("");

		// 3、请求列表
		Map<String, String> listBody = Maps.newHashMap();
		listBody.put("issue", issue);
//		listBody.put("lotteryId", "90");
		Map listResponse = HttpClientUtils.httpPost(RequestUrls.BasketListUrl, new Request(listBody));
		List list = (List) listResponse.get("data");
		if(CollectionUtils.isEmpty(list)) {
			XxlJobLogger.log("RESULT : 该期次列表数据为空");
			return FAIL;
		}
		Map listMap = null;
		for(int i=0; i<list.size(); i++) {
			Map map = (Map)list.get(i);
			Map map1 = (Map)map.get("matchInfo");
			if(matchId.equals(map1.get("matchId"))) {
				listMap = map1;
				break;
			}
		}
		if(listMap == null) {
			XxlJobLogger.log("RESULT : 该期次列表没有找到对应比赛数据");
			return FAIL;
		}

		// 4、开始比较数据
		String[] headListCheckColumns = businessConfig.getBasketHeadList().split(";");
		for(String checkColumn : headListCheckColumns) {

			String[] columns = checkColumn.split(":");
			String[] leftColumns = columns[0].split(",");
			Object left = "";
			String right = columns[1];
			if(leftColumns.length == 1) {
				left = headMap.get(columns[0]);
			}else{
				// 此处处理比分
				left = headMap.get(leftColumns[0]) + ":" + headMap.get(leftColumns[1]);
				if(left.equals(":")) {
					left = "";
				}
			}

			if((left == null && listMap.get(right) != null) ||
					(left != null && listMap.get(right) == null) ||
					(left != null && listMap.get(right) != null && !left.equals(listMap.get(right)))) {
				XxlJobLogger.log("RESULT : 列表与期次数据不一致 " + left + " : " + right);
				XxlJobLogger.log("Head : " + MyJsonUtils.bean2Json(headMap));
				XxlJobLogger.log("List : " + MyJsonUtils.bean2Json(listMap));
				return FAIL;
			}
		}
		XxlJobLogger.log("headlist end ...");
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
