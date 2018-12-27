package com.jdddata.dic.base.service.check.muti.source.controller;

import com.jdddata.dic.base.service.check.muti.source.jobhandler.BasketHeadJobHandler;
import com.jdddata.dic.base.service.check.muti.source.jobhandler.BasketListJobHandler;
import com.jdddata.dic.base.service.check.muti.source.jobhandler.SoccerHeadJobHandler;
import com.jdddata.dic.base.service.check.muti.source.jobhandler.SoccerListJobHandler;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@EnableAutoConfiguration
public class IndexController {

    @Resource
    SoccerHeadJobHandler demoJobHandler;

    @Resource
    SoccerListJobHandler soccerListJobHandler;

    @Resource
    BasketListJobHandler basketListJobHandler;

    @Resource
    BasketHeadJobHandler basketHeadJobHandler;

    @RequestMapping("/")
    @ResponseBody
    String index() {

        try {
//            soccerListJobHandler.execute("2018-12-19,16383816");
//            demoJobHandler.execute("");
//            basketListJobHandler.execute("2018-12-25,15329996");
            basketHeadJobHandler.execute("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "xxl job executor running.";
    }

}