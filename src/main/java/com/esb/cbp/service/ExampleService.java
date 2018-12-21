 /**
 * 程式資訊摘要：
 * 類別名稱：ExampleService.java
 * 程式內容說明：
 * 版本資訊：
 * 程式設計人員姓名：Sam,Chen
 * 程式修改記錄：2018年4月23日
 * 版權宣告：
 */
package com.esb.cbp.service;

import org.apache.logging.log4j.Logger;

import com.esb.common.logger.EsunLogger;

/**
 * @author sam.xps
 *
 */
public class ExampleService {
	
	private Logger log = EsunLogger.create(ExampleService.class);

	public void process() {
		log.info("ExampleService.process()");
	}
}
