package com.jdddata.dic.base.service.check.muti.source.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhengjiexiang on 2018/12/20
 */
@Configuration
@ConfigurationProperties(prefix = "business.datapool")
public class DataPoolConfig {

    private String sportradarRedisKey;
    private String sportradar1;
    private String sportradar2;
    private String sportradar3;
    private String sportradar4;
    private String sportradar5;
    private String sportradar6;
    private String sportradar7;

    private String sportradarScoreRedisKey;
    private String sportradarScore1;
    private String sportradarScore2;
    private String sportradarScore3;
    private String sportradarScore4;
    private String sportradarScore5;
    private String sportradarScore6;
    private String sportradarScore7;

    private String optaRedisKey;
    private String opta1;
    private String opta2;
    private String opta3;
    private String opta4;
    private String opta5;
    private String opta6;
    private String opta7;

    private String optaScoreRedisKey;
    private String optaScore1;
    private String optaScore2;
    private String optaScore3;
    private String optaScore4;
    private String optaScore5;
    private String optaScore6;
    private String optaScore7;

    private String fiveRedisKey;
    private String five1;
    private String five2;
    private String five3;
    private String five4;
    private String five5;

    // basket
    private String dtScoreRedisKey;
    private String dtScore1;
    private String dtScore2;
    private String dtScore3;
    private String dtScore4;
    private String dtScore5;
    private String dtScore6;
    private String dtScore7;
    private String dtScore8;
    private String dtScore9;
    private String dtScore10;

    private String dtRedisKey;
    private String dt1;
    private String dt2;
    private String dt3;
    private String dt4;
    private String dt5;
    private String dt6;
    private String dt7;
    private String dt8;
    private String dt9;
    private String dt10;

    private String bsFiveRedisKey;
    private String bsFive1;
    private String bsFive2;
    private String bsFive3;
    private String bsFive4;
    private String bsFive5;
    private String bsFive6;
    private String bsFive7;
    private String bsFive8;
    private String bsFive9;
    private String bsFive10;

    public String getBsFiveRedisKey() {
        return bsFiveRedisKey;
    }

    public void setBsFiveRedisKey(String bsFiveRedisKey) {
        this.bsFiveRedisKey = bsFiveRedisKey;
    }

    public String getBsFive1() {
        return bsFive1;
    }

    public void setBsFive1(String bsFive1) {
        this.bsFive1 = bsFive1;
    }

    public String getBsFive2() {
        return bsFive2;
    }

    public void setBsFive2(String bsFive2) {
        this.bsFive2 = bsFive2;
    }

    public String getBsFive3() {
        return bsFive3;
    }

    public void setBsFive3(String bsFive3) {
        this.bsFive3 = bsFive3;
    }

    public String getBsFive4() {
        return bsFive4;
    }

    public void setBsFive4(String bsFive4) {
        this.bsFive4 = bsFive4;
    }

    public String getBsFive5() {
        return bsFive5;
    }

    public void setBsFive5(String bsFive5) {
        this.bsFive5 = bsFive5;
    }

    public String getBsFive6() {
        return bsFive6;
    }

    public void setBsFive6(String bsFive6) {
        this.bsFive6 = bsFive6;
    }

    public String getBsFive7() {
        return bsFive7;
    }

    public void setBsFive7(String bsFive7) {
        this.bsFive7 = bsFive7;
    }

    public String getBsFive8() {
        return bsFive8;
    }

    public void setBsFive8(String bsFive8) {
        this.bsFive8 = bsFive8;
    }

    public String getBsFive9() {
        return bsFive9;
    }

    public void setBsFive9(String bsFive9) {
        this.bsFive9 = bsFive9;
    }

    public String getBsFive10() {
        return bsFive10;
    }

    public void setBsFive10(String bsFive10) {
        this.bsFive10 = bsFive10;
    }

    public String getDtScoreRedisKey() {
        return dtScoreRedisKey;
    }

    public void setDtScoreRedisKey(String dtScoreRedisKey) {
        this.dtScoreRedisKey = dtScoreRedisKey;
    }

    public String getDtScore1() {
        return dtScore1;
    }

    public void setDtScore1(String dtScore1) {
        this.dtScore1 = dtScore1;
    }

    public String getDtScore2() {
        return dtScore2;
    }

    public void setDtScore2(String dtScore2) {
        this.dtScore2 = dtScore2;
    }

    public String getDtScore3() {
        return dtScore3;
    }

    public void setDtScore3(String dtScore3) {
        this.dtScore3 = dtScore3;
    }

    public String getDtScore4() {
        return dtScore4;
    }

    public void setDtScore4(String dtScore4) {
        this.dtScore4 = dtScore4;
    }

    public String getDtScore5() {
        return dtScore5;
    }

    public void setDtScore5(String dtScore5) {
        this.dtScore5 = dtScore5;
    }

    public String getDtScore6() {
        return dtScore6;
    }

    public void setDtScore6(String dtScore6) {
        this.dtScore6 = dtScore6;
    }

    public String getDtScore7() {
        return dtScore7;
    }

    public void setDtScore7(String dtScore7) {
        this.dtScore7 = dtScore7;
    }

    public String getDtScore8() {
        return dtScore8;
    }

    public void setDtScore8(String dtScore8) {
        this.dtScore8 = dtScore8;
    }

    public String getDtScore9() {
        return dtScore9;
    }

    public void setDtScore9(String dtScore9) {
        this.dtScore9 = dtScore9;
    }

    public String getDtScore10() {
        return dtScore10;
    }

    public void setDtScore10(String dtScore10) {
        this.dtScore10 = dtScore10;
    }

    public String getDtRedisKey() {
        return dtRedisKey;
    }

    public void setDtRedisKey(String dtRedisKey) {
        this.dtRedisKey = dtRedisKey;
    }

    public String getDt1() {
        return dt1;
    }

    public void setDt1(String dt1) {
        this.dt1 = dt1;
    }

    public String getDt2() {
        return dt2;
    }

    public void setDt2(String dt2) {
        this.dt2 = dt2;
    }

    public String getDt3() {
        return dt3;
    }

    public void setDt3(String dt3) {
        this.dt3 = dt3;
    }

    public String getDt4() {
        return dt4;
    }

    public void setDt4(String dt4) {
        this.dt4 = dt4;
    }

    public String getDt5() {
        return dt5;
    }

    public void setDt5(String dt5) {
        this.dt5 = dt5;
    }

    public String getDt6() {
        return dt6;
    }

    public void setDt6(String dt6) {
        this.dt6 = dt6;
    }

    public String getDt7() {
        return dt7;
    }

    public void setDt7(String dt7) {
        this.dt7 = dt7;
    }

    public String getDt8() {
        return dt8;
    }

    public void setDt8(String dt8) {
        this.dt8 = dt8;
    }

    public String getDt9() {
        return dt9;
    }

    public void setDt9(String dt9) {
        this.dt9 = dt9;
    }

    public String getDt10() {
        return dt10;
    }

    public void setDt10(String dt10) {
        this.dt10 = dt10;
    }

    public String getFiveRedisKey() {
        return fiveRedisKey;
    }

    public void setFiveRedisKey(String fiveRedisKey) {
        this.fiveRedisKey = fiveRedisKey;
    }

    public String getSportradarRedisKey() {
        return sportradarRedisKey;
    }

    public void setSportradarRedisKey(String sportradarRedisKey) {
        this.sportradarRedisKey = sportradarRedisKey;
    }

    public String getSportradar1() {
        return sportradar1;
    }

    public void setSportradar1(String sportradar1) {
        this.sportradar1 = sportradar1;
    }

    public String getSportradar2() {
        return sportradar2;
    }

    public void setSportradar2(String sportradar2) {
        this.sportradar2 = sportradar2;
    }

    public String getSportradar3() {
        return sportradar3;
    }

    public void setSportradar3(String sportradar3) {
        this.sportradar3 = sportradar3;
    }

    public String getSportradar4() {
        return sportradar4;
    }

    public void setSportradar4(String sportradar4) {
        this.sportradar4 = sportradar4;
    }

    public String getSportradar5() {
        return sportradar5;
    }

    public void setSportradar5(String sportradar5) {
        this.sportradar5 = sportradar5;
    }

    public String getSportradar6() {
        return sportradar6;
    }

    public void setSportradar6(String sportradar6) {
        this.sportradar6 = sportradar6;
    }

    public String getSportradar7() {
        return sportradar7;
    }

    public void setSportradar7(String sportradar7) {
        this.sportradar7 = sportradar7;
    }

    public String getSportradarScoreRedisKey() {
        return sportradarScoreRedisKey;
    }

    public void setSportradarScoreRedisKey(String sportradarScoreRedisKey) {
        this.sportradarScoreRedisKey = sportradarScoreRedisKey;
    }

    public String getSportradarScore1() {
        return sportradarScore1;
    }

    public void setSportradarScore1(String sportradarScore1) {
        this.sportradarScore1 = sportradarScore1;
    }

    public String getSportradarScore2() {
        return sportradarScore2;
    }

    public void setSportradarScore2(String sportradarScore2) {
        this.sportradarScore2 = sportradarScore2;
    }

    public String getSportradarScore3() {
        return sportradarScore3;
    }

    public void setSportradarScore3(String sportradarScore3) {
        this.sportradarScore3 = sportradarScore3;
    }

    public String getSportradarScore4() {
        return sportradarScore4;
    }

    public void setSportradarScore4(String sportradarScore4) {
        this.sportradarScore4 = sportradarScore4;
    }

    public String getSportradarScore5() {
        return sportradarScore5;
    }

    public void setSportradarScore5(String sportradarScore5) {
        this.sportradarScore5 = sportradarScore5;
    }

    public String getSportradarScore6() {
        return sportradarScore6;
    }

    public void setSportradarScore6(String sportradarScore6) {
        this.sportradarScore6 = sportradarScore6;
    }

    public String getSportradarScore7() {
        return sportradarScore7;
    }

    public void setSportradarScore7(String sportradarScore7) {
        this.sportradarScore7 = sportradarScore7;
    }

    public String getOptaRedisKey() {
        return optaRedisKey;
    }

    public void setOptaRedisKey(String optaRedisKey) {
        this.optaRedisKey = optaRedisKey;
    }

    public String getOpta1() {
        return opta1;
    }

    public void setOpta1(String opta1) {
        this.opta1 = opta1;
    }

    public String getOpta2() {
        return opta2;
    }

    public void setOpta2(String opta2) {
        this.opta2 = opta2;
    }

    public String getOpta3() {
        return opta3;
    }

    public void setOpta3(String opta3) {
        this.opta3 = opta3;
    }

    public String getOpta4() {
        return opta4;
    }

    public void setOpta4(String opta4) {
        this.opta4 = opta4;
    }

    public String getOpta5() {
        return opta5;
    }

    public void setOpta5(String opta5) {
        this.opta5 = opta5;
    }

    public String getOpta6() {
        return opta6;
    }

    public void setOpta6(String opta6) {
        this.opta6 = opta6;
    }

    public String getOpta7() {
        return opta7;
    }

    public void setOpta7(String opta7) {
        this.opta7 = opta7;
    }

    public String getOptaScoreRedisKey() {
        return optaScoreRedisKey;
    }

    public void setOptaScoreRedisKey(String optaScoreRedisKey) {
        this.optaScoreRedisKey = optaScoreRedisKey;
    }

    public String getOptaScore1() {
        return optaScore1;
    }

    public void setOptaScore1(String optaScore1) {
        this.optaScore1 = optaScore1;
    }

    public String getOptaScore2() {
        return optaScore2;
    }

    public void setOptaScore2(String optaScore2) {
        this.optaScore2 = optaScore2;
    }

    public String getOptaScore3() {
        return optaScore3;
    }

    public void setOptaScore3(String optaScore3) {
        this.optaScore3 = optaScore3;
    }

    public String getOptaScore4() {
        return optaScore4;
    }

    public void setOptaScore4(String optaScore4) {
        this.optaScore4 = optaScore4;
    }

    public String getOptaScore5() {
        return optaScore5;
    }

    public void setOptaScore5(String optaScore5) {
        this.optaScore5 = optaScore5;
    }

    public String getOptaScore6() {
        return optaScore6;
    }

    public void setOptaScore6(String optaScore6) {
        this.optaScore6 = optaScore6;
    }

    public String getOptaScore7() {
        return optaScore7;
    }

    public void setOptaScore7(String optaScore7) {
        this.optaScore7 = optaScore7;
    }

    public String getFive1() {
        return five1;
    }

    public void setFive1(String five1) {
        this.five1 = five1;
    }

    public String getFive2() {
        return five2;
    }

    public void setFive2(String five2) {
        this.five2 = five2;
    }

    public String getFive3() {
        return five3;
    }

    public void setFive3(String five3) {
        this.five3 = five3;
    }

    public String getFive4() {
        return five4;
    }

    public void setFive4(String five4) {
        this.five4 = five4;
    }

    public String getFive5() {
        return five5;
    }

    public void setFive5(String five5) {
        this.five5 = five5;
    }
}
