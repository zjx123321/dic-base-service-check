package com.jdddata.dic.base.service.check.muti.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Security;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@SpringBootApplication
public class MutiSourceApplication {

	public static void main(String[] args) {
		Security.setProperty("crypto.policy", "unlimited");
		SpringApplication.run(MutiSourceApplication.class, args);
	}

}