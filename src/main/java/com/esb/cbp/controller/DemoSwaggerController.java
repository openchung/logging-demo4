 /**
 * 程式資訊摘要：
 * 類別名稱：ExampleController.java
 * 程式內容說明：
 * 版本資訊：
 * 程式設計人員姓名：Sam,Chen
 * 程式修改記錄：2018年4月23日
 * 版權宣告：
 */
package com.esb.cbp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author sam.xps
 *
 */
@Api(value = "ExampleController", description = "CBP Controller 範例")
@RestController
@RequestMapping("/swagger")
public class DemoSwaggerController {
	
	@ApiOperation(value="示範用Swagger get", notes="備註資訊說明")
    @RequestMapping(value={"/get"}, method=RequestMethod.GET)
    public String get() {
		return "Swagger get demo!";
    }
}
